package com.example.projectium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import static com.example.projectium.Calendario.BOOLEANO_CALENDARIO;
import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Informazioni_campo_da_mappa.BOOLEANO;
import static com.example.projectium.Login.PERSON_DA_PASSARE;

import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Mappa.NOME_CAMPO_DA_PASSARE;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;

public class Prenota_da_Partecipa_partita extends AppCompatActivity {

    Persona persona;
    Prenotazione prenotazione;
    TextView nomeEvento, data, nomeCreatore, ora, numeroGiocatori, prezzo, descrizione,campo ;
    Button prenotati,home;
    Boolean flag_prenotazione;
    Boolean flag_calendario;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenota_da__partecipa_partita);

        //Setta il colore della status bar
        Window window = Prenota_da_Partecipa_partita.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Prenota_da_Partecipa_partita.this, R.color.colorPrimaryDarkStatusBar));
        // infe colore della status bar

        /*Associo le text e i bottoni*/

        nomeEvento = findViewById(R.id.riepilogo_partita_nome);
        data = findViewById(R.id.prenota_partita_data);
        nomeCreatore = findViewById(R.id.prenota_partita_nome_capo);
        ora = findViewById(R.id.prenota_partita_ora);
        numeroGiocatori = findViewById(R.id.prenota_partita_numero_G);
        prezzo = findViewById(R.id.prenota_partita_prezzo);
        descrizione = findViewById(R.id.prenota_partita_descrizione);
        prenotati = findViewById(R.id.button_prenota_partita);
        home = findViewById(R.id.button_return_prenota_partita);
        campo = findViewById(R.id.prenota_partita_nome_campo);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE_2);
        Serializable obj2 = intent.getSerializableExtra(PRENOTAZIONE);
        Serializable obj3 = intent.getSerializableExtra(BOOLEANO);
        Serializable obj4 = intent.getSerializableExtra(BOOLEANO_CALENDARIO);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }

        if (obj2 instanceof Prenotazione) {
            prenotazione = (Prenotazione) obj2;
        } else {
            prenotazione = new Prenotazione();
        }

        if(obj3 instanceof  Boolean){
            flag_prenotazione = (Boolean)obj3;
        }else {
            flag_prenotazione=false;
        }

        if(obj4 instanceof Boolean){
            flag_calendario = (Boolean)obj4;
        }else
            flag_calendario=false;




        Integer numG = prenotazione.getNum_giocatori();

        nomeEvento.setText(prenotazione.getNome_evento());
        data.setText(prenotazione.getData_evento());
        nomeCreatore.setText(prenotazione.getCreatore().getNome());
        ora.setText(prenotazione.getOra_evento());
        numeroGiocatori.setText(numG.toString());
        prezzo.setText(prenotazione.getCampo().getPrezzo_a_persona());
        descrizione.setText(prenotazione.getDescrizione());
        campo.setText(prenotazione.getCampo().getNome());

        final String message = "Sei sicuro di voler prenotare in data " + prenotazione.getData_evento() + (" per " +
                "le ore "+prenotazione.getOra_evento()+" la partita: "+prenotazione.getNome_evento()+"?");

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Utilizzo person da passare perche nell altra activity usa quella*/

               /* if(!flag_prenotazione){
                Intent showCercaPartita = new Intent(Prenota_da_Partecipa_partita.this, Partecipa_partita.class);
                //Inserisco la persona dentro l'intent
                showCercaPartita.putExtra(PERSON_DA_PASSARE, persona);
                //richiamo activity
                startActivity(showCercaPartita);
                finish();
                }else{
                    Intent showCercaPartita = new Intent(Prenota_da_Partecipa_partita.this, Partecipa_partita.class);
                    //Inserisco la persona dentro l'intent
                    showCercaPartita.putExtra(PERSON_DA_PASSARE, persona);
                    showCercaPartita.putExtra(BOOLEANO,flag_prenotazione);
                    showCercaPartita.putExtra(NOME_CAMPO_DA_PASSARE,prenotazione.getCampo().getNome());
                    //richiamo activity
                    startActivity(showCercaPartita);
                    finish();
                }

                */



               if(flag_prenotazione){
                   Intent showCercaPartita = new Intent(Prenota_da_Partecipa_partita.this, Partecipa_partita.class);
                   //Inserisco la persona dentro l'intent
                   showCercaPartita.putExtra(PERSON_DA_PASSARE, persona);
                   showCercaPartita.putExtra(BOOLEANO,flag_prenotazione);
                   showCercaPartita.putExtra(NOME_CAMPO_DA_PASSARE,prenotazione.getCampo().getNome());
                   //richiamo activity
                   startActivity(showCercaPartita);
                   finish();

               }else{
                   if(!flag_prenotazione && !flag_calendario){

                       Intent showCercaPartita = new Intent(Prenota_da_Partecipa_partita.this, Partecipa_partita.class);
                       //Inserisco la persona dentro l'intent
                       showCercaPartita.putExtra(PERSON_DA_PASSARE, persona);
                       //richiamo activity
                       startActivity(showCercaPartita);
                       finish();
                   }else{

                       if(flag_calendario){

                           Intent showCercaPartita = new Intent(Prenota_da_Partecipa_partita.this, Calendario.class);
                           //Inserisco la persona dentro l'intent
                           showCercaPartita.putExtra(PERSON_DA_PASSARE, persona);
                           //richiamo activity
                           startActivity(showCercaPartita);
                           finish();
                       }
                   }
               }

            }

        });

        prenotati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Apre il messaggio di conferma uscita
                AlertDialog diaBox = AskOption(message);
                diaBox.show();
            }
        });
    }

    private AlertDialog AskOption(String message)
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // finestra di conferma eliminazione
                .setTitle("Riepilogo prenotaziane")
                .setMessage(message)
                .setPositiveButton("Conferma", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Aggiungo la persona alla prenotazione e lo riporto alla home
                        PrenotazioneFactory.getInstance().aggiungiPartecipante(listaPrenotazioni,prenotazione.getId(),persona);
                        Context context = getApplicationContext();

                        CharSequence text = "Prenotazione effettuata con successo ";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Intent showLogin = new Intent(Prenota_da_Partecipa_partita.this, Home.class);
                        showLogin.putExtra(PERSON_DA_PASSARE, persona);
                        startActivity(showLogin);
                        finish();
                        dialog.dismiss();
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

        /*
        if(!flag_prenotazione){
            Intent showCercaPartita = new Intent(Prenota_da_Partecipa_partita.this, Partecipa_partita.class);
            //Inserisco la persona dentro l'intent
            showCercaPartita.putExtra(PERSON_DA_PASSARE, persona);
            //richiamo activity
            startActivity(showCercaPartita);
            finish();
        }else{
            Intent showCercaPartita = new Intent(Prenota_da_Partecipa_partita.this, Partecipa_partita.class);
            //Inserisco la persona dentro l'intent
            showCercaPartita.putExtra(PERSON_DA_PASSARE, persona);
            showCercaPartita.putExtra(BOOLEANO,flag_prenotazione);
            showCercaPartita.putExtra(NOME_CAMPO_DA_PASSARE,prenotazione.getCampo().getNome());
            //richiamo activity
            startActivity(showCercaPartita);
            finish();
        }*/


        if(flag_prenotazione){
            Intent showCercaPartita = new Intent(Prenota_da_Partecipa_partita.this, Partecipa_partita.class);
            //Inserisco la persona dentro l'intent
            showCercaPartita.putExtra(PERSON_DA_PASSARE, persona);
            showCercaPartita.putExtra(BOOLEANO,flag_prenotazione);
            showCercaPartita.putExtra(NOME_CAMPO_DA_PASSARE,prenotazione.getCampo().getNome());
            //richiamo activity
            startActivity(showCercaPartita);
            finish();

        }else{
            if(!flag_prenotazione && !flag_calendario){

                Intent showCercaPartita = new Intent(Prenota_da_Partecipa_partita.this, Partecipa_partita.class);
                //Inserisco la persona dentro l'intent
                showCercaPartita.putExtra(PERSON_DA_PASSARE, persona);
                //richiamo activity
                startActivity(showCercaPartita);
                finish();
            }else{

                if(flag_calendario){

                    Intent showCercaPartita = new Intent(Prenota_da_Partecipa_partita.this, Calendario.class);
                    //Inserisco la persona dentro l'intent
                    showCercaPartita.putExtra(PERSON_DA_PASSARE, persona);
                    //richiamo activity
                    startActivity(showCercaPartita);
                    finish();
                }
            }
        }
    }
}
