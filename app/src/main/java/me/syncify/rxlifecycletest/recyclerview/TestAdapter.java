package me.syncify.rxlifecycletest.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.syncify.rxlifecycletest.R;

/**
 * Created by adarshpandey on 12/8/15.
 */
public class TestAdapter extends RecyclerViewItemAdapter<String, RecyclerView.ViewHolder> {
    private Context context;
    public TestAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).textView.setText(getItem(position));
    }

    @Override
    public RecyclerView.ViewHolder generateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

}
