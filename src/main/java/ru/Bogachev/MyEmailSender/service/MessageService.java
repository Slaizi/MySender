package ru.Bogachev.MyEmailSender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Bogachev.MyEmailSender.data.entity.MessageEntity;
import ru.Bogachev.MyEmailSender.data.entity.UserEntity;
import ru.Bogachev.MyEmailSender.data.repository.MessageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MailSender mailSender;
    @Transactional
    public void save(UserEntity user, MessageEntity messageEntity) {
        messageRepository.save(MessageEntity.builder()
                                            .email(messageEntity.getEmail())
                                            .title(messageEntity.getTitle())
                                            .sms(messageEntity.getSms())
                                            .user(user)
                                            .build());
    }
    public List<MessageEntity> findAllByUser(UserEntity user) {
        return messageRepository.findAllByUser(user);
    }

    public void deleteMessage(MessageEntity messageEntity) {
        messageRepository.delete(messageEntity);
    }

    public void sendMessage(MessageEntity messageEntity) {
        mailSender.send(messageEntity.getEmail(), messageEntity.getTitle(), messageEntity.getSms());
    }
}
