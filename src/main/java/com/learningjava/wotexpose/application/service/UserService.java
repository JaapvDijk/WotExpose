package com.learningjava.wotexpose.application.service;

import com.learningjava.wotexpose.application.mapper.UserMapper;
import com.learningjava.wotexpose.application.dto.UserRequest;
import com.learningjava.wotexpose.application.dto.UserResponse;
import com.learningjava.wotexpose.infrastructure.persistance.repo.UserRepository;
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

        return usersPage.map(mapper::toUserResponse);
    }

    public UserResponse getUserById(Integer id) {
        var user = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found, id " + id));
        return mapper.toUserResponse(user);
    }

    public UserResponse updateUser(Integer id, UserRequest request) {
        var user = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found, id " + id));

        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());

        var result = repo.save(user);
        return mapper.toUserResponse(result);
    }

    public void deleteUser(Integer id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("User not found, id " + id);
        }
        repo.deleteById(id);
    }
}
