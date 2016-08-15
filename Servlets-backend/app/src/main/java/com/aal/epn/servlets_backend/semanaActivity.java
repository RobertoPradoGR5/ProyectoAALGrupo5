package com.aal.epn.servlets_backend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class semanaActivity extends AppCompatActivity {
    Button btnLunes;
    Button btnMartes;
    Button btnMiercoles;
    Button btnJueves;
    Button btnViernes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semana);
        btnLunes=(Button)findViewById(R.id.item1);
        btnMartes=(Button)findViewById(R.id.item2);
        btnMiercoles=(Button)findViewById(R.id.item3);
        btnJueves=(Button)findViewById(R.id.item4);
        btnViernes=(Button)findViewById(R.id.item5);
        btnLunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrir=new Intent(semanaActivity.this,almuerzosActivity.class);
                abrir.putExtra("dia", "lunes");
                startActivity(abrir);
            }
        });
        btnMartes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrir=new Intent(semanaActivity.this,almuerzosActivity.class);
                abrir.putExtra("dia", "martes");
                startActivity(abrir);
            }
        });
        btnMiercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrir=new Intent(semanaActivity.this,almuerzosActivity.class);
                abrir.putExtra("dia", "miércoles");
                startActivity(abrir);
            }
        });
        btnJueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrir=new Intent(semanaActivity.this,almuerzosActivity.class);
                abrir.putExtra("dia", "jueves");
                startActivity(abrir);
            }
        });
        btnViernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrir=new Intent(semanaActivity.this,almuerzosActivity.class);
                abrir.putExtra("dia", "viernes");
                startActivity(abrir);
            }
        });

    }
}
