package com.example.afanasenko.lesson5;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Afanasenko on 14.04.2016.
 */
public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<MyAppsData> items;

    public CustomAdapter(Context context, List<MyAppsData> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;

       if (view == null) {
            view = View.inflate(context,R.layout.item_first,null);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.text_first);
            viewHolder.link =(TextView) view.findViewById(R.id.link_first);
            viewHolder.image =(ImageView) view.findViewById(R.id.image_first);
            viewHolder.layout=(LinearLayout) view.findViewById(R.id.item_first);
            viewHolder.layout_sub=(LinearLayout) view.findViewById(R.id.item_first_sub);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.text.setText(items.get(position).getLabel());
        viewHolder.image.setBackground(items.get(position).getIcon());
        viewHolder.link.setText(items.get(position).getApplicationPackageName());

        viewHolder.layout_sub.setOnTouchListener(new MyTouchListener());
        viewHolder.layout.setOnDragListener(new MyDragListener(context));

        viewHolder.item=(MyAppsData)getItem(position);

        return view;
    }

    public List<MyAppsData> getItems() {
        return items;
    }

    public void setItems(List<MyAppsData> items) {
        this.items = items;
    }

    public void removeItem(int index){
        this.items.remove(index);

    }
    public void swapItems(int index_1, int index_2){
        Collections.swap(items,index_1,index_2);
    }

    static class ViewHolder {
        public TextView text;
        public TextView link;
        public ImageView image;

        public LinearLayout layout;
        public LinearLayout layout_sub;

        private MyAppsData item;
    }
}
