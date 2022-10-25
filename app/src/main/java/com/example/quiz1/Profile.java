package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quiz1.Models.DataModel;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity{

    private TextView id;
    private ImageView imageView;
    private TextView first_name;
    private TextView last_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        id = findViewById(R.id.id_text);
        imageView = findViewById(R.id.image_view);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);

        Bundle extras = getIntent().getExtras();

        int userID = extras.getInt("userID");
        String userFirstName = extras.getString("userFirstName");
        String userLastName = extras.getString("userLastName");
        String userAvatar = extras.getString("userAvatar");

        Glide.with(this)
                .load(userAvatar)
                .into(imageView);

        id.setText(String.valueOf(userID));
        first_name.setText(userFirstName);
        last_name.setText(userLastName);
    }
}
