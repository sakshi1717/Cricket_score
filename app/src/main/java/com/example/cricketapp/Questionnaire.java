package com.example.cricketapp;

public class Questionnaire {

    public String questions[] = {
            "Which nation has won the Cricket World Cup maximum number of times?",
            "Which Stadium is known as the Mecca of Cricket?",
            "What is the name of Bangalore's Cricket Stadium?"
    };

    public String choices[][] = {
            {"India", "West Indies", "Australia", "South Africa"},
            {"Trent Bridge", "Old Trafford", "Lord's", "The Oval"},
            {"Chinnaswamy", "MA Chidambaram", "Wankhede", "Eden Gardens"}
    };

    public String correctAnswer[] = {
            "Australia",
            "Lord's",
            "Chinnaswamy"
    };

    public String getQuestion(int a){
        String question = questions[a];
        return question;
    }

    public String getchoice1(int a){
        String choice = choices[a][0];
        return choice;
    }

    public String getchoice2(int a){
        String choice = choices[a][1];
        return choice;
    }

    public String getchoice3(int a){
        String choice = choices[a][2];
        return choice;
    }

    public String getchoice4(int a){
        String choice = choices[a][3];
        return choice;
    }

    public String getCorrectAnswer(int a){
        String answer = correctAnswer[a];
        return answer;
    }
}
