package net.home.t1;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.sdsmdg.harjot.vectormaster.VectorMasterView;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

import static android.view.MotionEvent.INVALID_POINTER_ID;

public class plan extends Fragment implements View.OnTouchListener{
    float dX, dY;

    private Switch switchButton;
    private VectorMasterView my_house;
    private PathModel light;
    private PathModel light_in;
    private PathModel light_autre;

    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    private GestureDetector mDetector;
    private  MyVectorTouchListener touchListener;


    public  plan() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_plan,container,false);
        result.findViewById(R.id.my_house).setOnTouchListener(this);
        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        my_house = (VectorMasterView) view.findViewById(R.id.my_house);
        my_house.setBackgroundColor(0x0000FF00);
        light = my_house.getPathModelByName("L1");
        light_in = my_house.getPathModelByName("L1_in");

        mDetector = new GestureDetector(getContext(),new MyGestureListener());
        mScaleDetector = new ScaleGestureDetector(getContext(),new ScaleListener());
        //MyVectorTouchListener touchListener = new MyVectorTouchListener();
        touchListener = new MyVectorTouchListener();
        my_house.setOnTouchListener(touchListener);
        /*
        my_house.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Interpret MotionEvent data
                mScaleDetector.onTouchEvent(event);
                mDetector.onTouchEvent(event);
                return true;
            }
        });
*/
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.createCallBackToParentActivity();

    }

    private  OnPlanTouchListener mCallBack;

    public  interface OnPlanTouchListener{
        //public boolean onTouch(View v, MotionEvent event);
        public boolean onDown(MotionEvent event);
        public boolean onDoubleTap(MotionEvent event);
        public boolean onSingleTapConfirmed(MotionEvent event);
        public void onLongPress(MotionEvent event);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    private void createCallBackToParentActivity(){
        try {
            mCallBack = (OnPlanTouchListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(e.toString()+"implementer OnPlanTouchListener");
        }
    }

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
            //mCallBack.onDown(event);
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            Log.i("TAG", "onSingleTapConfirmed: ");
            Toast.makeText(getContext(), "Simple Tap", Toast.LENGTH_SHORT).show();
            mCallBack.onSingleTapConfirmed(event);
            return true;
        }
        @Override
        public boolean onDoubleTap(MotionEvent event) {
            Log.i("TAG", "onDoubleTap: ");
            Toast.makeText(getContext(), "Double Tap", Toast.LENGTH_SHORT).show();
            mCallBack.onDoubleTap(event);
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
        public void onLongPress(MotionEvent event) {
            Toast.makeText(getContext(), "Long press", Toast.LENGTH_SHORT).show();
            Log.i("TAG", "onLongPress: ");
            mCallBack.onLongPress(event);
        }


    }

        private class MyVectorTouchListener implements VectorMasterView.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // pass the events to the gesture detector
            // a return value of true means the detector is handling it
            // a return value of false means the detector didn't
            // recognize the event
            //mCallBack.onTouch(v,event);
            mScaleDetector.onTouchEvent(event);
            mDetector.onTouchEvent(event);
            return true;
        }

    };

}