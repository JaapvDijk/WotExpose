package com.learningjava.wotapi.api.mapper;

import com.learningjava.wotapi.api.model.dto.UserResponse;
import com.learningjava.wotapi.api.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface UserResponseMapper {
    UserResponseMapper INSTANCE = Mappers.getMapper(UserResponseMapper.class);

    UserResponse toDto(User entity);

    Page<UserResponse> toDtoList(Page<User> entities);
}