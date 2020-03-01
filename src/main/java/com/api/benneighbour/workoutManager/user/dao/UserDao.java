package com.api.benneighbour.workoutManager.user.dao;

import com.api.benneighbour.workoutManager.email.token.ChangePasswordToken;
import com.api.benneighbour.workoutManager.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findUserByEmail(String email);

    User findUserByUid(Long uid);

}
