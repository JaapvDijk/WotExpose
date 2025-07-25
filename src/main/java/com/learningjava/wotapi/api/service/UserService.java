package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.mapper.UserResponseMapper;
import com.learningjava.wotapi.api.model.dto.UserResponse;
import com.learningjava.wotapi.api.repo.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final UserResponseMapper mapper;

    public UserService(UserRepository repo, UserResponseMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Page<UserResponse> getUsers(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var usersPage = repo.findAll(pageable);

        return mapper.toDtoList(usersPage);
    }
}
