package com.example.afanasenko.lesson5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity  {
    public static final String APP_LINKS = "myAppLinks";
    private Set<String> chekedAppLinks = new HashSet<String>();
    private SharedPreferences mSettings;

    //private AutoCompleteTextView textView;
    private EditText editText;
    private FilteredRecyclerAdapter listAdapter;
    private Filter filter;

    private RecyclerView mRecyclerView;

    private MyApps myApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettings = getSharedPreferences(APP_LINKS, Context.MODE_PRIVATE);
        Set<String> ret = mSettings.getStringSet(APP_LINKS, new HashSet<String>());
        chekedAppLinks.addAll(ret);

        myApps=new MyApps(this,ret,false);

        editText = (EditText) findViewById(R.id.edit);
        editText.addTextChangedListener(textWatcher);
        //editText.setFocusable(false);


        listAdapter = new FilteredRecyclerAdapter(this, R.layout.item_auto,myApps.GetItems());
        filter = listAdapter.getFilter();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        onClickGrid(null);
        mRecyclerView.setAdapter(listAdapter);


    }

    public void onClickGrid(View view) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        listAdapter.UpdateChechedApps(chekedAppLinks);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public void onClickList(View view) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listAdapter.UpdateChechedApps(chekedAppLinks);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            listAdapter.UpdateChechedApps(chekedAppLinks);
            filter.filter(s);
        }
    };

    public void onClickCheckBox(View view) {
        boolean checked = ((CheckBox) view.findViewById(R.id.checkBox)).isChecked();

        LinearLayout mLayout = (LinearLayout)((ViewParent)view.getParent());
        String mLink = ((TextView)mLayout.findViewById(R.id.link)).getText().toString();

        if (checked) {
            chekedAppLinks.add(mLink);
        }
        else {
            chekedAppLinks.remove(mLink);
        }
        SharedPreferences.Editor e = mSettings.edit();
        e.putStringSet(APP_LINKS, chekedAppLinks);
        e.apply();
    }


    public void onClickEdit(View view) {
        view.setFocusable(true);
    }


}
