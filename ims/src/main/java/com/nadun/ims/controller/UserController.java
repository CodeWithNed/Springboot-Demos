package com.nadun.ims.controller;

import com.nadun.ims.dto.UserDTO;
import com.nadun.ims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/user")
    public String saveUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @PutMapping("/user")
    public String updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

}
