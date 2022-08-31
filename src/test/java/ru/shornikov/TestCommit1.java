package ru.shornikov;


import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.security.SystemAuthenticator;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import ru.shornikov.entity.User;
import ru.shornikov.security.DatabaseUserRepository;

@SpringBootTest
public class TestCommit1 {

    @Autowired
    private SystemAuthenticator systemAuthenticator;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private DatabaseUserRepository userRepository;


    @Test
    void CheckLogin(){
        Authentication user = systemAuthenticator.begin("admin");
    }

    @Test
    void GetSystemUser() {
        //systemAuthenticator.begin("admin");
        systemAuthenticator.runWithSystem(() -> {
            UserDetails user = currentAuthentication.getUser();
            String username = user.getUsername();
        });
    }

    @Test
    void GetSystemUser2() {
        String username = userRepository.getSystemUser().getUsername();
    }

    @Test
    void CheckSystemUser(){
        systemAuthenticator.runWithSystem(() -> {
            UserDetails user = currentAuthentication.getUser();
            String username = user.getUsername();
            User userObject = userRepository.loadUserByUsername(username);
        });
    }

    @Test
    void CheckUser(){
        systemAuthenticator.runWithSystem(() -> {
            String username = "admin";
            User userObject = userRepository.loadUserByUsername(username);
        });
    }

}
