package com.example.projectium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Informazioni_campo_da_mappa.BOOLEANO;
import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Mappa.NOME_CAMPO_DA_PASSARE;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;


public class Partecipa_partita extends AppCompatActivity {


    Button indietro;
    public Persona p1;
    Persona persona;
    TextView nessunaPartita;
    ArrayList<Prenotazione> prenotazioniInCorso;
    String data_evento,current_data;
    Boolean flag_prenotazione;
     String nomeCampo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partecipa_partita);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lineraDinamicCercaPartita);

        nessunaPartita=findViewById(R.id.testo_nessuna_partita);

        /*Richiamo l'intent per recuperare i dati dell'utente*/
        final Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE);
        Serializable obj2 = intent.getSerializableExtra(NOME_CAMPO_DA_PASSARE);
        Serializable obj3 = intent.getSerializableExtra(BOOLEANO);

        if (obj2 instanceof String) {
            nomeCampo = (String) obj2;
        } else {
            nomeCampo = null;
        }

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }

        if(obj3 instanceof  Boolean){
            flag_prenotazione = (Boolean)obj3;
        }else {
            flag_prenotazione=false;
        }

        p1=persona;

        indietro=findViewById(R.id.button_return_partecipa_partita);

        /*Prendo le partite NON ANNULLATE*/
        if(nomeCampo == null || nomeCampo.isEmpty()) {
            prenotazioniInCorso = PrenotazioneFactory.getInstance().getPrenotazioniInCorso(listaPrenotazioni, persona);
        }else{

          prenotazioniInCorso = PrenotazioneFactory.getInstance().getPrenotazioniPerCampo(listaPrenotazioni, persona,nomeCampo);
        }
        int i =0;





        /*Creo dinamicamente un bottone per ogni evento  ed un click on view*/

        Date currentTime,dataEvento;
        boolean flagdata = false;



        if(prenotazioniInCorso!= null && prenotazioniInCorso.size() > 0) {
            for (i = 0; i < prenotazioniInCorso.size(); i++) {

                currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                data_evento=prenotazioniInCorso.get(i).getData_evento();
                current_data = format.format(currentTime);

                if(!data_evento.equals(current_data)){
                try {
                    dataEvento = format.parse(prenotazioniInCorso.get(i).getData_evento());
                    flagdata = dataEvento.after(currentTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                }else{
                    flagdata=true;
                }


                if (flagdata) {

                    Space space = new Space(this);
                    space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 20));
                    Button bottone = new Button(this);
                    bottone.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    bottone.setGravity(Gravity.CENTER);
                    bottone.setTextColor(getResources().getColor(R.color.bianco));
                    bottone.setBackgroundResource(R.drawable.textbox);
                    bottone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
                    bottone.setId(i);
                    bottone.setText(prenotazioniInCorso.get(i).getNome_evento());
                    String debug = prenotazioniInCorso.get(i).getNome_evento();
                    linearLayout.addView(bottone);
                    linearLayout.addView(space);
                    final Prenotazione p = prenotazioniInCorso.get(i);

                    bottone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            Intent showPrenota_partita = new Intent(Partecipa_partita.this, Prenota_da_Partecipa_partita.class);
                            //Inserisco la persona dentro l'intent


                            showPrenota_partita.putExtra(PERSON_DA_PASSARE_2, persona);
                            showPrenota_partita.putExtra(PRENOTAZIONE, p);
                            showPrenota_partita.putExtra(BOOLEANO,flag_prenotazione);


                            startActivity(showPrenota_partita);
                            finish();
                        }
                    });


                }
            }
        }else{

            String s = "Nessuna partita disponibile";
            nessunaPartita.setText(s);


        }


      indietro.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              //Inserisco la persona dentro l'intent
              //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
              if(!flag_prenotazione){
                  Intent showHOME = new Intent(Partecipa_partita.this, Home.class);
              showHOME.putExtra(PERSON_DA_PASSARE, persona);
              //richiamo activity
              startActivity(showHOME);
              finish();

              }else {

                  Intent showHOME = new Intent(Partecipa_partita.this, Informazioni_campo_da_mappa.class);
                  showHOME.putExtra(PERSON_DA_PASSARE, persona);
                showHOME.putExtra(NOME_CAMPO_DA_PASSARE,nomeCampo);
                  //richiamo activity
                  startActivity(showHOME);
                  finish();
              }
          }
      });

    }


    public void onBackPressed(){
        if(!flag_prenotazione){
            Intent showHOME = new Intent(Partecipa_partita.this, Home.class);
            showHOME.putExtra(PERSON_DA_PASSARE, persona);
            //richiamo activity
            startActivity(showHOME);
            finish();

        }else {

            Intent showHOME = new Intent(Partecipa_partita.this, Informazioni_campo_da_mappa.class);
            showHOME.putExtra(PERSON_DA_PASSARE, persona);
            showHOME.putExtra(NOME_CAMPO_DA_PASSARE,nomeCampo);
            //richiamo activity
            startActivity(showHOME);
            finish();
        }
    }

}
