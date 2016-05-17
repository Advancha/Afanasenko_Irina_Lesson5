package com.example.afanasenko.lesson5;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Afanasenko on 15.05.2016.
 */
public class MyDragDellListener extends MyDragListener {
    public MyDragDellListener(Context context) {
        super(context);
    }

    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {

            case DragEvent.ACTION_DROP:

                View droped = (View) event.getLocalState();
                String dropPm = ((TextView) droped.findViewById(R.id.link_first)).getText().toString();

                GridView parent = (GridView) ((LinearLayout) droped.getParent()).getParent();
                CustomAdapter adapter = (CustomAdapter) parent.getAdapter();
                List<MyAppsData> items = adapter.getItems();

                Integer index_drop = 0;
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getApplicationPackageName().equals(dropPm)) {
                        index_drop = i;
                    }
                }
                adapter.removeItem(index_drop);

                chekedAppLinks.remove(dropPm);
                SharedPreferences.Editor e = mSettings.edit();
                e.putStringSet(APP_LINKS, chekedAppLinks);
                e.apply();

                droped.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();

            default:
                break;
        }
        return true;
    }
}
