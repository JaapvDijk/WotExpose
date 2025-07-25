package com.learningjava.wotapi.api.mapper;

import com.learningjava.wotapi.api.model.dto.UserResponse;
import com.learningjava.wotapi.api.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toDto(User entity);
}