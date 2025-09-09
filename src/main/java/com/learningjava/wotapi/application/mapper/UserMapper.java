package com.learningjava.wotapi.application.mapper;

import com.learningjava.wotapi.application.dto.UserResponse;
import com.learningjava.wotapi.infrastructure.persistance.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
}