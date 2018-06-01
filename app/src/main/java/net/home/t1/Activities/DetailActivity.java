package net.home.t1.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import  net.home.t1.Fragments.detail;
import net.home.t1.R;

public class DetailActivity extends AppCompatActivity{
    private detail detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);
    }


}
