package com.example.nguyendinhduc.myapplication.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyendinhduc.myapplication.R;

import java.util.List;

import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_STATUS_DEVELOPMENT;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_STATUS_RELEASE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_STATUS_STABLE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_STATUS_TEST;

/**
 * Created by nguyendinhduc on 11/8/15.
 */
public class ProjectAdapter extends ArrayAdapter<String> {
    Context context;
    int layoutId;
    String[] projectnames = {"Mobile Shop", "N-Puzzle", "Calculator", "Money Manager", "Beauty Wallpaper", "Stock Online"};
    int[] projectstatuses = {0, 1, 2, 3, 2, 0};
    String[] projectManagers = {"dinhduc0605", "vxhuy94", "ginksad", "kktoan", "ginksad", "vxhuy94"};

    public ProjectAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }
        TextView projectName = (TextView) convertView.findViewById(R.id.projectNameLvItem);
        TextView projectStatus = (TextView) convertView.findViewById(R.id.projectStatusLvItem);
        TextView projectManager = (TextView) convertView.findViewById(R.id.projectManagerLvItem);

        projectName.setText(projectnames[position]);
        switch (projectstatuses[position]) {
            case PROJECT_STATUS_STABLE:
                projectStatus.setText("Stable");
                break;
            case PROJECT_STATUS_DEVELOPMENT:
                projectStatus.setText("Development");
                break;
            case PROJECT_STATUS_TEST:
                projectStatus.setText("Test");
                break;
            case PROJECT_STATUS_RELEASE:
                projectStatus.setText("Release");
                break;
        }
        projectManager.setText("Manager: " + projectManagers[position]);
        return convertView;
    }

    @Override
    public int getCount() {
        return projectnames.length;
    }
}
