package com.example.vobis.myapplication.BMI;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vobis.myapplication.R;
import com.example.vobis.myapplication.async.Async;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BmiResult extends ActionBarActivity {

    private Patients load;
    String key = "Key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        load = (Patients)intent.getSerializableExtra("patients");
        store();
        getFromStore();

    }

    String keyDel = "itemDeleted";
    public void getFromStore(){

        /*<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
        android:layout_height="wrap_content" android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin"
        android:paddingBottom="@dimen/activity_vertical_margin"
        android:orientation="vertical"
        tools:context="com.example.vobis.myapplication.BMI.BmiResult"
        android:id="@+id/bmi_result_layout">
        </LinearLayout>*/

        SharedPreferences getPatients = getSharedPreferences(key,MODE_PRIVATE);
        final LinearLayout lila = new LinearLayout(this);
        lila.setOrientation(LinearLayout.VERTICAL);
        lila.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        final ScrollView scrollView = (ScrollView)findViewById(R.id.scrollView);
        int patientsAmount = getPatients.getInt("index", 0);
        ArrayList<Button> clear = new ArrayList<Button>();
        ArrayList<TextView> name = new ArrayList<TextView>();
        ArrayList<TextView> surname = new ArrayList<TextView>();
        ArrayList<TextView> result = new ArrayList<TextView>();
        final ArrayList<LinearLayout> row = new ArrayList<LinearLayout>();

        for (int i = 0, r=0; r < patientsAmount; r++)
        {

            final SharedPreferences delete = getSharedPreferences(key,MODE_PRIVATE);
            final SharedPreferences.Editor editor = delete.edit();
            if(delete.getBoolean(keyDel+r,true)) continue;


            name.add(new TextView(this));
            surname.add( new TextView(this));
            result.add(new TextView(this));
            row.add(new LinearLayout(this));
            clear.add(new Button(this));

            row.get(i).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            name.get(i).setText(getPatients.getString("name" + r, "Nothing found"));
            name.get(i).setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            surname.get(i).setText(getPatients.getString("surname" + r, "Nothing found"));
            surname.get(i).setLayoutParams((new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1)));

            result.get(i).setText(getPatients.getString("result" + r, "Nothing found"));
            result.get(i).setLayoutParams((new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1)));

            clear.get(i).setText("clean");
            clear.get(i).setAllCaps(false);
            clear.get(i).setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));


            row.get(i).addView(name.get(i));
            row.get(i).addView(surname.get(i));
            row.get(i).addView(result.get(i));
            row.get(i).addView(clear.get(i));

            final int indexInOrder = i;
            final int indexInEditor = r;

            lila.addView(row.get(i));
            clear.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putBoolean(keyDel + indexInEditor, true);
                    editor.apply();
                    editor.remove("name" + indexInEditor).remove("surname" + indexInEditor).remove("result" + indexInEditor);
                    editor.commit();
                    lila.removeView(row.get(indexInOrder));
                    //Toast.makeText(getApplicationContext(), "Button " + indexInOrder + " selected", Toast.LENGTH_SHORT).show();
                }
            });
            i++;
        }
        scrollView.addView(lila);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bmi_result, menu);
        return true;
    }

    public void store(){
        SharedPreferences patients = getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = patients.edit();
        for (int i = 0;i<load.patientsResult.size(); i++){
            int index = patients.getInt("index", MODE_PRIVATE);
            editor.putString("name" + index, load.patientsName.get(i));
            editor.putString("surname" + index, load.patientsSurname.get(i));
            editor.putString("result" + index, load.patientsResult.get(i));
            editor.putBoolean(keyDel + index, false);
            index++;
            editor.putInt("index",index);
            editor.apply();
        }
        load.patientsName.clear();
        load.patientsSurname.clear();
        load.patientsResult.clear();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
