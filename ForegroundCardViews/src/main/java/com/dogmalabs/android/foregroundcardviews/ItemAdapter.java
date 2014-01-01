package com.dogmalabs.android.foregroundcardviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.dogmalabs.android.foregroundcardviews.view.ForegroundRelativeLayout;

import java.util.List;

/**
 * Created by menor on 01/01/14.
 */
public class ItemAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;

    // Constructors
    public ItemAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }

    public ItemAdapter(Context context, String[] objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = inflater.inflate(R.layout.row_item, parent, false);
            vh = ViewHolder.create((com.dogmalabs.android.foregroundcardviews.view.ForegroundRelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        String item = getItem(position);

        // TODOBind your data to the views here

        return vh.rootView;
    }

    private static class ViewHolder {
        public final ForegroundRelativeLayout rootView;

        private ViewHolder(ForegroundRelativeLayout rootView) {
            this.rootView = rootView;
        }

        public static ViewHolder create(com.dogmalabs.android.foregroundcardviews.view.ForegroundRelativeLayout rootView) {
            return new ViewHolder(rootView);
        }
    }


}
