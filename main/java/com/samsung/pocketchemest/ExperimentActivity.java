package com.samsung.pocketchemest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class ExperimentActivity extends AppCompatActivity {

    DrawView drawView;
    private static final String DEBUG = "ExperimentActivityDebug";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);

        drawView = new DrawView(this);
        Log.d(DEBUG, drawView + "");



        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutExp);
        Log.d(DEBUG, linearLayout + "");
        linearLayout.addView(drawView);

        drawView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                drawView.drawThread.setTouch_X_and_Y(x, y);
                Log.d(DEBUG, x + "");
                Log.d(DEBUG, y + "");
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        drawView.surfaceDestroyed(drawView.getHolder());
    }

//    @Override
//    protected void onUserLeaveHint() {
//        super.onUserLeaveHint();
//
//    }
}
