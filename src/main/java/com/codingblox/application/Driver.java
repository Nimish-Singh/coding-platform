package com.codingblox.application;

import com.codingblox.exception.CodingBloxException;
import com.codingblox.io.output.Output;
import com.codingblox.model.Contest;
import com.codingblox.model.Question;
import com.codingblox.model.User;

import java.util.List;

import static com.codingblox.model.DifficultyLevel.HIGH;
import static com.codingblox.model.DifficultyLevel.LOW;
import static com.codingblox.model.SortOrder.DESC;

public class Driver {
    private final Output output;
    private final Application application;

    public Driver(Output output, Application application) {
        this.output = output;
        this.application = application;
    }

    public void run() {
        try {
            executeCommands();
        } catch (Exception exception) {
            output.print(exception.getMessage());
        }
    }

    private void executeCommands() throws CodingBloxException {
        createUsers();
        createQuestions();
        listQuestions();
        createContests();
        listContests();
        attendContests();
        runContests();
        getLeaderboard();
        output.print("Done");
    }

    private void createUsers() {
        application.createUser("Ross");
        application.createUser("Monica");
        application.createUser("Joey");
        application.createUser("Chandler");
        output.print("Users created");
    }

    private void createQuestions() {
        application.createQuestion(LOW, 10);
        application.createQuestion(LOW, 20);
        application.createQuestion(LOW, 30);
        application.createQuestion(LOW, 40);
        output.print("Questions created");
    }

    private void listQuestions() {
        List<Question> questions = application.listQuestions();
        questions.forEach(this::printQuestion);
        questions = application.listQuestions(HIGH);
        questions.forEach(this::printQuestion);
        output.print("Questions listed");
    }

    private void createContests() throws CodingBloxException {
        application.createContest("diwali_contest", LOW, "Ross");
        output.print("Contests created");
    }

    private void listContests() {
        List<Contest> contests = application.listContests();
        contests.forEach(this::printContest);
        contests = application.listContests(LOW);
        contests.forEach(this::printContest);
        output.print("Contests listed");
    }

    private void attendContests() throws CodingBloxException {
        application.attendContest(1, "Monica");
        application.attendContest(1, "Joey");
        output.print("Contests attended");
    }

    private void runContests() throws CodingBloxException {
        application.runContest(1, "Ross");
        output.print("Contests run");
    }

    private void getLeaderboard() {
        List<User> leaderBoard = application.getLeaderBoard(DESC);
        leaderBoard.forEach(user -> output.print(String.format("%-20s %s", user.getName(), user.getTotalScore())));
        output.print("Leaderboard printed");
    }

    private void printQuestion(Question question) {
        output.print(String.format("%-10s %-10s %s", question.getId(), question.getDifficultyLevel(), question.getScore()));
    }

    private void printContest(Contest contest) {
        List<User> participants = contest.getParticipants();
        StringBuilder participantString = new StringBuilder();
        for (User p : participants) {
            participantString.append(p.getName()).append(", ");
        }
        output.print(String.format("%-5s %-20s %-10s %-10s %-10s", contest.getId(), contest.getName(), contest.getCreator().getName(), contest.getDifficultyLevel(), participantString));
    }
}
