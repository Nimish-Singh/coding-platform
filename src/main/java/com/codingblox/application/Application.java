package com.codingblox.application;

import com.codingblox.exception.CodingBloxException;
import com.codingblox.model.Contest;
import com.codingblox.model.DifficultyLevel;
import com.codingblox.model.Question;
import com.codingblox.model.SortOrder;
import com.codingblox.model.User;
import com.codingblox.service.ContestService;
import com.codingblox.service.QuestionService;
import com.codingblox.service.UserService;

import java.util.List;
import java.util.Optional;

public class Application {
    private final UserService userService;
    private final ContestService contestService;
    private final QuestionService questionService;

    public Application(UserService userService, ContestService contestService, QuestionService questionService) {
        this.userService = userService;
        this.contestService = contestService;
        this.questionService = questionService;
    }

    public User createUser(String name) {
        return userService.createUser(name);
    }

    public Question createQuestion(DifficultyLevel difficultyLevel, Integer score) {
        return questionService.createQuestion(difficultyLevel, score);
    }

    public List<Question> listQuestions() {
        return questionService.listQuestions();
    }

    public List<Question> listQuestions(DifficultyLevel difficultyLevel) {
        return questionService.listQuestions(difficultyLevel);
    }

    public Contest createContest(String contestName, DifficultyLevel difficultyLevel, String creatorName) throws CodingBloxException {
        Optional<User> creator = userService.getUserWithName(creatorName);
        if (creator.isEmpty()) {
            throw new CodingBloxException("Creator should be a registered user");
        }

        return contestService.createContest(contestName, difficultyLevel, creator.get());
    }

    public List<Contest> listContests() {
        return contestService.listContests();
    }

    public List<Contest> listContests(DifficultyLevel difficultyLevel) {
        return contestService.listContests(difficultyLevel);
    }

    public void attendContest(Integer contestId, String userName) throws CodingBloxException {
        Optional<User> user = userService.getUserWithName(userName);
        if (user.isEmpty()) {
            throw new CodingBloxException("Creator should be a registered user");
        }

        contestService.attendContest(contestId, user.get());
    }

    public void runContest(Integer contestId, String creatorName) throws CodingBloxException {
        Optional<User> creator = userService.getUserWithName(creatorName);
        if (creator.isEmpty()) {
            throw new CodingBloxException("Creator should be a registered user");
        }

        contestService.runContest(contestId, creator.get());
    }

    public List<User> getLeaderBoard(SortOrder sortOrder) {
        return userService.getLeaderBoard(sortOrder);
    }
}
