package com.samsung.pocketchemest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by anton on 15.02.17.
 */

public class DrawThread extends Thread {
    private static final String DEBUG = "DrawThreadDebug";
    private SurfaceHolder surfaceHolder;
    boolean running = false;
    Context context;
    private int sodaX, sodaY = 0, vinegarX = 0, vinegarY = 0;
    private float touchX, touchY;
    private boolean[] isGoing = {false, false};
    Bitmap vinegar;
    Bitmap soda;
    double middle;
    double touchPrevX, touchPrevY;
    boolean isTouchPrev = false;


    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        this.context = context;
        vinegar = BitmapFactory.decodeResource(context.getResources(), R.drawable.uksus);
        soda = BitmapFactory.decodeResource(context.getResources(), R.drawable.soda);

    }

    public void setTouch_X_and_Y(float touchX, float touchY) {
        this.touchX = touchX;
        this.touchY = touchY;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            middle = canvas.getWidth() / 2;
            if (canvas != null) {
                try {
                    canvas.drawColor(Color.GREEN);
                    if (sodaX == 0) {
                        sodaX = canvas.getWidth() - soda.getWidth();
                    }
                    canvas.drawBitmap(soda, sodaX, sodaY, null);
                    canvas.drawBitmap(vinegar, vinegarX, vinegarY, null);

                    Paint paint = new Paint();
                    paint.setStyle(Paint.Style.STROKE);

                    int rectLeft = canvas.getWidth() / 4, rectTop = canvas.getHeight() / 4,
                            rectRight = canvas.getWidth() / 4 * 3, rectBottom = canvas.getHeight() / 4 * 3;
                    Rect rect = new Rect(rectLeft, rectTop, rectRight, rectBottom);
                    canvas.drawRect(rect, paint);

//                    canvas.drawLine(rect.centerX(),rectTop,rect.centerX(),rectBottom, paint);

                    Rect flask = new Rect(rect.centerX() - rect.centerX() / 4, rect.centerY(),
                            rect.centerX() + (rect.centerX() - rect.centerX() / 4 - rectLeft), rectBottom);
//                    Log.d("AnimDebug","left = " + rectLeft + "");
//                    Log.d("AnimDebug","rect = " + (rect.centerX()-rect.centerX()/4) + "");
//                    Log.d("AnimDebug","center = " + rect.centerX());
                    canvas.drawRect(flask, paint);
//                    boolean debug_end;
//                    debug_end = true;
//                    if (debug_end) {
//                        Log.d("RectDebug", "left = " + canvas.getWidth() / 4 + " top = " + canvas.getHeight() / 4 + " right = " + canvas.getWidth() / 4 * 3 + " bottom = " + canvas.getHeight() / 4 * 3);
//                    }
//                        debug_end = false;
                    if (touchX > canvas.getWidth() / 4 && touchX < canvas.getWidth() / 4 * 3
                            && touchY > canvas.getHeight() / 4 && touchY < canvas.getHeight() / 4 * 3) {
                        isGoing[0] = true;
                        isGoing[1] = true;
                    }

                    if (sodaY + soda.getHeight() >= rect.centerY() && vinegarY + vinegar.getHeight() >= rect.centerY())
                        isGoing[1] = false;

                    if (sodaX >= canvas.getWidth() / 2 && vinegarX + vinegar.getWidth() >= canvas.getWidth() / 2) {
                        isGoing[0] = false;
                        isGoing[1] = false;
                    }

//                    isGoing[0] - X
                    if (isGoing[0]) {
                        sodaX -= 10;
                        vinegarX += 10;
                    }
//                    isGoing[1] - Y
                    if (isGoing[1]) {
                        sodaY += 10;
                        vinegarY += 10;
                    }

                    if (touchX <= vinegar.getWidth() && touchY <= vinegar.getHeight()) {
                        if (isTouchPrev) {
                            touchPrevX = touchX;
                            touchPrevY = touchY;
                        }
                        isTouchPrev = true;
                        if (touchX != touchPrevX || touchY != touchPrevY){
                            vinegarX = (int)(touchX-touchPrevX);
                            vinegarY = (int)(touchY-touchPrevY);
                            sodaX = canvas.getWidth()-vinegarX;
                            sodaY = vinegarY;

                        }
                    } else if (touchX >= sodaX && touchY >= sodaY) {

                    }

//                    if (sodaY != 0 && vinegarY != 0) {
                        if (touchX >= rect.centerX() - rect.centerX() / 4 &&
                                touchX <= rect.centerX() + rect.centerX() - rect.centerX() / 4 - rectLeft &&
                                touchY >= rect.centerY() && touchY <= rectBottom) {
                            paint.setStyle(Paint.Style.FILL);
                            paint.setColor(Color.YELLOW);
                            canvas.drawRect(flask.left, flask.centerY(), flask.right, flask.bottom, paint);
                            paint.setColor(Color.BLUE);
                            canvas.drawRect(flask.left, flask.top, flask.right, flask.centerY(), paint);
                        }
//                    }

                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void crawl() {

    }
}
