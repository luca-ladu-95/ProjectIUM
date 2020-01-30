package com.example.projectium;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Partecipa_partita.EVENTO_DA_PASSARE;
import static com.example.projectium.Registrazione.PERSON_DA_PASSARE2;

public class Prenota_da_Partecipa_partita extends AppCompatActivity {

    Persona persona;
    Prenotazione prenotazione;
    TextView nomeEvento, data, nomeCreatore, ora, numeroGiocatori, prezzo, descrizione ;
    Button prenotati,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenota_da__partecipa_partita);

        /*Associo le text e i bottoni*/

        nomeEvento=findViewById(R.id.prenota_partita_nome);
        data=findViewById(R.id.prenota_partita_data);
        nomeCreatore=findViewById(R.id.prenota_partita_nome_capo);
        ora=findViewById(R.id.prenota_partita_ora);
        numeroGiocatori=findViewById(R.id.prenota_partita_numero_G);
        prezzo=findViewById(R.id.prenota_partita_prezzo);
        descrizione=findViewById(R.id.prenota_partita_descrizione);
        prenotati=findViewById(R.id.button_prenota_partita);
        home=findViewById(R.id.button_return_prenota_partita);




        /*Richiamo l'intent per recuperare i dati dell'utente*/
        Intent intent = getIntent();

        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE_2);

        Serializable obj2 = intent.getSerializableExtra(EVENTO_DA_PASSARE);


        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }

        if (obj2 instanceof Prenotazione) {
            prenotazione = (Prenotazione) obj;
        } else {
            prenotazione = new Prenotazione();
        }

        nomeEvento.setText(prenotazione.getNome_evento());
        data.setText(prenotazione.getData_evento());
        nomeCreatore.setText(prenotazione.getCreatore().getNome());
        ora.setText(prenotazione.getOra_evento());
        numeroGiocatori.setText(prenotazione.getNum_giocatori());
        prezzo.setText(prenotazione.getCampo().getPrezzo_a_persona());
        descrizione.setText(prenotazione.getDescrizione());

        String message = "Sei sicuro di voler prenotare in data " + prenotazione.getData_evento() + (" per" +
                "le ore "+prenotazione.getOra_evento()+" la partita: "+prenotazione.getNome_evento()+"?");


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Utilizzo person da passare perche nell altra activity usa quella*/
                Intent showCercaPartita = new Intent(Prenota_da_Partecipa_partita.this, Partecipa_partita.class);
                //Inserisco la persona dentro l'intent
                showCercaPartita.putExtra(PERSON_DA_PASSARE, persona);
                //richiamo activity
                startActivity(showCercaPartita);
                finish();
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
                        persona = null;
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
}
