package com.company.repos;

import com.company.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Override
    User getOne(Long aLong);

    List<User> findAllByUsername(String username);

    List<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "update USERS.USER_TABLE set role=:role where username=:username", nativeQuery = true)
    void updateRoleByUsername(@Param("role") String role, @Param("username") String username);
}
