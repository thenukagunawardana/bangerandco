package com.eirlss.bangerandco.Service;

import com.eirlss.bangerandco.DTO.UserRegistrationDTO;
import com.eirlss.bangerandco.Model.User;

public interface UserService
{
    User save (UserRegistrationDTO registrationDTO);
}
