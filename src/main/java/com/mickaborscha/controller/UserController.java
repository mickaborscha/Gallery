package com.mickaborscha.controller;

import com.mickaborscha.bindingModel.PaymentBindingModel;
import com.mickaborscha.bindingModel.UserBindingModel;
import com.mickaborscha.entity.Payment;
import com.mickaborscha.entity.Role;
import com.mickaborscha.entity.User;
import com.mickaborscha.repository.PaymentRepository;
import com.mickaborscha.repository.RoleRepository;
import com.mickaborscha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PaymentRepository paymentRepository;



    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("view", "user/register");

        return "base-layout";
    }

    @PostMapping("/register")
    public String registerProcess(UserBindingModel userBindingModel) {
        if(!userBindingModel.getPassword().equals(userBindingModel.getConfirmPassword())) {
            return "redirect:/register";
        }



        User user = new User(
                userBindingModel.getEmail(),
                userBindingModel.getFullName(),
               userBindingModel.getPassword()
        );

        Role userRole = this.roleRepository.findByName("ROLE_USER");

        user.addRole(userRole);
        this.userRepository.saveAndFlush(user);

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("view", "user/login");

        return "base-layout";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (org.apache.catalina.servlet4preview.http.HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model) {

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = this.userRepository.findByEmail(principal.getUsername());

       List<Payment> payments = this.paymentRepository.findAll();



        model.addAttribute("user", user);
        model.addAttribute("payments",payments);

        model.addAttribute("view", "user/profile");

        return "base-layout";
    }
}
