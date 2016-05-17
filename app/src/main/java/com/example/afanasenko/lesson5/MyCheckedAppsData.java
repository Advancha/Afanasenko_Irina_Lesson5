package com.example.afanasenko.lesson5;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Afanasenko on 07.05.2016.
 */
public class MyCheckedAppsData implements Serializable{
    private String mName;
    private Drawable mIcon;
    private String mLink;

    public MyCheckedAppsData(String mName, Drawable mIcon, String mLink) {
        this.mName = mName;
        this.mIcon = mIcon;
        this.mLink = mLink;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Drawable getmIcon() {
        return mIcon;
    }

    public void setmIcon(Drawable mIcon) {
        this.mIcon = mIcon;
    }

    public String getmLink() {
        return mLink;
    }

    public void setmLink(String mLink) {
        this.mLink = mLink;
    }
}
