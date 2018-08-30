package com.example.cip.myapplication;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class PointCounterWidget extends AppWidgetProvider {

    private static RemoteViews views;

    static PointsCounter counter= new PointsCounter();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

      //  CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        views = new RemoteViews(context.getPackageName(), R.layout.point_counter_widget);

        //Hier sollte der aktuelle Soielstand angezeigt werden
        int [] stands = counter.getPlayerGameState();
        views.setTextViewText(R.id.widget_player1GameState, String.valueOf(stands[0]));
        views.setTextViewText(R.id.widget_player2GameState, String.valueOf(stands[1]));
        //Toast.makeText(context, String.valueOf(stands[0]), Toast.LENGTH_SHORT).show();


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            Toast.makeText(context, "Widget has been updated! ", Toast.LENGTH_SHORT).show();
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



}

