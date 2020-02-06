package com.example.projectium;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
                    "Prova1","Semplice calcio","22/03/2020",7,false,"11:00"
                    );

            Prenotazione p2 = creaPrenotazione(utentiStandard.get(2),campi.get(2),
                    "Prova2","Semplice calcio","21/03/2020",8,false,"13:00"
            );

            Prenotazione p3 = creaPrenotazione(utentiStandard.get(2),campi.get(1),
                    "Prova3","Annullata per infortunio","22/03/2020",10,true,"13:00"
            );


            Prenotazione p4 = creaPrenotazione(utentiStandard.get(3),campi.get(3),
                    "Prova4","Semplice calcio","12/03/2020",9,false,"15:00"
            );


            Prenotazione p5 = creaPrenotazione(utentiStandard.get(2),campi.get(1),
                    "Prova5","Annullata per mancanza giocatori","2/03/2020",10,true,"11:00"
            );

            Prenotazione p6 = creaPrenotazione(utentiStandard.get(0),campi.get(1),
                    "Prova Annullata","Annullata per mal tempo","2/03/2020",9,true,"11:00"
            );

          /*  p1.getIscritti().add(utentiStandard.get(2));
            p1.getIscritti().add(utentiStandard.get(3));



            p2.getIscritti().add(utentiStandard.get(3));

            p6.getIscritti().add(utentiStandard.get(3));
            p5.getIscritti().add(utentiStandard.get(3));

*/
            lista.add(p1);

            lista.add(p2);
            lista.add(p3);
            lista.add(p4);
            lista.add(p5);
            lista.add(p6);


        }



    }

    public  void eliminaPrenotazioneOpuureDisdiciPrenotazione(ArrayList<Prenotazione> lista,int idPrenotazione, Persona p){
        if(lista!=null) {



            for (int i = 0; i < lista.size(); i++) {

                //Se è il titolare
                if(lista.get(i).getId()==idPrenotazione && lista.get(i).getCreatore().equals(p)){
                    //Rimuovo completamente la prenotazione nel senso setto il booleano
                    lista.get(i).setAnnullata(true);
                }else {
                    //Iscritto normale devo ciclare tutto l'array interno

                    if(lista.get(i).getId()==idPrenotazione && lista.get(i).getIscritti().contains(p)){
                        lista.get(i).getIscritti().remove(p);
                        lista.get(i).setNum_giocatori(lista.get(i).getNum_giocatori()-1);
                    }

                }




            }
        }

    }

    //Prende la lista generale delle prenotazioni , l id della prenotazione a cui si vuole aggiungere e la persona da aggiungere
    //Matcha id ed aggiunge
    public void aggiungiPartecipante(ArrayList<Prenotazione> lista,int idPrenotazione, Persona p){

        if(lista!=null) {
            for (int i = 0; i < lista.size(); i++) {

                if(lista.get(i).getId()==idPrenotazione){
                    lista.get(i).getIscritti().add(p);
                    lista.get(i).setNum_giocatori(lista.get(i).getNum_giocatori()+1);
                }

            }
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
            if(!list.get(i).isAnnullata() && !list.get(i).getCreatore().equals(p) && list.get(i).getNum_giocatori() < 10
            && !controlloIscrizioniEsterne(list.get(i).getIscritti(),p)){
                ritorno.add(list.get(i));
            }
        }

        return ritorno;
    }


    public ArrayList<Prenotazione> getPrenotazioniPerCampo(ArrayList<Prenotazione> list,Persona p,String nome){

        ArrayList<Prenotazione> ritorno = new ArrayList<>();

        for(int i = 0 ; i < list.size();i++){
            if(!list.get(i).isAnnullata() && !list.get(i).getCreatore().equals(p) && list.get(i).getNum_giocatori() < 10
                    && !controlloIscrizioniEsterne(list.get(i).getIscritti(),p) && list.get(i).getCampo().getNome().equals(nome)){
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
            if(list.get(i).getCreatore().equals(utente) || controlloIscrizioniEsterne(list.get(i).getIscritti(),utente)){
                ritorno.add(list.get(i));
            }
        }

        return ritorno;

    }


    public  void aggiungiIscrittoAllaPartita(Prenotazione p,Persona utente){
        if(!p.getIscritti().contains(utente))
            p.getIscritti().add(utente);
    }


    /*Controlla se è presente*/
    public boolean controlloIscrizioniEsterne(ArrayList<Persona> lista, Persona utente){
        return lista.contains(utente);
    }

    /*Scansiono tutte le prenotazioni , se trovo una per la stessa ora per lo stesso campo e per la stessa data impedisco all'utente
    * di creare un nuovo evento */
    public ArrayList<CampoDaCalcio> ricercaCampiLiberi(Prenotazione p, ArrayList<Prenotazione> prenotazioni,HashSet<CampoDaCalcio> listacampi){

        ArrayList<CampoDaCalcio> campi = new ArrayList<>(listacampi);
        HashSet<CampoDaCalcio> ritorno = new HashSet<>();
        for(int i=0;i<prenotazioni.size();i++){

            if(!prenotazioni.get(i).isAnnullata()){

                //Bisogna tassativamente rispettare l'ordine di inserimento nel controllo ...
                //Il controllo in pratica se trova una corrsipondenza rimuove il duplicato dalla lista geneerale dei campi
                if(controlloDataOraNuovaPartita(prenotazioni.get(i),p,campi)){

                    //DEbug
                    String ora1 = prenotazioni.get(i).getOra_evento();
                    String ora2 = p.getOra_evento();
                    String data1 = prenotazioni.get(i).getData_evento();
                    String data2 = p.getData_evento();
                    ritorno.add(prenotazioni.get(i).getCampo());
                    //Rimuovo eventuale duplicato
                    campi.remove(prenotazioni.get(i).getCampo());
                }

            }

        }


        //Puo capitare che nessun campo sia in una prenotazone quindi lo aggiungo ... tanto se ce gia il set mi impedisce di aggiungerlo
        for(int i=0;i<campi.size();i++){

            ritorno.add(campi.get(i));
        }

        return new ArrayList<CampoDaCalcio>(ritorno);
    }


    /*Prende due prenotazoni e la lista generale dei campi , se le prenotazioni sono alla stessa ora rimuovo il campo */
    public boolean controlloDataOraNuovaPartita(Prenotazione p1 ,Prenotazione p2, ArrayList<CampoDaCalcio> campi){

        if(p1.getOra_evento().equals(p2.getOra_evento()) && p1.getData_evento().equals(p2.getData_evento())) {
            campi.remove(p1.getCampo());
            return false;
        }else
            return true;

    }
}

