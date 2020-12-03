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
        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("User")||r.getAuthority().equals("user")))  //Change to employee
        {
            return "redirect:/userHomePage";
        }
        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Lecturer")||r.getAuthority().equals("lecturer"))) //change to user
        {
            return "redirect:/lecturerHomePage";
        }
        return "index";
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

//    @GetMapping("/lecturerHomePage")
//    private String lecturerHomePage()
//    {
//        return "lecturerHomePage";
//    }

}
