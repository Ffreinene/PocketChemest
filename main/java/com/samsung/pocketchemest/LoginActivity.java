package com.samsung.pocketchemest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LoginActivity extends ListActivity {

    private final String LOGIN_ACTIVITY_DEBUG = "LoginActivityDebug";
    private String nameArray[];
    private String surnameArray[];

    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nameArray = new String[MainActivity.nameList.size()];
        surnameArray = new String[MainActivity.surnameList.size()];
        nameArray = MainActivity.nameList.toArray(nameArray);
        surnameArray = MainActivity.surnameList.toArray(surnameArray);
        Log.d(LOGIN_ACTIVITY_DEBUG, "nameArray = " + nameArray);
        Log.d(LOGIN_ACTIVITY_DEBUG, "surnameArray = " + surnameArray);

        String loginArray[] = new String[surnameArray.length];

        for (int i = 0; i < loginArray.length; i++) {
            loginArray[i] = surnameArray[i] + " " + nameArray[i];
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, loginArray);
        setListAdapter(arrayAdapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        MainActivity.name = nameArray[position];
        MainActivity.surname = surnameArray[position];
        MainActivity.regLog = 'l';
        MainActivity.updateStaticName(MainActivity.name, MainActivity.surname);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
