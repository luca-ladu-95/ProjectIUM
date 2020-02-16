package com.example.projectium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.projectium.Calendario.BOOLEANO_CALENDARIO;
import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Informazioni_campo_da_mappa.BOOLEANO;
import static com.example.projectium.Login.PERSON_DA_PASSARE;

import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Mappa.NOME_CAMPO_DA_PASSARE;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;

public class Valutazioni_activity extends AppCompatActivity {
    Persona persona;
    Prenotazione prenotazione;
    Date currentTime,dataEvento;
    ArrayList<Prenotazione> prenotazioni;
    boolean flagdata;
    TextView nessuaValutazione;
    ArrayList<Button> in_corso,annullate,da_valutare;
    Button indietro;
    Button bottone;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valutazioni_activity);

        //Setta il colore della status bar
        Window window = Valutazioni_activity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Valutazioni_activity.this, R.color.colorPrimaryDark));
        // infe colore della status bar

        //Imposto un font da utilizzare sui bottoni generati dinamicamente
        Typeface typeface = ResourcesCompat.getFont(getBaseContext(), R.font.baloo);

        da_valutare = new ArrayList<>();

        int i =0;

        String currentHours = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.layout_prenotazioni_da_valutare);


        nessuaValutazione=findViewById(R.id.testo_nessuna_valutazione);
        indietro=findViewById(R.id.button_return_prenotazioni_effettuate);

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

            //Controllo prenotazioni annullate fa schifo questa funzione, era gia fatta e la ho usata
            // in pratica controllo le lunghezze delle due liste , se sono uguli vuol dire che sono tutte annullate
           /* if(PrenotazioneFactory.getInstance().getPrenotazioniAnnullate(prenotazioni).size()==prenotazioni.size()){
                nessunaP.setText("Nessuna prenotazione presente");

            }
*/
            for (i = 0; i < prenotazioni.size(); i++) {

                Space space = new Space(this);
                space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,20));
                bottone = new Button(this);
                bottone.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                bottone.setTextColor(getResources().getColor(R.color.bianco));
                bottone.setBackgroundResource(R.drawable.textbox);
                bottone.setGravity(Gravity.CENTER);
                bottone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                bottone.setId(i);
                bottone.setTypeface(typeface);
                bottone.setText(prenotazioni.get(i).getNome_evento());




// CONFRONTO LE DATE DEGLI EVENTI A QUELLA ODIERNA SE SONO PASSATE SONO DA VALUTARE
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


                currentTime = Calendar.getInstance().getTime();
                String debug1 = format.format(currentTime);
                String debug2=null ;
                try {
                    dataEvento = format.parse(prenotazioni.get(i).getData_evento());
                    debug2 = format.format(dataEvento);
                } catch (ParseException e) {
                    e.printStackTrace();
                }




                if(debug1.equals(debug2) && prenotazioni.get(i).getOra_evento().compareTo(currentHours)>0 ){
                    flagdata = true;
                }else
                    flagdata = dataEvento.after(currentTime) ;

//FINE CONFRONTO DATE
                if(!prenotazioni.get(i).isAnnullata() && flagdata){

                  //NULLA
                }else{

                    if(prenotazioni.get(i).isAnnullata() && flagdata){
                        //NULLA
                    }else {


                        if(!prenotazioni.get(i).isValutata()) {


                            da_valutare.add(bottone);
                            linearLayout3.addView(bottone);
                            linearLayout3.addView(space);
                        }

                    }
                }





                final Prenotazione p = prenotazioni.get(i);

                bottone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        /*Apro la descrizione delle partite in una nuova activity*/



                        final SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");

                        //Se non è annullata
                        currentTime = Calendar.getInstance().getTime();

                        final Date dataEvento2;


                        String debug1 = format2.format(currentTime);
                        String debug2=null ;

                        try {
                            dataEvento2 = format2.parse(p.getData_evento());


                            debug2=format2.format(dataEvento2);

                            if(debug1.equals(debug2)) flagdata=true;
                            else
                                flagdata = dataEvento2.after(currentTime) ;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }






                        if(!p.isAnnullata() && flagdata) {
                            //NULLA
                        }else {
                            if(p.isAnnullata()){
                                //Faccio vedere il motivo dell'annullamento

                            }else {

                                if(p.isValutata()){

                                    Context context = getApplicationContext();
                                    CharSequence text = "Hai già valutato questo evento";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }else{

                                    Intent showValutazione_partita = new Intent(Valutazioni_activity.this, Valutazione.class);
                                    //Inserisco la persona dentro l'intent


                                    showValutazione_partita.putExtra(PERSON_DA_PASSARE_2, persona);
                                    showValutazione_partita.putExtra(PRENOTAZIONE, p);


                                    startActivity( showValutazione_partita);
                                    finish();
                                }

                            }
                        }

                    }
                });


            }
        }else{

        }



        if(da_valutare == null || da_valutare.isEmpty()){
            nessuaValutazione.setText("Nessuna valutazione disponibile");
        }


        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHOME = new Intent(Valutazioni_activity.this, Home.class);
                //Inserisco la persona dentro l'intent
                //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
                showHOME.putExtra(PERSON_DA_PASSARE, persona);
                //richiamo activity
                startActivity(showHOME);
                finish();
            }
        });



    }

    public void onBackPressed(){
        Intent showHOME = new Intent(Valutazioni_activity.this, Home.class);
        //Inserisco la persona dentro l'intent
        //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
        showHOME.putExtra(PERSON_DA_PASSARE, persona);
        //richiamo activity
        startActivity(showHOME);
        finish();
    }
}
