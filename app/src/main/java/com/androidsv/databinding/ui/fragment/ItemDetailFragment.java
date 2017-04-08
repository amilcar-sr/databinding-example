package com.androidsv.databinding.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidsv.databinding.R;
import com.androidsv.databinding.data.DataManager;
import com.androidsv.databinding.databinding.FragmentItemDetailBinding;
import com.androidsv.databinding.databinding.FragmentItemFormBinding;
import com.androidsv.databinding.model.Item;
import com.androidsv.databinding.ui.viewmodel.ItemViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by amilcar on 4/7/17.
 */

public class ItemDetailFragment extends Fragment implements ValueEventListener, View.OnClickListener {

    public final static String TAG = ItemDetailFragment.class.getName();
    private final static String ARG_ITEM_ID = "ARG_ITEM_ID";

    private Item mItem = new Item();
    private ItemViewModel mViewModel;
    private boolean mIsEditing;

    public static ItemDetailFragment getInstance(String itemId) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_ITEM_ID, itemId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentItemDetailBinding itemFormBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_detail, container, false);

        mViewModel = new ItemViewModel(mItem);
        itemFormBinding.setItem(mViewModel);

        String itemId = getArguments().getString(ARG_ITEM_ID);
        DataManager.getItem(itemId, this);

        return itemFormBinding.getRoot();
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            mItem = snapshot.getValue(Item.class);
            mViewModel.updateModel(mItem);
        }

        mIsEditing = mItem.getId() != null;
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.d(TAG, "onCancelled: " + databaseError.getMessage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_fab:
                if (mIsEditing) {
                    DataManager.editItem(mItem);
                } else {
                    DataManager.addItem(mItem);
                }
                getActivity().finish();
                break;
            default:
                break;
        }
    }
}
