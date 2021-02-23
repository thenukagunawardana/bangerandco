package com.eirlss.bangerandco.Service;

import com.eirlss.bangerandco.DTO.AdminDTO;
import com.eirlss.bangerandco.DTO.ClientDTO;
import com.eirlss.bangerandco.Model.Admin;
import com.eirlss.bangerandco.Model.Client;
import com.eirlss.bangerandco.Model.Role;
import com.eirlss.bangerandco.Model.User;
import com.eirlss.bangerandco.Repository.AdminRepository;
import com.eirlss.bangerandco.Repository.ClientRepository;
import com.eirlss.bangerandco.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClientRepository clientRepository;

    public UserServiceImpl(UserRepository userRepository)
    {
        super();
        this.userRepository = userRepository;
    }

//    @Override
//    public User save(UserRegistrationDTO registrationDTO)
//    {
//        User user= new User(registrationDTO.getFirstName(),
//                registrationDTO.getLastName()
//                ,registrationDTO.getEmail(),
//                passwordEncoder.encode(registrationDTO.getPassword()),
//                Arrays.asList(new Role(registrationDTO.getRole())));
//
//        return userRepository.save(user);
//    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Admin registerAdmin(AdminDTO adminDTO) //new
    {
        Admin admin=new Admin();
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setEmail(adminDTO.getEmail());

        return adminRepository.save(admin);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = userRepository.searchUsername(username);
//
//
//        if (user == null) {
//            throw new UsernameNotFoundException("Invalid Credentials");
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
//    }
//
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
//    }

    @Override
    public void saveAdmin(Admin admin) //new
    {
        adminRepository.save(admin);
    }

    @Override
    public List<User> getAllUsers(String keyword)
    {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(int id)
    {
        this.userRepository.deleteById(id);
    }

    @Override
    public User searchByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(username);
        if(user == null)
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
    {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public Client registerClient(ClientDTO clientDTO) //new
    {
        Client client=new Client();
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setEmail(clientDTO.getEmail());

        return clientRepository.save(client);
    }

    @Override
    public void saveClient(Client client) //new
    {
        clientRepository.save(client);
    }




}
