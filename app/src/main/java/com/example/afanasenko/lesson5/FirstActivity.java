package com.example.afanasenko.lesson5;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FirstActivity extends AppCompatActivity {

    public static final String APP_LINKS = "myAppLinks";
    private SharedPreferences mSettings;

    private GridView gridView;
    private CustomAdapter adapter;

    private MyApps myApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mSettings = getSharedPreferences(APP_LINKS, Context.MODE_PRIVATE);
        Set<String> ret = mSettings.getStringSet(APP_LINKS, new HashSet<String>());

        gridView = (GridView)findViewById(R.id.gridview);
        myApps=new MyApps(this,ret,true);


        adapter=new CustomAdapter(this,myApps.GetItems());
        gridView.setAdapter(adapter);

        TextView dell_app= (TextView) findViewById(R.id.text_dell_app);
        dell_app.setOnDragListener(new MyDragDellListener(this));

        gridView.setOnDragListener(new MyDragGridListener());
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView appLink = (TextView) view.findViewById(R.id.link_first);
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(appLink.getText().toString());
                startActivity(launchIntent);
                Log.v("Long ", "click");
                return true;
            }


        });
/*
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                TextView appLink = (TextView) v.findViewById(R.id.link_first);
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(appLink.getText().toString());
                startActivity(launchIntent);
            }
        });

    */

    }

    public void onClickApp(View view) {
        Intent intent = new Intent(FirstActivity.this, MainActivity.class);
        startActivity(intent);

    }



}
