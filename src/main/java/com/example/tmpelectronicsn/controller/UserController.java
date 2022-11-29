package com.example.tmpelectronicsn.controller;


import com.example.tmpelectronicsn.dto.UserCredRequestDto;
import com.example.tmpelectronicsn.dto.UserRequestDto;
import com.example.tmpelectronicsn.entity.User;
import com.example.tmpelectronicsn.security.CurrentUser;
import com.example.tmpelectronicsn.service.MailService;
import com.example.tmpelectronicsn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MailService mailService;
    private final ModelMapper mapper;

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute @Valid UserCredRequestDto userRequestDto, BindingResult bindingResult, @RequestParam("confirmPassword") String confirmPassword, ModelMap map, Locale locale) throws MessagingException {
        Optional<User> byEmail = userService.findByEmail(userRequestDto.getEmail());
        List<String> errors = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            for (ObjectError allError : bindingResult.getAllErrors()) {
                errors.add(allError.getDefaultMessage());
            }
            map.addAttribute("errors", errors);
            return "account";
        } else {
            User user = mapper.map(userRequestDto, User.class);

            if (!user.getPassword().equals(confirmPassword)) {
                errors.add("Passwords doesnt match");
                map.addAttribute("errors", errors);
                return "account";
            }

            if(byEmail.isPresent()) {
                errors.add("User by this email already exists");
                map.addAttribute("errors", errors);
                return "account";
            }

            user.setActive(false);
            user.setToken(UUID.randomUUID().toString());
            user.setTokenCreatedDate(LocalDateTime.now());
            userService.create(user);
            mailService.sendHtmlEmail(user.getEmail(),
                    "Welcome " + user.getName() + " " + user.getSurname(),
                    user,
                    "http://localhost:8080/user/activate?token=" + user.getToken(),
                    "verify", locale);
        }
        return "redirect:/";
    }

    @GetMapping("/user/activate")
    public String activateUser(ModelMap map, @RequestParam String token) {
        Optional<User> user = userService.findByToken(token);
        if (!user.isPresent()) {
            map.addAttribute("message", "User Does not exists");
            return "checkout";
        }
        User userFromDb = user.get();
        if (userFromDb.isActive()) {
            map.addAttribute("message", "User already exists");
            return "checkout";
        }

        userFromDb.setActive(true);
        userFromDb.setToken(null);
        userFromDb.setTokenCreatedDate(null);
        userService.save(userFromDb);
        map.addAttribute("message", "User activated, please login!");
        return "checkout";
    }


    @PostMapping("/editUser")
    public String editUser(@ModelAttribute UserRequestDto userRequestDto, @AuthenticationPrincipal CurrentUser currentUser) {
        if (!userRequestDto.getName().equals("")) {
            currentUser.getUser().setName(userRequestDto.getName());
        }
        if (!userRequestDto.getSurname().equals("")) {
            currentUser.getUser().setSurname(userRequestDto.getSurname());
        }
        if (!userRequestDto.getPhone().equals("")) {
            currentUser.getUser().setPhone(userRequestDto.getPhone());
        }
        if (!userRequestDto.getAddress().equals("")) {
            currentUser.getUser().setAddress(userRequestDto.getAddress());
        }
        userService.save(currentUser.getUser());
        return "redirect:/myAccount";
    }


}
