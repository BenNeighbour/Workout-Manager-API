package com.api.benneighbour.workoutManager.completionList.dao;

import com.api.benneighbour.workoutManager.completionList.entity.CompletionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletionDao extends JpaRepository<CompletionItem, Long> {



}
