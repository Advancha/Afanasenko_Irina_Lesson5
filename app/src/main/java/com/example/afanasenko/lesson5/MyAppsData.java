package com.example.afanasenko.lesson5;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.io.File;

/**
 * Created by Afanasenko on 15.04.2016.
 */
public class MyAppsData {
    private final Context mContext;
    private final ApplicationInfo mInfo;

    private String mAppLabel;
    private Drawable mIcon;

    private boolean mMounted;
    private final File mApkFile;

    private boolean mChecked;

    public MyAppsData(Context context, ApplicationInfo info, boolean checked) {
               mContext = context;
               mInfo = info;

               mApkFile = new File(info.sourceDir);
               mChecked = checked;
            }


    public ApplicationInfo getAppInfo() {
              return mInfo;
    }


    public String getApplicationPackageName() {
              return getAppInfo().packageName;
    }


    public String getLabel() {
               return mAppLabel;
    }


    public Drawable getIcon() {
                if (mIcon == null) {
                        if (mApkFile.exists()) {
                            mIcon = mInfo.loadIcon(mContext.getPackageManager());
                                return mIcon;
                             } else {
                                 mMounted = false;
                             }
                   } else if (!mMounted) {
                         // If the app wasn't mounted but is now mounted, reload
                         // its icon.
                        if (mApkFile.exists()) {
                            mMounted = true;
                                 mIcon = mInfo.loadIcon(mContext.getPackageManager());
                                 return mIcon;
                             }
                    } else {
                        return mIcon;
                    }


                return mContext.getResources().getDrawable(android.R.drawable.sym_def_app_icon);
             }

    public void setmChecked(boolean mChecked) {
        this.mChecked = mChecked;
    }

    public boolean ismChecked() {
        return mChecked;
    }

    void loadLabel(Context context) {
                 if (mAppLabel == null || !mMounted) {
                       if (!mApkFile.exists()) {
                           mMounted = false;
                                mAppLabel = mInfo.packageName;
                             } else {
                                 mMounted = true;
                                 CharSequence label = mInfo.loadLabel(context.getPackageManager());
                                 mAppLabel = label != null ? label.toString() : mInfo.packageName;
                             }
                    }
            }

}
