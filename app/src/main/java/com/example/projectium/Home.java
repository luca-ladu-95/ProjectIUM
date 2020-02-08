package com.example.projectium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;

import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.utenti;

public class Home extends AppCompatActivity   implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;

    public Persona persona;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    Button nuovaPartita,cercaPartita,mappaCampi,prenotazioneEffettuate;
    TextView nomeCognome,nomeNavigationBar;

    public static final String PERSON_DA_PASSARE_2 = "package com.example.projectium";

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

        /*Quando clicchi su un icona del menu a tendina bisogna aprire un fragment*/
        navigationView=findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);


        /*Parte del menu a scorrimento */
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);

        drawerLayout.addDrawerListener(toggle);

        toggle.setDrawerIndicatorEnabled(true);

        toggle.syncState();
         /*Richiamo l'intent per recuperare i dati dell'utente*/
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }

        nomeCognome.setText("Benvenuto, "+persona.getNome());



        //Parte del codice quando clicco nuova partita
        nuovaPartita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showNuovaPartita = new Intent(Home.this, NuovaPartita.class);
                //Inserisco la persona dentro l'intent
                showNuovaPartita.putExtra(PERSON_DA_PASSARE_2, persona);
                //richiamo activity
                startActivity(showNuovaPartita);
                finish();

            }
        });


        mappaCampi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showMappa = new Intent(Home.this, Mappa.class);
                //Inserisco la persona dentro l'intent
                showMappa.putExtra(PERSON_DA_PASSARE_2, persona);
                //richiamo activity
                startActivity(showMappa);
                finish();
                }
        });


        prenotazioneEffettuate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showNuovaPartita = new Intent(Home.this, PrenotazioniEffettuate.class);
                //Inserisco la persona dentro l'intent
                showNuovaPartita.putExtra(PERSON_DA_PASSARE_2, persona);
                //richiamo activity
                startActivity(showNuovaPartita);
                finish();

            }
        });



        cercaPartita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showCercaPartita = new Intent(Home.this, Partecipa_partita.class);
                //Inserisco la persona dentro l'intent
                showCercaPartita.putExtra(PERSON_DA_PASSARE_2, persona);
                //richiamo activity
                startActivity(showCercaPartita);
                finish();
            }
        });







    }






    @Override
    /*Questa è la funzione dei tasti del menu a tendina , funziona come un onclicklistener
    * nello switch metti gli ID dei cosi del menu*/
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:

                Intent showHOME = new Intent(Home.this, Profilo.class);
                //Inserisco la persona dentro l'intent
                showHOME.putExtra(PERSON_DA_PASSARE_2, persona);
                //richiamo activity
                startActivity(showHOME);
                finish();


                break;

            case R.id.nav_Mappa:
                Intent showMappa = new Intent(Home.this, Mappa.class);
                //Inserisco la persona dentro l'intent
                showMappa.putExtra(PERSON_DA_PASSARE_2, persona);
                //richiamo activity
                startActivity(showMappa);
                finish();
                break;



            case  R.id.nav_Impostazioni:
                break;

            case R.id.nav_logout:

                //Apre il messaggio di conferma uscita
                AlertDialog diaBox = AskOption();
                diaBox.show();

        }

        menuItem.setChecked(true);

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    /*Questa funzione apre finestre di dialogo, di solito è usata per le allerte , doppia conferma*/
    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // finestra di conferma eliminazione
                .setTitle("Esci")
                .setMessage("Sei sicuro di voler uscire?")


                .setPositiveButton("esci", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        persona = null;
                        Intent showLogin = new Intent(Home.this, Login.class);
                        showLogin.putExtra(PERSON_DA_PASSARE, persona);
                        startActivity(showLogin);
                        dialog.dismiss();
                        finish();

                    }

                })
                .setNegativeButton("annulla", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();



        return myQuittingDialogBox;
    }


    @Override
    public void onBackPressed() {

        //Apre il messaggio di conferma uscita
        AlertDialog diaBox = AskOption();
        diaBox.show();

    }
}
