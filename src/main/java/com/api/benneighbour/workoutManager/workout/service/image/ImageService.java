package com.api.benneighbour.workoutManager.workout.service.image;

import com.api.benneighbour.workoutManager.workout.entity.image.ThumbnailImage;

import java.util.Optional;

public interface ImageService {

    ThumbnailImage getWorkoutImage(Long id);

    ThumbnailImage changeWorkoutImage(ThumbnailImage image);

    ThumbnailImage saveWorkoutImage(ThumbnailImage image);

}
