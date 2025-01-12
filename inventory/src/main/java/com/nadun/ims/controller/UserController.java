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

    // ==========================
    // CRUD Operations
    // ==========================

    /**
     * Retrieve all users.
     *
     * @return a list of all users.
     */
    @GetMapping("/users")
    public List<UserDTO> allUsers() {
        return userService.getAllUsers();
    }

    /**
     * Save a new user.
     *
     * @param userDTO the user details to be saved.
     * @return a success message.
     */
    @PostMapping("/user")
    public String saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    /**
     * Update an existing user.
     *
     * @param userDTO the updated user details.
     * @return a success message.
     */
    @PutMapping("/user")
    public String updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    /**
     * Retrieve a user by their ID.
     *
     * @param id the user ID.
     * @return the user details.
     */
    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable Long id) throws Exception {
        return userService.getUserById(Math.toIntExact(id));
    }

    /**
     * Delete a user by their ID.
     *
     * @param id the user ID.
     * @return a success message.
     */
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(Long.valueOf(id));
    }

    // ==========================
    // Partial Updates
    // ==========================

    /**
     * Update the name of a user.
     *
     * @param id   the user ID.
     * @param name the new name for the user.
     * @return a success message.
     */
    @PatchMapping("/user/{id}/name")
    public String updateUserName(@PathVariable Long id, @RequestParam String name) {
        return userService.updateUserName(id, name);
    }

    // ==========================
    // Additional Functionality
    // ==========================

    /**
     * Search for users by name.
     *
     * @param name the name or partial name to search for.
     * @return a list of users matching the search criteria.
     */
    @GetMapping("/users/search")
    public List<UserDTO> searchUsersByName(@RequestParam String name) {
        return userService.searchUsersByName(name);
    }

    /**
     * Save multiple users in bulk.
     *
     * @param userDTOList the list of users to be saved.
     * @return a success message.
     */
    @PostMapping("/users")
    public String saveUsers(@RequestBody List<UserDTO> userDTOList) {
        return userService.saveUsers(userDTOList);
    }

    /**
     * Check if a user exists by their ID.
     *
     * @param id the user ID.
     * @return true if the user exists, false otherwise.
     */
    @GetMapping("/user/{id}/exists")
    public String doesUserExist(@PathVariable Long id) {
        return userService.doesUserExist(id);
    }
}
