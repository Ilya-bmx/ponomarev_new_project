package com.company.repos;

import com.company.model.ProjectData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ponomarev_ia on 03.09.2019.
 * В бд надо к определённому логину привязать list of vendore_codes
 */
@Repository
public interface ProjectDataRepo extends JpaRepository<ProjectData, String> {
    List<ProjectData> findByLogin(String login);
}
