package me.syncify.rxlifecycletest.recyclerview;

import java.util.List;

import rx.Observer;

/**
 * Created by adarshpandey on 12/8/15.
 */
public interface ItemAdapter<T> extends Observer<List<T>> {
    void addItem(T paramItemT);

    void clear();

    T getItem(int paramInt);

    int getItemCount();

    List<T> getItems();

    boolean isEmpty();

    void notifyDataSetChanged();

    void prependItem(T paramItemT);

    void removeItem(int paramInt);
}
