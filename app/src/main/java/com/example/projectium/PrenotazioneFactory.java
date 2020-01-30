package com.example.projectium;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.projectium.Login.utenti;

public class PrenotazioneFactory {

    private  int contatoreID = 0;

    private static PrenotazioneFactory istanza;

    public static PrenotazioneFactory getInstance() {
        if (istanza == null) {
            istanza = new PrenotazioneFactory();
        }

        return istanza;
    }

    public Prenotazione creaPrenotazione(Persona user, CampoDaCalcio campo, String nome , String descrizione , String data , int num_G , boolean annulla , String ora){

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setCreatore(user);
        prenotazione.setAnnullata(annulla);
        prenotazione.setData_evento(data);
        prenotazione.setId(contatoreID);
        prenotazione.setNum_giocatori(num_G);
        prenotazione.setNome_evento(nome);
        prenotazione.setDescrizione(descrizione);
        prenotazione.setOra_evento(ora);

        contatoreID++;

        return prenotazione;


    }


    public void setPrenotazioniStandard(ArrayList<Prenotazione> lista,HashMap<String,Persona> utenti){

        if(lista== null ||lista.isEmpty()){


            ArrayList<Persona> utentiStandard = PersonaFactory.getInstance().getUtentiArray(utenti);
            ArrayList<CampoDaCalcio> campi = CampoDaCalcioFactory.getInstance().getCampiDefault();


            Prenotazione p1 = creaPrenotazione(utentiStandard.get(1),campi.get(1),
                    "Prova1","Semplice calcio","22-03-2020",10,false,"11:00"
                    );

            Prenotazione p2 = creaPrenotazione(utentiStandard.get(1),campi.get(2),
                    "Prova2","Semplice calcio","21-03-2020",8,false,"13:00"
            );

            Prenotazione p3 = creaPrenotazione(utentiStandard.get(2),campi.get(1),
                    "Prova3","Semplice calcio","22-03-2020",10,false,"13:00"
            );


            Prenotazione p4 = creaPrenotazione(utentiStandard.get(2),campi.get(3),
                    "Prova4","Semplice calcio","12-03-2020",9,false,"15:00"
            );


            Prenotazione p5 = creaPrenotazione(utentiStandard.get(3),campi.get(1),
                    "Prova5","Semplice calcio","2-03-2020",10,false,"11:00"
            );


            lista.add(p1);

            lista.add(p2);
            lista.add(p3);
            lista.add(p4);
            lista.add(p5);


        }



    }

    public boolean   aggiungiPrenotazione (ArrayList<Prenotazione> lista, Prenotazione p){

        if(lista.contains(p)) return  false;
        else {
            lista.add(p);
            return true;
        }
    }

}

