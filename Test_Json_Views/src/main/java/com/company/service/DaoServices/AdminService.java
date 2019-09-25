package com.company.service.DaoServices;

import com.company.model.User;
import com.company.repos.UserRepo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
@Service
public class AdminService extends WorkerService {
    @Inject
    UserRepo userRepo;

    public String giveSomeRules(String userlogin, String role) {
        if (userRepo.findAllByUsername(userlogin).size() > 0) {
            userRepo.updateRoleByUsername(role, userlogin);
            System.out.println("giveSomeRules_done!");
            return "";
        } else {
            return "not exist user with this login";
        }
    }

    public String deleteUser(String userlogin) {
        if (userRepo.findAllByUsername(userlogin).size() > 0) {
            User user = (User) userRepo.findByUsername(userlogin);
            userRepo.deleteById(user.getId());
            System.out.println("deleted user : " + userlogin + " !");
            return "";
        } else {
            return "not exist user with this login";
        }
    }

}
