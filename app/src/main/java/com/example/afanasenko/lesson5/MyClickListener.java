package com.example.afanasenko.lesson5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

import java.util.List;

/**
 * Created by Afanasenko on 17.05.2016.
 */
class MyClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        RecyclerView recyclerView = (RecyclerView)v.getParent();
        FilteredRecyclerAdapter adapter =  (FilteredRecyclerAdapter)recyclerView.getAdapter();

        List<MyAppsData> items = adapter.getItems();

        int itemPosition = ((RecyclerView)v.getParent()).getChildPosition(v);

        Log.e("Clicked", items.get(itemPosition).getApplicationPackageName());

        //String  = items.get(itemPosition).getApplicationPackageName();

        Context context = adapter.getContext();
        Intent i = new Intent(context, DialogActivity.class);

        /*
        Bundle appBundle = new Bundle();
        appBundle.putString("APP_PN", items.get(itemPosition).getApplicationPackageName());
        appBundle.putString("APP_NAME", items.get(itemPosition).getAppInfo().name);
        appBundle.putString("APP_DIR", items.get(itemPosition).getAppInfo().sourceDir);
        */
        i.putExtra("APP_NAME", items.get(itemPosition).getLabel());
        i.putExtra("APP_PN", items.get(itemPosition).getApplicationPackageName());
     //   i.putExtra("APP_DIR", items.get(itemPosition).getAppInfo().sourceDir.toString());
        context.startActivity(i);


    }
}