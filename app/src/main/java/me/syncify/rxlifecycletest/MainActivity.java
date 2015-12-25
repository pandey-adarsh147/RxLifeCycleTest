package me.syncify.rxlifecycletest;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).map(new Func1<Long, Object>() {
            @Override
            public Object call(Long aLong) {
                System.out.println("MAp:" + Thread.currentThread());
                return "Hello, Hell";
            }
        })
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("Subs:" + o + Thread.currentThread());
            }
        });

        /*FragmentManager fragmentManager = getSupportFragmentManager();
        JhakasFragment jhakasFragment = new JhakasFragment();


        jhakasFragment.lifecycle().subscribe(new Action1<FragmentEvent>() {
            @Override
            public void call(FragmentEvent fragmentEvent) {
                System.out.println("Jhakas Fragment: " + fragmentEvent);
            }
        });

        fragmentManager.beginTransaction().add(R.id.place_holder, jhakasFragment).commit();*/
    }

    public void add(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();


        fragmentManager.popBackStack("ok", FragmentManager.POP_BACK_STACK_INCLUSIVE);



        final AddFragment addFragment = new AddFragment();

        addFragment.lifecycle().filter(new Func1<FragmentEvent, Boolean>() {
            @Override
            public Boolean call(FragmentEvent fragmentEvent) {
                return fragmentEvent == FragmentEvent.RESUME;
            }
        }).switchMap(new Func1<FragmentEvent, Observable<?>>() {
            @Override
            public Observable<?> call(FragmentEvent fragmentEvent) {
                return RxView.globalLayouts(addFragment.getView());
            }
        }).compose(addFragment.bindUntilEvent(FragmentEvent.DESTROY))
        .subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (addFragment != null && addFragment.getView() != null) {
                    System.out.println("AddFm: " + o);
                }
            }
        });

        fragmentManager.beginTransaction().replace(R.id.place_holder, addFragment)
                .addToBackStack("ok").commit();
    }
}
