package com.example.projectium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

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

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaCampi;
import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;

public class Scegli_campo extends AppCompatActivity {

    Prenotazione prenotazione;
    Persona persona;
    TextView nessunCampo;
    ArrayList<CampoDaCalcio> campiDaCalcio;
    Button indietro;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scegli_campo);

        //Imposto un font da utilizzare sui bottoni generati dinamicamente
        Typeface typeface = ResourcesCompat.getFont(getBaseContext(), R.font.baloo);

        //Setta il colore della status bar
        Window window = Scegli_campo.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Scegli_campo.this, R.color.colorPrimaryDarkStatusBar));
        // infe colore della status bar

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_campi_disponibili);

        nessunCampo=findViewById(R.id.nessun_campo_disponibile);

        Intent intent = getIntent();

        Serializable obj2 = intent.getSerializableExtra(PRENOTAZIONE);


        indietro=findViewById(R.id.button_return_scegli_campo);




        if (obj2 instanceof Prenotazione) {
            prenotazione = (Prenotazione) obj2;
        } else {
            prenotazione = new Prenotazione();
        }

       //cosi ho anche la persona
        persona=prenotazione.getCreatore();


        /*Prendo le partite NON ANNULLATE*/
        ArrayList<Prenotazione> prenotazioniInCorso = PrenotazioneFactory.getInstance().getPrenotazioniInCorso(listaPrenotazioni,persona);


        //COntrollo i campi disponibili
        campiDaCalcio = PrenotazioneFactory.getInstance().ricercaCampiLiberi(prenotazione,listaPrenotazioni,listaCampi);
        //Ora avro i campi disponibili



        if(campiDaCalcio!= null && campiDaCalcio.size() > 0) {
            for (int i = 0; i < campiDaCalcio.size(); i++) {


                Space space = new Space(this);
                space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,20));

                Button bottone = new Button(this);
                bottone.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                bottone.setTextColor(getResources().getColor(R.color.bianco));
                bottone.setBackgroundResource(R.drawable.textbox);
                bottone.setGravity(Gravity.CENTER);
                bottone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                bottone.setId(i);
                bottone.setTypeface(typeface);
                String debug = campiDaCalcio.get(i).getNome();
                bottone.setText(campiDaCalcio.get(i).getNome());
                linearLayout.addView(bottone);
                linearLayout.addView(space);

                final  CampoDaCalcio campo = campiDaCalcio.get(i);

                bottone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //devo indirizzarlo ai campi
                        prenotazione.setCampo(campo);
                        // carico la prenotazione ATTENZIONE ANCORA non la creo perchè non ha confermato il campo
                        //Se torna indietro puo annullare

                        Intent showCampo = new Intent(Scegli_campo.this, Informazioni_campo.class);
                        //Inserisco la persona dentro l'intent


                        showCampo.putExtra(PRENOTAZIONE, prenotazione);
                        //richiamo activity
                        startActivity(showCampo);
                        finish();



                    }
                });


            }
        }else{

            String s = "Nessun campo disponibile";
            nessunCampo.setText(s);


        }

        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHOME = new Intent(Scegli_campo.this, NuovaPartita.class);
                //Inserisco la persona dentro l'intent


                showHOME.putExtra(PERSON_DA_PASSARE_2, persona);
                //richiamo activity
                startActivity(showHOME);
                finish();
            }
        });


    }

    public void onBackPressed() {
        Intent showHOME = new Intent(Scegli_campo.this, NuovaPartita.class);
        //Inserisco la persona dentro l'intent


        showHOME.putExtra(PERSON_DA_PASSARE_2, persona);
        //richiamo activity
        startActivity(showHOME);
        finish();
    }
}
