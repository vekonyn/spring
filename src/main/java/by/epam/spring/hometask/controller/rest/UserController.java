package by.epam.spring.hometask.controller.rest;

import by.epam.spring.hometask.domain.User;
import by.epam.spring.hometask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public String getAllUsers(@ModelAttribute("model") ModelMap model) {
        Set<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping("/users/{email}")
    public String getUserByEmail(@ModelAttribute("model") ModelMap model, @PathVariable(name = "email") String email) {
        User user = userService.getUserByEmail(email);
        model.addAttribute("users", Collections.singletonList(user));
        return "users";
    }

}
