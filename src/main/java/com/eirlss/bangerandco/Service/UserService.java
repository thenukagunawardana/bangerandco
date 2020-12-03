package com.eirlss.bangerandco.Service;

import com.eirlss.bangerandco.DTO.UserRegistrationDTO;
import com.eirlss.bangerandco.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface UserService extends UserDetailsService
{
    User save (UserRegistrationDTO registrationDTO);

    List<User>getAllUsers(String keyword);

    void deleteUserById(int id);
}
