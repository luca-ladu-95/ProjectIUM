package com.example.projectium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.listaPrenotazioni;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;

public class Calendario extends AppCompatActivity {

    CalendarView calendar;
    //TextView selectedDate;
    Button indietro;
    Persona persona;
    LinearLayout linearLayout1;
    TextView nessunaP;
    Space space;
    Button bottone;
    String correctedMonth;
    Date currentTime,dataEvento;
    boolean flagdata;

    public static final String BOOLEANO_CALENDARIO = "package com.example.projectium.Calendario";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        //Setta il colore della status bar
        Window window = Calendario.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Calendario.this, R.color.colorPrimaryDarkStatusBar));
        // infe colore della status bar


        calendar = findViewById(R.id.calendarView);
        //selectedDate = findViewById(R.id.textView4); //textView4 punta alla stringa nome in profilo.xml
        indietro=findViewById(R.id.button_return_prenotazioni_effettuate);
        linearLayout1 = findViewById(R.id.layout_prenotazioni_del_giorno);
        nessunaP =findViewById(R.id.calendario_nessunaP);


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

                linearLayout1.removeAllViews();


                //CASO PERSONA è GESTORE
                if(persona.isGestore()) {
                    for (int i = 0; i < listaPrenotazioni.size(); i++) {

                        //Imposto un font da utilizzare sui bottoni generati dinamicamente
                        Typeface typeface = ResourcesCompat.getFont(getBaseContext(), R.font.baloo);

                        space = new Space(Calendario.this);
                        bottone = new Button(Calendario.this);

                        space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 20));
                        bottone.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        bottone.setTextColor(getResources().getColor(R.color.bianco));
                        bottone.setBackgroundResource(R.drawable.textbox);
                        bottone.setGravity(Gravity.CENTER);
                        bottone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
                        bottone.setId(i);
                        bottone.setTypeface(typeface);
                        bottone.setText(listaPrenotazioni.get(i).getNome_evento());

                        if (!listaPrenotazioni.get(i).isAnnullata() && listaPrenotazioni.get(i).getData_evento().equals(giornoPremuto)) {
                            conto_partite_prenotate++;
                            linearLayout1.addView(bottone);
                            linearLayout1.addView(space);
                        }

                        //SVUOTA LISTA SE NON CI SONO PRENOTAZIONI
                        if (conto_partite_prenotate == 0){
                            linearLayout1.removeAllViews();
                            nessunaP.setText("Nessuna prenotazione");

                        }else{
                            nessunaP.setText("");
                        }

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
                }else{   //CASO PERSONA è UTENTE

                    ArrayList<Prenotazione> listaPrenotazione_Utente =  PrenotazioneFactory.getInstance().getPrenotazioniInCorso(listaPrenotazioni, persona);
                    for (int i = 0; i < listaPrenotazione_Utente.size(); i++) {

                        //Imposto un font da utilizzare sui bottoni generati dinamicamente
                        Typeface typeface = ResourcesCompat.getFont(getBaseContext(), R.font.baloo);

                        space = new Space(Calendario.this);
                        bottone = new Button(Calendario.this);

                        space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 20));
                        bottone.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        bottone.setTextColor(getResources().getColor(R.color.bianco));
                        bottone.setBackgroundResource(R.drawable.textbox);
                        bottone.setGravity(Gravity.CENTER);
                        bottone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
                        bottone.setId(i);
                        bottone.setTypeface(typeface);
                        bottone.setText(listaPrenotazione_Utente.get(i).getNome_evento());

                        // CONFRONTO LE DATE DEGLI EVENTI A QUELLA ODIERNA SE SONO PASSATE SONO DA VALUTARE
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        currentTime = Calendar.getInstance().getTime();
                        try {
                            dataEvento = format.parse(listaPrenotazione_Utente.get(i).getData_evento());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        flagdata = dataEvento.after(currentTime);

                        if (flagdata && !listaPrenotazione_Utente.get(i).isAnnullata() && listaPrenotazione_Utente.get(i).getData_evento().equals(giornoPremuto)) {
                            conto_partite_prenotate++;
                            linearLayout1.addView(bottone);
                            linearLayout1.addView(space);
                        }

                        //SVUOTA LISTA SE NON CI SONO PRENOTAZIONI
                        if (conto_partite_prenotate == 0){
                            linearLayout1.removeAllViews();
                            nessunaP.setText("Nessuna prenotazione");

                        }else{
                            nessunaP.setText("");
                        }

                        final Prenotazione p = listaPrenotazione_Utente.get(i);

                        bottone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                /*Apro la descrizione delle partite in una nuova activity*/
                                //Se non è annullata
                                if (!p.isAnnullata()) {
                                    Intent showRiepilogo_partita = new Intent(Calendario.this, Prenota_da_Partecipa_partita.class);
                                    //Inserisco la persona dentro l'intent
                                    showRiepilogo_partita.putExtra(PERSON_DA_PASSARE_2, persona);
                                    showRiepilogo_partita.putExtra(PRENOTAZIONE, p);
                                    showRiepilogo_partita.putExtra(BOOLEANO_CALENDARIO,true);
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
            }

        });

        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( persona.isGestore() ){
                    Intent showHOME = new Intent(Calendario.this, Gestore.class);
                    //Inserisco la persona dentro l'intent
                    //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
                    //richiamo activity
                    showHOME.putExtra(PERSON_DA_PASSARE, persona);

                    startActivity(showHOME);
                    finish();
                } else {
                    Intent showHOME = new Intent(Calendario.this, Home.class);
                    //Inserisco la persona dentro l'intent
                    //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
                    //richiamo activity
                    showHOME.putExtra(PERSON_DA_PASSARE, persona);

                    startActivity(showHOME);
                    finish();
                }
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

        if( persona.isGestore() ){
        Intent showHOME = new Intent(Calendario.this, Gestore.class);
        //Inserisco la persona dentro l'intent
        //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
        //richiamo activity
        showHOME.putExtra(PERSON_DA_PASSARE, persona);

        startActivity(showHOME);
        finish();
        } else {
            Intent showHOME = new Intent(Calendario.this, Home.class);
            //Inserisco la persona dentro l'intent
            //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
            //richiamo activity
            showHOME.putExtra(PERSON_DA_PASSARE, persona);

            startActivity(showHOME);
            finish();
        }
    }
}
