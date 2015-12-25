package me.syncify.rxlifecycletest.recyclerview;

import android.support.annotation.IntegerRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerViewAdapter;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxFragment;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by adarshpandey on 12/8/15.
 */
public abstract class RecyclerViewPresenter<T> {

    private RxRecyclerListView recyclerView;
    private RecyclerView.AdapterDataObserver emptyViewObserver;
    private final Options options;
    protected RxFragment rxFragment;
    private RecyclerViewItemAdapter<T, ? extends RecyclerView.ViewHolder> adapter;

    private View emptyView;

    private CompositeSubscription subscription = new CompositeSubscription();

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = recyclerView.getChildAdapterPosition(v);
            if (adapter.getItemCount() > 0 && adapter.getItemCount() > position) {
                onItemClicked(adapter.getItem(position), position);
            }
        }
    };

    public RecyclerViewPresenter(Options options, RxFragment rxFragment) {
        this.options = options;
        this.rxFragment = rxFragment;
    }

    public void setEmptyView(EmptyView emptyView) {
        this.emptyView = emptyView;
    }

    public void setupRecyclerView(RxRecyclerListView recyclerView) {
        this.recyclerView = recyclerView;
        lifeCycle();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(rxFragment.getActivity());
        this.recyclerView.setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerViewItemAdapter<T, ? extends RecyclerView.ViewHolder> adapter) {
        this.adapter = adapter;
        this.adapter.setOnClickListener(onClickListener);
    }

    private void lifeCycle() {
        rxFragment.lifecycle().subscribe(new Action1<FragmentEvent>() {
            @Override
            public void call(FragmentEvent fragmentEvent) {
                switch (fragmentEvent) {
                    case CREATE_VIEW:
                        dataObserver(adapter);
                        recyclerView.setAdapter(adapter);

                        break;
                    case DESTROY_VIEW:
                        subscription.unsubscribe();
                        break;
                }
            }
        });
    }

    private void dataObserver(RecyclerViewItemAdapter<T, ? extends RecyclerView.ViewHolder> adapter) {
        subscription.add(RxRecyclerViewAdapter.dataChanges(adapter).subscribe(new Action1<RecyclerViewItemAdapter<T, ? extends RecyclerView.ViewHolder>>() {
            @Override
            public void call(RecyclerViewItemAdapter<T, ? extends RecyclerView.ViewHolder> adapter) {
                if (emptyView != null) {
                    if (adapter.isEmpty()) {
                        emptyView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    } else {
                        emptyView.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            }
        }));
    }

    public void onItemClicked(T item, int position) {

    }

    public static class Options {
        private final Class<? extends RecyclerView.LayoutManager> layoutManagerClass;
        private final int numColumns;
        private final boolean useDividers;
        private final boolean useItemClickListener;

        private Options(boolean paramBoolean1, boolean paramBoolean2, Class<? extends RecyclerView.LayoutManager> paramClass, @IntegerRes int paramInt)
        {
            this.useDividers = paramBoolean1;
            this.useItemClickListener = paramBoolean2;
            this.layoutManagerClass = paramClass;
            this.numColumns = paramInt;
        }

        public static Builder custom()
        {
            return new Builder();
        }

        public static Options defaults()
        {
            return new Builder().build();
        }

        public static Builder grid(@IntegerRes int paramInt)
        {
            return new Builder().withLayoutManager(GridLayoutManager.class).columns(paramInt).useDividers().useItemClickListener();
        }

        public static Builder list()
        {
            return new Builder().withLayoutManager(LinearLayoutManager.class).useDividers().useItemClickListener();
        }

        public static class Builder
        {
            private Class<? extends RecyclerView.LayoutManager> layoutManagerClass;
            private int numColumns;
            private boolean useDividers;
            private boolean useItemClickListener;

            private Builder withLayoutManager(Class<? extends RecyclerView.LayoutManager> paramClass)
            {
                this.layoutManagerClass = paramClass;
                return this;
            }

            public RecyclerViewPresenter.Options build()
            {
                return new RecyclerViewPresenter.Options(this.useDividers, this.useItemClickListener, this.layoutManagerClass, this.numColumns);
            }

            public Builder columns(@IntegerRes int paramInt)
            {
                this.numColumns = paramInt;
                return this;
            }

            public Builder useDividers()
            {
                this.useDividers = true;
                return this;
            }

            public Builder useItemClickListener()
            {
                this.useItemClickListener = true;
                return this;
            }
        }
    }
}
