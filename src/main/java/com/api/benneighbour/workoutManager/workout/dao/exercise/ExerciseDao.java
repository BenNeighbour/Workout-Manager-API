package com.api.benneighbour.workoutManager.workout.dao.exercise;

import com.api.benneighbour.workoutManager.workout.entity.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseDao extends JpaRepository<Exercise, Long> {

    Exercise findByName(String name);

}
