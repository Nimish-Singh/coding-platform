package com.codingblox.service;

import com.codingblox.model.SortOrder;
import com.codingblox.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.codingblox.model.SortOrder.ASC;
import static java.util.Collections.reverse;
import static java.util.Comparator.comparingInt;

public class UserService {
    private final List<User> users;

    public UserService(List<User> users) {
        this.users = users;
    }

    public User createUser(String name) {
        User user = new User(name);
        users.add(user);
        return user;
    }

    public Optional<User> getUserWithName(String name) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();
    }

    public List<User> getLeaderBoard(SortOrder sortOrder) {
        ArrayList<User> leaders = new ArrayList<>(users);
        leaders.sort(comparingInt(User::getTotalScore));

        if (ASC.equals(sortOrder)) {
            return leaders;
        }

        reverse(leaders);
        return leaders;
    }
}
