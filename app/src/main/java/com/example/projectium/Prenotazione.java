package com.example.projectium;


import androidx.annotation.Nullable;



import java.io.Serializable;
import java.util.ArrayList;

public class Prenotazione implements Serializable {

    public static final String PRENOTAZIONE = "package com.example.projectium.Prenotazione";

    private CampoDaCalcio campo;
    private Persona creatore;
    private String nome_evento;
    private String descrizione;
    private String data_evento;
    private int num_giocatori;
    private boolean annullata;
    private String ora_evento;
    private int id;
    private ArrayList<Persona> iscritti = new ArrayList<>();




    public Persona getCreatore() {
        return creatore;
    }

    public void setCreatore(Persona creatore) {
        this.creatore = creatore;
    }

    public String getNome_evento() {
        return nome_evento;
    }

    public void setNome_evento(String nome_evento) {
        this.nome_evento = nome_evento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getData_evento() {
        return data_evento;
    }

    public void setData_evento(String data_evento) {
        this.data_evento = data_evento;
    }

    public int getNum_giocatori() {
        return num_giocatori;
    }

    public void setNum_giocatori(int num_giocatori) {
        this.num_giocatori = num_giocatori;
    }

    public boolean isAnnullata() {
        return annullata;
    }

    public void setAnnullata(boolean annullata) {
        this.annullata = annullata;
    }

    public String getOra_evento() {
        return ora_evento;
    }

    public void setOra_evento(String ora_evento) {
        this.ora_evento = ora_evento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if ((obj instanceof Prenotazione)) {
            Prenotazione temp = (Prenotazione) obj;
            if (this.getId()==temp.getId()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public CampoDaCalcio getCampo() {
        return campo;
    }

    public void setCampo(CampoDaCalcio campo) {
        this.campo = campo;
    }

    public ArrayList<Persona> getIscritti() {
        return iscritti;
    }

    public void setIscritti(ArrayList<Persona> iscritti) {
        this.iscritti = iscritti;
    }
}
