package com.example.projectium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import static com.example.projectium.Login.utenti;


public class Registrazione extends AppCompatActivity {


    TextInputLayout username, nome, cognome, pass, cpass, email;
    Button registrati;
    TextView login;
    Persona utente;
    RadioButton button_gestore,button_utente;
    RadioGroup radioGroup;
    boolean isGestore;

    public static final String PERSON_DA_PASSARE2 = "package com.example.projectium";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        //Setta il colore della status bar
        Window window = Registrazione.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Registrazione.this, R.color.colorPrimaryDarkStatusBar));
        // infe colore della status bar


        //creo l'oggetto Persona
        utente = new Persona();

        //recupero gli id per i vari campi
        radioGroup=findViewById(R.id.radio_group);
        button_gestore = findViewById(R.id.radio_gestore);
        button_utente = findViewById(R.id.radio_utente);
        username = findViewById(R.id.inputUsername);
        nome = findViewById(R.id.inputNome);
        cognome = findViewById(R.id.inputCognome);
        pass = findViewById(R.id.inputPass);
        cpass = findViewById(R.id.inputCPass);
        email = findViewById(R.id.inputEmail);
        registrati = findViewById(R.id.button_gestore_conferma);
        login = findViewById(R.id.back_login);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radio_gestore:
                        isGestore=true;
                        break;
                    case R.id.radio_utente:
                        // do operations specific to this selection
                        isGestore=false;
                        break;
                }
            }
        });

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkInput()){
                    //aggiorno il contenuto di persona
                    updatePerson();



                    utenti.put(utente.getUserId(), utente);


                    Context context = getApplicationContext();
                    CharSequence text = "Registrazione effettuata con successo ";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    //creo l'oggetto di tipo intent che ci serve per far comunicare le 2 activity
                    Intent collegamento = new Intent(Registrazione.this, Login.class);

                    //inserisco i dati di persona dentro il nuovo intent

                    collegamento.putExtra(PERSON_DA_PASSARE2, utente);

                    //richiama l'activity che vogliamo visualizzare (da ripetere per i bottoni)
                    startActivity(collegamento);

                    finish();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Registrazione.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean checkInput(){
        //controllo per lo username
        boolean errors = false;
        if(username.getEditText().getText() == null || username.getEditText().getText().length() == 0){
            username.setError("Inserire username");
            errors = true;
        }else{
            username.setError(null);
        }

        //controllo per il nome
        if(nome.getEditText().getText() == null || nome.getEditText().getText().length() == 0){
            nome.setError("Inserire il nome");
            errors = true;
        }else{
            nome.setError(null);
        }

        //controllo per il cognome
        if(cognome.getEditText().getText() == null || cognome.getEditText().getText().length() == 0){
            cognome.setError("Inserire il cognome");
            errors = true;
        }else{
            cognome.setError(null);
        }

        //controllo per la password
        if(pass.getEditText().getText() == null || pass.getEditText().getText().length() == 0){
            pass.setError("Inserire password");
            errors = true;
        }else{
            pass.setError(null);
        }


        //controllo per l'inserimento della password di conferma
        if(cpass.getEditText().getText() == null || cpass.getEditText().getText().length() == 0 || !(cpass.getEditText().getText().toString().equals(pass.getEditText().getText().toString()))){
            cpass.setError("Inserire la stessa password");
            errors = true;
        }else{
            cpass.setError(null);
        }

        //controllo per l'email da rimettere
        String emailPattern = "[a-zA-Z1-9._-]+@[a-z]+\\.+[a-z]+";
        if(email.getEditText().getText() == null || email.getEditText().getText().length() == 0){
            email.setError("Inserire email");
            errors = true;
        }else if (!email.getEditText().getText().toString().matches(emailPattern)){
            email.setError("L'email inserita non è valida");
            errors = true;
        }else{
            email.setError(null);
        }
        return errors;
    }

    private void updatePerson(){
        //aggiorno il contenuto di persona usando i dati inseriti dall'utente in fase di registrazione
        this.utente.setUserId(this.username.getEditText().getText().toString());
        this.utente.setNome(this.nome.getEditText().getText().toString());
        this.utente.setCognome(this.cognome.getEditText().getText().toString());
        this.utente.setPassword(this.pass.getEditText().getText().toString());
        this.utente.setCPassword(this.cpass.getEditText().getText().toString());
        this.utente.setEmail(this.email.getEditText().getText().toString());
        this.utente.setGestore(isGestore);
        this.utente.setPartite("0");
    }
}
