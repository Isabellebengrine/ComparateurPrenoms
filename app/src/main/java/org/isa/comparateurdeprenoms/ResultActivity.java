package org.isa.comparateurdeprenoms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.isa.comparateurdeprenoms.tools.Traitement;

public class ResultActivity extends AppCompatActivity {

    private TextView calcul, verdict, label;
    private ImageView imgResult;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        label = findViewById(R.id.labelResult);
        calcul = findViewById(R.id.labelCalcul);
        verdict = findViewById(R.id.labelVerdict);
        imgResult = findViewById(R.id.imgResult);
        progressBar = findViewById(R.id.progressBar);

        //get percentage value from MainActivity :
        Intent intent = getIntent();
        int nb = intent.getIntExtra(SaisieActivity.EXTRA_MESSAGE, 0);

        // Start long running operation in a background thread - to make progressbar work :
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < nb) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            calcul.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 50 milliseconds.
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //show a message depending on result:
        Traitement t = new Traitement();
        verdict.setText(t.getMessage(nb));

        //show image accordingly : t.showImage(nb); //makes app stop before showing result, but works when I do this directly without using method from Traitement:
        
        if(nb <12){
            imgResult.setImageResource(R.drawable.touchless);
        } else if(nb <23){
            imgResult.setImageResource(R.drawable.dissatisfied);
        } else if(nb <34){
            imgResult.setImageResource(R.drawable.badreview);
        } else if (nb <45){
            imgResult.setImageResource(R.drawable.notcompatible);
        } else if(nb < 56){
            imgResult.setImageResource(R.drawable.thinking);
        } else if(nb < 67){
            imgResult.setImageResource(R.drawable.united);
        } else if(nb < 78){
            imgResult.setImageResource(R.drawable.thumbsup);
        } else if(nb < 89){
            imgResult.setImageResource(R.drawable.satisfied);
        } else {
            imgResult.setImageResource(R.drawable.success);
        }
    }

}