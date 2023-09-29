package ru.Bogachev.MyEmailSender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Bogachev.MyEmailSender.data.entity.UserEntity;
import ru.Bogachev.MyEmailSender.data.entity.enums.Role;
import ru.Bogachev.MyEmailSender.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public String userList(Model model) {
       model.addAttribute("users", userService.userList());
       return "userList";
    }
    @GetMapping("/{user}")
    public String deleteUser(@PathVariable UserEntity user) {
        userService.deleteUser(user);
        return "redirect:/user";
    }
    @GetMapping("/edit/{user}")
    public String userEditForm(@PathVariable UserEntity user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PostMapping()
    public String editUserSave(
            @RequestParam("userId") UserEntity userEntity,
            @RequestParam String username,
            @RequestParam(value = "roles", required = false) List<String> roles) {

        userService.userEdit(userEntity, username, roles);
        return "redirect:/user";
    }
}
