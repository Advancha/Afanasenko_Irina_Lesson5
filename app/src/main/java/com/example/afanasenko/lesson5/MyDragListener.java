package com.example.afanasenko.lesson5;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MyDragListener implements View.OnDragListener {
    public static final String APP_LINKS = "myAppLinks";
    protected Set<String> chekedAppLinks = new HashSet<String>();
    protected SharedPreferences mSettings;

    private Context context;
    public MyDragListener(Context context){
        super();
        this.context=context;

        mSettings = context.getSharedPreferences(APP_LINKS, Context.MODE_PRIVATE);
        Set<String> ret = mSettings.getStringSet(APP_LINKS, new HashSet<String>());
        chekedAppLinks.addAll(ret);

    }
    @Override
    public boolean onDrag(View v, DragEvent event) {
        Drawable enterShape = context.getResources().getDrawable(R.drawable.shape_droptarget);
        Drawable normalShape = context.getResources().getDrawable(R.drawable.shape);

        switch (event.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                // do nothing
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackgroundDrawable(enterShape);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackgroundDrawable(normalShape);
                v.setVisibility(View.VISIBLE);
                break;
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

                View target = v;
                Integer index_target = 0;
                String targetPm = ((TextView) v.findViewById(R.id.link_first)).getText().toString();

                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getApplicationPackageName().equals(targetPm)) {
                        index_target = i;
                    }
                }
                if (index_drop != index_target) {
                    adapter.swapItems(index_drop, index_target);
                }
                droped.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackgroundDrawable(normalShape);
                v.setVisibility(View.VISIBLE);
            default:
                v.setBackgroundDrawable(normalShape);
                v.setVisibility(View.VISIBLE);
                break;
        }
        return true;
    }
}

