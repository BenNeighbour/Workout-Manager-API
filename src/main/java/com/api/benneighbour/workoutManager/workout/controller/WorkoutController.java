package com.api.benneighbour.workoutManager.workout.controller;


import com.api.benneighbour.workoutManager.workout.dao.WorkoutDao;
import com.api.benneighbour.workoutManager.workout.entity.Workout;
import com.api.benneighbour.workoutManager.workout.entity.image.ThumbnailImage;
import com.api.benneighbour.workoutManager.workout.service.WorkoutService;
import com.api.benneighbour.workoutManager.workout.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/workout/")
public class WorkoutController {

    private final String allowedOrigin = "localhost:3000/";

    @Autowired
    private WorkoutService service;

    @Autowired
    private ImageService imageService;

    @Autowired
    private WorkoutDao dao;

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

    @CrossOrigin(origins = allowedOrigin)
    @PostMapping("/image/save/{wid}/")
    public ResponseEntity<String> save(@RequestParam("image") MultipartFile file, @PathVariable("wid") Long wid) {

        Workout workout = dao.findItemByWid(wid);

        try {
            ThumbnailImage image = new ThumbnailImage(file.getOriginalFilename(), file.getContentType(), file.getBytes(), workout);

            if (imageService.saveWorkoutImage(image) == null) {
                throw new RuntimeException("Workout does not exist!");
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("This image did not meet the requirements");
        }

    }

    @CrossOrigin(origins = allowedOrigin)
    @GetMapping("/image/by/{id}")
    public String getImage(@PathVariable(name="id") Long id) {
        String encoded = Base64.getEncoder().encodeToString(imageService.getWorkoutImage(id).getImage());
        return encoded;
    }

}
