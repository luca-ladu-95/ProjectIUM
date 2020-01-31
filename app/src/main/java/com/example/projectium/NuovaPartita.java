package com.example.projectium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Registrazione.PERSON_DA_PASSARE2;

public class NuovaPartita extends AppCompatActivity {

    Persona persona;
    DatePickerFragment datePickerFragment;
    EditText nomePartita,dataPartita;
    Button indietro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuova_partita);

        datePickerFragment = new DatePickerFragment();

        dataPartita=findViewById(R.id.input_data_evento_nuovaP);


        indietro= findViewById(R.id.button_return_nuova_partita);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE2);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }


        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHOME = new Intent(NuovaPartita.this, Home.class);
                //Inserisco la persona dentro l'intent
                //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
                showHOME.putExtra(PERSON_DA_PASSARE, persona);
                //richiamo activity
                startActivity(showHOME);
                finish();
            }
        });

        dataPartita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment.show(getSupportFragmentManager(), "date picker");
            }
        });


        //questa funzione permette di controllare che l'utente non scriva nella textview
        dataPartita.setOnFocusChangeListener(new View.OnFocusChangeListener() { //funzione di view
            @Override
            public void onFocusChange(View v, boolean hasFocus) { //metodo chiamato quando lo stato della view cambia
                if (hasFocus) {
                    datePickerFragment.show(getSupportFragmentManager(), "datePicker");
                }
            }
        });

        datePickerFragment.setOnDatePickerFragmentChanged(new DatePickerFragment.DatePickerFragmentListener() {
            @Override
            public void onDatePickerFragmentOkButton(DialogFragment dialog, Calendar date) {
                //Associo il comportamento del bottone OK all'edit text della data, voglio che una
                // volta selezionata quindi ho premuto ok, l'edit text mostri la data selezionata
                // tramite il datepicker
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                dataPartita.setText(format.format(date.getTime()));
            }

            @Override
            public void onDatePickerFragmentCancelButton(DialogFragment dialog) {

            }
        });



    }

}
