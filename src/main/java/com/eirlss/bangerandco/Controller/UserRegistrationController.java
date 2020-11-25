package com.eirlss.bangerandco.Controller;

import com.eirlss.bangerandco.DTO.UserRegistrationDTO;
import com.eirlss.bangerandco.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController
{
    private UserService userService;

    public UserRegistrationController(UserService userService)
    {
        super();
        this.userService=userService;
    }

    public String registerUserAccount(@ModelAttribute("user")
                                              UserRegistrationDTO registrationDTO)
    {
        userService.save(registrationDTO);
        return "redirect:/registration?success";
    }

}
