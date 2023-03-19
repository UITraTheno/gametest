package com.mygdx.game;

public class Score {
    private int finalScore;
    String scoreData = "122";

    // Initial the game final score
    public Score(){
        this.finalScore = 0;
    }

    // update final score when player killed the enemy
    public void addScore(){
        finalScore ++;
    }

    // return final score;
    public int getFinalScore(){
        return this.finalScore;
    }
}
