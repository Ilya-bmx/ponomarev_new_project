package com.company.service;

import com.company.model.User;
import com.company.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by ponomarev_ia on 25.09.2019.
 * Позволяет работать с текущим пользователем авторизованным ( Spring Security )
 * Перед применением заИнжектить
 */
@Component
//@RequiredArgsConstructor
public class AuthHelper {

    private final PasswordEncoder passwordEncoder = null;//убрать комментарии настроить , взял для возможности использовать , этот класс
    private UserRepo userRepo;

    public String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getUsername();
        }
        return null;
    }

    public void setUsername(User user) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            ((User) principal).setUsername(user.getUsername());
        }
    }

    public void checkPassword(String password) {
        User user = userRepo.findByUsername(getUsername()).get(0);
        String currentPassword = user.getPassword();
        if (!passwordEncoder.matches(password, currentPassword)) {
            throw new BadCredentialsException("Bad credentials");
        }
    }
}
