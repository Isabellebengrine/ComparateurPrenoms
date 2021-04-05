package org.isa.comparateurdeprenoms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.isa.comparateurdeprenoms.tools.Traitement;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private final static int SPLASH_SCREEN = 5000;//durée animation (milliseconds)
    private TextView desc;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // enlevons la barre en haut
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animations
        Animation topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation_layout);
        Animation bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation_layout);

        //Hooks
        logo = findViewById(R.id.imageJigsaw);
        desc = findViewById(R.id.textView);

        //Assignment
        logo.setAnimation(topAnim);
        desc.setAnimation(bottomAnim);

        //to open SaisieActivity, use Handler with delay :
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Déclaration d'une nouvelle intention
                Intent intent = new Intent(MainActivity.this, SaisieActivity.class);
                //Démarrage de l'intention
                startActivity(intent);
                //Cloture du Splash Screen
                finish();
            }
        },SPLASH_SCREEN);

    }

}