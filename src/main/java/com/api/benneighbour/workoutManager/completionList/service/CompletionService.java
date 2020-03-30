package com.api.benneighbour.workoutManager.completionList.service;

import com.api.benneighbour.workoutManager.completionList.entity.CompletionItem;
import com.api.benneighbour.workoutManager.user.entity.User;

import java.util.List;

public interface CompletionService {

    CompletionItem saveCompletionItem(CompletionItem item);

    List<CompletionItem> getUserCompletionItems(User user);

    CompletionItem markCompleted(CompletionItem item);

    void deleteCompletionItem(Long iid);

}
