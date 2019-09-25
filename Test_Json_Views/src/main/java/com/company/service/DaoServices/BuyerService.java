package com.company.service.DaoServices;

import com.company.model.ProjectData;
import com.company.repos.ProjectDataRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
@Service
public class BuyerService {

    @Inject
    ProjectDataRepo projectDataRepo;

    public void putInfoAboutClient(Integer vendorCode , String login){
        //решить вопрос об адресе,
        projectDataRepo.save(new ProjectData(login , vendorCode , null));
    }

}
