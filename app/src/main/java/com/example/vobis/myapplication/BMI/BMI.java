package com.example.vobis.myapplication.BMI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vobis.myapplication.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BMI extends ActionBarActivity {

    private EditText weight;
    private EditText height;
    private EditText name;
    private EditText surname;
    private TextView result;
    float resultValue;
    Patients serialPatients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        init();
    }

    public void init(){
        name = (EditText)findViewById(R.id.edit_bmi_name);
        surname = (EditText)findViewById(R.id.edit_bmi_surname);
        weight = (EditText)findViewById(R.id.edit_bmi_weight);
        height = (EditText)findViewById(R.id.edit_bmi_height);
        result = (TextView)findViewById(R.id.bmi_result);
        serialPatients = new Patients();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bmi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_save:
                Toast.makeText(getApplicationContext(),"File saved",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_new:
                Toast.makeText(getApplicationContext(), "New Activity started", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_load:
                Toast.makeText(getApplicationContext(), "File Loaded", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                Toast.makeText(getApplicationContext(),"Search selected",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addResult(View view){
        try{
            serialPatients.patientsName.add(String.valueOf(name.getText()));
            serialPatients.patientsSurname.add(String.valueOf(surname.getText()));
            serialPatients.patientsResult.add(String.valueOf(resultValue));
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Invalid data",Toast.LENGTH_LONG).show();
        }
    }

    public void showResult(View view) {
        Intent intent = new Intent(this,BmiResult.class);
        intent.putExtra("patients",serialPatients);
        startActivity(intent);
    }


    public void calculate(View view){
       try{
           float heightValue = Float.parseFloat(String.valueOf(height.getText()));
           float weightValue = Float.parseFloat(String.valueOf(weight.getText()));
           resultValue = weightValue/(float)Math.pow(heightValue,2);
           result.setText(String.valueOf(resultValue));
       }catch(Exception e){
           Toast.makeText(getApplicationContext(),"Please, fill height and weight gaps above",Toast.LENGTH_LONG).show();
       }
    }
}
