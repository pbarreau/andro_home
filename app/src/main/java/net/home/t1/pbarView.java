package net.home.t1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.sdsmdg.harjot.vectormaster.VectorMasterView;

public class pbarView extends VectorMasterView{
    Context context;
    VectorMasterView tmp;

    public pbarView(Context context) {
        super(context);
        this.context = context;
        tmp = new VectorMasterView(context);
    }

    public pbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public pbarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

}
