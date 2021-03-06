package com.api.benneighbour.workoutManager.workout.dao;

import com.api.benneighbour.workoutManager.workout.entity.Workout;
import com.api.benneighbour.workoutManager.workout.entity.image.ThumbnailImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutDao extends JpaRepository<Workout, Long> {

    Workout findByName(String name);

    Workout findItemByWid(Long wid);

}
