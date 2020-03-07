package com.api.benneighbour.workoutManager.email.token;

import com.api.benneighbour.workoutManager.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
public class ChangePasswordToken implements Serializable  {

    private static final long serialVersionUID = 8861214468076063567L;

    private static final int expirationTimeMins = 90;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tid;

    @Column(unique = true)
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_uid", unique = true)
    private User user;

    private Date expiryDate = calculateExpirationDate(expirationTimeMins);

    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Timestamp((calendar.getTime().getTime())));
        calendar.add(Calendar.MINUTE, expirationTime);

        return new Date(calendar.getTime().getTime());
    }

    public ChangePasswordToken() {}

    public ChangePasswordToken(User user, String token) {
        this.user = user;
        this.token = token;
    }



    public Long getTid() {
        return tid;
    }
    public void setTid(Long tid) {
        this.tid = tid;
    }


    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }


    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
