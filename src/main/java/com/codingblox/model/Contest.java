package com.codingblox.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codingblox.model.ContestState.CREATED;
import static com.codingblox.model.ContestState.STARTED;

public class Contest {
    private static final int DEFAULT_USER_CONTEST_SCORE = 0;
    private static Integer ID = 1;

    private final Integer id;
    private final String name;
    private final DifficultyLevel difficultyLevel;
    private final User creator;
    private ContestState state;
    private final Map<User, Integer> participantScoreMap;

    public Contest(String name, DifficultyLevel difficultyLevel, User creator) {
        this.id = ID;
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.creator = creator;
        this.state = CREATED;
        this.participantScoreMap = new HashMap<>();
        ID++;
    }

    public Integer getId() {
        return id;
    }

    public void addParticipant(User user) {
        participantScoreMap.put(user, DEFAULT_USER_CONTEST_SCORE);
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public boolean isCreatedBy(User user) {
        return this.creator.isSameAs(user);
    }

    public void start() {
        this.state = STARTED;
    }

    public List<User> getParticipants() {
        return new ArrayList<>(participantScoreMap.keySet());
    }

    public void updateParticipantScore(User participant, Integer score) {
        this.participantScoreMap.put(participant, score);
    }

    public String getName() {
        return name;
    }

    public User getCreator() {
        return creator;
    }

    public ContestState getState() {
        return state;
    }
}
