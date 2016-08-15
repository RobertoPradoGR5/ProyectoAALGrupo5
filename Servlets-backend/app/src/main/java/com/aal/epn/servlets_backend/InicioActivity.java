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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        btnNormal=(Button)findViewById(R.id.btnNormal);
        btnEspecial=(Button)findViewById(R.id.btnEspecial);
        btnSemana=(Button)findViewById(R.id.btnSemana);

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
    }
}
