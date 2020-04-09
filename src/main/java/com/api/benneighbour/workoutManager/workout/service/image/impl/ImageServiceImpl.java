package com.api.benneighbour.workoutManager.workout.service.image.impl;

import com.api.benneighbour.workoutManager.workout.dao.image.ImageDao;
import com.api.benneighbour.workoutManager.workout.entity.image.ThumbnailImage;
import com.api.benneighbour.workoutManager.workout.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao dao;

    @Override
    public ThumbnailImage getWorkoutImage(Long id) {
        return dao.getOne(id);
    }

    @Override
    public ThumbnailImage changeWorkoutImage(ThumbnailImage image) {
        return dao.saveAndFlush(image);
    }

    @Override
    public ThumbnailImage saveWorkoutImage(ThumbnailImage image) {
        return dao.save(image);
    }

}
