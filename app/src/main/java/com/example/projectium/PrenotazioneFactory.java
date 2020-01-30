package com.example.projectium;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.projectium.Login.utenti;

public class PrenotazioneFactory  implements Serializable {

    private  int contatoreID = 0;

    private static PrenotazioneFactory istanza;
/*Restituisce istanza di prenotazione*/
    public static PrenotazioneFactory getInstance() {
        if (istanza == null) {
            istanza = new PrenotazioneFactory();
        }

        return istanza;
    }

    /*Permette di creare una prenotazione*/
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
        prenotazione.setCampo(campo);

        contatoreID++;

        return prenotazione;


    }


    /*Prenotazioni di defaul per fare debug*/
    public void setPrenotazioniStandard(ArrayList<Prenotazione> lista,HashMap<String,Persona> utenti){

        if(lista== null ||lista.isEmpty()){


            ArrayList<Persona> utentiStandard = PersonaFactory.getInstance().getUtentiArray(utenti);
            ArrayList<CampoDaCalcio> campi = CampoDaCalcioFactory.getInstance().getCampiDefault();


            Prenotazione p1 = creaPrenotazione(utentiStandard.get(1),campi.get(1),
                    "Prova1","Semplice calcio","22-03-2020",7,false,"11:00"
                    );

            Prenotazione p2 = creaPrenotazione(utentiStandard.get(2),campi.get(2),
                    "Prova2","Semplice calcio","21-03-2020",8,false,"13:00"
            );

            Prenotazione p3 = creaPrenotazione(utentiStandard.get(2),campi.get(1),
                    "Prova3","Semplice calcio","22-03-2020",10,false,"13:00"
            );


            Prenotazione p4 = creaPrenotazione(utentiStandard.get(3),campi.get(3),
                    "Prova4","Semplice calcio","12-03-2020",9,false,"15:00"
            );


            Prenotazione p5 = creaPrenotazione(utentiStandard.get(3),campi.get(1),
                    "Prova5","Semplice calcio","2-03-2020",10,false,"11:00"
            );

            Prenotazione p6 = creaPrenotazione(utentiStandard.get(3),campi.get(1),
                    "Prova Annullata","Semplice calcio","2-03-2020",9,true,"11:00"
            );

            lista.add(p1);

            lista.add(p2);
            lista.add(p3);
            lista.add(p4);
            lista.add(p5);
            lista.add(p6);


        }



    }

    /*Aggiunge una prenotazione*/
    public boolean   aggiungiPrenotazione (ArrayList<Prenotazione> lista, Prenotazione p){

        if(lista.contains(p)) return  false;
        else {
            lista.add(p);
            return true;
        }
    }


    /*Restituisce prenotazioni NON ANNULLATE naturlamente non ha senso che uno possa partecipare alla propria partita
    * e naturalmente deve avere meno di 10 giocatori */
    public ArrayList<Prenotazione> getPrenotazioniInCorso(ArrayList<Prenotazione> list,Persona p){

        ArrayList<Prenotazione> ritorno = new ArrayList<>();

        for(int i = 0 ; i < list.size();i++){
            if(!list.get(i).isAnnullata() && !list.get(i).getCreatore().equals(p) && list.get(i).getNum_giocatori() < 10){
                ritorno.add(list.get(i));
            }
        }

        return ritorno;
    }

    /*Restituisce partite ANNULLATE*/

    public ArrayList<Prenotazione> getPrenotazioniAnnullate(ArrayList<Prenotazione> list){

        ArrayList<Prenotazione> ritorno = new ArrayList<>();

        for(int i = 0 ; i < list.size();i++){
            if(list.get(i).isAnnullata()){
                ritorno.add(list.get(i));
            }
        }

        return ritorno;
    }


    /*Restituisce prenotazioni di un Utente*/

    public ArrayList<Prenotazione> getPrenotazioneUtente(ArrayList<Prenotazione> list,Persona utente){
        ArrayList<Prenotazione> ritorno = new ArrayList<>();

        for(int i = 0 ; i < list.size();i++){
            if(list.get(i).getCreatore().equals(utente)){
                ritorno.add(list.get(i));
            }
        }

        return ritorno;

    }
}

