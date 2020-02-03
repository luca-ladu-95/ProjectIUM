package com.example.projectium;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;

public class Informazioni_campo extends AppCompatActivity {

    Button indietro,prenota;
    Prenotazione prenotazione;
    Persona persona;
    TextView nomeCampo,numeroCampo,valutazione,tipoDiCampo,prezzoAPersona,viaCampo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informazioni_campo);


        Intent intent = getIntent();
        Serializable obj2 = intent.getSerializableExtra(PRENOTAZIONE);


        indietro=findViewById(R.id.button_return_info_campo);
        prenota=findViewById(R.id.button_info_prenota_campo);
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

        //cosi ho anche la persona
        persona=prenotazione.getCreatore();



        nomeCampo.setText(prenotazione.getCampo().getNome());
        numeroCampo.setText("Telefono: "+ prenotazione.getCampo().getTelefono());
        valutazione.setText(("Valutazione: " + prenotazione.getCampo().getValutazione()));
        tipoDiCampo.setText("Manto: "+prenotazione.getCampo().getMateriale());
        prezzoAPersona.setText("Prezzo a persona: "+prenotazione.getCampo().getPrezzo_a_persona()+" $");
        viaCampo.setText(prenotazione.getCampo().getVia());



        prenota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = "Sei sicuro di voler prenotare in data " + prenotazione.getData_evento() + (" per " +
                        "le ore "+prenotazione.getOra_evento()+" la partita: "+prenotazione.getNome_evento()+"?");

                //Apre il messaggio di conferma uscita
                AlertDialog diaBox = AskOption(message);
                diaBox.show();
            }
        });

        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //devo indirizzarlo indietro pero col campo vuoto non ho messo null per evitare null pointer
                prenotazione.setCampo(new CampoDaCalcio());

                //NON devo perdere i dati quindi gli ripasso tutti tranne il campo
                Intent showCampo = new Intent(Informazioni_campo.this, Scegli_campo.class);
                //Inserisco la persona dentro l'intent


                showCampo.putExtra(PRENOTAZIONE, prenotazione);
                //richiamo activity
                startActivity(showCampo);
                finish();
            }
        });
    }

    public void onBackPressed() {
        //devo indirizzarlo indietro pero col campo vuoto non ho messo null per evitare null pointer
        prenotazione.setCampo(new CampoDaCalcio());

        //NON devo perdere i dati quindi gli ripasso tutti tranne il campo
        Intent showCampo = new Intent(Informazioni_campo.this, Scegli_campo.class);
        //Inserisco la persona dentro l'intent


        showCampo.putExtra(PRENOTAZIONE, prenotazione);
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
                                false,prenotazione.getOra_evento());
                        PrenotazioneFactory.getInstance().aggiungiPrenotazione(listaPrenotazioni,finale);
                        //Torno alla home
                        Context context = getApplicationContext();
                        CharSequence text = "Prenotazione effettuata con successo ";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Intent showLogin = new Intent(Informazioni_campo.this, Home.class);
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
