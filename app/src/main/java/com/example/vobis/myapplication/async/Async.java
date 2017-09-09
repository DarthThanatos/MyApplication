package com.example.vobis.myapplication.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Vobis on 2015-08-03.
 */
public class Async extends AsyncTask<Void,Integer,String> {

    Context context;
    Button Download;
    ProgressDialog progressDialog;

    public Async(Context context, Button Download){
            this.context = context;
            this.Download = Download;
    }

    @Override
    protected String doInBackground(Void... params) {
        int i = 0;
        synchronized (this){
            while(i<10){
                try {
                    wait(1500);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return "Download complete";
    }
    @Override
    protected void onPreExecute(){
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Downloading in progress");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result){
        progressDialog.dismiss();
        Download.setEnabled(true);
    }
    @Override
    protected void onProgressUpdate(Integer... values){
        int progress = values[0];
        progressDialog.setProgress(progress);
    }
}
