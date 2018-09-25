package com.affogatostudios.quizzer;

import android.util.Log;

import java.util.Random;

public class Question {

    private static final int VARIANCE = 2;
    private static final int BOUND = 50;

    private Random random;
    private int answer;
    private int bogie1;
    private int bogie2;
    private int correctCount;
    private int incorrectCount;
    private String[] options;

    public Question() {
        random = new Random();
        options = new String[3];
        correctCount = 0;
        incorrectCount = 0;
    }

    public int randomNumber() {
        return random.nextInt(BOUND) + 1;
    }

    public void addCorrect() {
        correctCount++;
    }

    public String getCorrectCount() {
        return "Correct:  " + correctCount;
    }

    public void addInccorect() {
        incorrectCount++;
    }

    public String getIncorrectCount() {
        return "Wrong:  " + incorrectCount;
    }

    public boolean checkAnswer(int userSelection) {
        if (answer == userSelection) {
            addCorrect();
            return true;
        }
        addInccorect();
        return false;
    }

    public String[] getOptions() {
        int r = random.nextInt(3);
        switch (r) {
            case 0:
                options[0] = answer + "";
                options[1] = bogie1 + "";
                options[2] = bogie2 + "";
                break;
            case 1:
                options[0] = bogie1 + "";
                options[1] = answer + "";
                options[2] = bogie2 + "";
                break;
            case 2:
                options[0] = bogie1 + "";
                options[1] = bogie2 + "";
                options[2] = answer + "";
                break;
        }
        return options;
    }

    public String getQuestion() {
        int a = randomNumber();
        int b = randomNumber();
        int c = random.nextInt(10) + 1;
        answer = a + b;
        if (answer >= 50) {
            bogie1 = answer - c;
        } else {
            bogie1 = answer + c;
        }
        if (answer >= 50) {
            bogie2 = answer - c - VARIANCE;
        } else {
            bogie2 = answer + c + VARIANCE;
        }
        return "What is " + a + " + " + b + "?";
    }
}
