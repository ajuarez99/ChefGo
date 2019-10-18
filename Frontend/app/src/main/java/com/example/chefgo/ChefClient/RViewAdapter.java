package com.example.chefgo.ChefClient;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chefgo.R;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.RViewHolder>{

    private String[] data;

    public static class RViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RViewHolder(TextView v){
            super(v);
            textView = v;
        }
    }

    public RViewAdapter(String[] data){
        this.data = data;
    }

    @Override
    public RViewAdapter.RViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //R.layout.activity... could be wrong
        TextView view = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_chef_active_meals, parent, false);
        RViewHolder holder = new RViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(data[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.length;
    }

}
