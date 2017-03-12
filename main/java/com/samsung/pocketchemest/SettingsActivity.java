package com.samsung.pocketchemest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    Button addUser;
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        addUser = (Button) findViewById(R.id.buttonAddUser);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, RegistrationActivity.class);
                intent.putExtra("REGHELP", MainActivity.nameList.size());
                startActivity(intent);
            }
        });
    }
}
