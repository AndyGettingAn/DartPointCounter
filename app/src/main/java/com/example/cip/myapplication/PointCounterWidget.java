package com.example.cip.myapplication;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class PointCounterWidget extends AppWidgetProvider {

    private static final int NUMBER_OF_PLAYERS = 2;
    private static String [] playerNames = new String [NUMBER_OF_PLAYERS];
    private  static int []  gameState = new int[NUMBER_OF_PLAYERS],
            sets = new int[NUMBER_OF_PLAYERS];

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId) {
        int player1 = 0;
        int player2 = 1;
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.point_counter_widget);
        Intent intent = new Intent(context, PointsCounter.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_button, pendingIntent);
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
    public void onEnabled(Context context) {    }

    @Override
    public void onDisabled(Context context) {    }

    public void setGameState(int[]gameState) {
        PointCounterWidget.gameState = gameState;
    }

    public void setSets(int[] sets) {
        PointCounterWidget.sets = sets;
    }

    public void setPlayerNames(String[] playerNames) {
        PointCounterWidget.playerNames = playerNames;
    }
}

