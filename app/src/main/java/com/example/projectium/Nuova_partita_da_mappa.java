package com.example.projectium;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaCampi;
import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Mappa.NOME_CAMPO_DA_PASSARE;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;

public class Nuova_partita_da_mappa extends AppCompatActivity {

    String nomeCampo;
    Persona persona;
    DatePickerFragment datePickerFragment;
    EditText nomePartita,dataPartita,descrizione,ora;
    Button indietro;
    TextView numeroGiocatori;
    SeekBar seekBar;
    NumberPicker np;
    CampoDaCalcio campo;
    Button scegliCampo;
    String debug,debug3 ;
    Prenotazione prenotazione = new Prenotazione();
    ArrayList<CampoDaCalcio> CAMPI=new ArrayList<>(listaCampi);

    int MinValue=0;
    int maxValue=9;
    int modValue=0;
    int numGiocatori;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuova_partita_da_mappa);

        nomePartita = findViewById(R.id.edit_nome_partita_da_mappa);
        datePickerFragment = new DatePickerFragment();

        descrizione=findViewById(R.id.descrizione_nuova_partita_da_mappa);

        scegliCampo = findViewById(R.id.button_conferma_prenotazione_da_mappa);

        dataPartita=findViewById(R.id.input_data_evento_nuovaP_da_mappa);

        seekBar=findViewById(R.id.seekBarGiocatori_da_mappa);

        numeroGiocatori = findViewById(R.id.seekNumeroGiocatori_da_mappa);

        indietro= findViewById(R.id.button_return_nuova_partita_da_mappa);

        final NumberPicker np = findViewById(R.id.oraPicker_da_mappa);

        //Array che viene passato al numb picker per mascherare i valori effettivi
        final String[] values = {"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00","17:00","18:00",
                "19:00","20:00","21:00","22:00"};
        np.setMinValue(0);
        np.setMaxValue(values.length -1 );



        np.setDisplayedValues(values);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);






        seekBar.setMax(10);






        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE);
        Serializable obj2 = intent.getSerializableExtra(NOME_CAMPO_DA_PASSARE);


        if (obj2 instanceof String) {
            nomeCampo = (String) obj2;
        } else {
            nomeCampo = null;
        }


        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }


        if(nomeCampo!=null && !nomeCampo.isEmpty()){
           for(int i =0 ;i < CAMPI.size();i++){
               if(CAMPI.get(i).getNome().equals(nomeCampo))
                   campo=CAMPI.get(i);
           }
        }



        scegliCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!checkInput()){

                    numGiocatori = seekBar.getProgress();
                    //Funzionano gli input ... l'idea è di inserirli in una prenotazione temporanea per poterla
                    // passare dopo alla successiva activity e solo dopo che si sceglie il campo creare una prenotazione
                    // con la funzione crea prenotazione




                    debug = values[np.getValue()]; //Funziona per l'ora
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    debug3 =  format.format(datePickerFragment.getDate().getTime());

                    prenotazione.setAnnullata(false);
                    prenotazione.setNum_giocatori(numGiocatori+1);
                    prenotazione.setCreatore(persona);
                    prenotazione.setData_evento(debug3);
                    prenotazione.setOra_evento(debug);
                    prenotazione.setNome_evento(nomePartita.getText().toString());
                    prenotazione.setDescrizione(descrizione.getText().toString());
                    prenotazione.setCampo(campo);



                    //Gli id e l'array dei partecipanti verra generato dopo ora la utilizzo solo come appoggio
                    //Ora carico tutto nella macro e la passo per fare scegliere il campo

                    if(!checkInput2()) {
                        final String message = "Sei sicuro di voler prenotare in data " + prenotazione.getData_evento() + (" per " +
                                "le ore " + prenotazione.getOra_evento() + " la partita: " + prenotazione.getNome_evento() + "?");

                        //Apre il messaggio di conferma uscita
                        AlertDialog diaBox = AskOption(message);
                        diaBox.show();
                    }

                }
            }
        });

        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHOME = new Intent(Nuova_partita_da_mappa.this, Mappa.class);
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


        if(this.seekBar.getProgress() != modValue){
            this.seekBar.setProgress(modValue);
        }
        newVal = newVal > maxValue ? maxValue : newVal;

        newVal = newVal < MinValue ? MinValue : newVal;


        this.modValue = newVal;

        numeroGiocatori.setText(""+this.modValue);
    }


    @Override
    public void onBackPressed() {
        Intent showHOME = new Intent(Nuova_partita_da_mappa.this, Mappa.class);
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

    private boolean checkInput2(){
       boolean errors=false;
        if( PrenotazioneFactory.getInstance().checkCampoOccupato(prenotazione,listaPrenotazioni)){
            dataPartita.setError("Inserisci una data diversa");
            Context context = getApplicationContext();
            CharSequence text = "Il campo è gia prenotato per quell'ora e quella data";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            errors = true;
        }else dataPartita.setError(null);
        return errors;
    }

    private AlertDialog AskOption(String message)
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // finestra di conferma eliminazione
                .setTitle("Riepilogo prenotaziane")

                .setMessage(message)


                .setPositiveButton("Conferma", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        //Creo una nuova prenotazione
                        Prenotazione finale = PrenotazioneFactory.getInstance().creaPrenotazione(persona,prenotazione.getCampo(),
                                prenotazione.getNome_evento(),prenotazione.getDescrizione(),prenotazione.getData_evento(),prenotazione.getNum_giocatori(),
                                false,prenotazione.getOra_evento());
                        PrenotazioneFactory.getInstance().aggiungiPrenotazione(listaPrenotazioni,finale);
                        //Torno alla home
                        Context context = getApplicationContext();
                        CharSequence text = "Prenotazione effettuata con successo ";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Intent showLogin = new Intent(Nuova_partita_da_mappa.this, Home.class);
                        showLogin.putExtra(PERSON_DA_PASSARE, persona);
                        startActivity(showLogin);
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
}