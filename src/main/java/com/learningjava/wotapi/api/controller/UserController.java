package com.learningjava.wotapi.api.controller;

import com.learningjava.wotapi.api.model.dto.UserResponse;
import com.learningjava.wotapi.api.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/users")
public class UserController {

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/page")
    public ResponseEntity<Page<UserResponse>>
        getUsers(@RequestParam(defaultValue = "0") int pageNr,
                 @RequestParam(defaultValue = "20") int amount) {
        var result = userService.getUsers(pageNr, amount);

        return ResponseEntity.ok(result);
    }
}
