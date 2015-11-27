package com.example.nguyendinhduc.myapplication.issue;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_DESCRIPTION;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_ID;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_PROJECT;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_SUMMARY;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_CATEGORY;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_USER;

import com.example.nguyendinhduc.myapplication.R;
import com.example.nguyendinhduc.myapplication.account.EditAccountActivity;
import com.example.nguyendinhduc.myapplication.project.Project;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public class EditIssueActivity extends AppCompatActivity {
    String[] assignedUsers = {"mafiaboss0605", "boydeptrai", "boyxauxi", "girlxinh", "girlxau", "girlbinhthuong"};
    String[] category = {"Mobile", "Web", "Window"};
    String[] priority = {"low", "medium", "high", "urgent"};
    EditText summaryInput, descriptionInput;
    Spinner categorySpinnerInput, prioritySpinnerInput, assignToSpinnerInput, projectSpinnerInput;
    Issue issue;
    Project project;
    List<String> projectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_issue);
        new GetDataTask().execute();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        summaryInput = (EditText) findViewById(R.id.summaryInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);
        categorySpinnerInput = (Spinner) findViewById(R.id.categorySpinnerInput);
        prioritySpinnerInput = (Spinner) findViewById(R.id.prioritySpinnerInput);
        assignToSpinnerInput = (Spinner) findViewById(R.id.assignToSpinnerInput);
        projectSpinnerInput = (Spinner) findViewById(R.id.projectSpinnerInput);

        // lay du lieu tu parse cho vao edit text va spinner
        summaryInput.setText(issue.getString(ISSUE_SUMMARY));
        descriptionInput.setText(issue.getString(ISSUE_DESCRIPTION));


        projectSpinnerInput.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, category));
        categorySpinnerInput.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, category));
        prioritySpinnerInput.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, priority));
        assignToSpinnerInput.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, assignedUsers));


    }

    private void getWidgetControl() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    public void editAccount(View view) {
        Intent intent = new Intent(this, EditAccountActivity.class);
        startActivity(intent);
    }

    //Lop tao 1 thread khac de xu ly viec lay du lieu tu server
    private class GetDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            final String objectId = getIntent().getStringExtra(ISSUE_ID);

            //Lenh truy van  issue co objectId trong csdl
            ParseQuery<ParseObject> query = ParseQuery.getQuery(ISSUE_TABLE);
            try {
                issue = (Issue) query.get(objectId);

            } catch (com.parse.ParseException e) {
                e.printStackTrace();
            }

            // Lenh truy van tat ca project co trong csdl
            ParseQuery<ParseObject> query1 = ParseQuery.getQuery(PROJECT_TABLE);
            query.include(PROJECT_CATEGORY);
            query.include(PROJECT_USER);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, com.parse.ParseException e) {
                    project = (Project) objects;
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            initView();
            getWidgetControl();
        }
    }

}
