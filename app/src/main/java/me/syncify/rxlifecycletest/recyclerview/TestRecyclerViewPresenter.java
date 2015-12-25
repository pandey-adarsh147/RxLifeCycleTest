package me.syncify.rxlifecycletest.recyclerview;

import android.widget.Toast;

import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by adarshpandey on 12/8/15.
 */
public class TestRecyclerViewPresenter extends RecyclerViewPresenter<String> {

    public TestRecyclerViewPresenter(Options options, RxFragment rxFragment) {
        super(options, rxFragment);
    }

    @Override
    public void onItemClicked(String item, int position) {
        Toast.makeText(rxFragment.getContext(), item, Toast.LENGTH_SHORT).show();
    }
}
