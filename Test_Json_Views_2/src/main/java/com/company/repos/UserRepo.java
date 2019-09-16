package com.company.repos;

import com.company.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Override
    User getOne(Long aLong);
    List<User> findAllByLogin(String login);
}
