package com.example.cip.myapplication;

public class GameHistory {
    private int[] counter_100, counter_160, counter_180, highestThrow;
    private double[] average_points;
    private String[] players_name;
    private int ID_counter = 0;

    public GameHistory(){}

    public GameHistory(int[] counter_100, int[] counter_160, int[] counter_180, double[] average_points, int[] highestThrow, String[] players_name) {
        this.counter_100 = counter_100;
        this.counter_160 = counter_160;
        this.counter_180 = counter_180;
        this.average_points = average_points;
        this.highestThrow = highestThrow;
        this.players_name = players_name;
        ID_counter++;
    }

    public void setCounter_100(int[] counter_100) {
        this.counter_100 = counter_100;
    }

    public void setCounter_160(int[] counter_160) {
        this.counter_160 = counter_160;
    }

    public void setCounter_180(int[] counter_180) {
        this.counter_180 = counter_180;
    }

    public void setHighestThrow(int[] highestThrow) {
        this.highestThrow = highestThrow;
    }

    public void setAverage_points(double[] average_points) {
        this.average_points = average_points;
    }

    public void setPlayers_name(String[] players_name) {
        this.players_name = players_name;
    }

    public void setID_counter(int ID_counter) {
        this.ID_counter = ID_counter;
    }

    public int[] getCounter_100() {
        return counter_100;
    }

    public int[] getCounter_160() {
        return counter_160;
    }

    public int[] getCounter_180() {
        return counter_180;
    }

    public int[] getHighestThrow() {
        return highestThrow;
    }

    public double[] getAverage_points() {
        return average_points;
    }

    public String[] getPlayers_name() {
        return players_name;
    }

    public int getID_counter(){
        return ID_counter;
    }
}
