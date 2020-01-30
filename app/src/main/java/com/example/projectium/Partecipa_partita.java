package com.example.projectium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaPrenotazioni;


public class Partecipa_partita extends AppCompatActivity {

    public static final String EVENTO_DA_PASSARE = "package com.example.projectium";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Persona persona;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partecipa_partita);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lineraDinamicCercaPartita);

        /*Richiamo l'intent per recuperare i dati dell'utente*/
        final Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }





        /*Prendo le partite NON ANNULLATE*/
         ArrayList<Prenotazione> prenotazioniInCorso = PrenotazioneFactory.getInstance().getPrenotazioniInCorso(listaPrenotazioni,persona);

        int i =0;





        /*Creo dinamicamente un bottone per ogni evento  ed un click on view*/

      for ( i = 0 ; i < prenotazioniInCorso.size(); i++){


          Button bottone = new Button(this);
          bottone.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
          bottone.setTextColor(getResources().getColor(R.color.nero));
          bottone.setGravity(Gravity.CENTER);
          bottone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
          bottone.setId(i);
          bottone.setText(listaPrenotazioni.get(i).getNome_evento());
          linearLayout.addView(bottone);
          final  Prenotazione p = prenotazioniInCorso.get(i);

          bottone.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {


                  Intent showNuovaPartita = new Intent(Partecipa_partita.this, Prenota_da_Partecipa_partita.class);
                  //Inserisco la persona dentro l'intent

                  Bundle extras = new Bundle();

                  extras.putSerializable(PERSON_DA_PASSARE_2,persona);
                  extras.putSerializable(EVENTO_DA_PASSARE,p);


                  showNuovaPartita.putExtra("KEY_BUNDLE_VALUE", extras);
                  //richiamo activity
                  startActivity(showNuovaPartita);
                  finish();
              }
          });

      }








    }
}
