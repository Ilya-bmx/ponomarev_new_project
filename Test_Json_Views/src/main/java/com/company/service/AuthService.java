package com.company.service;

import com.company.components.Consts;
import com.company.model.AuthModel;
import com.company.model.ProjectData;
import com.company.model.User;
import com.company.repos.UserRepo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
@Service
public class AuthService {
    @Inject
    UserRepo userRepo;
    @Inject
    Consts consts;

    public int getRegistrated(AuthModel authModel){
        User user = new User(authModel.getLogin() , authModel.getPassword());
        if ( userRepo.findAllByLogin(authModel.getLogin()).size() == 0 ) {
            userRepo.save(user);
        } else {
            return 1;
        }
        return 0;
    }

    public int checkAccess(String worker_access){
        System.out.println("-----------------\n \n \n Пытаюсь найти доступ в списке доступов --------" + worker_access + "\n\n \n -------------");
        return consts.access_level.indexOf(worker_access);
    }

    public ProjectData getAccessData(){
        return new ProjectData();
    }

}
