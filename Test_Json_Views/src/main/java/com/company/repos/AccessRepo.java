package com.company.repos;

import com.company.model.AccessModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
public interface AccessRepo extends JpaRepository<AccessModel, String> {
    List<AccessModel> findAllByLogin(String login);
}
