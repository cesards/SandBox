package com.menor.android.sandbox.tabletfragments.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.menor.android.sandbox.tabletfragments.Constants;
import com.menor.android.sandbox.tabletfragments.R;
import com.menor.android.sandbox.tabletfragments.ui.activity.DetailActivity;

/**
 * Created by menor on 01/12/13.
 */
public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {

    // Constants
    private static final String ARG_CHOICE = "cur_choice";
    // Views
    private ListView listView;
    // Vars
    private boolean dualPane;
    private int curCheckPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View containerView = inflater.inflate(R.layout.fragment_list, container, false);
        findViews(containerView);
        return containerView;
    }

    private void findViews(View containerView) {
        listView = (ListView) containerView.findViewById(android.R.id.list);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setData(savedInstanceState);
        setListeners();
    }

    private void setData(Bundle savedInstanceState) {
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item_checkable_1, android.R.id.text1);
        adapter.add("One");
        adapter.add("Two");
        adapter.add("Three");
        adapter.add("Four");
        adapter.add("Five");
        adapter.add("Six");
        listView.setAdapter(adapter);

        // Check to see if we have a frame in which to embed the details
        // fragment directly in the containing UI.
        View detailsFrame = getActivity().findViewById(R.id.list_detail_container);
        dualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            curCheckPosition = savedInstanceState.getInt(ARG_CHOICE, 0);
        }

        if (dualPane) {
            // In dual-pane mode, the list view highlights the selected item.
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            // Make sure our UI is in the correct state.
            showDetails(curCheckPosition);
        }

    }

    private void setListeners() {
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_CHOICE, curCheckPosition);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        showDetails(i);
    }

    /**
     * Helper function to show the details of a selected item, either by
     * displaying a fragment in-place in the current UI, or starting a
     * whole new activity in which it is displayed.
     */
    private void showDetails(int index) {
        curCheckPosition = index;

        if (dualPane) {
            // We can display everything in-place with fragments, so update the list to highlight the selected item and show the data.
            listView.setItemChecked(index, true);

            // Check what fragment is currently shown, replace if needed.
            DetailFragment details = (DetailFragment) getFragmentManager().findFragmentById(R.id.list_detail_container);
            if (details == null || details.getShownIndex() != index) {
                // Make new fragment to show this selection.
                details = DetailFragment.newInstance(index);

                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.list_detail_container, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            // Otherwise we need to launch a new activity to display the dialog fragment with selected text.
            Intent intent = new Intent();
            intent.setClass(getActivity(), DetailActivity.class);
            intent.putExtra(Constants.ARG_INDEX, index);
            startActivity(intent);
        }
    }
}
