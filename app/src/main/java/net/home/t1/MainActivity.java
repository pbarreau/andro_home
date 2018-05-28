package net.home.t1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sdsmdg.harjot.vectormaster.VectorMasterView;
import com.sdsmdg.harjot.vectormaster.models.GroupModel;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

import static android.view.MotionEvent.INVALID_POINTER_ID;


public class MainActivity extends AppCompatActivity {
    private Switch switchButton;
    private VectorMasterView my_house;
    private pbarView lamienne;
    private PathModel light;
    private PathModel light_in;
    private PathModel light_autre;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    // The ‘active pointer’ is the one currently moving our object.
    private int mActivePointerId = INVALID_POINTER_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_house = (VectorMasterView) findViewById(R.id.my_house);
        lamienne = (pbarView) findViewById(R.id.my_house2);
        light = my_house.getPathModelByName("L1");
        light_in = my_house.getPathModelByName("L1_in");
        //mScaleDetector = new ScaleGestureDetector(getApplicationContext(),new ScaleListener());
        mScaleDetector = new ScaleGestureDetector(this,new ScaleListener());

        light_autre = lamienne.getPathModelByName("L1_in");

        switchButton = (Switch) findViewById(R.id.switch1);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    Log.i("MON SWITCH", "'On'");
                    light.setStrokeColor(Color.parseColor("#ED4337"));
                    light.setFillColor(Color.RED);
                    light_in.setFillColor(Color.RED);
                    light_autre.setFillColor(Color.RED);
                } else {
                    Log.i("MON SWITCH", "'Off'");
                    light.setStrokeColor(Color.GRAY);
                    light.setFillColor(Color.BLACK);
                    light_in.setFillColor(Color.GRAY);
                    light_autre.setFillColor(Color.GRAY);
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

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        mScaleDetector.onTouchEvent(event);
        return true;

        /*
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d("Dbg","Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE) :
                Log.d("Dbg","Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d("Dbg","Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d("Dbg","Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d("Dbg","Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
        */
    }
    /*
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        // Get the pointer ID
        mActivePointerId = ev.getPointerId(0);
        int pointerIndex = ev.findPointerIndex(mActivePointerId);

        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);

        // Get the pointer's current position
        float x = ev.getX(pointerIndex);
        float y = ev.getY(pointerIndex);

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
}
