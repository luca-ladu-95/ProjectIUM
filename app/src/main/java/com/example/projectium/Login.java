package com.example.projectium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Login extends AppCompatActivity {

    Button loginButton;
    ImageView image;
    TextInputLayout username,password;
    TextView logo_text, registrati;
    Persona utente;
/*ATTENZIONE QUESTE SONO LE 3 STRUTTURE DATI SU CUI SI APPOGGIA TUTTO IL PROGRAMMA  */
    static HashMap<String, Persona> utenti = new HashMap<String, Persona>(); //Contiente tutti gli utenti
    static HashSet<CampoDaCalcio> listaCampi = new HashSet<>(); // Contiente tutti i campi
    static ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<>();




    public static final String PERSON_DA_PASSARE = "package com.example.projectium";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if (ContextCompat.checkSelfPermission(Login.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(Login.this, Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(Login.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else{
                ActivityCompat.requestPermissions(Login.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

        /*Gli popolo solo se prima erano vuoti */
        if(listaCampi== null || listaCampi.isEmpty()) {
            CampoDaCalcioFactory.getInstance().setCampiDefault(listaCampi);
            Valutazione_campo.getInstance().set_valutazioni_default(listaCampi);
        }
        if(utenti==null || utenti.isEmpty()) {
            PersonaFactory.getInstance().setPersoneDefault(utenti);
        }

        if(listaPrenotazioni == null || listaPrenotazioni.isEmpty()) {
            PrenotazioneFactory.getInstance().setPrenotazioniStandard(listaPrenotazioni, utenti);
        }




        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        utente = new Persona();
        username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        image = findViewById(R.id.logo_image);
        logo_text = findViewById(R.id.logo_name);
        loginButton = findViewById(R.id.buttonLogin);
        registrati = findViewById(R.id.registrazione);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (checkInput() && checkUtente(username.getEditText().getText().toString(), utenti)) {


                    // Mi prendo la persona dalla lista
                    utente = utenti.get(username.getEditText().getText().toString());

                    if(!utente.isGestore()) {
                        //creo oggetto per far comunicare le activity
                        Intent showHOME = new Intent(Login.this, Home.class);
                        //Inserisco la persona dentro l'intent
                        showHOME.putExtra(PERSON_DA_PASSARE, utente);
                        //richiamo activity
                        startActivity(showHOME);
                        finish();
                    }
                    else{
                        Intent showHOME = new Intent(Login.this, Gestore.class);
                        //Inserisco la persona dentro l'intent
                        showHOME.putExtra(PERSON_DA_PASSARE, utente);
                        //richiamo activity
                        startActivity(showHOME);

                        finish();
                    }

                }
            }
        });


        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Login.this, Registrazione.class);

                Pair[] pairs = new Pair[6];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logo_text, "logo_text");
                pairs[2] = new Pair<View, String>(username, "user_transition");
                pairs[3] = new Pair<View, String>(password, "pass_transition");
                pairs[4] = new Pair<View, String>(loginButton, "button_transition");
                pairs[5] = new Pair<View, String>(registrati, "reg_transition");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });
    }

    private boolean checkInput() {
        boolean flag = true;
        if (username.getEditText().getText() == null || username.getEditText().getText().length() == 0) {
            username.setError("Inserisci l'username");
            flag = false;
        } else {
            username.setError(null);

        }

        if (password.getEditText().getText() == null || password.getEditText().getText().length() == 0) {
            password.setError("Inserisci la password");
            flag = false;
        } else {
            password.setError(null);
        }

        return flag;
    }

    private boolean checkUtente(String us, HashMap<String, Persona> map) {
        boolean flag = true;
        Persona temp;

        if (map.get(us) == null) {
            //non esiste proprio l'utente
            username.setError("Lo username inserito non è valido");
            flag = false;
        } else {
            //Salvo la persona e controllo la password
            temp = map.get(us);
            username.setError(null);
            username.getEditText().getText();
            //controllo se la password inserita corrsponde a quella nel set
            if (!temp.getPassword().equals(password.getEditText().getText().toString())) {
                flag = false;
                password.setError("La password inserita non corrisponde");
            } else {
                password.setError(null);
            }

        }

        return flag;
    }

    public void onBackPressed() {

        finish();
    }

}
