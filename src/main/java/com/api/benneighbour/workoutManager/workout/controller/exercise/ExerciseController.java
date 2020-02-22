package com.api.benneighbour.workoutManager.workout.controller.exercise;

import com.api.benneighbour.workoutManager.workout.entity.exercise.Exercise;
import com.api.benneighbour.workoutManager.workout.service.exercise.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workout/exercise/")
public class ExerciseController {

    private final String allowedOrigin = "localhost:3000/";

    @Autowired
    private ExerciseService service;

    @CrossOrigin(origins = allowedOrigin)
    @PostMapping("/save/")
    public Exercise save(@RequestBody Exercise e) {
        return service.saveExercise(e);
    }

    @CrossOrigin(origins = allowedOrigin)
    @GetMapping("/all/")
    public List<Exercise> getAllWorkouts() {
        return service.getAllExercises();
    }

    @CrossOrigin(origins = allowedOrigin)
    @PutMapping("/update/")
    public Exercise update(@RequestBody Exercise e) {
        return service.updateExercise(e);
    }

    @CrossOrigin(origins = allowedOrigin)
    @GetMapping("/by/{name}")
    public Exercise getExerciseByName(@PathVariable(name="name") String name) {
        return service.getExerciseByName(name);
    }

    @CrossOrigin(origins = allowedOrigin)
    @DeleteMapping("/delete/by/{eid}")
    public void deleteExercise(@PathVariable(name = "eid") Long eid) {
        service.deleteExercise(eid);
    }

}
