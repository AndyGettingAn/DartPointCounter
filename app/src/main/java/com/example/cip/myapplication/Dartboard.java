package com.example.cip.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Dartboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_dartboard);
        setContentView(new myView(this));
        //Extras: Makierte Feld: Multiplikator und Punkt (Z. 26 & 27). OutBull (25,1) und Bull (50,1)werden als Single angegeben.

    }

    class myView extends View {
        private Paint paint = new Paint();
        private final int dartboardLight = Color.rgb(191, 191, 191),
                dartboardDark = Color.rgb(0, 0, 0),
                dartboardRed = Color.rgb(139, 0, 0),
                dartboardGreen = Color.rgb(0, 100, 0),
                dartboardHighlight = Color.rgb(255, 215, 0),
                fullCircle = 360,
                numberOfFields = 20,
                numberOfFreeSpace = 21;
        private int finishMultiplier = 0,
                finishPoints = 0;
        private int[] fieldPoints = {6, 10, 15, 2, 17, 3, 19, 7, 16, 8, 11, 14, 9, 12, 5, 20, 1, 18, 4, 13};
        private double fieldsWidth = fullCircle / numberOfFreeSpace,
                freeSpace = (fullCircle - fieldsWidth * numberOfFields) / numberOfFields,
                startPoint = -fieldsWidth / 2;

        public myView(Context context) {
            super(context);
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawField(canvas);

        }

        private void drawField(Canvas canvas) {

            int single = 1,
                    doubles = 2,
                    triple = 3,
                    outBull = 25,
                    bull = 50,
                    margin = 60,
                    distance = 5,
                    middle = getWidth() / 2,
                    dartboard = getWidth() - margin,
                    radiusDouble = dartboard / 2;

            double widthDouble = dartboard * 0.025,
                    widthBig = dartboard * 0.2,
                    widthTripple = dartboard * 0.025,
                    widthLittle = dartboard * 0.2,
                    widthOutBull = dartboard * 0.02,
                    widthBull = dartboard * 0.01;
            int radiusBig = radiusDouble - (int) (widthDouble / 2) - (int) (widthBig / 2) - distance,
                    radiusTriple = radiusBig - (int) (widthBig / 2) - (int) (widthTripple / 2) - distance,
                    radiusLittle = radiusTriple - (int) (widthTripple / 2) - (int) (widthLittle / 2) - distance,
                    radiusOutBull = radiusLittle - (int) (widthLittle / 2) - (int) (widthOutBull / 2) - distance,
                    radiusBull = radiusOutBull - (int) (widthOutBull / 2) - (int) (widthBull / 2) - distance;
            RectF rectDouble = new RectF(middle - (radiusDouble), middle - (radiusDouble), middle + (radiusDouble), middle + (radiusDouble));
            RectF rectBig = new RectF(middle - (radiusBig), middle - (radiusBig), middle + (radiusBig), middle + (radiusBig));
            RectF rectTripple = new RectF(middle - (radiusTriple), middle - (radiusTriple), middle + (radiusTriple), middle + (radiusTriple));
            RectF rectLittle = new RectF(middle - (radiusLittle), middle - (radiusLittle), middle + (radiusLittle), middle + (radiusLittle));
            RectF rectOutBull = new RectF(middle - (radiusOutBull), middle - (radiusOutBull), middle + (radiusOutBull), middle + (radiusOutBull));

            for (int i = 0; i < numberOfFields; i++) {
                if (i % 2 == 0) {
                    drawArcs(canvas, getFieldColor(dartboardRed, fieldPoints[i], doubles), rectDouble, (int) widthDouble);
                    drawArcs(canvas, getFieldColor(dartboardDark, fieldPoints[i], single), rectBig, (int) widthBig);
                    drawArcs(canvas, getFieldColor(dartboardRed,fieldPoints[i], triple), rectTripple, (int) widthTripple);
                    drawArcs(canvas, getFieldColor(dartboardDark, fieldPoints[i], single), rectLittle, (int) widthLittle);
                    startPoint = startPoint + fieldsWidth + freeSpace;

                } else {
                    drawArcs(canvas, getFieldColor(dartboardGreen, fieldPoints[i], doubles), rectDouble, (int) widthDouble);
                    drawArcs(canvas, getFieldColor(dartboardLight, fieldPoints[i], single), rectBig, (int) widthBig);
                    drawArcs(canvas, getFieldColor(dartboardGreen,fieldPoints[i], triple), rectTripple, (int) widthTripple);
                    drawArcs(canvas, getFieldColor(dartboardLight, fieldPoints[i], single), rectLittle, (int) widthLittle);
                    startPoint = startPoint + fieldsWidth + freeSpace;
                }

            }
            drawRing(canvas, getFieldColor(dartboardGreen,outBull, single), rectOutBull, (int) widthOutBull);
            drawCircle(canvas, getFieldColor(dartboardRed, bull, single), radiusBull);
        }

        private void drawArcs(Canvas canvas, int color, RectF rect, int strokeWidth) {
            paint.setColor(color);
            paint.setStrokeWidth(strokeWidth);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawArc(rect, (float) startPoint, (float) fieldsWidth, false, paint);
        }

        private void drawRing(Canvas canvas, int color, RectF rect, int strokeWidth) {
            paint.setColor(color);
            paint.setStrokeWidth(strokeWidth);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawArc(rect, 0, 360, false, paint);
        }

        private void drawCircle(Canvas canvas, int color, int radius) {
            paint.setColor(color);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawCircle(getWidth() / 2, getWidth() / 2, radius, paint);
        }

        private int getFieldColor(int color, int points, int multiplier) {
            if (finishMultiplier == multiplier && finishPoints == points) {
                return dartboardHighlight;
            } else {
                return color;
            }

        }

    }
}

