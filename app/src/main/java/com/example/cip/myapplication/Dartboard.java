package com.example.cip.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;


public class Dartboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String [] finishValues = new String[]{""};
        if (intent.hasExtra("finishValues")){
            finishValues = intent.getStringArrayExtra("finishValues");
        }
        setContentView(new myView(this, finishValues));
    }

    class myView extends View {
        private final Paint PAINT = new Paint();
        private final int DARTBOARD_LIGHT = Color.rgb(245, 245, 245),
                DARTBOARD_DARK = Color.rgb(10, 10, 10),
                DARTBOARD_RED = Color.rgb(179,59,59),
                DARTBOARD_GREEN = Color.rgb(140,191,64),
                DARTBOARD_HIGHLIGHT = Color.rgb(255, 255, 0),
                CANVAS_BACKGROUND = Color.rgb(224,209,188),
                DARTBOARD_BACKGROUND = Color.rgb(10,10,10),
                FULL_CIRCLE = 360,
                NUMBER_OF_FIELDS = 20,
                NUMBER_OF_FREE_SPACE = 21,
                TEXT_SIZE = 30;
        private final double FIELD_WIDTH = FULL_CIRCLE / NUMBER_OF_FREE_SPACE,
                FREE_SPACE = (FULL_CIRCLE - FIELD_WIDTH * NUMBER_OF_FIELDS) / NUMBER_OF_FIELDS;
        private final String TEXT = "Die gelb makierten Felder zeigen an, welche Felder getroffen werden müssen";
        private double startPoint = -FIELD_WIDTH / 2;
        private final int[] FIELD_POINTS = {6, 10, 15, 2, 17, 3, 19, 7, 16, 8, 11, 14, 9, 12, 5, 20, 1, 18, 4, 13};
        private String [] finishValues;
        
        public myView(Context context, String [] finishValues) {
            super(context);
            this.finishValues = finishValues;
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(CANVAS_BACKGROUND);
            drawDartboard(canvas);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            finish();
            return super.dispatchTouchEvent(ev);
        }

        private void drawDartboard(Canvas canvas) {
            final char SINGLE = 'S',
                    DOUBLES = 'D',
                    TRIPLES = 'T';
            final int FIRST_COLOR = 0,
                    SECOND_COLOR = 1,
                    BULL = 25,
                    MARGIN = 60,
                    DISTANCE = 5,
                    CENTER = getWidth() / 2,
                    DARTBOARD = getWidth() - MARGIN,
                    RADIUS_DOUBLE = DARTBOARD / 2;
            final double WIDTH_DOUBLE = DARTBOARD * 0.025,
                    WIDTH_BIG = DARTBOARD * 0.2,
                    WIDTH_TRIPLE = DARTBOARD * 0.025,
                    WIDTH_LITTLE = DARTBOARD * 0.2,
                    WIDTH_OUT_BULL = DARTBOARD * 0.02,
                    WIDTH_BULL = DARTBOARD * 0.01;
            final int RADIUS_BIG = RADIUS_DOUBLE - (int) (WIDTH_DOUBLE / 2) - (int) (WIDTH_BIG / 2) - DISTANCE,
                    RADIUS_TRIPLE = RADIUS_BIG - (int) (WIDTH_BIG / 2) - (int) (WIDTH_TRIPLE / 2) - DISTANCE,
                    RADIUS_LITTLE = RADIUS_TRIPLE - (int) (WIDTH_TRIPLE / 2) - (int) (WIDTH_LITTLE / 2) - DISTANCE,
                    RADIUS_OUT_BULL = RADIUS_LITTLE - (int) (WIDTH_LITTLE / 2) - (int) (WIDTH_OUT_BULL / 2) - DISTANCE,
                    RADIUS_BULL = RADIUS_OUT_BULL - (int) (WIDTH_OUT_BULL / 2) - (int) (WIDTH_BULL / 2) - DISTANCE;
            RectF rectDouble = new RectF(CENTER - (RADIUS_DOUBLE), CENTER - (RADIUS_DOUBLE), CENTER + (RADIUS_DOUBLE), CENTER + (RADIUS_DOUBLE));
            RectF rectBig = new RectF(CENTER - (RADIUS_BIG), CENTER - (RADIUS_BIG), CENTER + (RADIUS_BIG), CENTER + (RADIUS_BIG));
            RectF rectTriple = new RectF(CENTER - (RADIUS_TRIPLE), CENTER - (RADIUS_TRIPLE), CENTER + (RADIUS_TRIPLE), CENTER + (RADIUS_TRIPLE));
            RectF rectLittle = new RectF(CENTER - (RADIUS_LITTLE), CENTER - (RADIUS_LITTLE), CENTER + (RADIUS_LITTLE), CENTER + (RADIUS_LITTLE));
            RectF rectOutBull = new RectF(CENTER - (RADIUS_OUT_BULL), CENTER - (RADIUS_OUT_BULL), CENTER + (RADIUS_OUT_BULL), CENTER + (RADIUS_OUT_BULL));
            drawCircle(canvas, DARTBOARD_BACKGROUND, CENTER);
            for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
                drawArcs(canvas, getFieldColor(getColorCombination(i)[FIRST_COLOR], FIELD_POINTS[i], DOUBLES), rectDouble, (int) WIDTH_DOUBLE);
                drawArcs(canvas, getFieldColor(getColorCombination(i)[SECOND_COLOR], FIELD_POINTS[i], SINGLE), rectBig, (int) WIDTH_BIG);
                drawArcs(canvas, getFieldColor(getColorCombination(i)[FIRST_COLOR],FIELD_POINTS[i], TRIPLES), rectTriple, (int) WIDTH_TRIPLE);
                drawArcs(canvas, getFieldColor(getColorCombination(i)[SECOND_COLOR], FIELD_POINTS[i], SINGLE), rectLittle, (int) WIDTH_LITTLE);
                startPoint = startPoint + FIELD_WIDTH + FREE_SPACE;
            }
            drawRing(canvas, getFieldColor(DARTBOARD_GREEN,BULL, SINGLE), rectOutBull, (int) WIDTH_OUT_BULL);
            drawCircle(canvas, getFieldColor(DARTBOARD_RED, BULL, DOUBLES), RADIUS_BULL);
            drawText(canvas, DARTBOARD_DARK, TEXT_SIZE, CENTER, getHeight()-(getHeight()/5), TEXT);
        }

        private void drawArcs(Canvas canvas, int color, RectF rect, int strokeWidth) {
            PAINT.setColor(color);
            PAINT.setStrokeWidth(strokeWidth);
            PAINT.setAntiAlias(true);
            PAINT.setStyle(Paint.Style.STROKE);
            canvas.drawArc(rect, (float) startPoint, (float) FIELD_WIDTH, false, PAINT);
        }

        private void drawRing(Canvas canvas, int color, RectF rect, int strokeWidth) {
            PAINT.setColor(color);
            PAINT.setStrokeWidth(strokeWidth);
            PAINT.setAntiAlias(true);
            PAINT.setStyle(Paint.Style.STROKE);
            canvas.drawArc(rect, 0, 360, false, PAINT);
        }

        private void drawCircle(Canvas canvas, int color, int radius) {
            PAINT.setColor(color);
            PAINT.setStyle(Paint.Style.FILL);
            canvas.drawCircle(getWidth() / 2, getWidth() / 2, radius, PAINT);
        }

        private void drawText(Canvas canvas, int color, int tsize, int x, int y, String TEXT ){
            PAINT.setColor(color);
            PAINT.setTextSize(tsize);
            PAINT.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(TEXT, x, y, PAINT);
        }

        private int getFieldColor(int color, int points, char multiplier) {
            if (finishValues[0] == ""){
                return color;
            }
            for (String finishValue : finishValues) {
                char finishMultiplier = finishValue.charAt(0);
                int finishPoints = Integer.parseInt(finishValue.substring(1));
                if (finishMultiplier == multiplier && finishPoints == points) {
                    return DARTBOARD_HIGHLIGHT;
                }
            }
            return color;
        }

        private int [] getColorCombination (int index){
            if (index % 2 == 0) {
                return new int[]{DARTBOARD_RED, DARTBOARD_DARK};
            }else{
                return new int[]{DARTBOARD_GREEN, DARTBOARD_LIGHT};
            }
        }
    }
}

