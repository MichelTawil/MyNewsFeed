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
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AllowLocation extends AppCompatActivity {

    //Inicializar variables
    Button btLocation;
    TextView textView1, textView2, textView3, textView4, textView5, tvUser;
    Button Blogout;
    Button btDeny;
    SharedPreferences pref;
    SharedPreferences.Editor Edi;


        FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow_location);

        //Asignar variables
        Blogout = findViewById(R.id.buttonLogout);
        btLocation = findViewById(R.id.bt_location);
        textView1 = findViewById(R.id.text_view1);
        textView2 = findViewById(R.id.text_view2);
        textView3 = findViewById(R.id.text_view3);
        textView4 = findViewById(R.id.text_view4);
        textView5 = findViewById(R.id.text_view5);
        btDeny = findViewById(R.id.bt_deny);
        tvUser = findViewById(R.id.tv_user);

        final SharedPreferences pref = getSharedPreferences("Deviceinfo", MODE_PRIVATE);

        //Obtenemos los datos de Sharedpreferences y ponemos en nombre en el textview tv_user
        SharedPreferences preferences = getSharedPreferences("Userinfo", MODE_PRIVATE);
        String registeredUsername = preferences.getString("username","");

        tvUser.setText(registeredUsername);

        //Inicializamos fusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Revisar permisos
                if (ActivityCompat.checkSelfPermission(AllowLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    ////Cuando obtenemos el permiso
                    getLocation();
                    //Llamar a la activity Tabbed_Activity y enviar variables
                    Intent intent_tabbed = new Intent(AllowLocation.this, Tabbed_Activity.class);
                    startActivity(intent_tabbed);
                } else {
                    //Cuando no obtenemos el permiso
                    ActivityCompat.requestPermissions(AllowLocation.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                    //Llamar a la activity Tabbed_Activity
                    Intent intent_cant = new Intent(AllowLocation.this, CantContinue.class);
                    startActivity(intent_cant);

                }
            }
        });

        btDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //Cuando no obtenemos el permiso
                //Llamar a la activity CantContinue
                Intent intent = new Intent(AllowLocation.this, CantContinue.class);
                startActivity(intent);
                }
        });

        Blogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Llamar a la activity main
                Intent intent = new Intent(AllowLocation.this, MainActivity.class);
                startActivity(intent);

            }
        });

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //Inicializar Ubicacion
                Location location = task.getResult();
                if(location != null){
                    try {
                        //Inicializar geoCoder
                        Geocoder geocoder = new Geocoder(AllowLocation.this, Locale.getDefault());
                        //Inicializar lista de direcciones
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);

                        //Poner latitud en textview
                        textView1.setText("Latitud = "+ addresses.get(0).getLatitude()
                        );
                        //Poner longitud en textview
                        textView2.setText("Longitud = "+ addresses.get(0).getLongitude()
                        );
                        //Poner el nombre del pais en textview
                        textView3.setText("Pais = "+ addresses.get(0).getCountryName()
                        );
                        //Poner localidad en textview
                        textView4.setText("Localidad = "+ addresses.get(0).getLocality()
                        );
                        //Poner direccion en textview
                        textView5.setText("Direccion = "+ addresses.get(0).getAddressLine(0)
                        );


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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