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

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;

public class Riepilogo_partita extends AppCompatActivity {


    Persona persona;
    Prenotazione prenotazione;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riepilogo_partita);

        //Setta il colore della status bar
        Window window = Riepilogo_partita.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Riepilogo_partita.this, R.color.colorPrimaryDark));
        // infe colore della status bar


        TextView nomeEvento, data, nomeCreatore, ora, numeroGiocatori, prezzo, descrizione,campo ;
        Button disdici,indietro;


        /*Associo le text e i bottoni*/

        nomeEvento=findViewById(R.id.riepilogo_partita_nome_2);
        data=findViewById(R.id.riepilogo_partita_data);
        nomeCreatore=findViewById(R.id.riepilogo_partita_nome_capo);
        ora=findViewById(R.id.rieplogo_partita_ora);
        numeroGiocatori=findViewById(R.id.riepilogo_partita_numero_G);
        prezzo=findViewById(R.id.riepilogo_partita_prezzo);
        descrizione=findViewById(R.id.riepilogo_partita_descrizione);
        disdici=findViewById(R.id.button_disdici_partita_riepilogo);
        indietro=findViewById(R.id.button_return_riepilogo_prenotazioni);
        campo = findViewById(R.id.riepilogo_partita_nome_campo);

        Intent intent = getIntent();

        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE_2);

        Serializable obj2 = intent.getSerializableExtra(PRENOTAZIONE);

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

        Integer numG = prenotazione.getNum_giocatori();

        nomeEvento.setText(prenotazione.getNome_evento());
        data.setText(prenotazione.getData_evento());
        nomeCreatore.setText(prenotazione.getCreatore().getNome());
        ora.setText(prenotazione.getOra_evento());
        numeroGiocatori.setText(numG.toString());
        prezzo.setText(prenotazione.getCampo().getPrezzo_a_persona());
        descrizione.setText(prenotazione.getDescrizione());
        campo.setText(prenotazione.getCampo().getNome());


        final String message = "Sei sicuro di voler disdire la prenotazione del " + prenotazione.getData_evento() + (" per " +
                "le ore "+prenotazione.getOra_evento()+" la partita: "+prenotazione.getNome_evento()+"?");



        disdici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Apre il messaggio di conferma uscita
                AlertDialog diaBox = AskOption(message);
                diaBox.show();
            }
        });

        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!persona.isGestore()) {
                    Intent showLogin = new Intent(Riepilogo_partita.this, PrenotazioniEffettuate.class);
                    showLogin.putExtra(PERSON_DA_PASSARE_2, persona);
                    startActivity(showLogin);
                    finish();
                }else{

                    Intent showLogin = new Intent(Riepilogo_partita.this, Calendario.class);
                    showLogin.putExtra(PERSON_DA_PASSARE_2, persona);
                    startActivity(showLogin);
                    finish();
                }
            }
        });
    }


    private AlertDialog AskOption(String message)
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // finestra di conferma eliminazione
                .setTitle("Disdici Evento")

                .setMessage(message)


                .setPositiveButton("Disdici", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        /*Elimino evento*/
                        if(persona.isGestore()){
                            PrenotazioneFactory.getInstance().elimnaPrenotazioneGestore(listaPrenotazioni,prenotazione.getId());
                        }else {
                            PrenotazioneFactory.getInstance().eliminaPrenotazioneOpuureDisdiciPrenotazione(listaPrenotazioni, prenotazione.getId(), persona);
                        }
                        Context context = getApplicationContext();
                        CharSequence text = "Prenotazione disdetta con successo";
                        int duration = Toast.LENGTH_SHORT;



                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        if(!persona.isGestore()){
                        Intent showLogin = new Intent(Riepilogo_partita.this, PrenotazioniEffettuate.class);
                        showLogin.putExtra(PERSON_DA_PASSARE_2, persona);
                        startActivity(showLogin);
                        finish();
                        dialog.dismiss();}else{

                            Intent showLogin = new Intent(Riepilogo_partita.this, Gestore.class);
                            showLogin.putExtra(PERSON_DA_PASSARE_2, persona);
                            startActivity(showLogin);
                            finish();
                        }
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
        if(!persona.isGestore()) {
            Intent showLogin = new Intent(Riepilogo_partita.this, PrenotazioniEffettuate.class);
            showLogin.putExtra(PERSON_DA_PASSARE_2, persona);
            startActivity(showLogin);
            finish();
        }else{

            Intent showLogin = new Intent(Riepilogo_partita.this, Calendario.class);
            showLogin.putExtra(PERSON_DA_PASSARE_2, persona);
            startActivity(showLogin);
            finish();
        }
    }
}
