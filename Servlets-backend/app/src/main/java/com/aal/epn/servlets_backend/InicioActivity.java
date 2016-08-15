package com.aal.epn.servlets_backend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class InicioActivity extends AppCompatActivity {
    Button btnNormal;
    Button btnEspecial;
    Button btnSemana;
    Button btnIMC;
    Button btnIndiceCintura;
    Button btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        btnNormal=(Button)findViewById(R.id.btnNormal);
        btnEspecial=(Button)findViewById(R.id.btnEspecial);
        btnSemana=(Button)findViewById(R.id.btnSemana);
        btnIMC=(Button)findViewById(R.id.btnCalculoIMC);
        btnIndiceCintura=(Button)findViewById(R.id.btnIndiceCintura);
        btnSalir=(Button)findViewById(R.id.btnSalir);

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrir=new Intent(InicioActivity.this,almuerzoActivity.class);
                abrir.putExtra("tipo", "Normal");
                startActivity(abrir);
            }
        });
        btnEspecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrir=new Intent(InicioActivity.this,almuerzoActivity.class);
                abrir.putExtra("tipo", "Especial");
                startActivity(abrir);
            }
        });
        btnSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrir=new Intent(InicioActivity.this,semanaActivity.class);
                startActivity(abrir);
            }
        });

        btnIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrir=new Intent(InicioActivity.this,IMC.class);
                startActivity(abrir);
            }
        });

        btnIndiceCintura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrir=new Intent(InicioActivity.this,IndiceCinturaCadera.class);
                startActivity(abrir);
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


    }
}
