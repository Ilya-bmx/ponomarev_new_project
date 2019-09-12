package com.company.service;

import com.company.model.ApplicationDraft;
import com.company.model.DraftEntity;
import com.company.model.PojoUser;
import com.company.repos.DraftRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
@Data
@Service
public class DraftService {
    @Inject
    DraftRepo draftRepo;

    public DraftService(){}

    public void saveConvertPojoUserDraft(PojoUser user){
        ApplicationDraft draft = new ApplicationDraft(user.getId(),user.getStatus(),true);
        saveDraft(draft);
    }

    public void saveDraft(ApplicationDraft draft){
        DraftEntity draftEntity = new DraftEntity(draft.getLetter() , draft.getNumber() , draft.isFlag());
        System.out.println("trying_to_save");
        draftRepo.save(draftEntity);
        System.out.println("saved_!" + draftEntity);
    }

    public String deleteDraftByNumber(int number){
        try {
            draftRepo.deleteById(number);
        } catch (Exception e){
            return "Draft with Id " + number + "is not exist";
        }
        return "deleted";
    }
}
