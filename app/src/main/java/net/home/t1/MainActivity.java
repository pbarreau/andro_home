package net.home.t1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sdsmdg.harjot.vectormaster.VectorMasterView;
import com.sdsmdg.harjot.vectormaster.models.GroupModel;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

import static android.view.MotionEvent.INVALID_POINTER_ID;

// https://github.com/codepath/android_guides/wiki/Gestures-and-Touch-Events

// https://medium.com/quick-code/pinch-to-zoom-with-multi-touch-gestures-in-android-d6392e4bf52d
// https://stackoverflow.com/questions/45054908/how-to-add-a-gesture-detector-to-a-view-in-android
// http://codetheory.in/android-gesturedetector/

// https://stackoverflow.com/questions/9398057/android-move-a-view-on-touch-move-action-move
// https://blahti.wordpress.com/2012/06/26/images-with-clickable-areas/

public class MainActivity extends AppCompatActivity {
    float dX, dY;

    private Switch switchButton;
    private VectorMasterView my_house;
    private pbarView lamienne;
    private PathModel light;
    private PathModel light_in;
    private PathModel light_autre;

    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    private GestureDetector mDetector;

    // The ‘active pointer’ is the one currently moving our object.
    private int mActivePointerId = INVALID_POINTER_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_house = (VectorMasterView) findViewById(R.id.my_house);
        my_house.setBackgroundColor(0x0000FF00);
        light = my_house.getPathModelByName("L1");
        light_in = my_house.getPathModelByName("L1_in");

        mDetector = new GestureDetector(this, new MyGestureListener());
        mScaleDetector = new ScaleGestureDetector(this,new ScaleListener());
        //my_house.setOnTouchListener(touchListener);


        my_house.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Interpret MotionEvent data
                mScaleDetector.onTouchEvent(event);
                mDetector.onTouchEvent(event);
                return true;
            }
        });


        switchButton = (Switch) findViewById(R.id.switch1);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    Log.i("MON SWITCH", "'On'");
                    light.setStrokeColor(Color.parseColor("#ED4337"));
                    light.setFillColor(Color.RED);
                    light_in.setFillColor(Color.RED);
                } else {
                    Log.i("MON SWITCH", "'Off'");
                    light.setStrokeColor(Color.GRAY);
                    light.setFillColor(Color.BLACK);
                    light_in.setFillColor(Color.GRAY);
                }

                my_house.update();
            }
        });

        // Mettre la config initiale avant affichage
        if(switchButton.isChecked()){
            light.setFillColor(Color.parseColor("#ED4337"));
        }
        else{
            light.setFillColor(Color.BLACK);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

/*
    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        mScaleDetector.onTouchEvent(event);
        mDetector.onTouchEvent(event);
        return true;

    }
    */


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
            my_house.setScaleX(mScaleFactor);
            my_house.setScaleY(mScaleFactor);

            my_house.invalidate();
            return true;
        }
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onDown(MotionEvent event) {
            Log.d("TAG","onDown: ");

            // don't return false here or else none of the other
            // gestures will work
            dX = my_house.getX() - event.getRawX();
            dY = my_house.getY() - event.getRawY();
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("TAG", "onSingleTapConfirmed: ");
            return true;
        }
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("TAG", "onDoubleTap: ");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.i("TAG", "onScroll: ");
            my_house.animate()
                    .x(e2.getRawX()+dX)
                    .y(e2.getRawY()+dY)
                    .setDuration(0)
                    .start();
            //my_house.setTranslationY(distanceY);
            my_house.invalidate();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("TAG", "onLongPress: ");
        }


    }


    /*
    VectorMasterView.OnTouchListener touchListener = new VectorMasterView.OnTouchListener () {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // pass the events to the gesture detector
            // a return value of true means the detector is handling it
            // a return value of false means the detector didn't
            // recognize the event
            mScaleDetector.onTouchEvent(event);
            mDetector.onTouchEvent(event);
            return true;
        }

    };
    */

}
