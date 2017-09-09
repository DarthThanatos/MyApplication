package com.example.vobis.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vobis.myapplication.BMI.BMI;
import com.example.vobis.myapplication.async.Async;
import com.example.vobis.myapplication.async.AsyncChangingPhoto;
import com.example.vobis.myapplication.display.DisplayMessageActivity;
import com.example.vobis.myapplication.stackOverflowAdapter.ObjectExpand;
import com.example.vobis.myapplication.trail.Trail;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.example.vobis.myapplication.MESSAGE";
    private Button addingButton;
    private Button multiplyingButton;
    private Button diviningButton;
    private Button subtrackingButton;
    private Button change;
    private TextView Result;
    private EditText firstNumber;
    private EditText secondNumber;
    private String changeTextMommy = "For Mommy";
    private String changeTextNormal = "For Normal";
    private boolean robbText = true;
    Button Download;
    ImageView imageSmile;
    SoundPool mySound;
    int sneezeId;

    Timer timer;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void init(){
       addingButton = (Button) findViewById(R.id.addingButton);
       subtrackingButton = (Button) findViewById(R.id.subtrackingButton);
       multiplyingButton = (Button) findViewById(R.id.multipyingButton);
       diviningButton = (Button) findViewById(R.id.diviningButton);
       change = (Button) findViewById(R.id.change);
       change.setText(changeTextMommy);
       Result = (TextView) findViewById(R.id.Result);
       Result.setGravity(Gravity.CENTER);
       firstNumber = (EditText) findViewById(R.id.firstNumber);
       secondNumber = (EditText) findViewById(R.id.secondNumber);
       Download = (Button) findViewById(R.id.download);
       Download.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Async async = new Async(MainActivity.this,  Download);
               async.execute();
               Download.setEnabled(false);
           }
       });
       imageSmile = (ImageView) findViewById(R.id.imageSmile);
       Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.raw.origami);
       imageSmile.setImageBitmap(bitmap);
       mySound = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
       sneezeId = mySound.load(this,R.raw.sneeze,1);
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
                Toast.makeText(getApplicationContext(),"File Loaded",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                Toast.makeText(getApplicationContext(),"Search selected",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void calculate(View view) {
        switch(view.getId()){
            case R.id.subtrackingButton:
                try {
                    int num1,num2,res;
                    num1 = Integer.parseInt(firstNumber.getText().toString());
                    num2 = Integer.parseInt(secondNumber.getText().toString());
                    res = num1 - num2;
                    Result.setText(String.valueOf(res));
                }catch(Exception e){
                    Result.setText("Invalid data format");
                }
                break;
            case R.id.addingButton:
                try {
                    int num1,num2,res;
                    num1 = Integer.parseInt(firstNumber.getText().toString());
                    num2 = Integer.parseInt(secondNumber.getText().toString());
                    res = num1 + num2;
                    Result.setText(String.valueOf(res));
                }catch(Exception e){
                    Result.setText("Invalid data format");
                }
                break;
            case R.id.diviningButton:
                try {
                    int num1,num2,res;
                    num1 = Integer.parseInt(firstNumber.getText().toString());
                    num2 = Integer.parseInt(secondNumber.getText().toString());
                    try{
                        res = num1 / num2;
                        Result.setText(String.valueOf(res));
                    }catch(Exception e){
                        Result.setText("Cannot Divide");
                    }
                }catch(Exception e){
                    Result.setText("Invalid data format");
                }
                break;
            case R.id.multipyingButton:
                try {
                    int num1,num2,res;
                    num1 = Integer.parseInt(firstNumber.getText().toString());
                    num2 = Integer.parseInt(secondNumber.getText().toString());
                    res = num1 * num2;
                    Result.setText(String.valueOf(res));
                }catch(Exception e){
                    Result.setText("Invalid data format");
                }
                break;
            default:
                Result.setText("Invalid input");
                break;
        }
    }

    public void changeSize(View view){
        if (robbText){
            robbText = false;
            Result.setTextSize(40);
            change.setText(changeTextNormal);
        }
        else{
            robbText = true;
            Result.setTextSize(20);
            change.setText(changeTextMommy);
        }
    }

    public void displayChild(View view){
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        String message = firstNumber.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
    public void displayBMI(View view){
        startActivity(new Intent(this,BMI.class));
    }

    public void displayWithoutView(View view){
        startActivity(new Intent(this,Trail.class));
    }

    public void displayObjectExpand(View view){
        startActivity(new Intent(this, ObjectExpand.class));
    }

    public void play(View view){

        mySound.play(sneezeId, 1, 1, 1, 0, 1);
        //AsyncChangingPhoto async = new AsyncChangingPhoto(this, imageSmile);
        //async.execute();
        final Button shoot = (Button) findViewById(R.id.display);
        shoot.setEnabled(false);
        flag=0;
        final int v[] = {R.raw.duringsneezing, R.raw.aftersneezing,R.raw.troll,R.raw.ashamed,R.raw.happy};
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                runOnUiThread(new Runnable() {

                    public void run() {
                        if (timer != null) {
                            if (flag > v.length - 1 ) {
                                timer.cancel();
                                timer = null;
                                shoot.setEnabled(true);
                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.raw.origami);
                                imageSmile.setImageBitmap(bitmap);
                            } else {

                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), v[flag]);
                                imageSmile.setImageBitmap(bitmap);
                                flag++;
                            }
                        }

                    }
                });

            }
        }, 0, 1000);
    }
}
