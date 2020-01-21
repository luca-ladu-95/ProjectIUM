package com.example.projectium;

import java.io.Serializable;
import java.util.Calendar;

public class Persona implements Serializable {
    private String userId;
    private String nome;
    private String cognome;
    private String passowrd;
    private String cpassword;
    private String email;

    public Persona(){
        this.setUserId("");
        this.setNome("");
        this.setCognome("");
        this.setPassword("");
        this.setCPassword("");
        this.setEmail("");
    }

    public Persona(String userId, String nome, String cognome, String password, String cpassword, String email) {
        this.setUserId(userId);
        this.setNome(nome);
        this.setCognome(cognome);
        this.setPassword(password);
        this.setCPassword(cpassword);
        this.setEmail(email);
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

    public String getPassowrd() {
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


}
