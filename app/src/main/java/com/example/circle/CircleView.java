package com.example.circle;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class CircleView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private final DrawThread drawThread;
    MyCircle circle;
    Canvas canvas;
    private final Paint paint = new Paint();

    public CircleView(Context context) {
        super(context);
        circle = new MyCircle(500,400);
        drawThread = new DrawThread();
        getHolder().addCallback(this);


    }






    public boolean onTouchEvent(MotionEvent event) {
        circle.setNextX(event.getX());
        circle.setNextY(event.getY());
        invalidate();
        return super.onTouchEvent(event);
    }
    public void move() {
        float lenght = (float) Math.sqrt((circle.nextY - circle.y) * (circle.nextY - circle.y) + (circle.nextX - circle.x) * (circle.nextX - circle.x));
        if (lenght <= 20) {
            circle.x = circle.nextX;
            circle.y = circle.nextY;
        } else {
            float dx = circle.nextX - circle.x;
            float dy = circle.nextY - circle.y;
            dx /= (lenght / 20);
            dy /= (lenght / 20);
            circle.x =circle.x + dx;
            circle.y = circle.y +dy;

        }
    }
    @Override
    protected void onDraw(Canvas canvas) {

        Log.v("RRR", "onDraw");
        super.onDraw(canvas);
    }
    private void drawStaff(Canvas canvas){
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        canvas.drawCircle(circle.x, circle.y, 50, paint);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        drawThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
    class DrawThread extends Thread {
        private volatile boolean running = true;
        private Canvas canvas;

        public void run() {
            while (running) {
                try {
                    sleep(100);
                    canvas = surfaceHolder.lockCanvas();
                    canvas.drawColor(Color.BLUE);
                    circle.draw(canvas);
                    move();
                } catch (Exception e) {
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }




}



