package com.api.benneighbour.workoutManager.workout.controller;


import com.api.benneighbour.workoutManager.workout.entity.Workout;
import com.api.benneighbour.workoutManager.workout.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/workout/")
public class WorkoutController {

    private final String allowedOrigin = "localhost:3000/";

    @Autowired
    private WorkoutService service;

    @CrossOrigin(origins = allowedOrigin)
    @PostMapping("/save/")
    public Object save(@RequestBody Workout w) {
        return service.saveWorkout(w);
    }

    @CrossOrigin(origins = allowedOrigin)
    @PutMapping("/update/")
    public Object update(@RequestBody Workout w) {
        return service.updateWorkout(w);
    }

    @CrossOrigin(origins = allowedOrigin)
    @GetMapping("/by/{wid}")
    public Optional<Workout> getWorkoutByName(@PathVariable(name="wid") Long wid) {
        return service.getWorkoutById(wid);
    }

    @CrossOrigin(origins = allowedOrigin)
    @DeleteMapping("/delete/by/{wid}")
    public void deleteWorkout(@PathVariable(name = "wid") Long wid) {
        service.deleteWorkout(wid);
    }

}
