package com.codingblox.model;

public class Question {
    private static Integer ID = 1;

    private final Integer id;
    private final DifficultyLevel difficultyLevel;
    private final Integer score;

    public Question(DifficultyLevel difficultyLevel, Integer score) {
        this.id = ID;
        this.difficultyLevel = difficultyLevel;
        this.score = score;
        ID++;
    }

    public Integer getId() {
        return id;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public Integer getScore() {
        return score;
    }
}
