package org.isa.comparateurdeprenoms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.isa.comparateurdeprenoms.tools.Traitement;

public class SaisieActivity extends AppCompatActivity {

    private EditText prenom1;
    private EditText prenom2;
    private ImageView imgCheck;
    private TextView oubli;
    public static final String EXTRA_MESSAGE = "comparateurdeprenoms.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie);
        oubli = findViewById(R.id.oubliPrenom);
        prenom1 = findViewById(R.id.prenom1);
        prenom2 = findViewById(R.id.prenom2);
        imgCheck = findViewById(R.id.imgCheck);

    }

    public void lancerActivite2(View view) {
        //if edittext fields are empty, show error message :
        if(prenom1.getText().length()==0 || prenom2.getText().length()==0){
            oubli.setVisibility(View.VISIBLE);
        } else {
            oubli.setVisibility(View.INVISIBLE);
            Traitement t = new Traitement();
            int calcul = t.getPourcentage(prenom1, prenom2);
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(EXTRA_MESSAGE, calcul);
            startActivity(intent);
        }

    }
}