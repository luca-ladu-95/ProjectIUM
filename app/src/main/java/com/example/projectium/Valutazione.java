package com.example.projectium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    Button feedback;
    ImageView character;
    RatingBar rateStars;
    String valore;
    Animation animation;

    Integer value =1 ;
    Persona persona;
    Prenotazione prenotazione;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valutazione);

        titolo = findViewById(R.id.titleRating);
        risultato = findViewById(R.id.resultRate);
        feedback = findViewById(R.id.button_feed);
        character = findViewById(R.id.icon);
        rateStars = findViewById(R.id.ratingStars);


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


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //todo -> aggiungi valutazione e porta indietro con persona

                Valutazione_campo.getInstance().add_valutazione(value,prenotazione.getCampo());

                Integer debug = value;

                for(int i = 0;i< listaPrenotazioni.size();i++){

                    if(listaPrenotazioni.get(i).equals(prenotazione)){
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


            }
        });

        rateStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                valore = String.valueOf((int) (rateStars.getRating()));

                if(valore.equals("1")){
                    character.setImageResource(R.drawable.iconestar);
                    risultato.setText("Pessimo");
                    value =1;

                } else if(valore.equals("2")){
                    character.setImageResource(R.drawable.ictwostar);
                    risultato.setText("Sufficiente");
                    value=2;

                } else if(valore.equals("3")){
                    character.setImageResource(R.drawable.icthreestar);
                    risultato.setText("Buono");

                    value=3;
                }else if(valore.equals("4")){
                    character.setImageResource(R.drawable.icfourstar);
                    risultato.setText("Ottimo");
                    value=4;

                }else if(valore.equals("5")){
                    character.setImageResource(R.drawable.icfivestar);
                    risultato.setText("Magnifico");
                    value=5;

                }else{
                    Toast.makeText(getApplicationContext(), "Nessuna Valutazione", Toast.LENGTH_SHORT).show();
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
