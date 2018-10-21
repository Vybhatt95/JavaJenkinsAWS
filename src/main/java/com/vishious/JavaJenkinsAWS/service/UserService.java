package com.vishious.JavaJenkinsAWS.service;

import com.vishious.JavaJenkinsAWS.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserService {

    Map<String, User> userMap = new HashMap<>();

    public boolean saveUser(User user) {
        if (!userMap.containsKey(user.getId())) {
            userMap.put(user.getId(), user);
            return true;
        }
        return false;
    }


    public User getUser(String id) {
        if (userMap.containsKey(id)) {
            return userMap.get(id);
        }
        return null;
    }
}
