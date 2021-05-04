package com.example.mynewsfeed;

import android.content.Intent;
import android.os.Bundle;

import com.example.mynewsfeed.Model.Articles;
import com.example.mynewsfeed.Model.Headlines;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewsfeed.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tabbed_Activity extends AppCompatActivity {

    TextView tvPais, Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

//        tvPais = findViewById(R.id.tv_pais);
//        Title = findViewById(R.id.title);
//
//        String Pais = getIntent().getStringExtra("Pais");
//        Title.setText(Pais);
//...
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Tabbed_Activity.this, "Obteniendo ubicacion del dispositivo...", Toast.LENGTH_SHORT).show();

                //Lamamos acticity Ubicacion_Activity
                //Llamar a la activity AllowLocation
                Intent intent = new Intent(Tabbed_Activity.this, Ubicacion_Activity1.class);
                startActivity(intent);
            }
        });
    }
}

