package com.eirlss.bangerandco.Service;

import com.eirlss.bangerandco.DTO.UserRegistrationDTO;
import com.eirlss.bangerandco.Model.Role;
import com.eirlss.bangerandco.Model.User;
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

    public UserServiceImpl(UserRepository userRepository)
    {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDTO registrationDTO)
    {
        User user= new User(registrationDTO.getFirstName(),registrationDTO.getLastName()
                ,registrationDTO.getEmail(),passwordEncoder.encode(registrationDTO.getPassword()),
                Arrays.asList(new Role(registrationDTO.getRole())));

        return userRepository.save(user);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user =userRepository.findByEmail(username);
        if (user==null)
        {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
    {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
