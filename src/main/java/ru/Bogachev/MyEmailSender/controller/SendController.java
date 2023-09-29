package ru.Bogachev.MyEmailSender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.Bogachev.MyEmailSender.data.entity.MessageEntity;
import ru.Bogachev.MyEmailSender.data.entity.UserEntity;
import ru.Bogachev.MyEmailSender.service.MessageService;

@Controller
@RequiredArgsConstructor
public class SendController {
    private final MessageService messageService;
    @GetMapping("/")
    public String getAllMessages(@AuthenticationPrincipal UserEntity user, Model model) {
        model.addAttribute("messages", messageService.findAllByUser(user));
        return "allMessages";
    }
    @GetMapping("/message/form")
    public String getFormForSend() {
        return "sendForm";
    }

    @PostMapping("/send")
    public String add(@AuthenticationPrincipal UserEntity user,
                      MessageEntity messageEntity) {
        messageService.save(user, messageEntity);
        try {
            messageService.sendMessage(messageEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
    @GetMapping("/send/{message}")
    public String deleteMessage(@PathVariable MessageEntity message) {
        messageService.deleteMessage(message);
        return "redirect:/";
    }
}
