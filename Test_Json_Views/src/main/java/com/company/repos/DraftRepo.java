package com.company.repos;

import com.company.model.DraftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
@Repository
public interface DraftRepo extends JpaRepository<DraftEntity, Integer> {
}
