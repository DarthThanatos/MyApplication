package com.example.vobis.myapplication.trail;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vobis.myapplication.R;

import java.util.ArrayList;

public class ALV extends ActionBarActivity {

    private String[] names = {"Andrzej","Robert","Wacek","Anna","Lila","Kamil","Maciej","Sebastian","Natalia","Zuza","Marcin","Mateusz","Bartek","Iwona","Kinga"};

    Storage s;
    private AVLFragment dataFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alv);
        FragmentManager fm = getFragmentManager();
        dataFragment = (AVLFragment) fm.findFragmentByTag("data");

        if (dataFragment == null){
            dataFragment = new AVLFragment();
            fm.beginTransaction().add(dataFragment,"data").commit();
            s = new Storage();
            dataFragment.setData(s);
        }
        s = dataFragment.getData();
        s.listView = (ListView) findViewById(R.id.list_view);
        if(s.adapter==null)
            s.listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        else
            s.listView.setAdapter(s.adapter);
        s.progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        if(s.count!=0){
            s.progressBar.setVisibility(View.VISIBLE);
            s.progressBar.setProgress(s.count);
            s.progressBar.setMax(15);
        }
        else
            s.progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataFragment.setData(s);
    }

    public void fillList(View view) {
        s.listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        s.count = 0;

        if (s.myTask!=null){

            s.work = false;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s.myTask.cancel(true);
        }
        s.myTask = new MyTask().execute();

    }

    public class MyTask extends AsyncTask<Void,String,String>{

        ProgressBar progressBar;
        int count = 0;

        public MyTask(){
            s.work = true;
        }

        @Override
        protected void onPreExecute() {
            s.adapter = (ArrayAdapter<String>)s.listView.getAdapter();
            s.progressBar.setMax(15);
            s.progressBar.setProgress(0);
            s.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {//params = first parameter, type of doIn... = second parameter
            if(s.work){
                for (String Name:names){
                    if (!s.work) break;
                    publishProgress(Name);//second parameter

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return "All the names were added";
            }
            return "";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            s.adapter.add(values[0]);
            s.count++;
            s.progressBar.setProgress(s.count);
        }

        @Override
        protected void onPostExecute(String result) { //result is what doInBackground returns
           s.progressBar.setVisibility(View.GONE);
           Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
