package com.eirlss.bangerandco.Service;

import com.eirlss.bangerandco.DTO.AdminDTO;
import com.eirlss.bangerandco.DTO.ClientDTO;
import com.eirlss.bangerandco.Model.Admin;
import com.eirlss.bangerandco.Model.Client;
import com.eirlss.bangerandco.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService
{
//    User save (UserRegistrationDTO registrationDTO);

    User save(User user);

    Admin registerAdmin(AdminDTO adminDTO);

    void saveAdmin(Admin admin);

    User searchByEmail(String email);

    Client registerClient(ClientDTO clientDTO);

    void saveClient(Client client);

    List<User>getAllUsers(String keyword);

    void deleteUserById(int id);


}
