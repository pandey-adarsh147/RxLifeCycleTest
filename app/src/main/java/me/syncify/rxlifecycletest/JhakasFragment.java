package me.syncify.rxlifecycletest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.syncify.rxlifecycletest.recyclerview.EmptyView;
import me.syncify.rxlifecycletest.recyclerview.RecyclerViewPresenter;
import me.syncify.rxlifecycletest.recyclerview.RxRecyclerListView;
import me.syncify.rxlifecycletest.recyclerview.TestAdapter;
import me.syncify.rxlifecycletest.recyclerview.TestRecyclerViewPresenter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by adarshpandey on 12/2/15.
 */
public class JhakasFragment extends RxFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, null);
        EmptyView emptyView = (EmptyView) view.findViewById(R.id.emptyView);
        TestRecyclerViewPresenter presenter = new TestRecyclerViewPresenter(RecyclerViewPresenter.Options.list().build(), this);
        presenter.setEmptyView(emptyView);
        TestAdapter adapter = new TestAdapter(getActivity());
        presenter.setAdapter(adapter);
        presenter.setupRecyclerView((RxRecyclerListView) view.findViewById(R.id.listView));

        List<String> strings = new ArrayList<>();
        strings.add("Hello");
        strings.add("Hello boy");
        strings.add("Hello girl");
        strings.add("Hello men");
        strings.add("Hello women");

        Observable.just(strings).delay(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(adapter);

        return view;
    }


}
