package com.androidsv.databinding.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidsv.databinding.R;
import com.androidsv.databinding.model.Item;
import com.androidsv.databinding.ui.fragment.ItemListFragment;
import com.google.firebase.crash.FirebaseCrash;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Show Firebase crash reports (add dependency, run this line... works like magic!)
//        FirebaseCrash.report(new Exception("Android non-fatal error"));
//
//        Item item = new Item();
//        item.getId().contains("");

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, ItemListFragment.getInstance(), ItemListFragment.TAG)
                    .commit();
        }
    }
}
