package com.example.nguyendinhduc.myapplication.account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyendinhduc.myapplication.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

/**
 * Created by nguyendinhduc on 11/8/15.
 */
public class AccountAdapter extends ArrayAdapter<String> {
    Context context;
    int layoutId;
    int[] avatars = {R.drawable.face1, R.drawable.face2, R.drawable.face3,
            R.drawable.face2, R.drawable.face4, R.drawable.face5};
    String[] realnames = {"Dinh Duc", "Duy Toan", "Thanh Tuan", "Quang Duy", "Nhat Quan", "Xuan Huy"};
    String[] usernames = {"mafiaboss0605", "boydeptrai", "boyxauxi", "girlxinh", "girlxau", "girlbinhthuong"};
    String[] positions = {"Admin", "Manager", "Tester", "Developer", "QualityController", "Manager"};

    public AccountAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }
        CircularImageView circularImageView = (CircularImageView) convertView.findViewById(R.id.avatarLvItem);
        TextView username = (TextView) convertView.findViewById(R.id.usernameLvItem);
        TextView jobLevel = (TextView) convertView.findViewById(R.id.jobLevelLvItem);
        TextView realName = (TextView) convertView.findViewById(R.id.realNameLvItem);
        circularImageView.setImageResource(avatars[position]);
        username.setText(usernames[position]);
        realName.setText(realnames[position]);
        jobLevel.setText(positions[position]);
        return convertView;
    }

    @Override
    public int getCount() {
        return avatars.length;
    }
}
