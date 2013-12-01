package com.menor.android.sandbox.tabletfragments.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.menor.android.sandbox.tabletfragments.R;

/**
 * Created by menor on 01/12/13.
 */
public class DetailFragment extends Fragment {

    // Constants
    private static final String PARAM_INDEX = "index";
    // Views
    private TextView indexView;

    public static DetailFragment newInstance(int index) {
        DetailFragment f = new DetailFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt(PARAM_INDEX, index);
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt(PARAM_INDEX, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }
        View containerView = inflater.inflate(R.layout.fragment_detail, container, false);
        findViews(containerView);
        return containerView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
    }

    private void findViews(View containerView) {
        indexView = (TextView) containerView.findViewById(R.id.detail_index);
    }

    private void setData() {
        indexView.setText(String.valueOf(getArguments().getInt(PARAM_INDEX)));
    }

}
