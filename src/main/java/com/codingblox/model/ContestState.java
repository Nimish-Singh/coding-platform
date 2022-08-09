package com.codingblox.model;

public enum ContestState {
    CREATED("created"),
    STARTED("started");

    private final String value;

    ContestState(String value) {
        this.value = value;
    }
}
