package com.example.mynewsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Inicializar variables
    EditText User;
    EditText Pass;
    Button Blogin;


    private String Username = "Michel";
    private String Password = "12345678";

    boolean validar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asignar variables
        User = (EditText) findViewById(R.id.ETuser);
        Pass = (EditText) findViewById(R.id.ETpass);
        Blogin = (Button) findViewById(R.id.buttonLogin);


        //Funcion Boton Login
        Blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputUser = User.getText().toString();
                String inputPass = Pass.getText().toString();

                //Verificamos que hayan ingresado el usuario y contraseña
                if (inputUser.isEmpty() || inputPass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debes de ingresar tu usuario y contraseña", Toast.LENGTH_SHORT).show();
                } else {

                    validar = validate(inputUser, inputPass);

                    //Si es false
                    if (!validar) {
                        Toast.makeText(MainActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Has ingresado correctamente", Toast.LENGTH_SHORT).show();

                        //Llamar a la activity AllowLocation
                        Intent intent = new Intent(MainActivity.this, AllowLocation.class);
                        startActivity(intent);

                    }

                }


            }
        });
    }


    private boolean validate(String User, String Pass) {

        if (User.equals(Username) && Pass.equals(Password)) {
            return true;
        } else {

            return false;
        }

    }
}


