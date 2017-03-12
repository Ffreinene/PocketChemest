package com.samsung.pocketchemest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    public static boolean isRegister = false;

    EditText editName, editSurname;
    Button ok;
    String name, surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editName = (EditText) findViewById(R.id.editName);
        editSurname = (EditText) findViewById(R.id.editSurame);
        ok = (Button) findViewById(R.id.buttonOk);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editName.getText().toString();
                surname = editSurname.getText().toString();
                isRegister = true;
                Intent toMain = new Intent(RegistrationActivity.this, MainActivity.class);
                if ((Integer) getIntent().getSerializableExtra("REGHELP") != null) {
                    MainActivity.nameList.add(name);
                    MainActivity.surnameList.add(surname);
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    if (name != null)
                        toMain.putExtra("Name", name);
                    else
                        toMain.putExtra("Name", "Рудольф");
                    if (surname != null)
                        toMain.putExtra("Surname", surname);
                    else
                        toMain.putExtra("Surname", "Нет");
                    MainActivity.regLog = 'r';
                    startActivity(toMain);


                }


            }
        });
    }
}
