package net.home.t1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
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
// https://stackoverflow.com/questions/2173936/how-to-set-background-color-of-a-view

public class MainActivity extends AppCompatActivity implements plan.OnPlanTouchListener {

    @Override
    public boolean onDown (MotionEvent event){
        Log.e(getClass().getSimpleName(),"Down");
        return true;
    }

    @Override
    public boolean onDoubleTap (MotionEvent event){
        Log.e(getClass().getSimpleName(),"DbTap");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed (MotionEvent event){
        Log.e(getClass().getSimpleName(),"STap");
        return true;
    }
    @Override
    public void onLongPress (MotionEvent event){
        Log.e(getClass().getSimpleName(),"LPress");
    }

    /*
    @Override
    public boolean onTouch(View v, MotionEvent event){
        Log.e(getClass().getSimpleName(),"Touch");
        return true;
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    return true;
    }

}
