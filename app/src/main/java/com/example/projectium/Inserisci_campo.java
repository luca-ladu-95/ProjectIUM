package com.example.projectium;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
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
    TextInputLayout nomeCampo,viaCampo,importoCampo,materiale,telefono;
    Button indietro,conferma;
    SeekBar seekBarValutazione;
    TextView valoreValutazione;
    CampoDaCalcio campo;
    String prezzoCampo;     //Prezzo finale dopo i controlli
    String prezzoProv;      //Prezzo provvisorio per i controlli

    int MinValue=0;
    int maxValue=5;
    int modValue=0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserisci_campo);

        //Setta il colore della status bar
        Window window = Inserisci_campo.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Inserisci_campo.this, R.color.colorPrimaryDark));
        // infe colore della status bar

        nomeCampo = findViewById(R.id.gestore_nome_campo);
        viaCampo = findViewById(R.id.gestore_via_campo);
        importoCampo=findViewById(R.id.gestore_prezzo_campo);

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

                    //CONTROLLI IMPORTO CAMPO
                    prezzoCampo = importoCampo.getEditText().getText().toString();
                    String prezzoFin = prezzoCampo;


                    if(prezzoCampo.length() < 2)    //Il prezzo inserito ha una sola cifra(ES "2")
                        prezzoFin = prezzoCampo.concat(".00");
                    else{
                        //La stringa dell'importo del campo viene copiata in un array di caratteri per scomporla
                        final char[] pr = new char[prezzoCampo.length()];
                        prezzoCampo.getChars(0, (prezzoCampo.length()), pr, 0);
                        prezzoCampo = String.valueOf(pr);

                        for(int i = 0; i < prezzoCampo.length(); i++){
                            if(String.valueOf(pr[i]).equals(".")){      //Nell'importo è stato inserito il punto
                                if(i + 1 == prezzoCampo.length())   //Nell'importo inserito, non è presente nessuna cifra dopo il punto
                                    prezzoFin = prezzoCampo.concat("00");
                                else{
                                    if((prezzoCampo.length() - i) == 3){    //L'importo inserito rispetta i controlli
                                        prezzoFin = prezzoCampo;
                                        i = prezzoCampo.length();
                                    }else if((prezzoCampo.length() - i) == 2){  //L'importo inserito ha solo una cifra dopo il punto
                                        prezzoFin = prezzoCampo.concat("0");
                                    }else{      //L'importo inserito ha più di 2 cifre dopo il punto
                                        prezzoProv = String.valueOf(pr[0]);
                                        prezzoFin = prezzoProv.concat(".").concat(String.valueOf(pr[i + 1]).concat(String.valueOf(pr[i + 2])));
                                        i = prezzoCampo.length();
                                    }
                                }
                            }else if(i + 1 == prezzoCampo.length()){
                                prezzoFin = prezzoCampo.concat(".00");
                            }
                        }
                    }
                    //FINE CONTROLLI IMPORTO CAMPO


                    campo = CampoDaCalcioFactory.getInstance().creaCampo(
                            nomeCampo.getEditText().getText().toString(),
                            prezzoFin,
                            10,viaCampo.getEditText().getText().toString(),
                            telefono.getEditText().getText().toString(),
                            materiale.getEditText().getText().toString(),
                            coordinate.latitude,
                            coordinate.longitude
                    );


                    if(campo!=null){

                        CampoDaCalcioFactory.getInstance().aggiungiCampo(listaCampi,campo);

                        Valutazione_campo.getInstance().add_campo(campo);

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

     /*   seekBarValutazione.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                UpdateValue(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
*/
    }

    @SuppressLint("SetTextI18n")
    protected void UpdateValue(int newVal){

        newVal = newVal > maxValue ? maxValue : newVal;

        newVal = newVal < MinValue ? MinValue : newVal;

        this.modValue = newVal;

        valoreValutazione.setText(""+this.modValue);

        if(this.seekBarValutazione.getProgress() != modValue){
            this.seekBarValutazione.setProgress(modValue);
        }
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
