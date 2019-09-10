package com.company.service;

import com.company.model.DraftEntity;
import com.company.model.ProjectData;
import com.company.repos.DraftRepo;
import com.company.repos.ProjectDataRepo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
@Service
public class WorkerService extends BuyerService {
    @Inject
    DraftRepo draftRepo;
    @Inject
    ProjectDataRepo projectDataRepo;
    @Inject
    AuthService authService;

    public WorkerService(){}

    public List<DraftEntity> getDraftsByWorker(){
        System.out.println("Im in _getDraftsByWorker ");
        return draftRepo.findAll();
    }
    public int addProjectData(String worker_access , String login , Integer vendor_code , String access){
        if ( authService.checkAccess(worker_access) > 0 && authService.checkAccess(access) <= 1 ) {
            ProjectData projectData = new ProjectData(login, vendor_code, access);
            projectDataRepo.save(projectData);
            System.out.println("\n\n\n\n saved \n\n\n\n");
        }

        return 0;
    }
}
