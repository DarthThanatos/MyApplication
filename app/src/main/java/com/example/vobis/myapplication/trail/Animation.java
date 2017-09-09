package com.example.vobis.myapplication.trail;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.vobis.myapplication.R;

public class Animation extends ActionBarActivity {

    Storage s;
    private MyFragment dataFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        dataFragment = (MyFragment) fm.findFragmentByTag("data");

        if (dataFragment == null){
            dataFragment = new MyFragment();
            fm.beginTransaction().add(dataFragment,"data").commit();
            s= new Storage();
            dataFragment.setData(s);
        }
        s = dataFragment.getData();

        setContentView(new MyView(this, 0, 0));
        //setContentView(new SingleTouchEventView(this, null));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataFragment.setData(s);
    }
    /*
   @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        return super.onTouchEvent(event);
    }
    */
    public class SingleTouchEventView extends View {
        private Paint paint = new Paint();
        private Path path = s.path;

        public SingleTouchEventView(Context context, AttributeSet attrs) {
            super(context, attrs);

            paint.setAntiAlias(true);
            paint.setStrokeWidth(6f);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    return true;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(eventX, eventY);
                    break;
                case MotionEvent.ACTION_UP:
                    // nothing to do
                    break;
                default:
                    return false;
            }
            s.path = path;

            // Schedules a repaint.
            invalidate();
            return true;
        }
    }

    public class MyView extends View
    {
        int x, y;
        int generalX, generalY;
        public MyView(Context context,int x, int y){

            super(context);
            this.x = x;
            this.y = y;
            generalX = 0;
            generalY = 0;
            vectorX = 0;
            vectorY = 0;
            drawVector = false;
        }

        Bitmap bird;
        Bitmap center;
        Bitmap catapult;
        Bitmap rightdart;
        Bitmap leftdart;
        Bitmap pyramid;
        int canvasHeight;
        int canvasWidth;
        Canvas globalCanvas;
        float vectorX;
        float vectorY;
        boolean drawVector;
        boolean birdFlies = false;
        float x0,x1,y0,y1;
        int t;
        float cosTheta,sinTheta;
        float v0;
        int g  = 10;
        boolean centerMode=false;
        int tmpX;


        @Override
        protected void onDraw( Canvas canvas)
        {
            super.onDraw(canvas);
            int radius;
            radius = 10;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            paint.setColor(Color.parseColor("#CD5C5C"));


            canvasHeight = canvas.getHeight();
            canvasWidth = canvas.getWidth();


            bird = BitmapFactory.decodeResource(getResources(), R.raw.angry);
            catapult = BitmapFactory.decodeResource(getResources(),R.raw.proca);
            rightdart = BitmapFactory.decodeResource(getResources(),R.raw.right_dart);
            leftdart = BitmapFactory.decodeResource(getResources(),R.raw.left_dart);
            pyramid = BitmapFactory.decodeResource(getResources(),R.raw.pyramid);
            center = BitmapFactory.decodeResource(getResources(),R.raw.center);

            if(drawVector){
                x1 = canvas.getWidth() - bird.getWidth()  - 2*generalX   - vectorX;
                x0 = canvasWidth/2-generalX;
                y0 =canvas.getHeight()- leftdart.getHeight() - catapult.getHeight() - bird.getHeight()/2;
                y1 =2*canvas.getHeight()- 2*leftdart.getHeight() - 2*catapult.getHeight() - bird.getHeight()-vectorY;
                canvas.drawLine(x0, y0, x1, y1, paint);
                fillArrow(paint,canvas,x0,y0,x1,y1);
            }



            //general section
            canvas.drawBitmap(catapult,
                    canvasWidth/2-generalX - catapult.getWidth()/2,
                    canvas.getHeight()- leftdart.getHeight() - catapult.getHeight() - generalY,
                    paint);
            canvas.drawBitmap(pyramid,
                    10 + canvas.getWidth() - generalX,
                    canvas.getHeight() - leftdart.getHeight() - pyramid.getHeight() - generalY,
                    paint);
            canvas.drawLine(0,
                    canvas.getHeight() - leftdart.getHeight() - generalY - 1,
                    canvas.getWidth(),
                    canvas.getHeight() - leftdart.getHeight() - generalY - 1,
                    paint);



            if(birdFlies) {

                if(centerMode){
                    generalX = (int) (v0 * cosTheta * t) ;
                    generalY = (int) (v0 * sinTheta * t + g * Math.pow(t, 2) / 2);
                    x = - bird.getWidth()/2 + canvas.getWidth()/2;
                    y = canvas.getHeight() - leftdart.getHeight() - bird.getHeight() - catapult.getHeight();

                    if (generalY < canvasHeight - leftdart.getHeight()) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        t++;
                        canvas.drawBitmap(bird, x ,y , paint);

                    } else {
                        birdFlies = false;
                        generalX = 0;
                        generalY = 0;

                    }
                }

                else {

                    x = (int) (v0 * cosTheta * t);
                    y = (int) (v0 * sinTheta * t + g * Math.pow(t, 2) / 2);

                    if (y < canvasHeight - leftdart.getHeight()) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        t++;
                        canvas.drawBitmap(bird, - bird.getWidth()/2 + canvas.getWidth()/2 +x - generalX,canvas.getHeight()- leftdart.getHeight() - catapult.getHeight() - bird.getHeight()/2+y, paint);

                    } else {
                        birdFlies = false;
                        generalX = 0;
                        generalY = 0;

                    }
                }
            }
            else{

                x = - bird.getWidth()/2 + canvas.getWidth()/2 - generalX;
                y = - generalY + canvas.getHeight() - leftdart.getHeight() - bird.getHeight() - catapult.getHeight();
                canvas.drawBitmap(bird, x, y, paint);
                canvas.drawBitmap(leftdart,
                        0,
                        canvas.getHeight() - leftdart.getHeight() ,
                        paint);
                canvas.drawBitmap(rightdart,
                        canvas.getWidth() - rightdart.getWidth(),
                        canvas.getHeight()-leftdart.getHeight(),
                        paint);
                canvas.drawBitmap(center,
                        canvas.getWidth()/2 - center.getWidth()/2,
                        canvas.getHeight()-center.getHeight(),
                        paint);
            }
            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            if (event.getX()<=leftdart.getWidth() && event.getY()>=canvasHeight - leftdart.getHeight()){
                //Toast.makeText(getApplicationContext(),"Left dart touched",Toast.LENGTH_SHORT).show();
                if(!centerMode) generalX-=20;
            }
            if (event.getX()>=canvasWidth  - rightdart.getWidth() && event.getY()>=canvasHeight - rightdart.getHeight()){
                //Toast.makeText(getApplicationContext(),"Right dart touched",Toast.LENGTH_SHORT).show();
                if (!centerMode) generalX+=20;
            }

            vectorX = event.getX();
            vectorY = event.getY();

            if (event.getX() >= canvasWidth/2 -generalX && event.getX()<=canvasWidth/2-generalX + bird.getWidth() &&event.getY()>=canvasHeight - leftdart.getHeight()-catapult.getHeight() - bird.getHeight() && event.getY()<=canvasHeight - leftdart.getHeight() - catapult.getHeight()){

                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        drawVector = true;
                        return true;
                }
            }

            if (event.getX() >= canvasWidth/2 -center.getWidth() && event.getX()<=canvasWidth/2 + center.getWidth() && event.getY()>=canvasHeight - center.getHeight()) {

                centerMode = centerMode==false?true:false;
                String statement = centerMode?"Center mode selected":"Center mode unabled";
                Toast.makeText(getApplicationContext(),statement,Toast.LENGTH_SHORT).show();
                if (centerMode)generalX = 0;
            }
                if(event.getAction() == MotionEvent.ACTION_UP){

                t = 0;
                tmpX = generalX;
                v0 = (float) Math.sqrt( (double) (Math.pow((double)(x1-x0),2) +Math.pow((double)(y1-y0),2) )  );
                cosTheta = (x1-x0)/v0;
                sinTheta = (y1- y0)/v0;
                drawVector = false;
                birdFlies = true;
            }
            return super.onTouchEvent(event);
        }

        private void fillArrow(Paint paint, Canvas canvas, float x0, float y0, float x1, float y1) {
            paint.setStyle(Paint.Style.STROKE);

            int arrowHeadLenght = 10;
            int arrowHeadAngle = 45;
            float[] linePts = new float[] {x1 - arrowHeadLenght, y1, x1, y1};
            float[] linePts2 = new float[] {x1, y1, x1, y1 + arrowHeadLenght};
            Matrix rotateMat = new Matrix();

            //get the center of the line
            float centerX = x1;
            float centerY = y1;

            //set the angle
            double angle = Math.atan2(y1 - y0, x1 - x0) * 180 / Math.PI + arrowHeadAngle;

            //rotate the matrix around the center
            rotateMat.setRotate((float) angle, centerX, centerY);
            rotateMat.mapPoints(linePts);
            rotateMat.mapPoints(linePts2);

            canvas.drawLine(linePts [0], linePts [1], linePts [2], linePts [3], paint);
            canvas.drawLine(linePts2[0], linePts2[1], linePts2[2], linePts2 [3], paint);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animation, menu);
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
