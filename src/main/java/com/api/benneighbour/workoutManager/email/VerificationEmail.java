package com.api.benneighbour.workoutManager.email;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class VerificationEmail implements Serializable {

    private static final long serialVersionUID = 5817308186721121155L;


    private String host;

    private int port;

    private String username;

    private String password;



    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }


    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
