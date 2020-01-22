package com.example.projectium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import static com.example.projectium.Login.PERSON_DA_PASSARE;

public class Profilo extends AppCompatActivity {

    Persona persona;
    TextView nome,cognome,email,passowrd;
    Button esci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);


        nome=findViewById(R.id.nome_fragment_profilo);
        cognome=findViewById(R.id.cognome_frament_prfilo);
        email=findViewById(R.id.email_fragment_profilo);
        passowrd=findViewById(R.id.password_fragment_profilo);
        esci=findViewById(R.id.button_profilo_home);


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
        passowrd.setText(persona.getPassowrd());


        esci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHOME = new Intent(Profilo.this, Home.class);
                //Inserisco la persona dentro l'intent
                showHOME.putExtra(PERSON_DA_PASSARE, persona);
                //richiamo activity
                startActivity(showHOME);
                finish();
            }
        });

    }
}
