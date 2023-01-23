

package com.example.circle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class MyCircle {
    Paint paint;
    public float nextX=500;
    public float nextY=400;
    public void setNextX(float nextX){
        this.nextX = nextX;
    }
    public void setNextY(float nextY){
        this.nextY = nextY;
    }

    public float getNextX() {
        return nextX;
    }

    public float getNextY() {
        return nextY;
    }



    public float x;
    public float y;
    public float radius;
    public MyCircle(float x,float y){
        this.x = x;
        this.y =y;
        this.radius =50;
        paint = new Paint();
    }
    public void draw(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        canvas.drawCircle(x, y, radius, paint);
    }




}

