package com.example.nguyendinhduc.myapplication.issue;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nguyendinhduc.myapplication.R;
import com.example.nguyendinhduc.myapplication.SetHeightListView;
import com.example.nguyendinhduc.myapplication.issue.CreateIssueActivity;
import com.example.nguyendinhduc.myapplication.issue.DetailIssueActivity;
import com.example.nguyendinhduc.myapplication.issue.IssueAdapter;

import static com.example.nguyendinhduc.myapplication.Constant.CREATED_AT;
import static com.example.nguyendinhduc.myapplication.Constant.CREATE_ISSUE_REQUEST_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.DELETE_ISSUE_RESULT_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.DETAIL_ISSUE_REQUEST_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.DETAIL_ISSUE_RESULT_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.DETAIL_PROJECT_RESULT_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_CATEGORY;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_ID;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_PROJECT;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_CATEGORY;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_NAME;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class IssueFragment extends Fragment {
    Context context;
    ListView issueList;
    IssueAdapter adapter;
    List<ParseObject> issues;
    FloatingActionButton createIssue;

    public IssueFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_issue, container, false);
        issueList = (ListView) view.findViewById(R.id.errorList);
        createIssue = (FloatingActionButton) view.findViewById(R.id.createIssue);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // lenh truy van tat ca issue trong csdl
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ISSUE_TABLE);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    issues = objects;
                    adapter = new IssueAdapter(context, R.layout.item_error_list, issues);
                    issueList.setAdapter(adapter);
                    issueList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getContext(), DetailIssueActivity.class);
                            intent.putExtra(ISSUE_ID, objects.get(position).getObjectId());
                            intent.putExtra("position", position);
                            startActivityForResult(intent, DETAIL_ISSUE_REQUEST_CODE);
                        }
                    });
                }
            }
        });

        createIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateIssueActivity.class);
                startActivityForResult(intent, CREATE_ISSUE_REQUEST_CODE);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DETAIL_ISSUE_REQUEST_CODE) {
            if (resultCode == DELETE_ISSUE_RESULT_CODE) {

                // xoa issue va cap nhat lai listview
                issues.remove(data.getIntExtra("position", -1));
                adapter.notifyDataSetChanged();
            }
        } else if (requestCode == CREATE_ISSUE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery(ISSUE_TABLE);
                query.orderByDescending(CREATED_AT);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {
                            issues.add(object);
                            adapter.notifyDataSetChanged();
                            SetHeightListView.setListViewHeightBasedOnChildren(issueList);
                        }
                    }
                });
            }
        }
    }
}
