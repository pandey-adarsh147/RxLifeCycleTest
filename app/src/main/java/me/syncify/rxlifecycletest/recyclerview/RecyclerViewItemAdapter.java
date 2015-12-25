package me.syncify.rxlifecycletest.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarshpandey on 12/8/15.
 */
public abstract class RecyclerViewItemAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> implements ItemAdapter<T> {

    private List<T> items = new ArrayList<>();
    private View.OnClickListener onClickListener;

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH vh = generateViewHolder(parent, viewType);
        View view = vh.itemView;
        if (onClickListener != null) {
            view.setOnClickListener(onClickListener);
        }
        return vh;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    public VH generateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void addItem(T paramItemT) {
        items.add(paramItemT);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public T getItem(int paramInt) {
        return items.get(paramInt);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public List<T> getItems() {
        return items;
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public void prependItem(T paramItemT) {
        items.add(0, paramItemT);
    }

    @Override
    public void removeItem(int paramInt) {
        items.remove(paramInt);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(List<T> ts) {
        if (ts != null && ts.size() > 0) {
            for (T item : ts) {
                addItem(item);
            }
            notifyDataSetChanged();
        }
    }

}
