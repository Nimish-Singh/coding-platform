package com.codingblox.command;

import com.codingblox.exception.CodingBloxException;

public interface CodingBloxOption {
    void execute(String... args) throws CodingBloxException;
}
