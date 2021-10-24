package com.example.ejdostextviewscontador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int contador1 = 0;
    private int contador2 = 0;
    private int col1 = Color.WHITE;
    private int col2 = Color.WHITE;
    TextView tv1 = null;
    TextView tv2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView3);
        refrescar();
        tv1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                contador1=0;
                return false;
            }
        });
        tv2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                contador2=0;
                return false;
            }
        });
    }

    public void incrementarContador1(View v) {
        contador1++;
        cambiarColor1();
        refrescar();
    }

    public void incrementarContador2(View v) {
        contador2++;
        cambiarColor2();
        refrescar();
    }

    public void cambiarColor1() {
        Random rnd = new Random();
        this.col1 = Color.argb(255,rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255));
        tv1.setBackgroundColor(col1);
    }

    public void cambiarColor2() {
        Random rnd = new Random();
        this.col2 = Color.argb(255,rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255));
        tv2.setBackgroundColor(col2);
    }

    public void refrescar() {
        Random rnd = new Random();
        tv1.setText("" + contador1);
        tv2.setText("" + contador2);
        if (contador1 == contador2) {
            vibrar(true);
        }
    }

    public void vibrar(boolean esLargo) {
        Vibrator vib1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(esLargo) {
            vib1.vibrate(500);
        } else {
            vib1.vibrate(100);
        }

    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("KC1", this.contador1);
        outState.putInt("KC2", this.contador2);
        outState.putInt("KCol1", this.col1);
        outState.putInt("KCol2", this.col2);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle inState) {
        super.onRestoreInstanceState(inState);
        this.contador1 = inState.getInt("KC1",0);
        this.contador2 = inState.getInt("KC2",0);
        this.col1 = inState.getInt("KCol1",0);
        this.col2 = inState.getInt("KCol2",0);
        refrescar();
        tv1.setBackgroundColor(col1);
        tv2.setBackgroundColor(col2);
    }
}