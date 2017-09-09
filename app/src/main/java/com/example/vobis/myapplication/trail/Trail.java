package com.example.vobis.myapplication.trail;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.vobis.myapplication.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public final class Trail extends ActionBarActivity {

    ExpandableListView expandableListView;
    Button fillingButton;
    Button animationButton;
    LinearLayout linearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        expandableListView = (ExpandableListView) findViewById(R.id.ParentLevel);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        fillingButton = (Button) findViewById(R.id.displayALV);
        animationButton = (Button)findViewById(R.id.animation_button);
        fillingButton.setWidth(size.x/4);
        animationButton.setWidth(size.x/4);
        int fillingButtonWidth = fillingButton.getWidth();
        int animationbuttonWidth = animationButton.getWidth();
        int fillingButtonPos;
        fillingButtonPos = (screenWidth )/4;
        linearLayout = (LinearLayout) findViewById(R.id.animation_layout);
        linearLayout.setPadding(fillingButtonPos,0,0,0);

        HashMap<String,ArrayList<String>> First = new HashMap<String,ArrayList<String>>();
        ArrayList<String> Keys = new ArrayList<String>();
        ArrayList<ArrayList<String>> Levels = new ArrayList<ArrayList<String>>();

        for(int i = 0; i<Constant.state.length; i++){
            Levels.add(new ArrayList<String>());
            Keys.add(Constant.state[i]);
            for (String first:Constant.parent[i])
                Levels.get(i).add(first);
            First.put(Keys.get(i),Levels.get(i));
        }
        expandableListView.setAdapter(new TrailSecondLevel(this,Keys,First));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_trail, menu);
        return super.onCreateOptionsMenu(menu);
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

    public void displayALV(View view) {
        startActivity(new Intent(this,ALV.class));
    }

    public void displayAnimation(View view) {
        Intent intent = new Intent(this,Animation.class);

        startActivity(intent);
    }
}