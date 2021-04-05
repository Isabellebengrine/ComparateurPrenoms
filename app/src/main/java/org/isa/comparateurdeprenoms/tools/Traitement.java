package org.isa.comparateurdeprenoms.tools;

import android.widget.EditText;
import android.widget.ImageView;

import org.apache.commons.lang3.StringUtils;
import org.isa.comparateurdeprenoms.R;

import java.util.HashMap;

public class Traitement {

    private HashMap<Character, Integer> alphabet;
    private ImageView imgResult;

    public Traitement() {
    }

    public String normaliseInput(EditText input){
        String normString = String.valueOf(input.getText());
        String sansAccent = StringUtils.stripAccents(normString);
        //test 24/12 - 10h :
        System.out.println(sansAccent.toLowerCase());
        return sansAccent.toLowerCase();
    }

    //create a list with letters and values (a=1, b=2, ... z=26):
    //verified 24/12 9h - ok:
    public void setAlphabet() {
        this.alphabet = new HashMap<>();
        int nombre = 1;
        for(Character letter = 'a'; letter <= 'z'; letter ++){
            alphabet.put(letter, nombre);
            nombre ++;
        }
    }

    //method to get input's letters sum:
    //verified 24/12 9h - ok:
    public int getLettersSum(String input){
        //remplir hashmap alphabet pour éviter NPE:
        setAlphabet();
        int lettersSum = 0;
        for(int index = 0; index<input.length(); index ++){
            lettersSum += alphabet.get(input.charAt(index));
        }
        return lettersSum;
    }

    //method to get input's digits sum:
    public int getDigitsSum(String input){
        int lettersSum = getLettersSum(input);
        int num, rem=0;
        while(lettersSum>9){
            int digitsSum = 0;
            num = lettersSum;
            while(num>0)
            {
                rem = num%10;
                digitsSum += rem;
                num = num/10;
            }
            lettersSum = digitsSum;
        }
        return lettersSum;
    }

    //method to compare 2 inputs:
    public int getPourcentage(EditText input1, EditText input2){
        int res1 = getDigitsSum(normaliseInput(input1));
        int res2 = getDigitsSum(normaliseInput(input2));
        float calcul = (float)(9-Math.abs(res1 - res2))/9*100;
        return (int)calcul;
    }
    //method to give a message depending on result:
    public String getMessage(int nombre){
        String message = "";
        if(nombre <12){
            message = "Le pire des cas!!!";
        } else if(nombre <23){
            message = "Pas terrible du tout!";
        } else if(nombre <34){
            message = "Bof, peut mieux faire!";
        } else if (nombre <45){
            message = "Très très moyen!";
        } else if(nombre < 56){
            message = "Tout juste mieux que la moyenne!";
        } else if(nombre < 67){
            message = "C'est pas mal!";
        } else if(nombre < 78){
            message = "Bien!";
        } else if(nombre < 89){
            message = "Très bien!";
        } else {
            message = "C'est parfait!";
        }
        return message;
    }

    //method to get image depending on number: worked on 12/26 but did not work once I added splashscreen (why?);
    //pb on 12/29 : app keeps stopping if I use this method in Resultactivity :
    public void showImage(int nb){
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
