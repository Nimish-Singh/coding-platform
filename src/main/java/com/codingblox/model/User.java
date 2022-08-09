package com.codingblox.model;

public class User {
    private static final int DEFAULT_TOTAL_SCORE = 1500;

    private static Integer ID = 1;

    private final Integer id;
    private final String name;
    private Integer totalScore;

    public User(String name) {
        this.id = ID;
        this.name = name;
        this.totalScore = DEFAULT_TOTAL_SCORE;
        ID++;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public boolean isSameAs(User anotherUser) {
        return this.id.equals(anotherUser.id);
    }

    public void addScore(Integer score) {
        this.totalScore += score;
    }
}
