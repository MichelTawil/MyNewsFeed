package com.example.mynewsfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Ubicacion_Activity1 extends AppCompatActivity {

    //Inicializar variables
    TextView tv_usuario, tv_latitud, tv_longitud, tv_pais, tv_municipio, tv_direccion;
    Button button_home, button_back;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_1);


        //Asignar variables
        tv_usuario = findViewById(R.id.tv_usuario);
        tv_latitud = findViewById(R.id.tv_latitud);
        tv_longitud = findViewById(R.id.tv_longitud);
        tv_pais = findViewById(R.id.tv_pais);
        tv_municipio = findViewById(R.id.tv_municipio);
        tv_direccion = findViewById(R.id.tv_direccion);
        button_home = findViewById(R.id.button_home);
        button_back = findViewById(R.id.button_back);


        //Obtenemos los datos de Sharedpreferences y ponemos en nombre en el textview tv_user
        SharedPreferences preferences = getSharedPreferences("Userinfo", MODE_PRIVATE);
        String registeredUsername = preferences.getString("username", "");
        tv_usuario.setText("Usuario: " + registeredUsername);

        //Inicializamos fusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //Revisar permisos
        if (ActivityCompat.checkSelfPermission(Ubicacion_Activity1.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    //Inicializar Ubicacion
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            //Inicializar geoCoder
                            Geocoder geocoder = new Geocoder(Ubicacion_Activity1.this, Locale.getDefault());
                            //Inicializar lista de direcciones
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                            //Poner latitud en textview
                            tv_latitud.setText("Latitud = " + addresses.get(0).getLatitude()
                            );
                            //Poner longitud en textview
                            tv_longitud.setText("Longitud = " + addresses.get(0).getLongitude()
                            );
                            //Poner el nombre del pais en textview
                            tv_pais.setText("Pais = " + addresses.get(0).getCountryName()
                            );
                            //Poner localidad en textview
                            tv_municipio.setText("Municipio = " + addresses.get(0).getLocality()
                            );
                            //Poner direccion en textview
                            tv_direccion.setText("Direccion = " + addresses.get(0).getAddressLine(0)
                            );


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llamar a la activity AllowLocation
                Intent intent = new Intent(Ubicacion_Activity1.this, MainActivity.class);
                startActivity(intent);

            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llamar a la activity AllowLocation
                Intent intent = new Intent(Ubicacion_Activity1.this, Tabbed_Activity.class);
                startActivity(intent);

            }
        });

    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

    }
}