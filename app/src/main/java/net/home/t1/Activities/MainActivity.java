package net.home.t1.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Menu;
import android.view.MenuItem;

import net.home.t1.R;
import net.home.t1.Fragments.detail;
import net.home.t1.Fragments.plan;

// https://github.com/codepath/android_guides/wiki/Gestures-and-Touch-Events

// https://medium.com/quick-code/pinch-to-zoom-with-multi-touch-gestures-in-android-d6392e4bf52d
// https://stackoverflow.com/questions/45054908/how-to-add-a-gesture-detector-to-a-view-in-android
// http://codetheory.in/android-gesturedetector/

// https://stackoverflow.com/questions/9398057/android-move-a-view-on-touch-move-action-move
// https://blahti.wordpress.com/2012/06/26/images-with-clickable-areas/
// https://stackoverflow.com/questions/2173936/how-to-set-background-color-of-a-view

public class MainActivity extends AppCompatActivity implements plan.OnPlanTouchListener {

    private plan mainFragment;
    private detail detailFragment;

    @Override
    public boolean onDown (MotionEvent event){
        Log.e(getClass().getSimpleName(),"Down");
        return true;
    }

    @Override
    public boolean onDoubleTap (MotionEvent event){
        Log.e(getClass().getSimpleName(),"DbTap");
        float x = event.getX();
        float y = event.getY();
        sendToDetailFragment(10,x,y);
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed (MotionEvent event){
        Log.e(getClass().getSimpleName(),"STap");
        float x = event.getX();
        float y = event.getY();
        sendToDetailFragment(20,x,y);
        return true;
    }
    @Override
    public void onLongPress (MotionEvent event){
        Log.e(getClass().getSimpleName(),"LPress");
        float x = event.getX();
        float y = event.getY();
        sendToDetailFragment(30,x,y);

    }

    /*
    @Override
    public boolean onTouch(View v, MotionEvent event){
        Log.e(getClass().getSimpleName(),"Touch");
        return true;
    }
    */

    private void sendToDetailFragment(int from, float v_x, float v_y){
        if(detailFragment !=null && detailFragment.isVisible()){
            detailFragment.updateTextView(from,v_x,v_y);
        }else{
            //Intent i = new Intent(getBaseContext(),this,detail.class);
        }
    }

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
