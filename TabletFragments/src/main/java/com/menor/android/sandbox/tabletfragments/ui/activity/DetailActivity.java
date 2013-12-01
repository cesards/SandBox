package com.menor.android.sandbox.tabletfragments.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.menor.android.sandbox.tabletfragments.Constants;
import com.menor.android.sandbox.tabletfragments.ui.fragment.DetailFragment;

public class DetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the dialog in-line with the list so we don't need this activity.
            finish();
            return;
        }

        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            DetailFragment detailFragment = DetailFragment.newInstance(getIntent().getExtras().getInt(Constants.ARG_INDEX));
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, detailFragment).commit();
        }
    }

}
