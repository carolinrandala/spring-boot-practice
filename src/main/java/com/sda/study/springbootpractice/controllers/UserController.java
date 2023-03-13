package com.sda.study.springbootpractice.controllers;

import com.sda.study.springbootpractice.exceptions.UserNotFoundException;
import com.sda.study.springbootpractice.models.User;
import com.sda.study.springbootpractice.services.AuthorityService;
import com.sda.study.springbootpractice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller to handle all user related requests
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;

    @GetMapping
    public String showAllUserPage(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "user/list-user";
    }

    @GetMapping("/signup")
    public String showSignUpPage(Model model, @ModelAttribute("user") User user, @ModelAttribute("message") String message,
                                 @ModelAttribute("messageType") String messageType) {
        model.addAttribute("authorities", authorityService.findAllAuthorities());
        return "user/create-user";
    }

    @PostMapping("/signup")
    public String createUser(User user, RedirectAttributes redirectAttributes) {
        try {
            userService.findUserByUsername(user.getUserName());
            redirectAttributes.addFlashAttribute("message", String.format("User (%s) already exists!", user.getUserName()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/school/signup";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "Signup successful!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/";
        }
    }
}
