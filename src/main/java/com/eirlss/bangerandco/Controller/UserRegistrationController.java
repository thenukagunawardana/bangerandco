package com.eirlss.bangerandco.Controller;

import com.eirlss.bangerandco.DTO.UserRegistrationDTO;
import com.eirlss.bangerandco.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private final UserService userService;


    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }


    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDto() {
        return new UserRegistrationDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDto) {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
