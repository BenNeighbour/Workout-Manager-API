package com.api.benneighbour.workoutManager.completionList.service.impl;

import com.api.benneighbour.workoutManager.completionList.dao.CompletionDao;
import com.api.benneighbour.workoutManager.completionList.entity.CompletionItem;
import com.api.benneighbour.workoutManager.completionList.service.CompletionService;
import com.api.benneighbour.workoutManager.user.dao.UserDao;
import com.api.benneighbour.workoutManager.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompletionServiceImpl implements CompletionService {

    @Autowired
    private CompletionDao dao;

    @Autowired
    private UserDao userDao;

    @Override
    public CompletionItem saveCompletionItem(CompletionItem item) {
        return dao.save(item);
    }

    @Override
    public List<CompletionItem> getUserCompletionItems(User user) {
        if (user != null) {
            User user1 = userDao.findByUsername(user.getUsername());
            return user1.getTodos();
        } else {
            throw new RuntimeException("User is not found");
        }
    }

    @Override
    public CompletionItem markCompleted(CompletionItem item) {
        return dao.saveAndFlush(item);
    }

    @Override
    public void deleteCompletionItem(Long iid) {
        dao.deleteById(iid);
    }

}
