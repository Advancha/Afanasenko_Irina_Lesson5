package com.example.afanasenko.lesson5;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity  {

  //  private List<String> textItems;
            //= new ArrayList<>(Arrays.asList("Android ","java","IOS","SQL","JDBC","Web services","Erlang",
            //"Asm","Lisp","Haskell","F-Sharp","OCalm","bash","javascript","Carry"));

    private AutoCompleteTextView textView;
    private EditText editText;
    private FilteredRecyclerAdapter listAdapter;
    private Filter filter;


    private RecyclerView mRecyclerView;
    private RecyclerAdapter adapter;

    ArrayList<MyAppsData> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager mPm = this.getPackageManager();
        List<ApplicationInfo> apps = mPm.getInstalledApplications(0);

        if (apps == null) {
            apps = new ArrayList<ApplicationInfo>();
        }

        items = new ArrayList<MyAppsData>(apps.size());
//      textItems = new ArrayList<String>(apps.size());

        for (int i = 0; i < apps.size(); i++) {
            String pkg = apps.get(i).packageName;
            // only apps which are launchable
            if (this.getPackageManager().getLaunchIntentForPackage(pkg) != null) {
                MyAppsData app = new MyAppsData(this, apps.get(i));
                app.loadLabel(this);
                items.add(app);
 //             textItems.add(app.getLabel());
            }
        }



//      textView = (AutoCompleteTextView) findViewById(R.id.auto);

        editText = (EditText) findViewById(R.id.edit);

    //    ArrayAdapter<String> textAdapter = new ArrayAdapter<String>(this, R.layout.item_auto,textItems);
    //   textView.setAdapter(textAdapter);

        editText.addTextChangedListener(textWatcher);

        listAdapter = new FilteredRecyclerAdapter(this, R.layout.item_auto,items);
        filter = listAdapter.getFilter();
        //setListAdapter(listAdapter);




       // CustomAdapter adapter= new CustomAdapter(this, items);
       // adapter= new RecyclerAdapter(this, items);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        onClickList(null);
        //mRecyclerView.setAdapter(adapter);
        mRecyclerView.setAdapter(listAdapter);

       // mList = (RecyclerView) findViewById(R.id.my_recycler_view);
       // mList.setAdapter(adapter);

    }

    public void onClickGrid(View view) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public void onClickList(View view) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
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
            filter.filter(s);
        }
    };

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
