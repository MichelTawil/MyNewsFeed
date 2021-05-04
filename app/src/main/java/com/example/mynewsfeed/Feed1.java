package com.example.mynewsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Feed1 extends AppCompatActivity {


    //Inicializar variables
    Button btlo, b2, btab;
    TextView tvPais;


    String Pais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed1);

        //Asignar variables
        btlo = findViewById(R.id.button_atras);
        tvPais = findViewById(R.id.tv_pais);
        b2 = findViewById(R.id.button2);
        btab = findViewById(R.id.button_tab);


        //Recibimos la variable Pais desde AllowLocation
        Pais = getIntent().getStringExtra("Pais");
        tvPais.setText("Pais: "+ Pais);



        btlo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Llamar a la activity main
                Intent intent = new Intent(Feed1.this, AllowLocation.class);
                startActivity(intent);

            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Llamar a la activity Feed2
                Intent intent = new Intent(Feed1.this, Feed2.class);
                startActivity(intent);

            }
        });

        btab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llamar a la tabbed activity
                Intent intent = new Intent(Feed1.this, Tabbed_Activity.class);
                startActivity(intent);
            }
        });
    }
}