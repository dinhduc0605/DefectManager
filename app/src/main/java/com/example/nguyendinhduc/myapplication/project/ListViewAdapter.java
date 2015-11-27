package com.example.nguyendinhduc.myapplication.project;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by nguyendinhduc on 11/26/15.
 */
public class ListViewAdapter extends ArrayAdapter<String> {
    public ListViewAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }
}
