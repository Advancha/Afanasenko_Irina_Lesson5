package com.example.afanasenko.lesson5;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
            view = View.inflate(context,R.layout.new_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.text);
            viewHolder.link =(TextView) view.findViewById(R.id.link);
            viewHolder.image =(ImageView) view.findViewById(R.id.image);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.text.setText(items.get(position).getLabel());
        viewHolder.image.setBackground(items.get(position).getIcon());
        viewHolder.link.setText(items.get(position).getApplicationPackageName());

        return view;
    }

    /*
    public void addItem(String string){
        items.add(string);
        notifyDataSetChanged();
    }
*/
    static class ViewHolder {
        public TextView text;
        public TextView link;
        public ImageView image;

    }
}
