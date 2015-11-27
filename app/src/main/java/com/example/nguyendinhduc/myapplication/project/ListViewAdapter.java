package com.example.nguyendinhduc.myapplication.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyendinhduc.myapplication.R;

import java.util.List;

/**
 * Created by nguyendinhduc on 11/26/15.
 */
public class ListViewAdapter extends ArrayAdapter<String> {
    Context context;
    int layoutId;
    List<String> objects;

    public ListViewAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutId = resource;
        this.objects = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(layoutId, null);
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        final String s = objects.get(position);
        textView.setText(s);
        return convertView;
    }

}
