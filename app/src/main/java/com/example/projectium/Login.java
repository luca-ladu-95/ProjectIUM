package com.example.projectium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
public class Login extends AppCompatActivity {

    Button loginButton,registratiButton;
    EditText username,password;
    Persona utente;
    TextView errorText;
    static HashMap<String, Persona> utenti = new HashMap<String, Persona>();

    public static final String PERSON_DA_PASSARE = "package com.example.projectium";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        utente = new Persona();
        username=findViewById(R.id.editUsername);
        password=findViewById(R.id.editPassword);
        loginButton=findViewById(R.id.buttonLogin);
        registratiButton=findViewById(R.id.buttonRegistrati);
        /*Debug codice*/
        Persona persona1= new Persona("luca","Luca","Ladu","1234");
        utenti.put("luca",persona1);

        /*Fine debug*/


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (checkInput() && checkUtente(username.getText().toString(), utenti)) {


                    // Mi prendo la persona dalla lista
                    utente = utenti.get(username.getText().toString());

                    //creo oggetto per far comunicare le activity
                    Intent showHOME = new Intent(Login.this, Home.class);
                    //Inserisco la persona dentro l'intent
                    showHOME.putExtra(PERSON_DA_PASSARE, utente);
                    //richiamo activity
                    startActivity(showHOME);


                }


            }
        });
    }





    private boolean checkInput() {
        boolean flag = true;
        if (username.getText() == null || username.getText().length() == 0) {
            username.setError("Inserisci l'username");
            flag = false;
        } else {
            username.setError(null);

        }

        if (password.getText() == null || password.getText().length() == 0) {
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
            username.setText(username.getText());
            //controllo se la password inserita corrsponde a quella nel set
            if (!temp.getPassowrd().equals(password.getText().toString())) {
                flag = false;
                password.setError("La password inserita non corrisponde");
            } else {
                password.setError(null);


            }

        }

        return flag;
    }


    public void setUtenti(HashMap<String, Persona> utenti) {
        this.utenti = utenti;
    }
}
