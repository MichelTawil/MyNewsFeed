package com.example.mynewsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Inicializar variables
    EditText User;
    EditText Pass;
    Button Blogin, b_signup;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    //Old login
    //private String Username = "Michel";
    //private String Password = "123";

    String Username, Password;

    boolean validar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Asignar variables
        User = (EditText) findViewById(R.id.ETuser);
        Pass = (EditText) findViewById(R.id.ETpass);
        Blogin = (Button) findViewById(R.id.buttonLogin);
        b_signup = (Button) findViewById(R.id.button_Signup);

        final SharedPreferences preferences = getSharedPreferences("Userinfo", MODE_PRIVATE);


        //Funcion Boton Login
        Blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String inputUser = User.getText().toString();
                String inputPass = Pass.getText().toString();

                String registeredUsername = preferences.getString("username","");
                String registeredPassword = preferences.getString("password","");




                //Verificamos que hayan ingresado el usuario y contraseña
                if (inputUser.isEmpty() || inputPass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debes de ingresar tu usuario y contraseña", Toast.LENGTH_SHORT).show();
                } else {

                    validar = validate(inputUser, inputPass);

                    //Si son correctos
                    if (inputUser.equals(registeredUsername) && inputPass.equals(registeredPassword)) {
                        Toast.makeText(MainActivity.this, "Has ingresado correctamente", Toast.LENGTH_SHORT).show();

                        //Llamar a la activity AllowLocation
                        Intent intent = new Intent(MainActivity.this, AllowLocation.class);
                        startActivity(intent);
                    } else {

                        Toast.makeText(MainActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();

                    }

                }


            }
        });

        b_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llamar a la activity Sign Up
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);

            }
        });
    }


    private boolean validate(String inputUser, String inputPass) {



        if (inputUser.equals(Username) && inputPass.equals(Password)) {
            return true;
        } else {

            return false;
        }

    }

}


