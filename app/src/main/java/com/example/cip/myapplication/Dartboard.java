package com.example.cip.myapplication;

import android.content.Context;
import android.content.Intent;
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
        Intent intent = getIntent();
        String [] finishValues = new String[]{""};
        // damit man auch den Button betätigen kann, wenn keine Extras vorhanden sind
        if (intent.hasExtra("finishValues")){
            finishValues = intent.getStringArrayExtra("finishValues");
        }
        setContentView(new myView(this, finishValues));
    }

    class myView extends View {
        private Paint paint = new Paint();
        private final int dartboardLight = Color.rgb(255, 255, 255),
                dartboardDark = Color.rgb(0, 0, 0),
                dartboardRed = Color.rgb(100, 0, 0),
                dartboardGreen = Color.rgb(0, 100, 0),
                dartboardHighlight = Color.rgb(255, 255, 0),
                dartboardBackground = Color.rgb(225,225,225),
                fullCircle = 360,
                numberOfFields = 20,
                numberOfFreeSpace = 21,
                textSize = 30;
        private final String text = "Die gelb makierten Felder zeigen an, welche Felder getroffen werden müssen";
        private String [] finishValues;
        private int[] fieldPoints = {6, 10, 15, 2, 17, 3, 19, 7, 16, 8, 11, 14, 9, 12, 5, 20, 1, 18, 4, 13};

        private double fieldsWidth = fullCircle / numberOfFreeSpace,
                freeSpace = (fullCircle - fieldsWidth * numberOfFields) / numberOfFields,
                startPoint = -fieldsWidth / 2;

        public myView(Context context, String [] finishValues) {
            super(context);
            this.finishValues = finishValues;
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawField(canvas);
        }

        private void drawField(Canvas canvas) {
            char single = 'S',
                    doubles = 'D',
                    triple = 'T';
            int fistColor = 0,
                    secondColor = 1,
                    bull = 25,
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
            drawCircle(canvas, dartboardBackground, middle);
            for (int i = 0; i < numberOfFields; i++) {
                drawArcs(canvas, getFieldColor(getColorCombination(i)[fistColor], fieldPoints[i], doubles), rectDouble, (int) widthDouble);
                drawArcs(canvas, getFieldColor(getColorCombination(i)[secondColor], fieldPoints[i], single), rectBig, (int) widthBig);
                drawArcs(canvas, getFieldColor(getColorCombination(i)[fistColor],fieldPoints[i], triple), rectTripple, (int) widthTripple);
                drawArcs(canvas, getFieldColor(getColorCombination(i)[secondColor], fieldPoints[i], single), rectLittle, (int) widthLittle);
                startPoint = startPoint + fieldsWidth + freeSpace;
            }
            drawRing(canvas, getFieldColor(dartboardGreen,bull, single), rectOutBull, (int) widthOutBull);
            drawCircle(canvas, getFieldColor(dartboardRed, bull, doubles), radiusBull);
            drawText(canvas, dartboardDark, textSize, middle, getHeight()-(getHeight()/5), text);
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
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(getWidth() / 2, getWidth() / 2, radius, paint);
        }

        private void drawText(Canvas canvas, int color, int tsize, int x, int y, String text ){
            paint.setColor(color);
            paint.setTextSize(tsize);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(text, x, y, paint);
        }

        private int getFieldColor(int color, int points, char multiplier) {
            if (finishValues[0] == ""){
                return color;
            }
            for (int i = 0; i < finishValues.length; i++ ){
                char finishMultiplier = finishValues[i].charAt(0);
                int finishPoints = Integer.parseInt(finishValues[i].substring(1));
                if (finishMultiplier == multiplier && finishPoints == points) {
                    return dartboardHighlight;
                }
            }
            return color;
        }

        private int [] getColorCombination (int index){
            if (index % 2 == 0) {
                return new int[]{dartboardRed, dartboardDark};
            }else{
                return new int[]{dartboardGreen, dartboardLight};
            }
        }
    }
}

