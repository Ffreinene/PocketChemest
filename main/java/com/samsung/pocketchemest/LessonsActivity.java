package com.samsung.pocketchemest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LessonsActivity extends ListActivity {


    String lessons[];
    Intent intent;
    private ArrayAdapter<String> arrayAdapter;
    public static final String EXPERIMENT_ID = "experiment_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lessons = new String[20];
        lessons[0] = ("Сода с уксусом");
        lessons[1] = "Жареный сахар";
        lessons[2] = "Невидимые чернила";
        lessons[3] = "Очищение от жира";
        lessons[4] = "Новый пункт 1";
        lessons[5] = "Новый пункт 2";
        lessons[6] = "Новый пункт 3";
        lessons[7] = "Новый пункт 4";
        lessons[8] = "Новый пункт 5";

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lessons);
        setListAdapter(arrayAdapter);
        intent = new Intent(this, ExperimentActivity.class);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d("LessonsActivityDebug", position + "");
        if (position == 0) {
            intent.putExtra(EXPERIMENT_ID, 0);
        }


        startActivity(intent);
    }
}
