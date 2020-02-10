package com.example.projectium;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static com.example.projectium.Login.PERSON_DA_PASSARE;
import static com.example.projectium.Prenotazione.PRENOTAZIONE;

public class Valutazione_campo {

   private static Valutazione_campo istanza;

   private  HashMap<CampoDaCalcio,ArrayList<Integer>> valutazioni = new HashMap<>();

    public static Valutazione_campo getInstance() {
        if (istanza == null) {
            istanza = new Valutazione_campo() ;
        }
        return istanza;
    }



    public void add_valutazione(Integer valutazione,CampoDaCalcio campoDaCalcio){
        if(valutazioni!=null && valutazioni.containsKey(campoDaCalcio))
      valutazioni.get(campoDaCalcio).add(valutazione);
    }

    public void add_campo(CampoDaCalcio campoDaCalcio){
        valutazioni.put(campoDaCalcio,new ArrayList<Integer>());
    }


    public void set_valutazioni_default(HashSet<CampoDaCalcio> campidefaul){

        ArrayList<CampoDaCalcio> temp = new ArrayList<>(campidefaul);

        for(int i=0;i<temp.size();i++){

            valutazioni.put(temp.get(i),new ArrayList<Integer>());
        }

    }

    public Integer get_valutazione(CampoDaCalcio campoDaCalcio){
        Integer risultato=0,somma=0;
        ArrayList<Integer> da_sommare;
        if(valutazioni!= null && valutazioni.containsKey(campoDaCalcio)){
            da_sommare = valutazioni.get(campoDaCalcio);

            if(da_sommare == null || da_sommare.isEmpty()){
                risultato=0;
            }else{
                for(int i = 0;i<da_sommare.size();i++){
                    somma+=da_sommare.get(i);
                }

                risultato = somma/da_sommare.size();

            }

        }
        return risultato;
    }

}
