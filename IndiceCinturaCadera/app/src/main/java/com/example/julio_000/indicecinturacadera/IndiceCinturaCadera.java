package com.example.julio_000.indicecinturacadera;

import android.content.DialogInterface;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class IndiceCinturaCadera extends AppCompatActivity implements View.OnClickListener {
    private EditText Cintura;
    private EditText Cadera;
    private Button Calcula;
    private Button Ayuda;
    private RadioButton Feme;
    private RadioButton Masc;
    private TextView mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indice_cintura_cadera);
        Calcula= (Button) findViewById(R.id.button);
        Ayuda =(Button) findViewById(R.id.button2);
        Calcula.setOnClickListener(this);
        Ayuda.setOnClickListener(this);
        };

    public void CalcularInd(){
        double indice;
        Cintura = (EditText)findViewById(R.id.editText);
        Cadera = (EditText)findViewById(R.id.editText2);
        double valorCintura=Double.parseDouble(Cintura.getText().toString());
        double valorCadera=Double.parseDouble(Cadera.getText().toString());
        mensaje=(TextView) findViewById(R.id.textView3);
        Masc= (RadioButton) findViewById(R.id.radioButton);
        Feme= (RadioButton) findViewById(R.id.radioButton2);
        indice= valorCintura/valorCadera;
        if(Masc.isChecked())
        {
            if (indice>=0.78 && indice<=0.94){
                mensaje.setText("Tiene valores normales");
            }
            if (indice<0.78){
                mensaje.setText("Síndrome ginecoide (cuerpo de pera)");
            }
            if (indice>0.94){
                mensaje.setText("Síndrome androide (cuerpo de manzana)");
            }
        }
        if (Feme.isChecked())
        {
            if (indice>=0.71 && indice<=0.84){
                mensaje.setText("Tiene valores normales");
            }
            if (indice<0.71){
                mensaje.setText("Síndrome ginecoide (cuerpo de pera)");
            }
            if (indice>0.84){
                mensaje.setText("Síndrome androide (cuerpo de manzana)");
            }
        }
    }
    public void VistaAyuda(){
        AlertDialog.Builder constructor= new AlertDialog.Builder(this);
        constructor.setMessage("Interpretación:\n" +
                "\n" +
                "ICC = 0,71-0,84 normal para mujeres.\n" +
                "ICC = 0,78-0,94 normal para hombres.\n" +
                "Valores mayores: Síndrome androide (cuerpo de manzana).\n" +
                "Valores menores: Síndrome ginecoide (cuerpo de pera).");
        constructor.setTitle("Ayuda");
        constructor.setCancelable(false);
        constructor.setNeutralButton("Aceptar",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = constructor.create();
        alert.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                CalcularInd();
                break;
            case R.id.button2:
                VistaAyuda();
                break;
        }
    }
}


