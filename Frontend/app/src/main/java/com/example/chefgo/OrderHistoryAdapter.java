package com.example.chefgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderHistoryAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public OrderHistoryAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    /**
     *
     * @return size of list
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     *
     * @param position
     * @return object at position
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     *
     * @param position
     * @return null
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter, null);
        }

        TextView listItemText = view.findViewById(R.id.list_itm_string);
        listItemText.setText(list.get(position));


        Button reviewsBtn = view.findViewById(R.id.review_btn);
        Button profileBtn = view.findViewById(R.id.chefProfile_btn);

        //Do stuff with review button
        reviewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Do stuff with profile button
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return null;
    }
}
