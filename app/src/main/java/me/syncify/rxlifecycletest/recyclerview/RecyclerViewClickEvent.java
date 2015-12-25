package me.syncify.rxlifecycletest.recyclerview;

import android.view.View;

/**
 * Created by adarshpandey on 12/8/15.
 */
public class RecyclerViewClickEvent {
    public enum Touch {
        CLICK
    }

    public View view;
    public Touch touch;
    public int position;

    public RecyclerViewClickEvent(Touch touch, View view, int position) {
        this.view = view;
        this.touch = touch;
        this.position = position;
    }
}
