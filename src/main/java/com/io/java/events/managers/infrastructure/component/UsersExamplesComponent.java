package com.io.java.events.managers.infrastructure.component;

import com.io.java.events.managers.infrastructure.entity.UsersEntity;
import com.io.java.events.managers.infrastructure.entity.field.Role;
import com.io.java.events.managers.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class UsersExamplesComponent implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
       this.saveUser(user());
       this.saveUser(userAdmin());
       this.saveUser(userRh());
    }

    private void saveUser(UsersEntity usersEntity){
        if (userRepository.findByEmail(usersEntity.getEmail()).isEmpty()){
            userRepository.save(usersEntity);
            log.info("usuário de exemplo criado: {} , acesse a classe: {} para ter acesso as senhas de exemplo.", usersEntity.getEmail(), UsersExamplesComponent.class.getName());
        }
    }

    private UsersEntity user(){
        return UsersEntity.builder()
                .email("user.example@gmail.com")
                .role(Role.USER)
                .password(new BCryptPasswordEncoder().encode("userExample"))
                .name("user example")
                .build();
    }

    private UsersEntity userAdmin(){
        return UsersEntity.builder()
                .email("admin.example@gmail.com")
                .role(Role.ADMIN)
                .password(new BCryptPasswordEncoder().encode("adminExample"))
                .name("admin example")
                .build();
    }

    private UsersEntity userRh(){
        return UsersEntity.builder()
                .email("rh.example@gmail.com")
                .role(Role.RH)
                .password(new BCryptPasswordEncoder().encode("rhExample"))
                .name("rh example")
                .build();
    }
}
