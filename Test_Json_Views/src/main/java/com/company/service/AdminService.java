package com.company.service;

import com.company.model.AccessModel;
import com.company.repos.AccessRepo;
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
    @Inject
    AccessRepo accessRepo;

    public void giveSomeRules(String userlogin, String access) {
        if (userRepo.findAllByLogin(userlogin).size() > 0)
            accessRepo.save(new AccessModel(userlogin, access));
        System.out.println("giveSomeRules_done!");
    }
}
