package com.kikplan.backend.services;

import com.kikplan.backend.dto.UserDto;

import java.util.List;

public interface UserService extends AbstractService<UserDto> {

    UserDto login(String email,String password);
    List<UserDto> filterByEmail(String email);
    UserDto changePassword(String email,String password,String repassword);
    UserDto findByEmail(String email);
}
