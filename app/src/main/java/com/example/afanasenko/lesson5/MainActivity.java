package com.example.afanasenko.lesson5;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

//    String[] str = {"fly", "sky", "high"};
//    private EditText e;
//    private CustomAdapter adapter;
 //           = new CustomAdapter(this, new ArrayList<>(Arrays.asList(str)));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager mPm = this.getPackageManager();
        List<ApplicationInfo> apps = mPm.getInstalledApplications(0);

        if (apps == null) {
            apps = new ArrayList<ApplicationInfo>();
        }

        ArrayList<MyAppsData> items = new ArrayList<MyAppsData>(apps.size());
        for (int i = 0; i < apps.size(); i++) {
            String pkg = apps.get(i).packageName;
            // only apps which are launchable
            if (this.getPackageManager().getLaunchIntentForPackage(pkg) != null) {
                MyAppsData app = new MyAppsData(this, apps.get(i));
                app.loadLabel(this);
                items.add(app);
            }
        }

        CustomAdapter adapter= new CustomAdapter(this, items);
        final GridView mList = (GridView) findViewById(R.id.list);

        mList.setAdapter(adapter);

       // mList.setOnItemSelectedListener(this);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                TextView appLink = (TextView)v.findViewById(R.id.link);
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(appLink.getText().toString());
                startActivity(launchIntent);
            }
        });

    }
/*
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
   */
}
