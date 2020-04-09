package com.api.benneighbour.workoutManager.workout.dao.image;

import com.api.benneighbour.workoutManager.workout.entity.image.ThumbnailImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDao extends JpaRepository<ThumbnailImage, Long> {



}
