package com.codingblox;

import com.codingblox.application.Application;
import com.codingblox.application.Driver;
import com.codingblox.io.output.Output;
import com.codingblox.service.ContestService;
import com.codingblox.service.QuestionService;
import com.codingblox.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Output output = Output.CONSOLE;
        UserService userService = new UserService(new ArrayList<>());
        QuestionService questionService = new QuestionService(new HashMap<>());
        ContestService contestService = new ContestService(new HashMap<>(), questionService);
        Application application = new Application(userService, contestService, questionService);

        Driver driver = new Driver(output, application);
        driver.run();
    }
}
