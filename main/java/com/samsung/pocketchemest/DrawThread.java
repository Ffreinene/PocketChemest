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


                    Rect flask = new Rect(rect.centerX() - rect.centerX() / 4, rect.centerY(),
                            rect.centerX() + (rect.centerX() - rect.centerX() / 4 - rectLeft), rectBottom);
                    canvas.drawRect(flask, paint);
                    if (touchX > canvas.getWidth() / 4 && touchX < canvas.getWidth() / 4 * 3
                            && touchY > canvas.getHeight() / 4 && touchY < canvas.getHeight() / 4 * 3) {

                    }
//                    Если касание в квадрате уксуса
                    if (touchX <= vinegar.getWidth() && touchY <= vinegar.getHeight()) {
//                        Запомнить координаты
                            touchPrevX = touchX-vinegarX;
                            touchPrevY = touchY-vinegarY;
                        if (touchX != touchPrevX || touchY != touchPrevY){
                            vinegarX = (int)(touchX-touchPrevX);
                            vinegarY = (int)(touchY-touchPrevY);
//                            Положеие картинки с содой по X противоположно (весь размер-размер картинки с  уксусом-
//                            -размер картинки с содой), а по Y анологично
                            sodaX = canvas.getWidth()-vinegarX-sodaX;
                            sodaY = vinegarY;

                        }
                    } else if (touchX >= sodaX && touchY >= sodaY) {

                    } // Если касание вне области действия






                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }


}
