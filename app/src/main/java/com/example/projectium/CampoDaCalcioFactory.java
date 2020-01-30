package com.example.projectium;

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

    public CampoDaCalcio creaCampo(String nome,String prezzo_a_persona,int posti,String via){

        CampoDaCalcio campoDaCalcio = new CampoDaCalcio();
        campoDaCalcio .setNome(nome);
        campoDaCalcio .setPosti(posti);
        campoDaCalcio .setPrezzo_a_persona(prezzo_a_persona);
        campoDaCalcio .setVia(via);

        return campoDaCalcio;

    }


    public void setCampiDefault (HashSet<CampoDaCalcio> set){

        if(set == null || set.isEmpty()){

            set.add(creaCampo("Bonaria","2.00",10,"Via Ravenna, 09125 Cagliari CA"));
            set.add(creaCampo("Ossigeno","2,50",10,"Viale Cimitero, 29, 09125 Cagliari CA"));
            set.add(creaCampo("Terrapieno","1,50",10,"Viale Regina Elena, 14, 09124 Cagliari CA"));
            set.add(creaCampo("Salesiani","2,00",10," Via Sant'Ignazio da Laconi, 09123 Cagliari CA"));

        }

    }


    public ArrayList<CampoDaCalcio> getCampiDefault(){

        ArrayList<CampoDaCalcio> def = new ArrayList<>();
        def.add(creaCampo("Bonaria","2.00",10,"Via Ravenna, 09125 Cagliari CA"));
        def.add(creaCampo("Ossigeno","2,50",10,"Viale Cimitero, 29, 09125 Cagliari CA"));
        def.add(creaCampo("Terrapieno","1,50",10,"Viale Regina Elena, 14, 09124 Cagliari CA"));
        def.add(creaCampo("Salesiani","2,00",10," Via Sant'Ignazio da Laconi, 09123 Cagliari CA"));

        return def;

    }




}
