package com.example.danilo.projetoandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;



public class TelaDeAberturaActivity extends Activity {

    private final int tempo = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_abertura);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(TelaDeAberturaActivity.this, TelaPrincipalActivity.class);
                TelaDeAberturaActivity.this.startActivity(it);
                TelaDeAberturaActivity.this.finish();
            }
        }, tempo);



    }

}
