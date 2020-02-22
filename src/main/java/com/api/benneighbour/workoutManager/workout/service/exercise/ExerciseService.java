package com.api.benneighbour.workoutManager.workout.service.exercise;

import com.api.benneighbour.workoutManager.workout.entity.exercise.Exercise;

import java.util.List;

public interface ExerciseService {

    Exercise saveExercise(Exercise exercise);

    Exercise updateExercise(Exercise exercise);

    List<Exercise> getAllExercises();

    Exercise getExerciseByName(String name);

    void deleteExercise(Long eid);

}
