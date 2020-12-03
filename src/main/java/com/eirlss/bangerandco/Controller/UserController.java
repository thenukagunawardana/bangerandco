package com.eirlss.bangerandco.Controller;


import com.eirlss.bangerandco.Model.User;
import com.eirlss.bangerandco.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController
{
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
}
