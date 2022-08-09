package com.codingblox.service;

import com.codingblox.exception.CodingBloxException;
import com.codingblox.model.Contest;
import com.codingblox.model.DifficultyLevel;
import com.codingblox.model.Question;
import com.codingblox.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

public class ContestService {
    private static final int NUMBER_OF_QUESTIONS_FOR_USER = 3;
    private static final Map<DifficultyLevel, Integer> SCORE_REDUCTIONS_PER_DIFFICULTY_LEVEL = Map.of(DifficultyLevel.LOW, 50, DifficultyLevel.MEDIUM, 30, DifficultyLevel.HIGH, 0);
    private final Map<Integer, Contest> idContestMap;
    private final QuestionService questionService;

    public ContestService(Map<Integer, Contest> contestMap, QuestionService questionService) {
        this.idContestMap = contestMap;
        this.questionService = questionService;
    }

    public Contest createContest(String name, DifficultyLevel difficultyLevel, User creator) {
        Contest contest = new Contest(name, difficultyLevel, creator);
        Integer contestId = contest.getId();
        idContestMap.put(contestId, contest);
        contest.addParticipant(creator);

        return contest;
    }

    public List<Contest> listContests() {
        return new ArrayList<>(idContestMap.values());
    }

    public List<Contest> listContests(DifficultyLevel difficultyLevel) {
        return idContestMap.values().stream()
                .filter(contest -> contest.getDifficultyLevel().equals(difficultyLevel))
                .toList();
    }

    public void attendContest(Integer contestId, User user) throws CodingBloxException {
        Contest contest = idContestMap.get(contestId);
        if (isNull(contest)) {
            throw new CodingBloxException("Contest with given id doesn't exist");
        }

        contest.addParticipant(user);
    }

    public void runContest(Integer id, User creator) throws CodingBloxException {
        Contest contest = getAndValidateContest(id, creator);
        DifficultyLevel contestDifficultyLevel = contest.getDifficultyLevel();
        contest.start();

        List<User> participants = contest.getParticipants();
        participants.forEach(participant -> {
            List<Question> questionsSolvedByUser = questionService.getQuestionsForLevel(NUMBER_OF_QUESTIONS_FOR_USER, contestDifficultyLevel);
            Integer totalQuestionsScore = computeScoreForQuestions(questionsSolvedByUser, contestDifficultyLevel).get();

            contest.updateParticipantScore(participant, totalQuestionsScore);
            participant.addScore(totalQuestionsScore);
        });
    }

    private Contest getAndValidateContest(Integer id, User creator) throws CodingBloxException {
        Contest contest = idContestMap.get(id);
        if (isNull(contest)) {
            throw new CodingBloxException("Contest with given id doesn't exist");
        }

        if (!contest.isCreatedBy(creator)) {
            throw new CodingBloxException("Only contest creator can run a contest");
        }
        return contest;
    }

    private Optional<Integer> computeScoreForQuestions(List<Question> questions, DifficultyLevel contestDifficultyLevel) {
        return questions.stream()
                .map(Question::getScore)
                .map(score -> score -= SCORE_REDUCTIONS_PER_DIFFICULTY_LEVEL.get(contestDifficultyLevel))
                .reduce(Integer::sum);
    }
}
