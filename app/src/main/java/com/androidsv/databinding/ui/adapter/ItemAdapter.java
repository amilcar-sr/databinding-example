package com.androidsv.databinding.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidsv.databinding.R;
import com.androidsv.databinding.databinding.ItemListBinding;
import com.androidsv.databinding.model.Item;
import com.androidsv.databinding.ui.viewmodel.ItemViewModel;

import java.util.ArrayList;

/**
 * Created by amilcar on 4/5/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<Item> mItems;
    private ItemClickListener mListener;

    public interface ItemClickListener {
        void onItemClick(View view, String itemId);

        boolean onItemLongClick(View view, String itemId);
    }

    public ItemAdapter(ItemClickListener listener) {
        mListener = listener;
    }

    public ItemAdapter(ArrayList<Item> items) {
        mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = mItems.get(position);

        holder.itemBinding.setItem(new ItemViewModel(item));
        holder.itemBinding.setListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public void setItems(ArrayList<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public Item getItem(int position) {
        return mItems.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemListBinding itemBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);
        }
    }
}
