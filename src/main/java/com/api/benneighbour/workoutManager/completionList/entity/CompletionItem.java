package com.api.benneighbour.workoutManager.completionList.entity;

import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.workout.entity.Workout;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "completionItem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CompletionItem implements Serializable {

    private static final long serialVersionUID = -7027490953102366241L;

    @Id
    @Column(name = "iid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long iid;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Workout.class, fetch = FetchType.LAZY)
    @JoinColumn(name ="workout_wid", referencedColumnName = "wid")
    private Workout workout;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = User.class, optional = false)
    @JoinColumn(name ="user_uid", referencedColumnName = "uid")
    private User user;

    @Column(name = "day", nullable = false)
    @JsonIgnore
    private String completionDay;

    @Column(name = "isCompleted")
    private Boolean isCompleted = false;


    public CompletionItem() {
    }

    public CompletionItem(Long iid, String description, Workout workout, User user, Boolean isCompleted) {
        this.iid = iid;
        this.description = description;
        this.workout = workout;
        this.user = user;
        this.isCompleted = isCompleted;
    }


    public Long getIid() {
        return iid;
    }
    public void setIid(Long iid) {
        this.iid = iid;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public Workout getWorkout() {
        return workout;
    }
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }


    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


    public Boolean getCompleted() {
        return isCompleted;
    }
    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }


    public String getCompletionDay() {
        return completionDay;
    }
    public void setCompletionDay(String completionDay) {
        this.completionDay = completionDay;
    }

}
