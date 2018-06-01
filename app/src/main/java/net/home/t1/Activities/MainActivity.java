package net.home.t1.Activities;

import android.app.FragmentTransaction;
import android.content.Intent;
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
    public boolean onSingleTapConfirmed (MotionEvent event){
        Log.e(getClass().getSimpleName(),"STap");
        float x = event.getRawX();
        float y = event.getRawY();
        sendToDetailFragment(10,x,y);
        return true;
    }
    @Override
    public boolean onDoubleTap (MotionEvent event){
        Log.e(getClass().getSimpleName(),"DbTap");
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
        Bundle args = new Bundle();
        args.putInt("ARG_ORIGINE",from);
        args.putFloat("ARG_X",v_x);
        args.putFloat("ARG_Y",v_y);

        if(detailFragment !=null && detailFragment.isVisible()){
            detailFragment.setArguments(args);
            detailFragment.updateTextView(from,v_x,v_y);
            Log.e(getClass().getSimpleName(),"1 detai");
        }else{
            detail newDetail = new detail();
            newDetail.setArguments(args);

            android.support.v4.app.FragmentTransaction Matransaction = getSupportFragmentManager().beginTransaction();
            Matransaction.replace(R.id.fl_detail,newDetail);
            Matransaction.addToBackStack(null);
            Matransaction.commit();
            Log.e(getClass().getSimpleName(),"2 detai");
            //Intent i = new Intent(this,detail.class);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureAndShowFraments();
    }

    private void configureAndShowFraments(){
        mainFragment = (plan)getSupportFragmentManager().findFragmentById(R.id.le_plan);

        if(mainFragment == null){
            mainFragment = new plan();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.le_plan,mainFragment).commit();
        }

        detailFragment = (detail)getSupportFragmentManager().findFragmentById((R.id.les_details));

        if(detailFragment == null){
            detailFragment = new detail();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.les_details,detailFragment).commit();
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
    return true;
    }

}
