package com.example.mynewsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    Button bRegister;
    EditText etname, etemail, etpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        bRegister = (Button) findViewById(R.id.b_register);
        etname = (EditText) findViewById(R.id.et_name);
        etemail = (EditText) findViewById(R.id.et_email);
        etpassword = (EditText) findViewById(R.id.et_password);

        //SharedPreferences
        final SharedPreferences preferences = getSharedPreferences("Userinfo", MODE_PRIVATE);

        //Funcion boton Register
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Almacenar en shared preferences

                String newUser = etname.getText().toString();
                String newEmail = etemail.getText().toString();
                String newPassword = etpassword.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();

                if(newUser.length()>1 && newEmail.length()>1 && newPassword.length()>1) {
                    editor.putString("username", newUser);
                    editor.putString("email", newEmail);
                    editor.putString("password", newPassword);
                    editor.apply();

                    Toast.makeText(Signup.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Signup.this, "Debes de ingresar todos los datos", Toast.LENGTH_SHORT).show();
                }
//

                //Llamar a la activity Main Activity
                Intent intent = new Intent(Signup.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}