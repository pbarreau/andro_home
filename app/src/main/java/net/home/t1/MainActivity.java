package net.home.t1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sdsmdg.harjot.vectormaster.VectorMasterView;
import com.sdsmdg.harjot.vectormaster.models.PathModel;


public class MainActivity extends AppCompatActivity {
    private Switch switchButton;
    private VectorMasterView my_house;
    private PathModel light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_house = (VectorMasterView) findViewById(R.id.my_house);
        light = my_house.getPathModelByName("outline");

        switchButton = (Switch) findViewById(R.id.switch1);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    Log.i("MON SWITCH", "'On'");
                    light.setStrokeColor(Color.parseColor("#ED4337"));
                    light.setFillColor(Color.RED);
                } else {
                    Log.i("MON SWITCH", "'Off'");
                    light.setStrokeColor(Color.GRAY);
                    light.setFillColor(Color.BLACK);
                }

                my_house.invalidate();
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
}
