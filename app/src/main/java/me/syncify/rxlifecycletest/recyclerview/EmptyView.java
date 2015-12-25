package me.syncify.rxlifecycletest.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import me.syncify.rxlifecycletest.R;

/**
 * Created by adarshpandey on 12/8/15.
 */
public class EmptyView extends LinearLayout {
    public EmptyView(Context context) {
        super(context);
        init();
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        View.inflate(getContext(), R.layout.empty_view, this);
    }

}
