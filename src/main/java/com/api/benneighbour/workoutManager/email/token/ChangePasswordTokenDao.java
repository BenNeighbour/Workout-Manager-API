package com.api.benneighbour.workoutManager.email.token;

import com.api.benneighbour.workoutManager.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangePasswordTokenDao extends JpaRepository<ChangePasswordToken, Long> {

    ChangePasswordToken findByToken(String token);

    ChangePasswordToken findTokenByUser(User user);

}
