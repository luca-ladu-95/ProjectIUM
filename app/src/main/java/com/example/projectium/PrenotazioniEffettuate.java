package com.example.projectium;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;

public class PrenotazioniEffettuate extends AppCompatActivity {

    Persona persona;
    ArrayList<Prenotazione> prenotazioni;
    TextView nessunaP,nessunaP2;
    Button indietro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazioni_effettuate);

        /*Recupero i lay dove stampare dinamicamente i bottoni degli eventi */
        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.layout_prenotazioni_in_corso);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.layout_prenotazioni_annullate);
        nessunaP=findViewById(R.id.testo_nessuna_prenotazione1);
        nessunaP2=findViewById(R.id.testo_nessuna_prenotazione2);
        indietro=findViewById(R.id.button_return_prenotazioni_effettuate);
        int i=0;


        /*Richiamo l'intent per recuperare i dati dell'utente*/
        final Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE_2);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }


        /*Recupero le partite in corso e le partite annullate lista prenotazioni sono quelle di default
        * poi dovra essere sostituito con un altro */
        prenotazioni = PrenotazioneFactory.getInstance().getPrenotazioneUtente(listaPrenotazioni,persona);

        /*Creo dinamicamente un bottone per ogni evento  ed un click on view*/

        if(prenotazioni!= null && prenotazioni.size()>0) {

            //Controllo prenotazioni annullate fa schifo questa funzione, era gia fatta e la ho usata
            // in pratica controllo le lunghezze delle due liste , se sono uguli vuol dire che sono tutte annullate
            if(PrenotazioneFactory.getInstance().getPrenotazioniAnnullate(prenotazioni).size()==prenotazioni.size()){
                nessunaP.setText("Nessuna prenotazione presente");

            }

            for (i = 0; i < prenotazioni.size(); i++) {


                Button bottone = new Button(this);
                bottone.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                bottone.setTextColor(getResources().getColor(R.color.nero));
                bottone.setGravity(Gravity.CENTER);
                bottone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
                bottone.setId(i);

                bottone.setText(prenotazioni.get(i).getNome_evento());

                if(!prenotazioni.get(i).isAnnullata()){

                    linearLayout1.addView(bottone);
                }else{
                    linearLayout2.addView(bottone);

                }


                final Prenotazione p = prenotazioni.get(i);

                bottone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        /*Apro la descrizione delle partite in una nuova activity*/


                        //Se non è annullata

                        if(!p.isAnnullata()) {
                            Intent showRiepilogo_partita = new Intent(PrenotazioniEffettuate.this, Riepilogo_partita.class);
                            //Inserisco la persona dentro l'intent


                            showRiepilogo_partita.putExtra(PERSON_DA_PASSARE_2, persona);
                            showRiepilogo_partita.putExtra(PRENOTAZIONE, p);


                            startActivity(showRiepilogo_partita);
                            finish();
                        }else{

                            //Faccio vedere il motivo dell'annullamento

                            final String message = "Prenotazione del " + p.getData_evento() + (" per " +
                                    "le ore "+p.getOra_evento()+",campo: "+p.getCampo().getNome());

                            AlertDialog diaBox = AskOption(message);
                            diaBox.show();

                        }

                    }
                });


            }
        }else{

            nessunaP.setText("Nessuna prenotazione presente");
            nessunaP2.setText("Nessuna prenotazione presente");



        }


        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHOME = new Intent(PrenotazioniEffettuate.this, Home.class);
                //Inserisco la persona dentro l'intent
                //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
                showHOME.putExtra(PERSON_DA_PASSARE, persona);
                //richiamo activity
                startActivity(showHOME);
                finish();
            }
        });


    }

    public void onBackPressed(){
        Intent showHOME = new Intent(PrenotazioniEffettuate.this, Home.class);
        //Inserisco la persona dentro l'intent
        //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
        showHOME.putExtra(PERSON_DA_PASSARE, persona);
        //richiamo activity
        startActivity(showHOME);
        finish();
    }


    private AlertDialog AskOption(String message)
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // finestra di conferma eliminazione
                .setTitle("Partita annullata")
                .setMessage(message)



                .setNegativeButton("idietro", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();



        return myQuittingDialogBox;
    }


}
