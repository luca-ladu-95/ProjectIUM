package com.example.projectium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;

public class PrenotazioniEffettuate extends AppCompatActivity {

    Persona persona;
    ArrayList<Prenotazione> prenotazioni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazioni_effettuate);

        /*Recupero i lay dove stampare dinamicamente i bottoni degli eventi */
        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.layout_prenotazioni_in_corso);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.layout_prenotazioni_annullate);
        int i=0;


        /*Richiamo l'intent per recuperare i dati dell'utente*/
        final Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE_2);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }


        /*Recupero le partite in corso e le partite annullate lista prenotazioni sono quelle di default
        * poi dovra essere sostituito con un altro */
        prenotazioni = PrenotazioneFactory.getInstance().getPrenotazioneUtente(listaPrenotazioni,persona);

        /*Creo dinamicamente un bottone per ogni evento  ed un click on view*/

        if(prenotazioni!= null && prenotazioni.size()>0) {
            for (i = 0; i < prenotazioni.size(); i++) {


                Button bottone = new Button(this);
                bottone.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                bottone.setTextColor(getResources().getColor(R.color.nero));
                bottone.setGravity(Gravity.CENTER);
                bottone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
                bottone.setId(i);
                bottone.setText(prenotazioni.get(i).getNome_evento());

                if(!prenotazioni.get(i).isAnnullata()){

                    linearLayout1.addView(bottone);
                }else{
                    linearLayout2.addView(bottone);

                }


                final Prenotazione p = prenotazioni.get(i);

                bottone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                });


            }
        }else{



        }




    }
}
