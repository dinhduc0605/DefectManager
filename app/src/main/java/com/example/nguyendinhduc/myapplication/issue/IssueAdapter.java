package com.example.nguyendinhduc.myapplication.issue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyendinhduc.myapplication.Constant;
import com.example.nguyendinhduc.myapplication.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_ASSIGN_TO;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_CATEGORY;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_DESCRIPTION;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_PRIORITY;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_STATUS;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_SUMMARY;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_PROJECT;
import static com.example.nguyendinhduc.myapplication.Constant.USER_NAME;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gink on 11/8/15.
 */

public class IssueAdapter extends ArrayAdapter<ParseObject> {

    Context context;
    int layoutId;
    String[] issueStatus;
    List<ParseObject> issues = new ArrayList<>();

    public IssueAdapter(Context context, int resource, List<ParseObject> issues) {
        super(context, resource, issues);
        this.context = context;
        layoutId = resource;
        this.issues.addAll(issues);
        issueStatus = context.getResources().getStringArray(R.array.issue_status);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }

        TextView summary = (TextView) convertView.findViewById(R.id.summaryLvItem);
        TextView assignTo = (TextView) convertView.findViewById(R.id.assignToLvItem);
        TextView date = (TextView) convertView.findViewById(R.id.dateLvItem);

        if (issues != null) {
            ParseObject issue = issues.get(position);
            summary.setText(issue.getString(ISSUE_SUMMARY));

            try {
                assignTo.setText(issue.getParseUser(ISSUE_ASSIGN_TO).fetchIfNeeded().getUsername());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            date.setText(issue.getCreatedAt().toString());
        }

        return convertView;
    }

}
