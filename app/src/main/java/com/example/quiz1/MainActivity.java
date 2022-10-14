package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button clickMe, clickReg;
    private EditText inputUsername;
    private EditText inputPassword;
    private String usernameString;
    private String passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickMe = findViewById(R.id.login_btn);
        clickReg = findViewById(R.id.register_btn);

        inputUsername = findViewById(R.id.username_field);
        inputPassword = findViewById(R.id.password_field);

        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usernameString = inputUsername.getText().toString();
                passwordString = inputPassword.getText().toString();

                if (usernameString.equalsIgnoreCase("") && passwordString.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Please enter username or password", Toast.LENGTH_SHORT).show();
                } else if (!usernameString.equalsIgnoreCase("admin") && !passwordString.equalsIgnoreCase("admin")) {
                    Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(view.getContext(), ListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        clickReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Register.class);
                startActivity(intent);
            }
        });
    }
}