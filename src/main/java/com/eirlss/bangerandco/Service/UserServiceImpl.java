package com.eirlss.bangerandco.Service;

import com.eirlss.bangerandco.DTO.UserRegistrationDTO;
import com.eirlss.bangerandco.Model.Role;
import com.eirlss.bangerandco.Model.User;
import com.eirlss.bangerandco.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository)
    {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDTO registrationDTO)
    {
        User user= new User(registrationDTO.getFirstName(),registrationDTO.getLastName()
                ,registrationDTO.getEmail(),registrationDTO.getPassword(),
                Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(user);
    }
}
