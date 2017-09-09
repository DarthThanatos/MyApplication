package com.example.vobis.myapplication.display;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.vobis.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DisplayMessageActivity extends ActionBarActivity {
    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        expandableListView =(ExpandableListView) findViewById(R.id.exp_listview);
        List<String> Headings = new ArrayList<String>();
        List<String> L1 = new ArrayList<String>();
        List<String> L2 = new ArrayList<String>();
        List<String> L3 = new ArrayList<String>();
        HashMap<String,List<String>> ChildList = new HashMap<String,List<String>>();
        String heading_items[] = getResources().getStringArray(R.array.header_titles);
        String l1[] = getResources().getStringArray(R.array.h1_items);
        String l2[] = getResources().getStringArray(R.array.h2_items);
        String l3[] = getResources().getStringArray(R.array.h3_items);
        for(String title : heading_items){
            Headings.add(title);
        }
        for(String title : l1){
            L1.add(title);
        }
        for(String title : l2){
            L2.add(title);
        }
        for(String title : l3){
            L3.add(title);
        }
        ChildList.put(Headings.get(0),L1);
        ChildList.put(Headings.get(1),L2);
        ChildList.put(Headings.get(2),L3);
        MyAdapter myAdopter = new MyAdapter(this,Headings,ChildList);
        expandableListView.setAdapter(myAdopter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_message, menu);
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
}
