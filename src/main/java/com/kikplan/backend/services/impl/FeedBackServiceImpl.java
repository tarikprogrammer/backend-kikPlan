package com.kikplan.backend.services.impl;

import com.kikplan.backend.dto.FeedBackDto;
import com.kikplan.backend.entities.FeedBack;
import com.kikplan.backend.entities.User;
import com.kikplan.backend.repository.FeedBackRepository;
import com.kikplan.backend.repository.UserRepository;
import com.kikplan.backend.services.FeedBackService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedBackServiceImpl implements FeedBackService {
    private final FeedBackRepository feedBackRepository;
    private final UserRepository userRepository;

    @Override
    public FeedBackDto save(FeedBackDto feedBackDto) {
      User user=userRepository.findByEmail(feedBackDto.getEmail()).orElseThrow(()->new EntityNotFoundException("User not found "));
      FeedBack  feedBack=FeedBackDto.toEntity(feedBackDto);
      if(user.getFeedBacks()==null){
          List<FeedBackDto>feedBackDtos=new ArrayList<>();
          feedBackDtos.add(feedBackDto);
          user.setFeedBacks(feedBackDtos.stream().map(FeedBackDto::toEntity).collect(Collectors.toList()));
          userRepository.save(user);
      }else{
          List<FeedBackDto>feedBackDtos=user.getFeedBacks().stream().map(FeedBackDto::fromEntity).collect(Collectors.toList());
          feedBackDtos.add(feedBackDto);
          user.setFeedBacks(feedBackDtos.stream().map(FeedBackDto::toEntity).collect(Collectors.toList()));
          userRepository.save(user);
      }

      return feedBackDto;
    }
}
