package com.example.projectium;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Calendar;

public class Persona implements Serializable {
    private String userId;
    private String nome;
    private String cognome;
    private String passowrd;
    private String cpassword;
    private String email;
    private String partite;
    private boolean gestore;

    public Persona(){
        this.setUserId("");
        this.setNome("");
        this.setCognome("");
        this.setPassword("");
        this.setCPassword("");
        this.setEmail("");
        this.setPartite("0");
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPassword() {
        return passowrd;
    }

    public void setPassword(String passowrd) {
        this.passowrd = passowrd;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCPassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPartite() {
        return partite;
    }

    public void setPartite(String partite) {
        this.partite = partite;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if ((obj instanceof Persona)) {
            Persona temp = (Persona) obj;
            if (this.getUserId().equals(temp.getUserId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isGestore() {
        return gestore;
    }

    public void setGestore(boolean gestore) {
        this.gestore = gestore;
    }
}
