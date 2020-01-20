package com.example.projectium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;

public class Home extends AppCompatActivity   implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;

    Persona persona;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    Button nuovaPartita,cercaPartita,mappaCampi,prenotazioneEffettuate;
    TextView nomeCognome,nomeNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nuovaPartita=findViewById(R.id.buttonNewPartita);
        cercaPartita=findViewById(R.id.buttonFindPartita);
        mappaCampi=findViewById(R.id.buttonMappaCampi);
        prenotazioneEffettuate=findViewById(R.id.buttonPrenotazioni);
        nomeCognome=findViewById(R.id.homeNome);
        nomeNavigationBar=findViewById(R.id.nomeNavigationBar);
        drawerLayout=findViewById(R.id.drawer);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);

        drawerLayout.addDrawerListener(toggle);

        toggle.setDrawerIndicatorEnabled(true);

        toggle.syncState();

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.PERSON_DA_PASSARE);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }

        nomeCognome.setText("Benvenuto, "+persona.getNome());











    }






    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;


            case  R.id.nav_Impostazioni:
                break;

        }

        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
