package com.example.afanasenko.lesson5;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilteredRecyclerAdapter extends RecyclerAdapter implements Filterable {

    private ListFilter filter=new ListFilter();
    private List<MyAppsData> objects;
    private List<MyAppsData> originalObjects;
    private ArrayList<MyAppsData> filteredList;

    public FilteredRecyclerAdapter(Context context, int resource, List<MyAppsData> objects) {
        super(context, objects);
        this.objects = objects;
        originalObjects = new ArrayList<>(objects);
        filteredList = new ArrayList<MyAppsData>();

    }
    public void UpdateChechedApps(Set<String> checkedApps){
        Boolean checked=false;

        for (int i=0; i<objects.size(); i++){
            checked= checkedApps.contains(objects.get(i).getApplicationPackageName());
            objects.get(i).setmChecked(checked);
        }

    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class ListFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String str;
            objects.clear();
            filteredList.clear();
            FilterResults filterResults = new FilterResults();

            if (constraint != null || constraint.length() != 0) {
                for (MyAppsData myAppsData : originalObjects) {
                    str=myAppsData.getLabel();
                    if (str.contains(constraint.toString())) {
                        str = str.replaceAll(constraint.toString(), "<big><b>" + constraint + "</b></big>");
                        filteredList.add(myAppsData);
                    }
                }

            }

            filterResults.values = filteredList;
            filterResults.count = filteredList.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            objects.addAll(((List<MyAppsData>) results.values));
            notifyDataSetChanged();
        }
    }
}
