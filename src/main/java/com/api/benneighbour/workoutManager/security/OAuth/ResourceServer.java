package com.api.benneighbour.workoutManager.security.OAuth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()

            .antMatchers("/api/v1/user/signup/").permitAll()
            .antMatchers("/api/v1/user/all/").hasAuthority("ADMIN")
            .antMatchers("/api/v1/user/username/by/**").authenticated()
            .antMatchers("/api/v1/user/update/").authenticated()
            .antMatchers("/api/v1/user/delete/by/**").hasAuthority("ADMIN")

            .antMatchers("/api/v1/workout/update/").authenticated()
            .antMatchers("/api/v1/workout/save/").authenticated()
            .antMatchers("/api/v1/workout/delete/by/**").authenticated()
            .antMatchers("/api/v1/workout/by/**").authenticated()

            .antMatchers("/api/v1/workout/exercise/by/**").authenticated()
            .antMatchers("/api/v1/workout/exercise/delete/by/**").authenticated()
            .antMatchers("/api/v1/workout/exercise/save/").authenticated()
            .antMatchers("/api/v1/workout/exercise/update/").authenticated()

            .antMatchers("/api/v1/user/todos/**").authenticated()

            .and()

            .httpBasic();
    }

}
