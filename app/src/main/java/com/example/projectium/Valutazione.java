package com.example.projectium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaCampi;
import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;

public class Valutazione extends AppCompatActivity {

    TextView titolo, risultato;
    Button feedback, esci;
    ImageView character;
    RatingBar rateStars;
    String valore;

    Integer value = 1 ;
    Persona persona;
    Prenotazione prenotazione;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valutazione);


        //Setta il colore della status bar
        Window window = Valutazione.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Valutazione.this, R.color.colorPrimaryDark));
        // infe colore della status bar

        titolo = findViewById(R.id.titleRating);
        risultato = findViewById(R.id.resultRate);
        feedback = findViewById(R.id.button_feed);
        character = findViewById(R.id.icon);
        rateStars = findViewById(R.id.ratingStars);
        esci = findViewById(R.id.indietroPrenotazioni);

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

        esci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Intent showHOME = new Intent(Valutazione.this, PrenotazioniEffettuate.class);
                    //Inserisco la persona dentro l'intent
                    //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
                    showHOME.putExtra(PERSON_DA_PASSARE, persona);
                    //richiamo activity
                    startActivity(showHOME);
                    finish();
                }
            }
        });


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(String.valueOf((int) (rateStars.getRating())).equals("0"))) {
                    //todo -> aggiungi valutazione e porta indietro con persona

                    Valutazione_campo.getInstance().add_valutazione(value, prenotazione.getCampo());

                    Integer debug = value;

                    for (int i = 0; i < listaPrenotazioni.size(); i++) {

                        if (listaPrenotazioni.get(i).equals(prenotazione)) {
                            listaPrenotazioni.get(i).setValutata(true);

                        }
                    }


                    Context context = getApplicationContext();
                    CharSequence text = "Valutazione registrata con successo";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    Intent showCercaPartita = new Intent(Valutazione.this, PrenotazioniEffettuate.class);
                    //Inserisco la persona dentro l'intent
                    showCercaPartita.putExtra(PERSON_DA_PASSARE, persona);
                    //richiamo activity
                    startActivity(showCercaPartita);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Inserire una valutazione per continuare", Toast.LENGTH_SHORT).show();
                }

            }
        });

        rateStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                valore = String.valueOf((int) (rateStars.getRating()));

                if(valore.equals("0")){
                    character.setImageResource(R.drawable.iczerostar);
                    risultato.setText("Errore");
                    value = 1;
                } else if(valore.equals("1")){
                    character.setImageResource(R.drawable.iconestar);
                    risultato.setText("Pessimo");
                    value = 1;

                } else if(valore.equals("2")){
                    character.setImageResource(R.drawable.ictwostar);
                    risultato.setText("Sufficiente");
                    value = 2;

                } else if(valore.equals("3")){
                    character.setImageResource(R.drawable.icthreestar);
                    risultato.setText("Buono");
                    value = 3;
                }else if(valore.equals("4")){
                    character.setImageResource(R.drawable.icfourstar);
                    risultato.setText("Ottimo");
                    value = 4;
                }else if(valore.equals("5")){
                    character.setImageResource(R.drawable.icfivestar);
                    risultato.setText("Magnifico");
                    value = 5;
                }
            }
        });

    }

    public void onBackPressed() {
        Intent showHOME = new Intent(Valutazione.this, PrenotazioniEffettuate.class);
        //Inserisco la persona dentro l'intent


        showHOME.putExtra(PERSON_DA_PASSARE_2, persona);
        //richiamo activity
        startActivity(showHOME);
        finish();
    }
}
