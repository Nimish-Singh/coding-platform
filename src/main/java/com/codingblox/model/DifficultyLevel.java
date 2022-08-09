package com.codingblox.model;

public enum DifficultyLevel {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");

    private final String value;

    DifficultyLevel(String value) {
        this.value = value;
    }
}
