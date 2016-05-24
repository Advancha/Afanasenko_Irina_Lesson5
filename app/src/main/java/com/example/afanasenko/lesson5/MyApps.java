package com.example.afanasenko.lesson5;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Afanasenko on 26.04.2016.
 */
public class MyApps {

    private ArrayList<MyAppsData> items;
//    private Set<String> checkedApps;


    public MyApps(Context context, Set<String> checkedApps, boolean onlyChecked) {
        PackageManager mPm = context.getPackageManager();
  //      this.checkedApps = checkedApps;

        List<ApplicationInfo> apps = mPm.getInstalledApplications(0);

        if (apps == null) {
            apps = new ArrayList<ApplicationInfo>();
        }

        if (onlyChecked) {
            items = new ArrayList<MyAppsData>(checkedApps.size());
        }
        else{
            items = new ArrayList<MyAppsData>(apps.size());
        }

        for (int i = 0; i < apps.size(); i++) {
            String pkg = apps.get(i).packageName;
            boolean checked=(checkedApps.contains(pkg));
            // only apps which are launchable
            if ((context.getPackageManager().getLaunchIntentForPackage(pkg) != null)) {
                MyAppsData app = new MyAppsData(context, apps.get(i),checked);
                app.loadLabel(context);

                if (onlyChecked) {
                    if (checked) {
                        items.add(app);
                    }
                }
                else{
                    items.add(app);
                }

            }
        }
    }

    public ArrayList<MyAppsData> GetItems(){
     return items;
    }
/*
    public Set<String> getCheckedApps() {
        return checkedApps;
    }

    public void setCheckedApps(Set<String> checkedApps) {
        this.checkedApps = checkedApps;
    }
    */
}
