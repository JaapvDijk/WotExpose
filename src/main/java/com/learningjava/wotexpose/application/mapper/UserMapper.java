package com.learningjava.wotexpose.application.mapper;

import com.learningjava.wotexpose.application.dto.UserResponse;
import com.learningjava.wotexpose.infrastructure.persistance.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
}