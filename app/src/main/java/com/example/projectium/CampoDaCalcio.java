package com.example.projectium;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

public class CampoDaCalcio implements Serializable {

    private String nome;
    private int posti;
    private String via;
    private String prezzo_a_persona;
    private String materiale;
    private String telefono;
    private double latitudine;
   private double longitudine;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPosti() {
        return posti;
    }

    public void setPosti(int posti) {
        this.posti = posti;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getPrezzo_a_persona() {
        return prezzo_a_persona;
    }

    public void setPrezzo_a_persona(String prezzo_a_persona) {
        this.prezzo_a_persona = prezzo_a_persona;
    }







    @Override
    public boolean equals(@Nullable Object obj) {
        if ((obj instanceof CampoDaCalcio)) {
            CampoDaCalcio temp = (CampoDaCalcio) obj;
            if (this.getNome().equals(temp.getNome()) && this.getVia().equals(temp.getVia())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getNome().hashCode();
    }

    public String getMateriale() {
        return materiale;
    }

    public void setMateriale(String materiale) {
        this.materiale = materiale;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }


}
