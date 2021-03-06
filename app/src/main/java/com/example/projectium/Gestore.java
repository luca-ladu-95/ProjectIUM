package com.example.projectium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;

public class Gestore extends AppCompatActivity {

    Persona persona;
    TextView nome;
    CardView prenotazioni,inserisci_campo,indietro,profilo;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestore);

        //Setta il colore della status bar
        Window window = Gestore.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Gestore.this, R.color.colorPrimaryDarkStatusBar));
        // infe colore della status bar

        prenotazioni = findViewById(R.id.gestore_calendario);
        inserisci_campo = findViewById(R.id.gestore_inserisci_campo);
        nome = findViewById(R.id.nome_gestore);
        indietro = findViewById(R.id.gestore_esci);
        profilo = findViewById(R.id.gestore_profilo);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }

        nome.setText("Ciao, " + persona.getNome());



        inserisci_campo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHOME = new Intent(Gestore.this, Inserisci_campo.class);
                //Inserisco la persona dentro l'intent
                showHOME.putExtra(PERSON_DA_PASSARE_2, persona);
                //richiamo activity
                startActivity(showHOME);
                finish();
            }
        });

        prenotazioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showCalendario = new Intent(Gestore.this, Calendario.class);
                //Inserisco la persona dentro l'intent
                showCalendario.putExtra(PERSON_DA_PASSARE_2, persona);
                //richiamo activity
                startActivity(showCalendario);
                finish();
            }
        });

        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Apre il messaggio di conferma uscita
                AlertDialog diaBox = AskOption();
                diaBox.show();
            }
        });

        profilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showHOME = new Intent(Gestore.this, Profilo.class);
                //Inserisco la persona dentro l'intent
                showHOME.putExtra(PERSON_DA_PASSARE_2, persona);
                //richiamo activity
                startActivity(showHOME);
                finish();
            }
        });

    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // finestra di conferma eliminazione
                .setTitle("Logout")
                .setMessage("Sei sicuro di voler effettuare il logout?")


                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        persona = null;
                        Intent showLogin = new Intent(Gestore.this, Login.class);
                        showLogin.putExtra(PERSON_DA_PASSARE, persona);
                        startActivity(showLogin);
                        dialog.dismiss();
                        finish();

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

    public void onBackPressed() {

        //Apre il messaggio di conferma uscita
        AlertDialog diaBox = AskOption();
        diaBox.show();


    }
}
