package com.example.projectium;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashSet;

public class CampoDaCalcioFactory {


    private static CampoDaCalcioFactory istanza;


    public static CampoDaCalcioFactory getInstance() {
        if (istanza == null) {
            istanza = new CampoDaCalcioFactory();
        }

        return istanza;
    }

    public CampoDaCalcio creaCampo(String nome, String prezzo_a_persona, int posti, String via, String telefono, String materiale, String valutazione){

        CampoDaCalcio campoDaCalcio = new CampoDaCalcio();
        campoDaCalcio .setNome(nome);
        campoDaCalcio .setPosti(posti);
        campoDaCalcio .setPrezzo_a_persona(prezzo_a_persona);
        campoDaCalcio .setVia(via);
        campoDaCalcio.setTelefono(telefono);
        campoDaCalcio.setMateriale(materiale);
        campoDaCalcio.setValutazione(valutazione);

        return campoDaCalcio;

    }


    public void setCampiDefault (HashSet<CampoDaCalcio> set){

        if(set == null || set.isEmpty()){

            set.add(creaCampo("Bonaria","2.00",10,"Via Ravenna, 09125 Cagliari","3456282932","Erba","4"));
            set.add(creaCampo("Ossigeno","2,50",10,"Viale Cimitero, 29, 09125 Cagliari","3478293914","Sintetico","3"));
            set.add(creaCampo("Terrapieno","1,50",10,"Viale Regina Elena, 14, 09124 Cagliari","3456282932","Sintetico","5"));
            set.add(creaCampo("Salesiani","2,00",10," Via Sant'Ignazio da Laconi, 09123 Cagliari","3456282932","Erba","3"));

        }

    }


    public ArrayList<CampoDaCalcio> getCampiDefault(){

        ArrayList<CampoDaCalcio> def = new ArrayList<>();
        def.add(creaCampo("Bonaria","2.00",10,"Via Ravenna, 09125 Cagliari","3456282932","Erba","4"));
        def.add(creaCampo("Ossigeno","2,50",10,"Viale Cimitero, 29, 09125 Cagliari","3478293914","Sintetico","3"));
        def.add(creaCampo("Terrapieno","1,50",10,"Viale Regina Elena, 14, 09124 Cagliari","3456282932","Sintetico","5"));
        def.add(creaCampo("Salesiani","2,00",10," Via Sant'Ignazio da Laconi, 09123 Cagliari","3456282932","Erba","3"));


        return def;

    }




}
