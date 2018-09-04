package com.example.cip.myapplication;

public class GameHistory {
    int[] counter_100, counter_160, counter_180, highestThrow;
    double[] average_points;
    String[] players_name;
    int ID_counter = 0;

    public GameHistory(int[] counter_100, int[] counter_160, int[] counter_180, double[] average_points, int[] highestThrow, String[] players_name) {
        this.counter_100 = counter_100;
        this.counter_160 = counter_160;
        this.counter_180 = counter_180;
        this.average_points = average_points;
        this.highestThrow = highestThrow;
        this.players_name = players_name;
        ID_counter++;
    }
}
