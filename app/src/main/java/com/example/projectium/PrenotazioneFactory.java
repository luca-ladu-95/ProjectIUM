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
    public Prenotazione creaPrenotazione(Persona user, CampoDaCalcio campo, String nome , String descrizione , String data , int num_G , boolean annulla , String ora,boolean valutata){

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
        prenotazione.setValutata(valutata);

        contatoreID++;

        return prenotazione;
    }

    /*Prenotazioni di defaul per fare debug*/
    public void setPrenotazioniStandard(ArrayList<Prenotazione> lista,HashMap<String,Persona> utenti){

        if(lista== null ||lista.isEmpty()){

            ArrayList<Persona> utentiStandard = PersonaFactory.getInstance().getUtentiArray(utenti);
            ArrayList<CampoDaCalcio> campi = CampoDaCalcioFactory.getInstance().getCampiDefault();

            /*
            *   FRANK = 0
            * DANIELE = 1
            *    LUCA = 2
            *   JORGE = 4
            * */

            // FRANK
            // In Corso
            lista.add(creaPrenotazione(utentiStandard.get(0),campi.get(5),"Calcio Champagne","Puro Divertimento","3/03/2020",9,false,"15:00",false));
            lista.add(creaPrenotazione(utentiStandard.get(0),campi.get(3),"Vamonos!","Annullata per mal tempo","2/03/2020",9,false,"13:00",false));
            // Annullate
            lista.add(creaPrenotazione(utentiStandard.get(0),campi.get(1),"Prova1 Annullata","Annullata per mal tempo","2/03/2020",9,true,"11:00",false));
            lista.add(creaPrenotazione(utentiStandard.get(0),campi.get(2),"Prova2 Annullata","Annullata per mal tempo","2/03/2020",9,true,"12:00",false));
            lista.add(creaPrenotazione(utentiStandard.get(0),campi.get(5),"Prova5 Annullata","Annullata per mal tempo","2/03/2020",9,true,"15:00",false));
            lista.add(creaPrenotazione(utentiStandard.get(0),campi.get(4),"Prova4 Annullata","Annullata per mal tempo","2/03/2020",9,true,"14:00",false));
            // Da Valutare
            lista.add(creaPrenotazione(utentiStandard.get(0),campi.get(4),"Sciobalaaa","tesa","18/02/2020",10,false,"17:00",true));
            lista.add(creaPrenotazione(utentiStandard.get(0),campi.get(4),"Baldoria","Amiconi","20/02/2020",10,false,"14:00",true));


            // DANELE
            // In Corso
            lista.add(creaPrenotazione(utentiStandard.get(1),campi.get(1),"Prova10","Semplice calcio","22/03/2020",7,false,"11:00",false));
            lista.add(creaPrenotazione(utentiStandard.get(1),campi.get(2),"Prova11","Semplice calcio","23/03/2020",4,false,"10:00",false));
            // Annullate
            lista.add(creaPrenotazione(utentiStandard.get(1),campi.get(2),"Joga Bonito","Olè!","25/03/2020",7, true,"21:00",false));
            lista.add(creaPrenotazione(utentiStandard.get(2),campi.get(1),"Prova3","Annullata per infortunio","22/03/2020",10,true,"13:00",false));
            lista.add(creaPrenotazione(utentiStandard.get(2),campi.get(5),"Prova5","Annullata per mancanza giocatori","2/03/2020",10,true,"11:00",false));
            // Da Valutare
            lista.add(creaPrenotazione(utentiStandard.get(1),campi.get(3),"Da Aimone","Il terzo tempo conta piu dei primi due","19/02/2020",10,false,"22:00",true));
            lista.add(creaPrenotazione(utentiStandard.get(1),campi.get(6),"Da Aimone - il ritorno","I supertstiti","18/02/2020",10,false,"22:00",true));

            // LUCA
            // In Corso
            lista.add(creaPrenotazione(utentiStandard.get(2),campi.get(2),"Prova2","Semplice calcio","21/03/2020",8,false,"15:00",false));
            lista.add(creaPrenotazione(utentiStandard.get(2),campi.get(4),"Vecchia","Semplice calcio","21/03/2019",8,false,"20:00",false));
            lista.add(creaPrenotazione(utentiStandard.get(2),campi.get(1),"Vecchia2","Semplice calcio","22/03/2019",8,false,"19:00",false));
            // Annullate
            lista.add(creaPrenotazione(utentiStandard.get(2),campi.get(3),"Vecchia2","Semplice calcio","22/03/2019",8,true,"17:00",false));
            lista.add(creaPrenotazione(utentiStandard.get(2),campi.get(2),"Vecchia2","Semplice calcio","24/03/2019",5,true,"21:00",false));
            // Da Valutare
            lista.add(creaPrenotazione(utentiStandard.get(2),campi.get(5),"Vecchia2","Semplice calcio","18/02/2019",10,false,"11:00",true));
            lista.add(creaPrenotazione(utentiStandard.get(2),campi.get(1),"Marvel's - Spaccalegna","Assemble on me!","24/02/2019",10,false,"21:00",true));

            // JORGE
            // In Corso
            lista.add(creaPrenotazione(utentiStandard.get(4),campi.get(3),"Partitona Sbronza","Calcio sbronzo","29/02/2020",9,false,"15:00",false));
            lista.add(creaPrenotazione(utentiStandard.get(4),campi.get(4),"MESSIng about","Tiki Taka","13/03/2020",4,false,"16:00",false));
            lista.add(creaPrenotazione(utentiStandard.get(4),campi.get(3),"Prova4","Semplice calcio","28/02/2020",10,false,"19:00",false));
            // Annullate
            lista.add(creaPrenotazione(utentiStandard.get(4),campi.get(7),"episcoPALI","San pietro","23/03/2020",3,true,"20:00",false));
            lista.add(creaPrenotazione(utentiStandard.get(4),campi.get(7),"Prova4","Semplice calcio","25/03/2020",4,true,"20:00",false));
            // Da Valutare
            lista.add(creaPrenotazione(utentiStandard.get(4),campi.get(7),"Prova4","Semplice calcio","25/03/2020",10,false,"20:00",true));
            lista.add(creaPrenotazione(utentiStandard.get(4),campi.get(7),"Prova4","Semplice calcio","25/03/2020",10,false,"20:00",true));

/*
            // FRANK
            Prenotazione p1 = creaPrenotazione(utentiStandard.get(0),campi.get(1),"Prova1 Annullata","Annullata per mal tempo","2/03/2020",9,true,"11:00",false);
            lista.add(p1);
            Prenotazione p2 = creaPrenotazione(utentiStandard.get(0),campi.get(2),"Prova2 Annullata","Annullata per mal tempo","2/03/2020",9,true,"12:00",false);
            lista.add(p2);
            Prenotazione p3 = creaPrenotazione(utentiStandard.get(0),campi.get(3),"Vamonos!","Annullata per mal tempo","2/03/2020",9,false,"13:00",false);
            lista.add(p3);
            Prenotazione p4 = creaPrenotazione(utentiStandard.get(0),campi.get(4),"Prova4 Annullata","Annullata per mal tempo","2/03/2020",9,true,"14:00",false);
            lista.add(p4);
            Prenotazione p5 = creaPrenotazione(utentiStandard.get(0),campi.get(5),"Prova5 Annullata","Annullata per mal tempo","2/03/2020",9,true,"15:00",false);
            lista.add(p5);
            Prenotazione p6 = creaPrenotazione(utentiStandard.get(0),campi.get(5),"Calcio Champagne","Puro Divertimento","3/03/2020",9,false,"15:00",false);
            lista.add(p6);

            // DANIELE
            Prenotazione p10 = creaPrenotazione(utentiStandard.get(1),campi.get(1),"Prova10","Semplice calcio","22/03/2020",7,false,"11:00",false);
            lista.add(p10);
            Prenotazione p11 = creaPrenotazione(utentiStandard.get(1),campi.get(2),"Prova11","Semplice calcio","23/03/2020",4,false,"10:00",false);
            lista.add(p11);
            Prenotazione p12 = creaPrenotazione(utentiStandard.get(1),campi.get(6),"Da Aimone","Il terzo tempo conta piu dei primi due","24/03/2020",9,false,"22:00",true);
            lista.add(p12);
            Prenotazione p13 = creaPrenotazione(utentiStandard.get(1),campi.get(2),"Joga Bonito","Olè!","25/03/2020",7, true,"21:00",false);
            lista.add(p13);


            // LUCA
            Prenotazione p20 = creaPrenotazione(utentiStandard.get(2),campi.get(1),"Prova3","Annullata per infortunio","22/03/2020",10,true,"13:00",false);
            lista.add(p20);
            Prenotazione p21 = creaPrenotazione(utentiStandard.get(2),campi.get(1),"Prova5","Annullata per mancanza giocatori","2/03/2020",10,true,"11:00",false);
            lista.add(p21);
            Prenotazione p22 = creaPrenotazione(utentiStandard.get(2),campi.get(2),"Prova2","Semplice calcio","21/03/2020",8,false,"13:00",false);
            lista.add(p22);
            Prenotazione p23 = creaPrenotazione(utentiStandard.get(2),campi.get(4),"Vecchia","Semplice calcio","21/03/2019",8,false,"13:00",false);
            lista.add(p23);
            Prenotazione p24 = creaPrenotazione(utentiStandard.get(2),campi.get(4),"Vecchia2","Semplice calcio","22/03/2019",8,false,"13:00",false);
            lista.add(p24);

            // JORGE
            Prenotazione p30 = creaPrenotazione(utentiStandard.get(4),campi.get(3),"Partitona Sbronza","Calcio sbronzo","29/02/2020",9,false,"15:00",false);
            lista.add(p30);
            Prenotazione p31 = creaPrenotazione(utentiStandard.get(4),campi.get(4),"MESSIng about","Tiki Taka","13/03/2020",4,false,"16:00",false);
            lista.add(p31);
            Prenotazione p32 = creaPrenotazione(utentiStandard.get(4),campi.get(3),"Prova4","Semplice calcio","28/02/2020",10,false,"19:00",false);
            lista.add(p32);
            Prenotazione p33 = creaPrenotazione(utentiStandard.get(4),campi.get(7),"episcoPALI","San pietro","23/03/2020",3,true,"20:00",true);
            lista.add(p33);
            Prenotazione p34 = creaPrenotazione(utentiStandard.get(4),campi.get(7),"Prova4","Semplice calcio","25/03/2020",4,true,"20:00",false);
            lista.add(p34);


 */
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

    public void elimnaPrenotazioneGestore(ArrayList<Prenotazione> lista,int idPrenotazione){
        if(lista!=null) {
            for (int i = 0; i < lista.size(); i++) {


                if(lista.get(i).getId()==idPrenotazione ){
                    //Rimuovo completamente la prenotazione nel senso setto il booleano
                    lista.get(i).setAnnullata(true);
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

        boolean flag =  controlloDataOraNuovaPartita(prenotazioni,p,campi);

        /*
        for(int i=0;i<prenotazioni.size();i++){

            if(!prenotazioni.get(i).isAnnullata()){

                //Bisogna tassativamente rispettare l'ordine di inserimento nel controllo ...
                //Il controllo in pratica se trova una corrsipondenza rimuove il duplicato dalla lista geneerale dei campi
                if(controlloDataOraNuovaPartita(prenotazioni,p,campi)){

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

         */
        //Puo capitare che nessun campo sia in una prenotazone quindi lo aggiungo ... tanto se ce gia il set mi impedisce di aggiungerlo
        for(int i=0;i<campi.size();i++){
            ritorno.add(campi.get(i));
        }
        return new ArrayList<CampoDaCalcio>(ritorno);
    }


    /*Prende due prenotazoni e la lista generale dei campi , se le prenotazioni sono alla stessa ora rimuovo il campo */
    public boolean controlloDataOraNuovaPartita(ArrayList<Prenotazione> prenotazioni ,Prenotazione p2, ArrayList<CampoDaCalcio> campi){

        boolean flag = true;

        for(int i = 0 ;i < prenotazioni.size();i++){

            if(prenotazioni.get(i).getOra_evento().equals(p2.getOra_evento()) && prenotazioni.get(i).getData_evento().equals(p2.getData_evento())){
                campi.remove(prenotazioni.get(i).getCampo());

            }
        }
        return flag;


    }

    public boolean checkCampoOccupato (Prenotazione p,ArrayList<Prenotazione> prenotazioni){

        boolean flag = false;
        for(int i = 0 ; i<prenotazioni.size();i++){
            if(p.getCampo().getNome().equals(prenotazioni.get(i).getCampo().getNome()) && p.getData_evento().equals(prenotazioni.get(i).getData_evento())
            && p.getOra_evento().equals(prenotazioni.get(i).getOra_evento())){
                return true;
            }
        }
        return flag;
    }


//TODO create function to get all Reserved matches
/*Prenotazioni di defaul per fare debug*/
public ArrayList<Prenotazione> setPrenotazioniComplete(){

        ArrayList<Prenotazione> lista = new ArrayList<Prenotazione>();
        ArrayList<Persona> utentiStandard = PersonaFactory.getInstance().getUtentiArray(utenti);
        ArrayList<CampoDaCalcio> campi = CampoDaCalcioFactory.getInstance().getCampiDefault();

        /*
        Prenotazione p6 = creaPrenotazione(utentiStandard.get(0),campi.get(1),"Prova Annullata","Annullata per mal tempo","2/03/2020",9,true,"11:00",true);
        Prenotazione p1 = creaPrenotazione(utentiStandard.get(1),campi.get(1),"Prova1","Semplice calcio","22/03/2020",7,false,"11:00",false);
        Prenotazione p3 = creaPrenotazione(utentiStandard.get(2),campi.get(1),"Prova3","Annullata per infortunio","22/03/2020",10,true,"13:00",false);
        Prenotazione p5 = creaPrenotazione(utentiStandard.get(2),campi.get(1),"Prova5","Annullata per mancanza giocatori","2/03/2020",10,true,"11:00",false);
        Prenotazione p2 = creaPrenotazione(utentiStandard.get(2),campi.get(2),"Prova2","Semplice calcio","21/03/2020",8,false,"13:00",false);
        Prenotazione p4 = creaPrenotazione(utentiStandard.get(3),campi.get(3),"Prova4","Semplice calcio","12/03/2020",9,false,"15:00",false);
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);
        lista.add(p4);
        lista.add(p5);
        lista.add(p6);
        */

        return lista;
    }
}
