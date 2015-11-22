package com.example.nguyendinhduc.myapplication.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nguyendinhduc.myapplication.Constant;
import com.example.nguyendinhduc.myapplication.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.parse.ParseObject;
import com.parse.ParseRole;

import java.util.List;

/**
 * Created by nguyendinhduc on 11/8/15.
 */
public class AccountAdapter extends ArrayAdapter<ParseObject> {
    Context context;
    int layoutId;
    int[] avatars = {R.drawable.face1, R.drawable.face2, R.drawable.face3,R.drawable.face4};
    List<ParseObject> projects;

    public AccountAdapter(Context context, int resource, List<ParseObject> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutId = resource;
        projects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }
        ParseObject project = projects.get(position);
        CircularImageView circularImageView = (CircularImageView) convertView.findViewById(R.id.avatarLvItem);
        TextView username = (TextView) convertView.findViewById(R.id.usernameLvItem);
        TextView jobLevel = (TextView) convertView.findViewById(R.id.jobLevelLvItem);
        TextView realName = (TextView) convertView.findViewById(R.id.realNameLvItem);
        return convertView;
    }

}
