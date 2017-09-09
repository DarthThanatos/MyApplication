package com.example.vobis.myapplication.trail;

import android.graphics.Path;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.Serializable;

/**
 * Created by Vobis on 2015-08-11.
 */
public class Storage implements Serializable {

    Path path;
    ArrayAdapter<String> adapter;
    ListView listView;
    AsyncTask myTask;
    int count;
    ProgressBar progressBar;
    boolean work;

    public Storage(){
        path = new Path();
        count = 0;
    }

}
