package com.androidsv.databinding.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.androidsv.databinding.R;
import com.androidsv.databinding.ui.fragment.ItemDetailFragment;
import com.androidsv.databinding.ui.fragment.ItemFormFragment;

/**
 * Created by amilcar on 4/7/17.
 */

public class ItemDetailActivity extends AppCompatActivity {

    public final static String TAG = ItemDetailActivity.class.getName();
    private final static String EXT_ITEM_ID = "EXT_ITEM_ID";

    public static Intent getInstance(Context context, String itemId) {
        Intent intent = new Intent(context, ItemDetailActivity.class);
        intent.putExtra(EXT_ITEM_ID, itemId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_form);

        if (savedInstanceState == null) {
            String itemId = getIntent().getStringExtra(EXT_ITEM_ID);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, ItemDetailFragment.getInstance(itemId), ItemDetailFragment.TAG)
                    .commit();
        }
    }
}
