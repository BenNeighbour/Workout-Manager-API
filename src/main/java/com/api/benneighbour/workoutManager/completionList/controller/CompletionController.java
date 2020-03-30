package com.api.benneighbour.workoutManager.completionList.controller;

import com.api.benneighbour.workoutManager.completionList.dao.CompletionDao;
import com.api.benneighbour.workoutManager.completionList.entity.CompletionItem;
import com.api.benneighbour.workoutManager.completionList.service.impl.CompletionServiceImpl;
import com.api.benneighbour.workoutManager.user.dao.UserDao;
import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.workout.dao.WorkoutDao;
import com.api.benneighbour.workoutManager.workout.entity.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user/todos")
public class CompletionController {

    @Autowired
    private CompletionServiceImpl service;

    @Autowired
    private UserDao userDao;

    @Autowired
    private WorkoutDao workoutDao;

    @Autowired
    private CompletionDao completionDao;

    @GetMapping("/{uid}/{username}/{day}")
    public List<CompletionItem> getUserCompletionItems(@PathVariable("uid") Long uid, @PathVariable("username") String username, @PathVariable("day") String day) throws RuntimeException {
        User user1 = userDao.findUserByUid(uid);
        User user2 = userDao.findByUsername(username);

        if (user1 != user2) {
            throw new RuntimeException("Something went wrong");
        }

        List<CompletionItem> arr = service.getUserCompletionItems(user2);
        String day1;
        List<CompletionItem> newArr = new ArrayList<>();

        for (int i1 = 0; i1 < arr.size(); i1 += 1) {
            CompletionItem i = arr.get(i1);
            int size  = arr.size();
            int thing = service.getUserCompletionItems(user2).indexOf(i);


            day1 = service.getUserCompletionItems(user2).get(thing).getCompletionDay();

            if (day1.equals(day)) {
                CompletionItem splitItem = service.getUserCompletionItems(user2).get(thing);
                newArr.add(splitItem);
            }
        }

        return newArr;
    }

    @PostMapping("/{uid}/{username}/{day}/save")
    public CompletionItem saveCompletionItem(@PathVariable("uid") Long uid, @PathVariable("username") String username, @PathVariable("day") String day, @RequestBody CompletionItem item) throws RuntimeException {
        User user1 = userDao.findUserByUid(uid);
        User user2 = userDao.findByUsername(username);

        if (user1 != user2) {
            throw new RuntimeException("Something went wrong");
        }

        item.setUser(user1);
        item.setCompletionDay(day);

        return service.saveCompletionItem(item);
    }

    @PostMapping("/{uid}/{username}/{name}/{completed}")
    public CompletionItem markItemCompleted(@PathVariable("uid") Long uid, @PathVariable("username") String username, @PathVariable("name") String name, @PathVariable("completed") boolean completed) throws RuntimeException {
        User user1 = userDao.findUserByUid(uid);
        User user2 = userDao.findByUsername(username);

        if (user1 != user2) {
            throw new RuntimeException("Something went wrong");
        }

        Workout w1 = workoutDao.findByName(name);

        CompletionItem item = workoutDao.findItemByWid(w1.getWid()).getCompletionItems();
        item.setCompleted(completed);

        return service.markCompleted(item);

    }

    @DeleteMapping("/{uid}/{username}/{iid}/delete")
    public void deleteCompletionItem(@PathVariable("uid") Long uid, @PathVariable("username") String username, @PathVariable("iid") Long iid) throws RuntimeException {
        User user1 = userDao.findUserByUid(uid);
        User user2 = userDao.findByUsername(username);

        if (user1 != user2) {
            throw new RuntimeException("Something went wrong");
        }

        // If the user does not own/have the todos
        if (completionDao.findItemByIid(iid).getUser() != null) {
            if (!completionDao.findItemByIid(iid).getUser().equals(user2)) {
                throw new RuntimeException("Something went wrong");
            }
        }

        // After all statements above are passed, then the todos are purged from the API
        service.deleteCompletionItem(iid);
    }

}
