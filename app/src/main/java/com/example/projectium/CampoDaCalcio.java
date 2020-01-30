package com.example.projectium;


import androidx.annotation.Nullable;

public class CampoDaCalcio {

    private String nome;
    private int posti;
    private String via;
    private String prezzo_a_persona;
    private int valutazione;

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

    /*
    * */
    public CampoDaCalcio(String nome,String prezzo_a_persona,int posti,String via){

        this.setNome(nome);
        this.setPosti(posti);
        this.setPrezzo_a_persona(prezzo_a_persona);
        this.setVia(via);

    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
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
}
