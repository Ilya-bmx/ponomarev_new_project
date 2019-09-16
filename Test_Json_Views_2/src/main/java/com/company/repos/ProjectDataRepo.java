package com.company.repos;

import com.company.model.ProjectData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ponomarev_ia on 03.09.2019.
 */
@Repository
public interface ProjectDataRepo extends JpaRepository<ProjectData, String> {
}
