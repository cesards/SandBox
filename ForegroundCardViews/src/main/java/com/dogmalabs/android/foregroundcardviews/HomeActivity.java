package com.dogmalabs.android.foregroundcardviews;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeActivity extends Activity {

    // Views
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViews();
        setData();
        setListeners();

    }

    private void setListeners() {

    }

    private void setData() {
        ArrayList<String> titles = new ArrayList<String>();
        titles.add("1");
        titles.add("1");
        titles.add("1");
        titles.add("1");
        titles.add("1");
        titles.add("1");
        titles.add("1");
        titles.add("1");
        ItemAdapter itemAdapter = new ItemAdapter(this, titles);
        listView.setAdapter(itemAdapter);
    }

    private void findViews() {
        listView = (ListView) findViewById(android.R.id.list);
    }


}
