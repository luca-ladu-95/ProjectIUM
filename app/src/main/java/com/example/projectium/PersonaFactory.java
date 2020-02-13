package com.example.projectium;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class PersonaFactory {

    private static PersonaFactory istanza;

    public static PersonaFactory getInstance() {
        if (istanza == null) {
            istanza = new PersonaFactory();
        }
        return istanza;
    }

    public Persona creaPersona(String userId, String nome, String cognome, String password, String cpassword, String email,String partite,boolean gestore) {
        Persona user = new Persona();
        user.setUserId(userId);
        user.setNome(nome);
        user.setCognome(cognome);
        user.setPassword(password);
        user.setCPassword(cpassword);
        user.setEmail(email);
        user.setPartite(partite);
        user.setGestore(gestore);

        return user;
    }

    public void aggiungiUtente(HashMap<String,Persona> map,Persona persona){
        map.put(persona.getUserId(),persona);
    }

    public  void setPersoneDefault(HashMap<String,Persona> map){
         boolean gestore = true;
         boolean utente = false;

        Persona persona1 = this.creaPersona("luca","Luca","Ladu","1234","1234","laduluca95@hotm.it","0",utente);
        Persona persona2 = this.creaPersona("frank","Francesco","Podda","1234","1234","frk@hotmail.it","0",utente);
        Persona persona3 = this.creaPersona("d","Daniele","Stochino","d","d","Dshot92@hotmail.it","3",utente);
        Persona persona4 = this.creaPersona("Jorge","Giorgio","Fanni","1234","1234","Gf96@hotm.it","23",utente);
        Persona persona5 = this.creaPersona("Z","Admin","admin","admin","admin","Gf96@hotm.it","23",gestore);
        Persona persona6 = this.creaPersona("a","Admin","admin","a","a","Gf96@hotm.it","23",gestore);

        map.put(persona1.getUserId(),persona1);
        map.put(persona2.getUserId(),persona2);
        map.put(persona3.getUserId(),persona3);
        map.put(persona4.getUserId(),persona4);
        map.put(persona5.getUserId(),persona5);
        map.put(persona6.getUserId(),persona6);

    }

    public ArrayList<Persona> getUtentiArray( HashMap<String,Persona> map){

        Collection<Persona> set = map.values();
        ArrayList<Persona> utenti = new ArrayList<>(set);

        return  utenti;
    }

    public void rimuoviUtente(HashMap<String, Persona> utenti , ArrayList<Prenotazione> listaPrenotazioni,Persona utente){

        //Rimuovo tutte le prenotazioni
        for(int i=0 ; i< listaPrenotazioni.size();i++){

            if(listaPrenotazioni.get(i).getCreatore().equals(utente)){
                listaPrenotazioni.remove(listaPrenotazioni.get(i));
            }else{
                if(listaPrenotazioni.get(i).getIscritti().contains(utente)){
                    listaPrenotazioni.get(i).getIscritti().remove(utente);
                }
            }
        }
       utenti.remove(utente.getUserId());
    }
}
