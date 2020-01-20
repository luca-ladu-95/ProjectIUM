package com.example.projectium;

import java.io.Serializable;
import java.util.Calendar;

public class Persona implements Serializable {
    private String nome;
    private String cognome;
    private String passowrd;
    private String userId;

    public Persona(){
        this.setNome("");
        this.setCognome("");
        this.setPassowrd("");
        this.setUserId("");
    }

    public Persona(String username, String nome, String cognome, String password) {
        this.setNome(nome);
        this.setCognome(cognome);
        this.setPassowrd(password);
        this.setUserId(username);
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

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
