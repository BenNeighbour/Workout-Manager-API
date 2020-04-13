package com.api.benneighbour.workoutManager.workout.entity.image;

import com.api.benneighbour.workoutManager.workout.entity.Workout;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

// Entity annotation to flag to java.persistence
@Entity
// Table annotation to give the Entity a name when it creates all of the tables inside the Database
@Table(name = "image")
// Jackson annotation to hide it's lazy initialization handler, which interfered with the JSON response
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ThumbnailImage implements Serializable {

    private static final long serialVersionUID = -1800826840429474049L;

    // The id field for each workout
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @JsonIgnore
    private String type;

    @Lob
    @JsonIgnore
    @Column(name = "image")
    private byte[] image;

    @JsonIgnore
    @OneToOne(targetEntity = Workout.class, cascade = CascadeType.PERSIST)
    private Workout workout;

    // Required default constructor
    public ThumbnailImage() {}

    // Constructor with it's values
    public ThumbnailImage(String name, String type, byte[] image, Workout workout) {
        this.name = name;
        this.type = type;
        this.image = image;
        this.workout = workout;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }


    public Workout getWorkout() {
        return workout;
    }
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

}
