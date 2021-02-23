package com.eirlss.bangerandco.Controller;


import com.eirlss.bangerandco.DTO.AdminDTO;
import com.eirlss.bangerandco.DTO.ClientDTO;
import com.eirlss.bangerandco.Model.Admin;
import com.eirlss.bangerandco.Model.Client;
import com.eirlss.bangerandco.Model.Role;
import com.eirlss.bangerandco.Model.User;
import com.eirlss.bangerandco.Service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController
{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String viewHomePage(Model model, @Param("keyword")String keyword)
    {
        List<User>listUser=userService.getAllUsers(keyword);
        model.addAttribute("listUsers",userService.getAllUsers(keyword));
        model.addAttribute("keyword",keyword);
        return "user";
    }

    @GetMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id")int id)
    {
        this.userService.deleteUserById(id);
        return "redirect:/user";
    }

    @GetMapping("loadAdminRegistration")
    public String loadAdminRegistration(Model model)
    {
        model.addAttribute("admin",new AdminDTO());

        return "registerAdmin";
    }

    @PostMapping("/saveAdmin")
    public String saveAdmin(@ModelAttribute("admin")AdminDTO adminDTO)
    {
        Admin savedAdmin=userService.registerAdmin(adminDTO);
        User user=new User();
        user.setTableID(savedAdmin.getId());
        user.setFirstName(savedAdmin.getFirstName());
        user.setLastName(savedAdmin.getLastName());
        user.setEmail(savedAdmin.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(adminDTO.getPassword()));
        user.setRoles(Arrays.asList(new Role("Admin")));
        User savedUser=userService.save(user);

        savedAdmin.setUserID(savedUser.getId());
        userService.saveAdmin(savedAdmin);

        return "redirect:/registerAdmin?success";
    }

    @GetMapping("loadClientRegistration")
    public String loadClientRegistration(Model model)
    {
        model.addAttribute("client",new ClientDTO());

        return "registerClient";
    }

    @PostMapping("/saveClient")
    public String saveClient(@ModelAttribute("client") ClientDTO clientDTO)
    {
        Client savedClient=userService.registerClient(clientDTO);
        User user=new User();
        user.setTableID(savedClient.getId());
        user.setFirstName(savedClient.getFirstName());
        user.setLastName(savedClient.getLastName());
        user.setEmail(savedClient.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(clientDTO.getPassword()));
        savedClient.setAge(clientDTO.getAge());
        savedClient.setDriversLicense(clientDTO.getDriversLicense());
        savedClient.setStatus("Active");
        user.setRoles(Arrays.asList(new Role("Client")));
        User savedUser=userService.save(user);

        savedClient.setUserID(savedUser.getId());
        userService.saveClient(savedClient);

        return "redirect:/registerClient?success";
    }
}
