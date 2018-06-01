package net.home.t1.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.home.t1.R;

public class detail extends Fragment{
    private TextView textView;

    public  detail() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_detail,container,false);
        this.textView = (TextView) view.findViewById(R.id.frag_det_texview);
        return view;
    }

    public void updateTextView(int origine, float val_x, float  val_y){
        switch (origine){
            case 10:
                this.textView.setText("Simple click dans l'image !");
                break;
            case 20:
                this.textView.setText("Double click dans l'image !");
                break;
            case 30:
                this.textView.setText("Appui Long click dans l'image !");
                break;

            default:
                break;
        }
    }


}