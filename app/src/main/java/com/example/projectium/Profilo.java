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

import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Login.utenti;

public class Profilo extends AppCompatActivity {

    Persona persona;
    TextView nome,cognome,email,passowrd,partite,nickname;
    Button esci,eliminaProfilo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);


        nome=findViewById(R.id.nome_fragment_profilo);
        cognome=findViewById(R.id.cognome_frament_prfilo);
        email=findViewById(R.id.email_fragment_profilo);
        passowrd=findViewById(R.id.password_fragment_profilo);
        esci=findViewById(R.id.button_profilo_home);
        partite=findViewById(R.id.partite_fragmewnt_profilo);
        eliminaProfilo=findViewById(R.id.button_elimina_profilo);
        nickname=findViewById(R.id.userName_fragment_profilo);


        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }


        nome.setText(persona.getNome());
        cognome.setText(persona.getCognome());
        email.setText(persona.getEmail());
        passowrd.setText(persona.getPassword());
        partite.setText(persona.getPartite());
        nickname.setText(persona.getUserId());



        esci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHOME = new Intent(Profilo.this, Home.class);
                //Inserisco la persona dentro l'intent
                //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
                showHOME.putExtra(PERSON_DA_PASSARE, persona);
                //richiamo activity
                startActivity(showHOME);
                finish();
            }
        });

        eliminaProfilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Richiamo la
                AlertDialog diaBox = AskOption();
                diaBox.show();

            }
        });

    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // finestra di conferma eliminazione
                .setTitle("Attenzione")
                .setMessage("Vuoi davvero eliminare il profilo e tutti i suoi dati associati?")


                .setPositiveButton("elimina", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        PersonaFactory.getInstance().rimuoviUtente(utenti,listaPrenotazioni,persona);

                        persona=null;
                        Intent showHOME = new Intent(Profilo.this, Login.class);
                        startActivity(showHOME);
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


    public void onBackPressed(){

        Intent showHOME = new Intent(Profilo.this, Home.class);
        //Inserisco la persona dentro l'intent
        //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
        showHOME.putExtra(PERSON_DA_PASSARE, persona);
        //richiamo activity
        startActivity(showHOME);
        finish();

    }
}
