package com.example.nguyendinhduc.myapplication.issue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.nguyendinhduc.myapplication.Constant.DELETE_ISSUE_RESULT_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.DETAIL_ISSUE_RESULT_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.EDIT_ISSUE_REQUEST_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_ASSIGN_TO;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_CATEGORY;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_DESCRIPTION;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_ID;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_PRIORITY;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_PROJECT;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_SUMMARY;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_NAME;

import com.example.nguyendinhduc.myapplication.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class DetailIssueActivity extends AppCompatActivity {

    Issue issue;
    Button btnEditIssue, btnDeleteIssue;
    TextView txtProject, txtCategory, txtPriority;
    TextView txtSummary, txtDescription, txtAssignTo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_issue);
        initView();
        getWidgetControl();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtProject = (TextView) findViewById(R.id.txtProject);
        txtCategory = (TextView) findViewById(R.id.txtCategory);
        txtPriority = (TextView) findViewById(R.id.txtPriority);
        txtSummary = (TextView) findViewById(R.id.txtSummary);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtAssignTo = (TextView) findViewById(R.id.txtAssignTo);

        btnEditIssue = (Button) findViewById(R.id.btnEditIssue);
        btnDeleteIssue = (Button) findViewById(R.id.btnDeleteIssue);


    }

    private void getWidgetControl() {

        issue = new Issue();
        final String objectId = getIntent().getStringExtra(ISSUE_ID);
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ISSUE_TABLE);
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                issue = (Issue) object;
                try {
                    txtProject.setText(issue.getParseObject(ISSUE_PROJECT).fetch().getString(PROJECT_NAME));
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                txtCategory.setText(issue.getString(ISSUE_CATEGORY));
                try {
                    txtAssignTo.setText(issue.getParseUser(ISSUE_ASSIGN_TO).fetchIfNeeded().getUsername());
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                txtPriority.setText(issue.getString(ISSUE_PRIORITY));
                txtSummary.setText(issue.getString(ISSUE_SUMMARY));
                txtDescription.setText(issue.getString(ISSUE_DESCRIPTION));
            }
        });

        btnEditIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditIssueActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(DETAIL_ISSUE_RESULT_CODE);
            finish();
        }
        return true;
    }

    /**
     * Phuong thuc xu ly su kien button sua issue duoc nhan
     * @param view button sua project
     */
    public void editProject(View view) {
        Intent intent = new Intent(this, EditIssueActivity.class);
        intent.putExtra(ISSUE_ID, getIntent().getStringExtra(ISSUE_ID));

        //Mo giao dien chinh sua issue
        startActivityForResult(intent, EDIT_ISSUE_REQUEST_CODE);
    }

    /**
     * Phuong thuc xu ly su kien button xoa issue duoc nhan
     * @param view button xoa issue
     */
    public void deleteProject(View view) {
        //Thuc hien action xoa issue tu lop Invoker ProjectAction
        IssueAction action = new IssueAction();
        action.action(new RemoveIssueCommand(this, issue));
        setResult(DELETE_ISSUE_RESULT_CODE, getIntent());
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_ISSUE_REQUEST_CODE) {
//            accessAccounts.clear();
            initView();
        }
    }


}
