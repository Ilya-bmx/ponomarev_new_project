package com.company.service;

import com.company.model.ApplicationDraft;
import com.company.model.DraftEntity;
import com.company.repos.DraftRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
@Data
@Service
public class DraftService {
    @Inject
    DraftRepo draftRepo;

    public DraftService(){}

    public void save(ApplicationDraft draft){
        DraftEntity draftEntity = new DraftEntity(draft.getLetter() , draft.getNumber() , draft.isFlag());
        System.out.println("trying_to_save");
        draftRepo.save(draftEntity);
        System.out.println("saved_!");
    }
}
