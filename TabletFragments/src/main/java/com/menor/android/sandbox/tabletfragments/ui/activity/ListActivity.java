package com.menor.android.sandbox.tabletfragments.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.menor.android.sandbox.tabletfragments.R;
import com.menor.android.sandbox.tabletfragments.ui.fragment.ListFragment;

public class ListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setData(savedInstanceState);
    }

    private void setData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            // Fragments are automatically recreated when device is rotated. There is no need in create another instance of them
            ListFragment listFragment = new ListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.list_list_container, listFragment).commit();
        }
    }

}
