package com.example.projectium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

import static com.example.projectium.Mappa.NOME_CAMPO_DA_PASSARE;


public class Mostra_Campo extends AppCompatActivity {

    String nomeCampo;
    TextView campo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra__campo);

        campo = findViewById(R.id.campo);

        /*Richiamo l'intent per recuperare i dati dell'utente*/
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(NOME_CAMPO_DA_PASSARE);

        if (obj instanceof String) {
            nomeCampo = (String) obj;
        }
        campo.setText(nomeCampo);
        }

}
