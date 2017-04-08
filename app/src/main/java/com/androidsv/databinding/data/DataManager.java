package com.androidsv.databinding.data;

import com.androidsv.databinding.model.Item;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by amilcar on 4/4/17.
 */

public class DataManager {
    private final static String TAG = DataManager.class.getName();

    private final static String ITEM_REFERENCE_NAME = "items";

    private static DatabaseReference getItemsReference() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        return database.getReference(ITEM_REFERENCE_NAME);
    }

    public static void addItem(Item item) {
        DatabaseReference itemReference = getItemsReference().push();
        item.setId(itemReference.getKey());

        itemReference.setValue(item);
    }

    public static void editItem(Item item) {
        DatabaseReference itemReference = getItemsReference().child(item.getId());

        itemReference.setValue(item);
    }

    public static void deleteItem(String itemId) {
        DatabaseReference itemReference = getItemsReference().child(itemId);

        itemReference.removeValue();
    }

    public static void getItem(String itemId, ValueEventListener listener) {
        DatabaseReference itemReference = getItemsReference();
        itemReference.orderByChild("id").equalTo(itemId).addListenerForSingleValueEvent(listener);
    }

    public static void addItemListener(ValueEventListener listener) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference(ITEM_REFERENCE_NAME).addValueEventListener(listener);
    }

    public static void removeItemListener(ValueEventListener listener) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference(ITEM_REFERENCE_NAME).removeEventListener(listener);
    }
}
