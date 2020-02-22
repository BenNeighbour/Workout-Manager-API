package com.api.benneighbour.workoutManager.workout.service;

import com.api.benneighbour.workoutManager.workout.entity.Workout;
import java.util.List;
import java.util.Optional;

public interface WorkoutService {

    Workout saveWorkout(Workout workout);

    Workout updateWorkout(Workout workout);

    List<Workout> getAllWorkouts();

    Optional<Workout> getWorkoutById(Long wid);

    void deleteWorkout(Long wid);

}
