package com.kikplan.backend.services.impl;

import com.kikplan.backend.dto.NotificationDto;
import com.kikplan.backend.dto.UserDto;
import com.kikplan.backend.entities.Notification;
import com.kikplan.backend.entities.Status;
import com.kikplan.backend.entities.Team;
import com.kikplan.backend.entities.User;
import com.kikplan.backend.exceptions.OperationNotPermittedException;
import com.kikplan.backend.repository.NotificationRepository;
import com.kikplan.backend.repository.UserRepository;
import com.kikplan.backend.services.UserService;
import com.kikplan.backend.validator.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ObjectValidator<UserDto> validate;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;


    @Override
    public UserDto save(UserDto dto) {
        dto.setColorProfile(UserDto.generateColor());
        validate.validate(dto);
        NotificationDto notificationDto=NotificationDto.builder()
                .name("Welcom to KikPlan !!")
                .sendTo(dto.getEmail())
                .isView(false)
                .build();
        dto.setNotificationDtos(List.of(notificationDto));
        User user=UserDto.toEntity(dto);
        boolean emailExist=repository.findByEmail(user.getEmail()).isPresent();
        if(emailExist){
            throw new OperationNotPermittedException("Email exist",UserServiceImpl.class.getName());
        }
        return UserDto.fromEntityWithoutTeamId(repository.save(user));
    }

    @Override
    public UserDto findById(Long id) {
        return  repository.findById(id).map(UserDto::fromEntity).orElseThrow(()->new EntityNotFoundException("User was not found with id : "+id));
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream().map(UserDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        /*todo search user by id */

        boolean UserNotExist=repository.findById(id).isEmpty();
        if(UserNotExist){
            throw new EntityNotFoundException("User was not found with id :"+id);
        }
       repository.deleteById(id);
    }

    @Override
    public UserDto login(String email,String password) {
        User search=repository.findByEmail(email).orElseThrow(()->new OperationNotPermittedException("Email not Found",UserServiceImpl.class.getName()));
        if(!email.equals(search.getEmail()) || !password.equals(search.getPassword())){
            throw new OperationNotPermittedException("Wrong Password or Email",UserServiceImpl.class.getName());
        }
        search.setStatus(Status.ONLINE);
        return UserDto.fromEntity(search);
    }

    @Override
    public List<UserDto> filterByEmail(String email) {
        return repository.findAll().stream().filter(c ->c.getEmail().contains(email)).map(c->{
            return UserDto.fromEntityWithoutTeamId(c);

        }).collect(Collectors.toList());
    }

    @Override
    public UserDto changePassword(String email, String password, String repassword) {
        User userSearch=userRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("user not found with this email "+email));
        if(!password.equals(repassword)) {
           throw new OperationNotPermittedException("two password not match",UserServiceImpl.class.getName());
        }
        userSearch.setPassword(password);

        return UserDto.fromEntityWithoutTeamId(userRepository.save(userSearch));
    }

    @Override
    public UserDto findByEmail(String email) {
        return UserDto.fromEntityWithoutTeamId( userRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("user not found ")));
    }
}
