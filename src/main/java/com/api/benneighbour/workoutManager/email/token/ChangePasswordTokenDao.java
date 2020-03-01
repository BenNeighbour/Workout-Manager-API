package com.api.benneighbour.workoutManager.email.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangePasswordTokenDao extends JpaRepository<ChangePasswordToken, Long> {

    ChangePasswordToken findByToken(String token);

}
