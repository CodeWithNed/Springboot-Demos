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
import java.util.Optional;

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

    public UserDTO getUserById(Integer id) throws Exception {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return modelMapper.map(optionalUser.get(), UserDTO.class);
        } else {
            throw new Exception("User with ID " + id + " not found.");
        }
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
