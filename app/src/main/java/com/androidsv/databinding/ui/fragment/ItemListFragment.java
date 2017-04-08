package com.androidsv.databinding.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.androidsv.databinding.R;
import com.androidsv.databinding.data.DataManager;
import com.androidsv.databinding.databinding.FragmentItemListBinding;
import com.androidsv.databinding.model.Item;
import com.androidsv.databinding.ui.activity.ItemDetailActivity;
import com.androidsv.databinding.ui.activity.ItemFormActivity;
import com.androidsv.databinding.ui.adapter.ItemAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by amilcar on 4/4/17.
 */

public class ItemListFragment extends Fragment implements ValueEventListener, ItemAdapter.ItemClickListener {

    public final static String TAG = ItemListFragment.class.getName();

    private ItemAdapter mItemAdapter;
    private String mSelectedItemId;

    public static ItemListFragment getInstance() {
        return new ItemListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentItemListBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_list, container, false);

        RecyclerView itemRecycler = fragmentBinding.itemRecycler;
        mItemAdapter = new ItemAdapter(this);
        registerForContextMenu(itemRecycler);

        itemRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        itemRecycler.setAdapter(mItemAdapter);

        return fragmentBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        DataManager.addItemListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        DataManager.removeItemListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.item_context_menu, menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.item_list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item_action:
                startActivity(ItemFormActivity.getInstance(getContext(), null));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_item:
                DataManager.deleteItem(mSelectedItemId);
                return true;
            case R.id.action_edit_item:
                startActivity(ItemFormActivity.getInstance(getContext(), mSelectedItemId));
                return true;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        ArrayList<Item> items = new ArrayList<>();

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Log.d(TAG, "onDataChange: " + snapshot.getValue());
            Item item = snapshot.getValue(Item.class);
            items.add(item);
        }

        mItemAdapter.setItems(items);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    public void onItemClick(View view, String itemId) {
        startActivity(ItemDetailActivity.getInstance(getContext(), itemId));
    }

    @Override
    public boolean onItemLongClick(View view, String itemId) {
        mSelectedItemId = itemId;
        return false;
    }
}
