package me.syncify.rxlifecycletest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class Main2Activity extends AppCompatActivity {

    private SlidingUpPanelLayout slidingPaneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        slidingPaneLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
    }

    public void open(View view) {
    }
}
