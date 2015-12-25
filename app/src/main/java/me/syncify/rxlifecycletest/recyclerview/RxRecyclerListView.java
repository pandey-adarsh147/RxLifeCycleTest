package me.syncify.rxlifecycletest.recyclerview;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewChildAttachStateChangeEvent;
import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;

import rx.Observable;

/**
 * Created by adarshpandey on 12/8/15.
 */
public class RxRecyclerListView extends RecyclerView {
    public RxRecyclerListView(Context context) {
        super(context);
    }

    public RxRecyclerListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RxRecyclerListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @CheckResult
    @NonNull
    public Observable<RecyclerViewChildAttachStateChangeEvent> childAttachStateChangeEvents() {
        return RxRecyclerView.childAttachStateChangeEvents(this);
    }

    @CheckResult @NonNull
    public Observable<RecyclerViewScrollEvent> scrollEvents() {
        return RxRecyclerView.scrollEvents(this);
    }

    @CheckResult @NonNull
    public Observable<Integer> scrollStateChanges() {
        return RxRecyclerView.scrollStateChanges(this);
    }

}
