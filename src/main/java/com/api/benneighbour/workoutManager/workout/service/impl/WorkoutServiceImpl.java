package com.api.benneighbour.workoutManager.workout.service.impl;

import com.api.benneighbour.workoutManager.workout.dao.WorkoutDao;
import com.api.benneighbour.workoutManager.workout.entity.Workout;
import com.api.benneighbour.workoutManager.workout.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    private WorkoutDao dao;

    @Override
    public Workout saveWorkout(Workout w) {
        return dao.save(w);
    }

    @Override
    public Workout updateWorkout(Workout w) {
        return dao.saveAndFlush(w);
    }

    @Override
    public List<Workout> getAllWorkouts() {
        return dao.findAll();
    }

    @Override
    public void deleteWorkout(Long wid) {
        dao.deleteById(wid);
    }

    @Override
    public Optional<Workout> getWorkoutById(Long wid) {
        return dao.findById(wid);
    }
}
