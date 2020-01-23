package com.example.projectium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import static com.example.projectium.Login.listaCampi;
public class Partecipa_partita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partecipa_partita);
        ArrayList<Button> buttons = new ArrayList<>();

        /*Data attuale questo è da rivedere perchè il giorno dell'esame che facciamo??? direi di prestabilire date fisse io
        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
        */

        /*Bisogna generare dinamicamente le text view , ho trovato questa funzione*/

        /*PROBLEMA COME CAZZO GLI DO GLI ID MANNAGGIA MAOMETTTO */

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lineraDinamicCercaPartita);

        /*Allora in pratica questo è solo debug per vedere se funziona, devo ancora creare le pertite
        * per adesso stampo solo i nomi dei campi in text view dinamiche , e funziona porco maometto
        * Ho risolto il problema di come creare gli id per identificarli quando si cliccano */
      for (int i=0; i < listaCampi.size();i++) {
          Button bottone = new Button(this);
          bottone.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
          bottone.setTextColor(getResources().getColor(R.color.nero));
          bottone.setGravity(Gravity.CENTER);
          bottone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
          bottone.setId(i);
          bottone.setText(listaCampi.get(i).getNome());
          linearLayout.addView(bottone);
          buttons.add(bottone);
      }
      //Test per vedere se set Id funziona
      int b0 = buttons.get(0).getId();
      int cavolo = buttons.get(1).getId();
        int b2 = buttons.get(2).getId();



        
    }
}
