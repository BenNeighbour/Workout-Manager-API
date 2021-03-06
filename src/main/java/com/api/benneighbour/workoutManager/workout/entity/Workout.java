package com.api.benneighbour.workoutManager.workout.entity;

import com.api.benneighbour.workoutManager.completionList.entity.CompletionItem;
import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.workout.entity.exercise.Exercise;
import com.api.benneighbour.workoutManager.workout.entity.image.ThumbnailImage;
import com.api.benneighbour.workoutManager.workout.service.image.impl.ImageServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Base64;
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

    // A hidden completion item field that allows multiple different workouts on separate days/todos to the same workout
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "workout", targetEntity = CompletionItem.class, orphanRemoval = true)
    private CompletionItem completionItems;

    @OneToOne(mappedBy = "workout", targetEntity = ThumbnailImage.class, cascade = CascadeType.ALL)
    private ThumbnailImage image;

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

    // Required default constructor
    public Workout() {}

    // Constructor with it's values
    public Workout(Long wid, String name, int duration, List<Exercise> exerciseList, ThumbnailImage image, Date created, Date updated) {
        this.wid = wid;
        this.name = name;
        this.duration = duration;
        this.exerciseList = exerciseList;
        this.image = image;

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
        if (getExerciseList() != null) {
            duration = getExerciseList().stream()
                    .mapToInt(Exercise::getDuration)
                    .sum();
        }
        return duration;
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


    public CompletionItem getCompletionItems() {
        return completionItems;
    }
    public void setCompletionItems(CompletionItem completionItems) {
        this.completionItems = completionItems;
    }


    public ThumbnailImage getImage() {
        return image;
    }
    public void setImage(ThumbnailImage image) {
        this.image = image;
    }

}
