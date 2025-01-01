package com.nadun.ims.controller;

import com.nadun.ims.dto.UserDTO;
import com.nadun.ims.model.User;
import com.nadun.ims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<UserDTO> allUsers(){
        return userService.getAllUsers();
    }

}
