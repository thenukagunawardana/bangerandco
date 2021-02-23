package com.eirlss.bangerandco.Controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController
{
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/")
    public String home()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Admin")||r.getAuthority().equals("admin")))
        {
            return "redirect:/adminHomePage";
        }
        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Client")||r.getAuthority().equals("client")))
        {
            return "redirect:/userHomePage";
        }
        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("User")||r.getAuthority().equals("user"))) //employee
        {
            return "redirect:/employeeHomePage";
        }
        return "vehicleIndex";
    }

    @GetMapping("/adminHomePage")
    private String adminHomePage()
    {
        return "adminHomePage"; //index changed to adminHomePage
    }

    @GetMapping("/userHomePage")
    private String studentHomePage()
    {
        return "userHomePage";
    }

    @GetMapping("/employeeHomePage")
    private String employeeHomePage()
    {
        return "employeeHomePage";
    }

}
