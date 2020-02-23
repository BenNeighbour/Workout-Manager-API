package com.api.benneighbour.workoutManager;

import com.api.benneighbour.workoutManager.user.CustomUserDetails;
import com.api.benneighbour.workoutManager.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableSpringConfigured
@EnableAuthorizationServer
public class WorkoutManagerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WorkoutManagerApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserDao dao) throws Exception {
		builder.userDetailsService(s -> new CustomUserDetails(dao.findByUsername(s)));
	}

}
