package com.samsung.pocketchemest;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    DrawThread drawThread;
    private static final String DEBUG = "DrawViewDebug";

    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        drawThread = new DrawThread(getContext(), getHolder());
        Log.d(DEBUG, drawThread + "");
        drawThread.start();
    }



    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        drawThread.running = false;
        boolean retry = true;
        while (retry){
            try {
                drawThread.join();
                retry=false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
