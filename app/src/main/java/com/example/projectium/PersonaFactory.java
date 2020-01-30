package com.example.projectium;

import java.util.HashMap;

public class PersonaFactory {


    private static PersonaFactory istanza;

    public static PersonaFactory getInstance() {
        if (istanza == null) {
            istanza = new PersonaFactory();
        }

        return istanza;
    }


    public Persona creaPersona(String userId, String nome, String cognome, String password, String cpassword, String email,String partite) {
        Persona user = new Persona();
        user.setUserId(userId);
        user.setNome(nome);
        user.setCognome(cognome);
        user.setPassword(password);
        user.setCPassword(cpassword);
        user.setEmail(email);
        user.setPartite("0");

        return user;
    }

    public  void setPersoneDefault(HashMap<String,Persona> map){


    }

}
