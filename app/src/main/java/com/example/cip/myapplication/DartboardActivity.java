package com.example.cip.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;


public class DartboardActivity extends AppCompatActivity {
//    Hier wird das Darboard (idealerweise ) gezeichnet und die entspechenden Felder (Extras) eingefärbt

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_dartboard);
        setContentView(new myView(this));
    }
    class myView extends View {

        public myView(Context context) {
            super(context);
        }


        @Override
        public void onDraw(Canvas canvas) {
            //Zeichnet einen Kreis
            super.onDraw(canvas);
            int x = getWidth();
            int y = getHeight();
            int radius;
            radius = 100;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            paint.setColor(Color.BLACK);
            canvas.drawCircle(x/2, y/2, radius, paint);
        }
    }


}
