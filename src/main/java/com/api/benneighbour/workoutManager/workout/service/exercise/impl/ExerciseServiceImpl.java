package com.api.benneighbour.workoutManager.workout.service.exercise.impl;

import com.api.benneighbour.workoutManager.workout.dao.exercise.ExerciseDao;
import com.api.benneighbour.workoutManager.workout.entity.exercise.Exercise;
import com.api.benneighbour.workoutManager.workout.service.exercise.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseDao dao;

    @Override
    public Exercise saveExercise(Exercise e) {
        return dao.save(e);
    }

    @Override
    public Exercise updateExercise(Exercise e) {
        return dao.saveAndFlush(e);
    }

    @Override
    public List<Exercise> getAllExercises() {
        return dao.findAll();
    }

    @Override
    public Exercise getExerciseByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public void deleteExercise(Long eid) {
        dao.deleteById(eid);
    }
}
