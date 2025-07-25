package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.mapper.UserMapper;
import com.learningjava.wotapi.api.model.dto.UserRequest;
import com.learningjava.wotapi.api.model.dto.UserResponse;
import com.learningjava.wotapi.api.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final UserMapper mapper;

    public UserService(UserRepository repo, UserMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Page<UserResponse> getUserPage(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var usersPage = repo.findAll(pageable);

        return usersPage.map(mapper::toDto);
    }

    public UserResponse getUserById(Integer id) {
        var user = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found, id " + id));
        return mapper.toDto(user);
    }

    public UserResponse updateUser(Integer id, UserRequest request) {
        var user = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found, id " + id));

        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());

        var result = repo.save(user);
        return mapper.toDto(result);
    }

    public void deleteUser(Integer id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("User not found, id " + id);
        }
        repo.deleteById(id);
    }
}
