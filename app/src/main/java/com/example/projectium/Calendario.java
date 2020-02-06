package com.example.projectium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;

public class Calendario extends AppCompatActivity {

    CalendarView calendar;
    TextView selectedDate;
    Button indietro;
    Persona persona;
    LinearLayout linearLayout1;
    TextView nessunaP;
    Space space;
    Button bottone;
    String correctedMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        calendar = findViewById(R.id.calendarView);
        selectedDate = findViewById(R.id.textView4);
        indietro=findViewById(R.id.button_return_prenotazioni_effettuate);
        linearLayout1 = findViewById(R.id.layout_prenotazioni_del_giorno);
        nessunaP =findViewById(R.id.testo_nessuna_prenotazione1);


        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }


        //Lister per sapere su quale giorni si è premuto e visualizzare le prenotazioni di quel giorno.
        //TODO come elimino le prenotazioni che metto a schermo???
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                int conto_partite_prenotate = 0;
                if (month < 10 ) {
                    correctedMonth = "0" + (++month);
                   // Toast.makeText(getApplicationContext(), "" + dayOfMonth + "/" + correctedMonth + "/" + year, 0).show();// TODO Auto-generated method stub
                }else {
                  //  Toast.makeText(getApplicationContext(), "" + dayOfMonth + "/" + (month) + "/" + year, 0).show();// TODO Auto-generated method stub

                }


                String giornoPremuto = ""+dayOfMonth+ "/" + correctedMonth + "/" + year;

                //Controllo prenotazioni annullate fa schifo questa funzione, era gia fatta e la ho usata
                // in pratica controllo le lunghezze delle due liste , se sono uguli vuol dire che sono tutte annullate

                for (int i = 0; i < listaPrenotazioni.size(); i++) {

                    space = new Space(Calendario.this);
                    bottone = new Button(Calendario.this);

                    space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 20));
                    bottone.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    bottone.setTextColor(getResources().getColor(R.color.bianco));
                    bottone.setBackgroundResource(R.drawable.textbox);
                    bottone.setGravity(Gravity.CENTER);
                    bottone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
                    bottone.setId(i);

                    bottone.setText(listaPrenotazioni.get(i).getNome_evento());

                    if (!listaPrenotazioni.get(i).isAnnullata() && listaPrenotazioni.get(i).getData_evento().equals(giornoPremuto)) {
                        conto_partite_prenotate++;
                        linearLayout1.addView(bottone);
                        linearLayout1.addView(space);
                    }

                    //SVUOTA LISTA SE NON CI SONO PRENOTAZIONI
                    if(conto_partite_prenotate == 0) linearLayout1.removeAllViews();

                    final Prenotazione p = listaPrenotazioni.get(i);

                    bottone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            /*Apro la descrizione delle partite in una nuova activity*/
                            //Se non è annullata
                            if (!p.isAnnullata()) {
                                Intent showRiepilogo_partita = new Intent(Calendario.this, Riepilogo_partita.class);
                                //Inserisco la persona dentro l'intent
                                showRiepilogo_partita.putExtra(PERSON_DA_PASSARE_2, persona);
                                showRiepilogo_partita.putExtra(PRENOTAZIONE, p);
                                startActivity(showRiepilogo_partita);
                                finish();
                            } else {
                                //Faccio vedere il motivo dell'annullamento
                                final String message = "Prenotazione del " + p.getData_evento() + (" per " +
                                        "le ore " + p.getOra_evento() + ",campo: " + p.getCampo().getNome());
                                AlertDialog diaBox = AskOption(message);
                                diaBox.show();
                            }
                        }
                    });
                }
            }

        });

        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHOME = new Intent(Calendario.this, Gestore.class);
                //Inserisco la persona dentro l'intent
                //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
                showHOME.putExtra(PERSON_DA_PASSARE, persona);
                //richiamo activity
                startActivity(showHOME);
                finish();
            }
        });
    }

        private AlertDialog AskOption(String message)
        {
            AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                    // finestra di conferma eliminazione
                    .setTitle("Partita annullata")
                    .setMessage(message)
                    .setNegativeButton("indietro", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            return myQuittingDialogBox;
        }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime setDate(long ldate){

        Date dateLong = new Date(ldate);
        Instant instant = dateLong.toInstant();
        ZoneId defaultZoneId = ZoneId.systemDefault();

        LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
        return localDateTime;
    }

    public void onBackPressed(){
        Intent showHOME = new Intent(Calendario.this, Gestore.class);
        //Inserisco la persona dentro l'intent
        //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
        //richiamo activity
        showHOME.putExtra(PERSON_DA_PASSARE, persona);

        startActivity(showHOME);
        finish();
    }
}
