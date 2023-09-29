package ru.Bogachev.MyEmailSender.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Bogachev.MyEmailSender.data.entity.MessageEntity;
import ru.Bogachev.MyEmailSender.data.entity.UserEntity;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findAllByUser(UserEntity user);
}
