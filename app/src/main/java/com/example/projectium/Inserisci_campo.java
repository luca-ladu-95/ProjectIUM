package com.example.projectium;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;

import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaCampi;

public class Inserisci_campo extends AppCompatActivity {

    Persona persona;
    LatLng coordinate;
    TextInputLayout nomeCampo,viaCampo,importoCampo,valutazione,materiale,telefono;
    Button indietro,conferma;
    CampoDaCalcio campo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserisci_campo);

        nomeCampo = findViewById(R.id.gestore_nome_campo);
        viaCampo = findViewById(R.id.gestore_via_campo);
        importoCampo=findViewById(R.id.gestore_prezzo_campo);
        valutazione=findViewById(R.id.gestore_valutazione_campo);
        materiale=findViewById(R.id.gestore_materiale_campo);
        telefono=findViewById(R.id.gestore_telefono);
        conferma=findViewById(R.id.button_gestore_conferma);

        indietro=findViewById(R.id.button_return_gestore_inserisci_campo);


        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }
        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkInput()){

                    GeocodingLocation locationAddress = new GeocodingLocation();
                    String via = viaCampo.getEditText().getText().toString();

                    coordinate = locationAddress.getAddressFromLocation(via,
                            getApplicationContext());



                    campo = CampoDaCalcioFactory.getInstance().creaCampo(
                            nomeCampo.getEditText().getText().toString(),importoCampo.getEditText().getText().toString(),10,viaCampo.getEditText().getText().toString(),telefono.getEditText().getText().toString(),materiale.getEditText().getText().toString(),valutazione.getEditText().getText().toString(),coordinate.latitude,coordinate.longitude
                    );


                    if(campo!=null){

                        CampoDaCalcioFactory.getInstance().aggiungiCampo(listaCampi,campo);

                        Context context = getApplicationContext();
                        CharSequence text = "Inserimento effettuato con successo ";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        Intent showHOME = new Intent(Inserisci_campo.this, Gestore.class);
                        //Inserisco la persona dentro l'intent
                        //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
                        //richiamo activity
                        showHOME.putExtra(PERSON_DA_PASSARE, persona);

                        startActivity(showHOME);
                        finish();

                    }



                }
            }
        });


        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showHOME = new Intent(Inserisci_campo.this, Gestore.class);
                //Inserisco la persona dentro l'intent
                //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
                //richiamo activity
                showHOME.putExtra(PERSON_DA_PASSARE, persona);

                startActivity(showHOME);
                finish();
            }
        });


    }


    public boolean checkInput(){
        boolean flag = false;

        if(nomeCampo.getEditText().getText()==null || nomeCampo.getEditText().getText().length()==0){

            nomeCampo.setError("Inserire il nome");
            flag = true;
        }else
            nomeCampo.setError(null);


        if(viaCampo.getEditText().getText()==null || viaCampo.getEditText().getText().length()==0){

            viaCampo.setError("Inserire la via");
            flag = true;
        }else
            viaCampo.setError(null);

        if(importoCampo.getEditText().getText()==null || importoCampo.getEditText().getText().length()==0){

            importoCampo.setError("Inserire l' importo");
            flag = true;
        }else
            importoCampo.setError(null);


        if(valutazione.getEditText().getText()==null || valutazione.getEditText().getText().length()==0){

            valutazione.setError("Inserire la valutazione");
            flag = true;
        }else
            valutazione.setError(null);

        if(materiale.getEditText().getText()==null || materiale.getEditText().getText().length()==0){

            materiale.setError("Inserire il materiale");
            flag = true;
        }else
            materiale.setError(null);


        if(telefono.getEditText().getText()==null || telefono.getEditText().getText().length()==0){

            telefono.setError("Inserire il telefono");
            flag = true;
        }else
            telefono.setError(null);

        return flag;

    }

    public void onBackPressed(){
        Intent showHOME = new Intent(Inserisci_campo.this, Gestore.class);
        //Inserisco la persona dentro l'intent
        //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
        //richiamo activity
        showHOME.putExtra(PERSON_DA_PASSARE, persona);

        startActivity(showHOME);
        finish();
    }

}
