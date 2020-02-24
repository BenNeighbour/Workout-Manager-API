package com.api.benneighbour.workoutManager.workout.entity;

import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.workout.entity.exercise.Exercise;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

// Entity annotation to flag to java.persistence
@Entity
// Table annotation to give the Entity a name when it creates all of the tables inside the Database
@Table(name = "workout")
// Jackson annotation to hide it's lazy initialization handler, which interfered with the JSON response
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Workout implements Serializable {

    // Serializable version id for the class
    private static final long serialVersionUID = 5202735610840765545L;

    // The id field for each workout
    @Id
    @Column(name = "wid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wid;

    // The name field for the workout
    @Column(name = "name")
    private String name;

    // The corresponding thumbnail field for the workout
    @Column(name = "thumbnail_num")
    private int thumbnail_num;

    // The list of many exercises that are linked to it's class for each workout
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workout", targetEntity = Exercise.class, cascade = CascadeType.REFRESH, orphanRemoval = true)
    private List<Exercise> exerciseList;

    // The duration field for each workout
    private int duration;

    // The description field for each workout
    @Column(name = "description")
    private String description;

    // The user field which each workout belongs to
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = User.class, optional = false)
    @JoinColumn(name ="user_uid", referencedColumnName = "uid")
    private User user;

    // The creation timestamp field for each workout
    @JsonIgnore
    @CreationTimestamp
    @Column(name = "dateCreated", updatable = false, nullable = false)
    private Date created;

    // The update timestamp field for each workout
    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "dateUpdated")
    private Date updated;

    // Constructor with it's values
    public Workout(Long wid, String name, int thumbnail_num, int duration, List<Exercise> exerciseList, Date created, Date updated) {
        this.wid = wid;
        this.name = name;
        this.thumbnail_num = thumbnail_num;
        this.duration = duration;
        this.exerciseList = exerciseList;

        this.created = created;
        this.updated = updated;
    }

    /*
        Setters and Getters for the Workout class so that the values in it's instance can be seen by
        the other classes in the service and security layers.
    */


    // The statements for the Workout id
    public Long getWid() {
        return wid;
    }
    public void setWid(Long wid) {
        this.wid = wid;
    }


    // The statements for the name of the Workout
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // The statements for the duration of the Workout
    public int getDuration() {
        return getExerciseList().stream()
                .mapToInt(Exercise::getDuration)
                .sum();
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }


    // The statements for the list of exercises
    public List<Exercise> getExerciseList() {
        return exerciseList;
    }
    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }


    // The statements for the creation timestamps for the entity
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }


    // The statements for the update timestamps for the entity
    public Date getUpdated() {
        return updated;
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }


    // The statements for the User for the entity
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


    // The statements for the description for the Entity
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    // The statements for the thumbnail of the Entity
    public int getThumbnail() {
        return thumbnail_num;
    }
    public void setThumbnail(int thumbnail_num) {
        this.thumbnail_num = thumbnail_num;
    }

}
