package com.example.projectium;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class PersonaFactory {


    private static PersonaFactory istanza;

    public static PersonaFactory getInstance() {
        if (istanza == null) {
            istanza = new PersonaFactory();
        }

        return istanza;
    }


    public Persona creaPersona(String userId, String nome, String cognome, String password, String cpassword, String email,String partite) {
        Persona user = new Persona();
        user.setUserId(userId);
        user.setNome(nome);
        user.setCognome(cognome);
        user.setPassword(password);
        user.setCPassword(cpassword);
        user.setEmail(email);
        user.setPartite("0");

        return user;
    }

    public void aggiungiUtente(HashMap<String,Persona> map,Persona persona){
        map.put(persona.getUserId(),persona);
    }


    public  void setPersoneDefault(HashMap<String,Persona> map){

        Persona persona1 = this.creaPersona("luca","Luca","Ladu","1234","1234","laduluca95@hotm.it","0");

        Persona persona2 = this.creaPersona("frank","Francesco","Podda","1234","1234","frk@hotmail.it","0");

        Persona persona3 = this.creaPersona("Dshot92","Daniele","Stochino","1234","1234","Dshot92@hotmail.it","3");

        Persona persona4 = this.creaPersona("Iorge","Giorgio","Fanni","1234","1234","Gf96@hotm.it","23");

        map.put(persona1.getUserId(),persona1);
        map.put(persona2.getUserId(),persona2);
        map.put(persona3.getUserId(),persona3);
        map.put(persona4.getUserId(),persona4);


    }

    public ArrayList<Persona> getUtentiArray( HashMap<String,Persona> map){


        Collection<Persona> set = map.values();
        ArrayList<Persona> utenti = new ArrayList<>(set);

        return  utenti;

    }

}
