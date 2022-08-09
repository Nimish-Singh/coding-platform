package com.codingblox.service;

import com.codingblox.model.DifficultyLevel;
import com.codingblox.model.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class QuestionService {
    private static final int START_INDEX = 0;
    private final Map<DifficultyLevel, List<Question>> difficultyLevelQuestionsMap;
    private final Random randomGenerator;

    public QuestionService(Map<DifficultyLevel, List<Question>> difficultyLevelQuestionsMap) {
        this.difficultyLevelQuestionsMap = difficultyLevelQuestionsMap;
        this.randomGenerator = new Random();
    }

    public Question createQuestion(DifficultyLevel difficultyLevel, Integer score) {
        Question question = new Question(difficultyLevel, score);
        List<Question> existingQuestions = difficultyLevelQuestionsMap.getOrDefault(difficultyLevel, new ArrayList<>());
        existingQuestions.add(question);
        difficultyLevelQuestionsMap.put(difficultyLevel, existingQuestions);
        return question;
    }

    public List<Question> listQuestions() {
        return difficultyLevelQuestionsMap.keySet().stream()
                .flatMap(level -> listQuestions(level).stream())
                .toList();
    }

    public List<Question> listQuestions(DifficultyLevel difficultyLevel) {
        List<Question> questions = difficultyLevelQuestionsMap.getOrDefault(difficultyLevel, new ArrayList<>());
        return new ArrayList<>(questions);
    }

    public List<Question> getQuestionsForLevel(Integer numberOfQuestions, DifficultyLevel difficultyLevel) {
        Set<Question> questions = new HashSet<>();
        List<Question> questionsForLevel = difficultyLevelQuestionsMap.get(difficultyLevel);

        do {
            int index = randomGenerator.nextInt(START_INDEX, questionsForLevel.size());
            questions.add(questionsForLevel.get(index));
        } while (questions.size() != numberOfQuestions);

        return new ArrayList<>(questions);
    }
}
