package com.example.projectium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;
import static com.example.projectium.Registrazione.PERSON_DA_PASSARE2;

public class NuovaPartita extends AppCompatActivity {

    Persona persona;
    DatePickerFragment datePickerFragment;
    EditText nomePartita,dataPartita,descrizione;
    Button indietro;
    Date data_di_oggi;
    int indice;
    TextView numeroGiocatori;
    SeekBar seekBar;
    NumberPicker np;
    TextView ora;
    String[] valori = new String[2];
    Button scegliCampo;
    String debug,debug3 ;
    Prenotazione prenotazione = new Prenotazione();




    int MinValue=1;
    int maxValue=10;
    int modValue=1;
    int numGiocatori;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuova_partita);

        //Setta il colore della status bar
        Window window = NuovaPartita.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(NuovaPartita.this, R.color.colorPrimaryDark));
        // infe colore della status bar

        nomePartita = findViewById(R.id.edit_nome_partita);
        datePickerFragment = new DatePickerFragment();

        descrizione=findViewById(R.id.descrizione_nuova_partita);

        scegliCampo = findViewById(R.id.button_scegli_il_campo_nuova_partita);

        dataPartita=findViewById(R.id.input_data_evento_nuovaP);

        seekBar=findViewById(R.id.seekBarGiocatori);

        ora=findViewById(R.id.tv);

        numeroGiocatori = findViewById(R.id.seekNumeroGiocatori);

        indietro= findViewById(R.id.button_return_nuova_partita);
        final NumberPicker np = findViewById(R.id.oraPicker);



        //Array che viene passato al numb picker per mascherare i valori effettivi
        final String[] values = {"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00","17:00","18:00",
                "19:00","20:00","21:00","22:00"};


        String currentTime = new SimpleDateFormat("HH", Locale.getDefault()).format(new Date());

        data_di_oggi = Calendar.getInstance().getTime();

        //prendo ora attuale
        indice = get_indice_data(currentTime);

        valori = new String[indice];
        //creo array con gli orari a cui puo entrare

        int j = values.length - indice;

        for (int i = 0; i < valori.length && j <= values.length; i++) {
            valori[i] = values[j];
            j++;
        }




        seekBar.setMax(10);

        seekBar.setProgress(1);
        numeroGiocatori.setText("1");


        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE_2);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }


        scegliCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!checkInput()){

                    numGiocatori = seekBar.getProgress();
                    //Funzionano gli input ... l'idea è di inserirli in una prenotazione temporanea per poterla
                    // passare dopo alla successiva activity e solo dopo che si sceglie il campo creare una prenotazione
                    // con la funzione crea prenotazione



                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


                    Date deb1 = datePickerFragment.getDate().getTime();
                    Date deb2 = Calendar.getInstance().getTime();

                    String deb1String = format.format(deb1);
                    String deb2String = format.format(deb2);
                    int debug2 = np.getValue();
                    if(!deb1String.equals(deb2String)) {
                        debug = values[np.getValue()]; //Funziona per l'ora
                    }else{
                        debug = valori[np.getValue()];
                    }
                    debug3 =  format.format(datePickerFragment.getDate().getTime());

                    prenotazione.setAnnullata(false);
                    if(numGiocatori ==0 ) numGiocatori =1;
                    prenotazione.setNum_giocatori(numGiocatori);
                    prenotazione.setCreatore(persona);
                    prenotazione.setData_evento(debug3);
                    prenotazione.setOra_evento(debug);
                    prenotazione.setNome_evento(nomePartita.getText().toString());
                    prenotazione.setDescrizione(descrizione.getText().toString());
                    prenotazione.setValutata(false);
                    //Gli id e l'array dei partecipanti verra generato dopo ora la utilizzo solo come appoggio
                    //Ora carico tutto nella macro e la passo per fare scegliere il campo

                    Intent showPrenota_partita = new Intent(NuovaPartita.this, Scegli_campo.class);
                    //Inserisco la persona dentro l'intent

                    //La persona non la passo perchè me la ricavo dal creatore
                    showPrenota_partita.putExtra(PRENOTAZIONE, prenotazione);

                    startActivity(showPrenota_partita);
                    finish();
                }
            }
        });

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
                np.setVisibility(View.VISIBLE);
                ora.setVisibility(View.VISIBLE);


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

                Date deb1 = datePickerFragment.getDate().getTime();
                Date deb2 = Calendar.getInstance().getTime();

                String deb1String = format.format(deb1);
                String deb2String = format.format(deb2);




                if(deb1String.equals(deb2String)) {

                    np.setMinValue(0);
                    np.setMaxValue(valori.length - 1);
                    //Gets whether the selector wheel wraps when reaching the min/max value.
                    np.setWrapSelectorWheel(true);


                    np.setDisplayedValues(valori);
                }else {
                    np.setDisplayedValues(null);
                    np.setMinValue(0);
                    int debugde= values.length;
                    np.setMaxValue(values.length - 1);
                    //Gets whether the selector wheel wraps when reaching the min/max value.
                    np.setWrapSelectorWheel(true);
                    np.setDisplayedValues(values);
                }


            }

            @Override
            public void onDatePickerFragmentCancelButton(DialogFragment dialog) {

            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
    }

    protected void UpdateValue(int newVal){

        newVal = newVal > maxValue ? maxValue : newVal;

        newVal = newVal < MinValue ? MinValue : newVal;

        this.modValue = newVal;


        numeroGiocatori.setText(""+this.modValue);

        if(this.seekBar.getProgress() != modValue){
            this.seekBar.setProgress(modValue);
        }
    }


    @Override
    public void onBackPressed() {
        Intent showHOME = new Intent(NuovaPartita.this, Home.class);
        //Inserisco la persona dentro l'intent
        //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
        showHOME.putExtra(PERSON_DA_PASSARE, persona);
        //richiamo activity
        startActivity(showHOME);
        finish();
    }

    private boolean checkInput(){
        boolean errors = false;

        if(nomePartita.getText() == null || nomePartita.getText().length() == 0){
            errors = true;
            nomePartita.setError("Inserisci il nome della partita");
        }else
            nomePartita.setError(null);

        if(dataPartita.getText() == null || dataPartita.getText().length()==0){
            errors = true;
            dataPartita.setError("Inserisci la data");
        }else
            dataPartita.setError(null);

        if(descrizione.getText()==null || descrizione.getText().length()==0){
            descrizione.setError("Inserisci descrizione");
            errors = true;
        }else
            descrizione.setError(null);

        return errors;
    }

    public  int get_indice_data(String ora){
        int i=0;
        switch (ora){
            case "09":i=13;
                break;
            case  "10":i=12;
                break;
            case "11":i=11;
                break;
            case "12":i=10;
                break;
            case "13":i=9;
                break;
            case  "14":i=8;
                break;
            case "15":i=7;
                break;
            case "16":i=6;
                break;
            case "17":i=5;
                break;
            case  "18":i=4;
                break;
            case "19":i=3;
                break;
            case "20":i=2;
                break;
            case "21":i=1;
                break;
            case  "22":i=0;
                break;

        }

        return i;
    }
}