package it.mirea.jsonparser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Profil extends AppCompatActivity {
    Button load;
    Button regs;
    ImageButton back;
    Button rereg;
    Button nlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_state);

        back = findViewById(R.id.imageButton1);
        rereg = findViewById(R.id.continueBtn4);
        nlog = findViewById(R.id.continueBtn5);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profil.this, MainActivity.class);
                startActivity(i);
            }
        });
        rereg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profil.this, RegistrationActivity.class);
                startActivity(i);
            }
        });
        nlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profil.this, LoginActivity.class);
                startActivity(i);
            }
        });


    }







}
