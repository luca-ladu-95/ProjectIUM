package com.example.projectium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

import static com.example.projectium.Partecipa_partita.EVENTO_DA_PASSARE;
import static com.example.projectium.Registrazione.PERSON_DA_PASSARE2;

public class Prenota_da_Partecipa_partita extends AppCompatActivity {

    Persona persona;
    Prenotazione prenotazione;
    TextView nome, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenota_da__partecipa_partita);

        /*Richiamo l'intent per recuperare i dati dell'utente*/
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE2);

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

        nome.setText(prenotazione.getNome_evento());
        data.setText(prenotazione.getData_evento());


    }
}
