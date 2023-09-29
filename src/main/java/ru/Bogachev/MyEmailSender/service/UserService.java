package ru.Bogachev.MyEmailSender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Bogachev.MyEmailSender.data.entity.UserEntity;
import ru.Bogachev.MyEmailSender.data.entity.enums.Role;
import ru.Bogachev.MyEmailSender.data.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public void save (UserEntity userEntity) {
        userRepository.save(UserEntity.builder()
                                      .username(userEntity.getUsername())
                                      .password(passwordEncoder.encode(userEntity.getPassword()))
                                      .active(true)
                                      .roles(Collections.singleton(Role.USER))
                                      .build());
    }

    public boolean addUser (UserEntity userEntity) {
        UserEntity user = userRepository.findByUsername(userEntity.getUsername());

        if(user != null) {
            return false;
        }
        save(userEntity);
        return true;
    }
    @Transactional
    public void userEdit (UserEntity userEntity, String username, List<String> roles) {
        userEntity.setUsername(username);

        if(roles == null) {
            userEntity.setRoles(Collections.emptySet());
        } else {
           Set<Role> userRoles = roles.stream()
                                     .map(Role :: valueOf)
                                     .collect(Collectors.toSet());
           userEntity.setRoles(userRoles);
           userRepository.save(userEntity);
        }
    }

    public List<UserEntity> userList () {
        return userRepository.findAll();
    }

    public void deleteUser(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }
}
