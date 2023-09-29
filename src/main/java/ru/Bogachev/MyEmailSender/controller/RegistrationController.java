package ru.Bogachev.MyEmailSender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.Bogachev.MyEmailSender.data.entity.UserEntity;
import ru.Bogachev.MyEmailSender.service.UserService;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser (UserEntity userEntity, Model model) {
        if(!userService.addUser(userEntity)) {
            model.addAttribute("message", "User exist!");
            return "login";
        }
        return "redirect:/login";
    }
}
