package com.example.projectium;

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

}

