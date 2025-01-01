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

}
