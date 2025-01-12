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
        Optional<User> optionalUser = userRepository.findById(Long.valueOf(id));
        if (optionalUser.isPresent()) {
            return modelMapper.map(optionalUser.get(), UserDTO.class);
        } else {
            throw new Exception("User with ID " + id + " not found.");
        }
    }

    public String deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return "User with ID " + id + " deleted successfully.";
        } else {
            return "User with ID " + id + " not found.";
        }
    }

    public String updateUserName(Long id, String name) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUserName(name);
            userRepository.save(user);
            return "User name updated to " + name + " for ID " + id + ".";
        } else {
            return "User with ID " + id + " not found.";
        }
    }

    public List<UserDTO> searchUsersByName(String name) {
        List<User> users = userRepository.findByUserNameContaining(name);
        return modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
    }

    public String saveUsers(List<UserDTO> userDTOList) {
        List<User> users = modelMapper.map(userDTOList, new TypeToken<List<User>>(){}.getType());
        userRepository.saveAll(users);
        return userDTOList.size() + " users saved successfully.";
    }

    public String doesUserExist(Long id) {
        boolean existance = userRepository.existsById(id);
        if(existance){
            return "User exists.";
        }
        else{
            return "User doesn't exists.";
        }
    }
}
