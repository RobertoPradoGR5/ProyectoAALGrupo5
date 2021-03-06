package com.aal.epn.servlets_backend;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;


public class IMC extends Activity {


    private Spinner heightspin;
    private EditText feet;
    private EditText cms;
    private EditText lbs;
    private TextView Lbstext;
    private TextView result;
    private RadioGroup rg;
    private RadioButton radioSexButton;

    public int hfeet;
    public int hinc;
    public int wlbs;
    public int wkgs;
    public int hcms;
    public float bmifeet;
    public float bmicms;
    public String bmifeetStatus;
    public String bmicmsstatus;
    public int selectedId;
    public String spintext;

    Button claculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        heightspin = (Spinner) findViewById(R.id.heightspin);
        feet = (EditText) findViewById(R.id.feet);
        cms = (EditText) findViewById(R.id.cms);
        lbs = (EditText) findViewById(R.id.lbs);
        Lbstext = (TextView )  findViewById(R.id.Lbstext);
        result = (TextView )  findViewById(R.id.result);

        rg = (RadioGroup) findViewById(R.id.radiosex);
        spintext= heightspin.getSelectedItem().toString();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.height_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heightspin.setAdapter(adapter);
        heightspin.setOnItemSelectedListener(new OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position ==0 )
                {
                    feet.setVisibility(View.VISIBLE);
                    cms.setHint("Inches");
                    Lbstext.setText("Lbs");
                    lbs.setHint("Lbs");
                }
                else
                {
                    feet.setVisibility(View.GONE);
                    cms.setHint("Cms");
                    Lbstext.setText("Kgs");
                    lbs.setHint("Kgs");
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        final Button button = (Button) findViewById(R.id.claculate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (v.getId() == R.id.claculate )
                {

                    EditText feet = (EditText)findViewById(R.id.feet);
                    EditText inches = (EditText)findViewById(R.id.cms);
                    EditText lbs = (EditText)findViewById(R.id.lbs);


                    result = (TextView )  findViewById(R.id.result);
                    hfeet = Integer.parseInt(feet.getText().toString());
                    hinc = Integer.parseInt(inches.getText().toString());
                    wlbs = Integer.parseInt(lbs.getText().toString());


                    selectedId = rg.getCheckedRadioButtonId();
                    radioSexButton = (RadioButton) findViewById(selectedId);

                    if(spintext.equalsIgnoreCase("Feet/Inches") && selectedId == R.id.radiomale)
                    {
                        bmifeet = lbsbmi(hfeet, hinc ,wlbs);
                        bmifeetStatus = staBMI(bmifeet);
                        result.setText("PRESENT STATUS :"+ String.format( "%.2f", bmifeet )  + "-" + bmifeetStatus);

                    }
                    if(spintext.equalsIgnoreCase("Feet/Inches") && selectedId == R.id.radiofemale)
                    {

                        bmifeet = lbsbmi(hfeet, hinc ,wlbs);
                        bmicmsstatus = fstaBMI(bmifeet);
                        result.setText("PRESENT STATUS :"+ String.format( "%.2f", bmifeet )  + "-" + bmicmsstatus);
                    }
                    if(spintext.equalsIgnoreCase("Cms") && selectedId == R.id.radiomale)
                    {

                        bmicms = kgscbmi(hcms, wkgs);
                        bmifeetStatus = staBMI(bmicms);
                        result.setText("PRESENT STATUS :"+ String.format( "%.2f", bmicms )  + "-" + bmifeetStatus);
                    }

                    if(spintext.equalsIgnoreCase("Cms") && selectedId == R.id.radiofemale)
                    {

                        bmicms = kgscbmi(hinc, wlbs);
                        bmicmsstatus = fstaBMI(bmicms);

                        result.setText("PRESENT STATUS :"+ String.format( "%.2f", bmicms )  + "-" + bmicmsstatus);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Please Put the values",

                                Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    private float lbsbmi(int feet, int inches, int lbs )
    {
        int inc= feet + (inches/12);
        return (float) (lbs * 4.88 / (inc * inc));
    }
    private float kgscbmi (float cms,  float kgs)
    {

        float hgt = cms/100 ;
        return (float) ( kgs / (hgt * hgt));
    }

    private String staBMI(float bmiValue)
    {
        if (bmiValue < 18.5) {
            return "Underweight";
        }
        else if (bmiValue < 25) {
            return "Normal";
        } else if (bmiValue < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }

    }

    private String fstaBMI(float bmiValue)
    {
        if (bmiValue < 16.5) {
            return "Underweight";
        }
        else if (bmiValue < 22) {
            return "Normal";

        } else if (bmiValue < 27) {
            return "Overweight";
        } else {
            return "Obese";
        }

    }
}