package com.company.service;

import com.company.components.Consts;
import com.company.model.AuthModel;
import com.company.model.ProjectData;
import com.company.model.Role;
import com.company.model.User;
import com.company.repos.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
@Service
public class AuthService {
    @Inject
    UserRepo userRepo;
    @Inject
    Consts consts;

    public int checkAccess(String worker_access){
        System.out.println("-----------------\n \n \n Пытаюсь найти доступ в списке доступов --------" + worker_access + "\n\n \n -------------");
        return consts.access_level.indexOf(worker_access);
    }

    public ProjectData getAccessData(){
        return new ProjectData();
    }

    public String getPrincipalsRole(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            System.out.println(username);
        } else {
            username = principal.toString();
            System.out.println(username);
        }
        return userRepo.findByUsername(username).get(0).getRole();
    }
}
