package com.example.projectium;

import android.text.Editable;

import com.google.android.gms.maps.model.LatLng;

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

    public CampoDaCalcio creaCampo(String nome, String prezzo_a_persona, int posti, String via, String telefono, String materiale,double latitudine,double longitudine){

        CampoDaCalcio campoDaCalcio = new CampoDaCalcio();
        campoDaCalcio .setNome(nome);
        campoDaCalcio .setPosti(posti);
        campoDaCalcio .setPrezzo_a_persona(prezzo_a_persona);
        campoDaCalcio .setVia(via);
        campoDaCalcio.setTelefono(telefono);
        campoDaCalcio.setMateriale(materiale);
        campoDaCalcio.setLatitudine(latitudine);
        campoDaCalcio.setLongitudine(longitudine);


        return campoDaCalcio;

    }


    public void setCampiDefault (HashSet<CampoDaCalcio> set){

        if(set == null || set.isEmpty()){
            set.add(creaCampo("Bonaria","2.00",10,"Via Ravenna, Cagliari","3456282932","Erba",39.209651,9.126720));
            set.add(creaCampo("Ossigeno","2,50",10,"Viale Cimitero, 29, Cagliari","3478293914","Sintetico",39.212714,9.124624));
            set.add(creaCampo("Terrapieno","1,50",10,"Viale Regina Elena, 14, Cagliari","3456282932","Sintetico",39.219123,9.117656));
            set.add(creaCampo("Salesiani","2,00",10," Via Sant'Ignazio da Laconi, Cagliari","3456282932","Erba", 39.221463,9.109041));
            set.add(creaCampo("Argonne","2,00",10," Via Argonne, Cagliari","3456282932","Erba", 39.233013,9.105147));
            set.add(creaCampo("Montesanto","2,00",10," Via Montesanto, Cagliari","3456282932","Erba", 39.236863,9.102035));
            set.add(creaCampo("Biasi","2,00",10," Via Biasi, Cagliari","3456282932","Erba", 39.233054,9.123792));
            set.add(creaCampo("Piazza Giovanni","2,00",10," Piazza Giovanni, Cagliari","3456282932","Erba",39.228087,9.125191));
        }

    }

    public ArrayList<CampoDaCalcio> getCampiDefault(){

        ArrayList<CampoDaCalcio> def = new ArrayList<>();
        def.add(creaCampo("Bonaria","2.00",10,"Via Ravenna, Cagliari","3456282932","Erba",39.209651,9.126720));
        def.add(creaCampo("Ossigeno","2,50",10,"Viale Cimitero, 29, Cagliari","3478293914","Sintetico",39.212714,9.124624));
        def.add(creaCampo("Terrapieno","1,50",10,"Viale Regina Elena, 14, Cagliari","3456282932","Sintetico",39.219123,9.117656));
        def.add(creaCampo("Salesiani","2,00",10," Via Sant'Ignazio da Laconi, Cagliari","3456282932","Erba", 39.221463,9.109041));
        def.add(creaCampo("Argonne","2,00",10," Via Argonne, Cagliari","3456282932","Erba",39.233013,9.105147));
        def.add(creaCampo("Montesanto","2,00",10," Via Montesanto, Cagliari","3456282932","Erba", 39.236863,9.102035));
        def.add(creaCampo("Biasi","2,00",10," Via Biasi, Cagliari","3456282932","Erba", 39.233054,9.123792));
        def.add(creaCampo("Piazza Giovanni","2,00",10," Piazza Giovanni, Cagliari","3456282932","Erba", 39.228087,9.125191));

        return def;

    }

    public boolean aggiungiCampo(HashSet<CampoDaCalcio> lista, CampoDaCalcio campoNuovo){

        if(lista.contains(campoNuovo)) return
                false;
        else {
            lista.add(campoNuovo);
            return true;
        }
    }
}
