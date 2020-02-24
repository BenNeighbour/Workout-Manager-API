package com.api.benneighbour.workoutManager.workout.entity.exercise;

import com.api.benneighbour.workoutManager.workout.entity.Workout;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

// Entity annotation to flag to java.persistence
@Entity
// Table annotation to give the Entity a name when it creates all of the tables inside the Database
@Table(name = "exercise")
// Jackson annotation to hide it's lazy initialization handler, which interfered with the JSON response
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Exercise implements Serializable {

    // Serializable version id for the class
    private static final long serialVersionUID = -4678951580982202818L;

    // The id field for each workout
    @Id
    @Column(name = "eid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eid;

    // The name field for the exercise
    @Column(name = "name")
    private String name;

    // The amount of reps field for the exercise
    @Column(name = "reps")
    private int reps;

    // The amount of sets field for the exercise
    @Column(name = "sets")
    private int sets;

    // The number of burnt calories field for the total exercise
    @Column(name = "burntCals")
    private int burntCals;

    // The scale of duration field for each of the exercise
    @Column(name = "duration")
    private int duration;

    // The scale of difficulty field for each of the exercise
    @Column(name = "difficulty")
    private int difficulty;

    // The workout join, which holds the wid that it is linked to
    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Workout.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name ="workout_wid", referencedColumnName = "wid")
    private Workout workout;

    // Constructor with it's values
    public Exercise(Long eid, String name, int reps, int sets, int burntCals, int difficulty, Workout workout) {
        this.eid = eid;
        this.name = name;
        this.reps = reps;

        this.sets = sets;
        this.burntCals = burntCals;
        this.difficulty = difficulty;

        this.workout = workout;

    }

    /*
        Setters and Getters for the Exercise class so that the values in it's instance can be seen by
        the other classes in the service and security layers.
    */


    // The statements for the Exercise id
    public Long getEid() {
        return eid;
    }
    public void setEid(Long eid) {
        this.eid = eid;
    }


    // The statements for the Exercise id
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    // The statements for the number of Reps
    public int getReps() {
        return reps;
    }
    public void setReps(int reps) {
        this.reps = reps;
    }


    // The statements for the number of Sets
    public int getSets() {
        return sets;
    }
    public void setSets(int sets) {
        this.sets = sets;
    }


    // The statements for the number of Burnt Calories
    public int getBurntCals() {
        return burntCals;
    }
    public void setBurntCals(int burntCals) {
        this.burntCals = burntCals;
    }


    // The statements for the Difficulty
    public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }


    // The statements for the Duration
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }


    // The statements for the Workout
    public Workout getWorkout() {
        return workout;
    }
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}