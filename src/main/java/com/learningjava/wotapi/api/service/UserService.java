package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.mapper.UserResponseMapper;
import com.learningjava.wotapi.api.model.dto.UserResponse;
import com.learningjava.wotapi.api.repo.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponse> getUsers(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var usersPage = userRepository.findAll(pageable);

        return UserResponseMapper.INSTANCE.toDtoList(usersPage);
    }
}
