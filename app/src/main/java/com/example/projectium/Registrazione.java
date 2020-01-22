package com.example.projectium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Login.utenti;


public class Registrazione extends AppCompatActivity {


    EditText username, name, surname, pass, cpass, email;
    Button registrati;
    Persona utente;

    public static final String PERSON_DA_PASSARE2 = "package com.example.projectium";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        //getSupportActionBar().hide();

        //creo l'oggetto Persona
        utente = new Persona();

        //recupero gli id per i vari campi
        username = findViewById(R.id.inputUsername);
        name = findViewById(R.id.inputNome);
        surname = findViewById(R.id.inputCognome);
        pass = findViewById(R.id.inputPass);
        cpass = findViewById(R.id.inputCPass);
        email = findViewById(R.id.inputEmail);
        registrati = findViewById(R.id.button_reg);



        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkInput()){
                    //aggiorno il contenuto di persona
                    updatePerson();

                    utenti.put(utente.getUserId(), utente);

                    //creo l'oggetto di tipo intent che ci serve per far comunicare le 2 activity
                    Intent collegamento = new Intent(Registrazione.this, Home.class);

                    //inserisco i dati di persona dentro il nuovo intent

                    collegamento.putExtra(PERSON_DA_PASSARE, utente);

                    //richiama l'activity che vogliamo visualizzare (da ripetere per i bottoni)
                    startActivity(collegamento);

                    finish();
                }
            }
        });
    }

    private boolean checkInput(){
        //controllo per lo username
        boolean errors = false;
        if(username.getText() == null || username.getText().length() == 0){
            username.setError("Inserire username");
            errors = true;
        }else{
            username.setError(null);
        }

        //controllo per il nome
        if(name.getText() == null || name.getText().length() == 0){
            name.setError("Inserire il nome");
            errors = true;
        }else{
            name.setError(null);
        }

        //controllo per il cognome
        if(surname.getText() == null || surname.getText().length() == 0){
            surname.setError("Inserire il cognome");
            errors = true;
        }else{
            surname.setError(null);
        }

        //controllo per la password
        if(pass.getText() == null || pass.getText().length() == 0){
            pass.setError("Inserire password");
            errors = true;
        }else{
            pass.setError(null);
        }

        //controllo per l'inserimento della password di conferma
        if(cpass.getText() == null || cpass.getText().length() == 0 || !(cpass.getText().toString().equals(pass.getText().toString()))){
            cpass.setError("Inserire la stessa password");
            errors = true;
        }else{
            cpass.setError(null);
        }

        //controllo per l'email
        if(email.getText() == null || email.getText().length() == 0){
            email.setError("Inserire email");
            errors = true;
        }else{
            email.setError(null);
        }
        return errors;
    }

    private void updatePerson(){
        //aggiorno il contenuto di persona usando i dati inseriti dall'utente in fase di registrazione
        this.utente.setUserId(this.username.getText().toString());
        this.utente.setNome(this.name.getText().toString());
        this.utente.setCognome(this.surname.getText().toString());
        this.utente.setPassword(this.pass.getText().toString());
        this.utente.setCPassword(this.cpass.getText().toString());
        this.utente.setEmail(this.email.getText().toString());
        this.utente.setPartite("0");
    }

}
