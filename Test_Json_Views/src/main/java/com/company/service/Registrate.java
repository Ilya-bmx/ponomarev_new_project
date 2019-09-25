package com.company.service;

import com.company.model.User;
import com.company.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ponomarev_ia on 17.09.2019.
 */
@Service
public class Registrate {

    @Autowired
    UserRepo userRepo;
    //delete
    public User user;

    public Registrate() {
        user = new User("u", "1", true, "USER");
    }

    public void saveFunc() {
        userRepo.save(user);
        user.setRole("ADMIN");
        user.setUsername("a");
        userRepo.save(user);
    }

    public void saveUserFromRequest(User user) {
        userRepo.save(user);
    }
}
