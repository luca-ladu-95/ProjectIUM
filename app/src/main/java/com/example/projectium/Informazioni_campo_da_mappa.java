package com.example.projectium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaCampi;
import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Mappa.NOME_CAMPO_DA_PASSARE;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;


public class Informazioni_campo_da_mappa extends AppCompatActivity {

    public static final String BOOLEANO = "package com.example.projectium.Infromazioni_campo_da_mappa";
    Button indietro, partecipa, nuova_partita;
    Prenotazione prenotazione;
    Persona persona;
    Boolean flag_prenotazione = true;
    String valut;
    String campo_da_intent;
    CampoDaCalcio campo_selezionato;
    TextView nomeCampo,numeroCampo,valutazione,tipoDiCampo,prezzoAPersona,viaCampo;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informazioni_campo_da_mappa);

        //Setta il colore della status bar
        Window window = Informazioni_campo_da_mappa.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Informazioni_campo_da_mappa.this, R.color.colorPrimaryDark));
        // infe colore della status bar

        Intent intent = getIntent();
        Serializable obj2 = intent.getSerializableExtra(PRENOTAZIONE);
        Serializable obj3 = intent.getSerializableExtra(PERSON_DA_PASSARE);

        campo_selezionato = new CampoDaCalcio();

        indietro = findViewById(R.id.button_return_info_campo);
        partecipa = findViewById(R.id.button_info_partecipa);
        nuova_partita = findViewById(R.id.button_info_nuova_partita);
        nomeCampo = findViewById(R.id.info_nome_campo);
        numeroCampo = findViewById(R.id.info_numero_telefono);
        valutazione = findViewById(R.id.info_valutazione);
        tipoDiCampo = findViewById(R.id.info_tipo_di_campo);
        prezzoAPersona = findViewById(R.id.info_prezzo_a_persona);
        viaCampo = findViewById(R.id.info_via_campo);

        if (obj2 instanceof Prenotazione) {
            prenotazione = (Prenotazione) obj2;
        } else {
            prenotazione = new Prenotazione();
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            campo_da_intent = extras.getString(NOME_CAMPO_DA_PASSARE);
        }

        if(obj3 instanceof Persona){
            persona=(Persona) obj3;
        }else{
            persona=new Persona();
        }



        for (CampoDaCalcio c : listaCampi) {
            if (c.getNome().equals(campo_da_intent)) {
                campo_selezionato = c;
            }
        }

        String value = Valutazione_campo.getInstance().get_valutazione(campo_selezionato).toString();

        nomeCampo.setText(campo_da_intent);
        numeroCampo.setText(campo_selezionato.getTelefono());

        valutazione.setText(value);
        tipoDiCampo.setText(campo_selezionato.getMateriale());
        prezzoAPersona.setText(campo_selezionato.getPrezzo_a_persona() + " $");
        viaCampo.setText(campo_selezionato.getVia());

        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //devo indirizzarlo indietro pero col campo vuoto non ho messo null per evitare null pointer

                //NON devo perdere i dati quindi gli ripasso tutti tranne il campo
                Intent showCampo = new Intent(Informazioni_campo_da_mappa.this, Mappa.class);
                //Inserisco la persona dentro l'intent


                //richiamo activity
                startActivity(showCampo);
                finish();
            }
        });


        nuova_partita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showField = new Intent(Informazioni_campo_da_mappa.this, Nuova_partita_da_mappa.class);
                showField.putExtra(NOME_CAMPO_DA_PASSARE, campo_da_intent);
                showField.putExtra(PERSON_DA_PASSARE, persona);


                startActivity(showField);
                finish();

            }
        });

        partecipa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeCampo = campo_da_intent;
                Intent showField = new Intent(Informazioni_campo_da_mappa.this, Partecipa_partita.class);
                showField.putExtra(NOME_CAMPO_DA_PASSARE, nomeCampo);
                showField.putExtra(PERSON_DA_PASSARE, persona);
                showField.putExtra(BOOLEANO,flag_prenotazione);
                startActivity(showField);
                finish();

            }
        });
    }

    public void onBackPressed() {
        //devo indirizzarlo indietro pero col campo vuoto non ho messo null per evitare null pointer

        //NON devo perdere i dati quindi gli ripasso tutti tranne il campo
        Intent showCampo = new Intent(Informazioni_campo_da_mappa.this, Mappa.class);
        //Inserisco la persona dentro l'intent

        showCampo.putExtra(PERSON_DA_PASSARE,persona);
        //richiamo activity
        startActivity(showCampo);
        finish();


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
                                false,prenotazione.getOra_evento(),prenotazione.isValutata());
                        PrenotazioneFactory.getInstance().aggiungiPrenotazione(listaPrenotazioni,finale);
                        //Torno alla home
                        Context context = getApplicationContext();
                        CharSequence text = "Prenotazione effettuata con successo ";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Intent showLogin = new Intent(Informazioni_campo_da_mappa.this, Home.class);
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
