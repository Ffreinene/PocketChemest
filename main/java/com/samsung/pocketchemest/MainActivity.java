package com.samsung.pocketchemest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "pocket_chemist_settings";
    public static final String ACCAUNTS_AMOUNT = "1";
    private static final String DEBUG_MAIN_ACTIVITY = "MainActivityDebug";
    private static final String APP_PREFERENCES_NAME = "name";
    private static final String APP_PREFERENCES_SURNAME = "surname";
    private static final String APP_PREFERENCES_AMOUNT = "amount";
    public static Context context;
    public static String name, surname;
    public static ArrayList<String> nameList, surnameList;
    public static char regLog = 'i';
    Button buttonRegister, buttonStart, buttonSettings, buttonAbout;
    TextView dataTV;
    ProgressBar progress;
    private SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public static void updateStaticName(String name, String surname) {
        new MainActivity().updateName(name, surname);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonRegister = (Button) findViewById(R.id.buttonReg);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonSettings = (Button) findViewById(R.id.buttonSettings);
        buttonAbout = (Button) findViewById(R.id.buttonAbout);
        dataTV = (TextView) findViewById(R.id.dataTV);context = this;
        nameList = new ArrayList<>();
        surnameList = new ArrayList<>();


        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);


//        Назначение OnClickListener
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LessonsActivity.class);
                startActivity(intent);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
        editor = preferences.edit();


    }

    @Override
    protected void onPause() {
        super.onPause();
        putInfo();
    }

    private void putInfo(){
        for (int i = 0; i < nameList.size(); i++) {
            editor.putString(APP_PREFERENCES_NAME + i + "", nameList.get(i));
            editor.putString(APP_PREFERENCES_SURNAME + i + "", surnameList.get(i));
        }
        editor.putInt(APP_PREFERENCES_AMOUNT, nameList.size());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (preferences.contains(APP_PREFERENCES_NAME) && preferences.contains(APP_PREFERENCES_SURNAME)) {
            int amount = preferences.getInt(APP_PREFERENCES_AMOUNT, 0);
            for (int i = 0; i < Integer.parseInt(ACCAUNTS_AMOUNT); i++) {
                Log.d(DEBUG_MAIN_ACTIVITY, "Обнаружено сохранённое имя (№" + i + ") (=" + preferences.getString(APP_PREFERENCES_NAME + i + "", null) + ")");
                nameList.add(preferences.getString(APP_PREFERENCES_NAME + i + "", null));
                Log.d(DEBUG_MAIN_ACTIVITY, "Обнаруженна сохранённая фамилия (№" + i + ") (=" + preferences.getString(APP_PREFERENCES_NAME + i + "", null) + ")");
                surnameList.add(preferences.getString(APP_PREFERENCES_SURNAME + i + "", null));
            }
            Log.d(DEBUG_MAIN_ACTIVITY, "Всего " + Integer.parseInt(ACCAUNTS_AMOUNT));
        dataTV.setText(surname + " " + name);
            buttonStart.setVisibility(View.VISIBLE);
            buttonRegister.setVisibility(View.GONE);
        } else {
            loginOrRegister();
        }
    }

    //Проводим регистрацию или входим
    public void loginOrRegister() {

        Log.d(DEBUG_MAIN_ACTIVITY, "regLog = " + regLog);

//        'r' = registration complite
        if (regLog == 'r') {
            Log.d(DEBUG_MAIN_ACTIVITY, "regLog = " + regLog);

            nameList.add((String) getIntent().getSerializableExtra("Name"));
            surnameList.add((String) getIntent().getSerializableExtra("Surname"));

            updateName(nameList.get(nameList.size()-1), surnameList.get(surnameList.size()-1));
            regLog = '0';
            if (nameList.size() > 1) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                regLog = 'l';
                startActivity(intent);

            }
//            'l' = logged in
        } else if (regLog == 'l') {

            nameList.add((String) getIntent().getSerializableExtra(SettingsActivity.NAME));
            surnameList.add((String) getIntent().getSerializableExtra(SettingsActivity.SURNAME));
            putInfo();
            regLog='r';
            loginOrRegister();
//            updateName(name, surname);

//            'i' = didn't indificate
        } else if (regLog == 'i') {
            regLog = 'r';
        }
    }

    //    Метод обновления имени и открытия уроков
    public void updateName(String name, String surname) {
        Log.d(DEBUG_MAIN_ACTIVITY, "name = " + name);
        Log.d(DEBUG_MAIN_ACTIVITY, "surname = " + surname);

//        if (surname != null && name != null)
        if (dataTV == null)
            dataTV = (TextView) findViewById(R.id.dataTV);
        if(surname != null && name != null){
            dataTV.setText(surname + " " + name);
        } else{
            dataTV.setText("Неизвестный человек");
        }
//        else

        buttonStart.setVisibility(View.VISIBLE);
        buttonRegister.setVisibility(View.GONE);
    }
}
