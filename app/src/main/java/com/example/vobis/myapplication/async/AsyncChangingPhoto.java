package com.example.vobis.myapplication.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.vobis.myapplication.R;

/**
 * Created by Vobis on 2015-08-08.
 */
public class AsyncChangingPhoto  extends AsyncTask<Void,Integer,String> {

    ImageView imageSmile;
    Context context;

    public AsyncChangingPhoto(Context context, ImageView imageSmile){
        this.imageSmile = imageSmile;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {

        synchronized (this){
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        return null;
    }
    @Override
    protected void onPreExecute(){

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.raw.duringsneezing);
        imageSmile.setImageBitmap(bitmap);
    }

    @Override
    protected void onPostExecute(String result){
        imageSmile.setImageResource(R.drawable.smile);
    }

    @Override
    protected void onProgressUpdate(Integer... values){

    }
}
