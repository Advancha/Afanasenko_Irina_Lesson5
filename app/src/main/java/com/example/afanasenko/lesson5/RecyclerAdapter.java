package com.example.afanasenko.lesson5;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<MyAppsData> items;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public TextView link;
        public ImageView image;
        public CheckBox checkBox;

        public ViewHolder(View v) {
            super(v);

            text = (TextView) v.findViewById(R.id.text);
            link = (TextView) v.findViewById(R.id.link);
            image = (ImageView) v.findViewById(R.id.image);
            checkBox = (CheckBox)v.findViewById(R.id.checkBox);
        }
    }

    public RecyclerAdapter(Context context, List<MyAppsData> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);

        v.setOnClickListener(new MyClickListener());

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(items.get(position).getLabel());
        holder.image.setBackground(items.get(position).getIcon());
        holder.link.setText(items.get(position).getApplicationPackageName());
        holder.checkBox.setChecked(items.get(position).ismChecked());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;

        if (view == null) {
            view = View.inflate(context,R.layout.item_main,parent);
            viewHolder = new ViewHolder(view);
            viewHolder.text = (TextView) view.findViewById(R.id.text);
            viewHolder.link =(TextView) view.findViewById(R.id.link);
            viewHolder.image =(ImageView) view.findViewById(R.id.image);
            viewHolder.checkBox =(CheckBox) view.findViewById(R.id.checkBox);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.text.setText(items.get(position).getLabel());
        viewHolder.image.setBackground(items.get(position).getIcon());
        viewHolder.link.setText(items.get(position).getApplicationPackageName());
        viewHolder.checkBox.setChecked(items.get(position).ismChecked());

        return view;
    }

    public List<MyAppsData> getItems() {
        return items;
    }

    public Context getContext() {
        return context;
    }
}
