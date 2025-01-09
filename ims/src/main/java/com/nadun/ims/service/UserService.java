package com.nadun.ims.service;

import com.nadun.ims.dto.UserDTO;
import com.nadun.ims.model.User;
import com.nadun.ims.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
    }

    public String saveUser(UserDTO userDTO){
        userRepository.save(modelMapper.map(userDTO , User.class));
        return userDTO.getUserId() + " " + userDTO.getUserName() + " saved successfully.";
    }

    public String updateUser(UserDTO userDTO) {
        userRepository.save(modelMapper.map(userDTO , User.class));
        return userDTO.getUserId() + " " + userDTO.getUserName() + " updated successfully.";

    }


    public UserDTO getUserById(Long id) {
        return null;
    }

    public String deleteUser(Long id) {
        return null;
    }

    public String updateUserName(Long id, String name) {
        return name;
    }

    public List<UserDTO> searchUsersByName(String name) {
        return null;
    }

    public String saveUsers(List<UserDTO> userDTOList) {
        return null;
    }

    public boolean doesUserExist(Long id) {
        return false;
    }
}
