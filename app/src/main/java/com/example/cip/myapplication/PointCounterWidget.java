package com.example.cip.myapplication;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class PointCounterWidget extends AppWidgetProvider {

    private static int numberOfPlayers = 2;

    private static String [] playerNames = new String [numberOfPlayers];
    private  static int []  gameState = new int[numberOfPlayers],
            sets = new int[numberOfPlayers];

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        int player1 = 0;
        int player2 = 1;
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.point_counter_widget);
        views.setTextViewText(R.id.widget_player1Name, playerNames[player1]);
        views.setTextViewText(R.id.widget_player2Name, playerNames[player2]);
        views.setTextViewText(R.id.widget_player1GameState, String.valueOf(gameState[player1]));
        views.setTextViewText(R.id.widget_player2GameState, String.valueOf(gameState[player2]));
        views.setTextViewText(R.id.widget_player1Set, "Sets: " + String.valueOf(sets[player1]));
        views.setTextViewText(R.id.widget_player2Set, "Sets: " + String.valueOf(sets[player2]));
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public void setGameState(int[]gameState) {
        this.gameState = gameState;
    }

    public void setSets(int[] sets) {
        this.sets = sets;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }
}

