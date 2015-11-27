package com.example.nguyendinhduc.myapplication.issue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;

import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_ASSIGN_TO;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_CATEGORY;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_DESCRIPTION;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_PRIORITY;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_PROJECT;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_STATUS;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_SUMMARY;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_CATEGORY;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_USER;
import static com.example.nguyendinhduc.myapplication.Constant.USER_FULLNAME;
import static com.example.nguyendinhduc.myapplication.Constant.USER_TABLE;

import com.example.nguyendinhduc.myapplication.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CreateIssueActivity extends AppCompatActivity {

    private ParseObject issue;
    String[] issueStatus;
    String[] issuePriority;
    List<String> category = new ArrayList<>();
    List<ParseUser> users = new ArrayList<>();
    List<String> usernames = new ArrayList<>();
    List<String> projectKey = new ArrayList<>();
    HashMap<String, List<String>> project = new HashMap<>();
    HashMap<String, List<String>> usersInProject = new HashMap<>();

    Spinner categorySpinnerInput, prioritySpinnerInput;
    Spinner projectSpinnerInput, assignToSpinnerInput;

    EditText editTextSummary, editTextDescription;
    Button btnSubmit;

    ParseUser userAssignTo;
    ParseObject projectOfIssue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_issue);
        initView();
        getWidgetControl();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        issueStatus = getResources().getStringArray(R.array.issue_status);
        issuePriority = getResources().getStringArray(R.array.issue_priority);

        editTextSummary = (EditText) findViewById(R.id.summaryInput);
        editTextDescription = (EditText) findViewById(R.id.descriptionInput);

        btnSubmit = (Button) findViewById(R.id.submitBtn);
        categorySpinnerInput = (Spinner) findViewById(R.id.categorySpinnerInput);
        prioritySpinnerInput = (Spinner) findViewById(R.id.prioritySpinnerInput);
        assignToSpinnerInput = (Spinner) findViewById(R.id.assignToSpinnerInput);
        projectSpinnerInput = (Spinner) findViewById(R.id.projectSpinnerInput);

        // lay tat ca project tu csdl


    }
    public void getWidgetControl() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery(PROJECT_TABLE);
//        query.whereEqualTo(PROJECT_USER, ParseUser.getCurrentUser());
        query.include(PROJECT_USER);
        query.include(PROJECT_CATEGORY);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject p : objects) {
                        category = p.getList(PROJECT_CATEGORY);
                        project.put(p.getString(PROJECT_NAME), category);

                        // lay ds user trong project tru currentUser
                        users = p.getList(PROJECT_USER);
//                        users.remove(ParseUser.getCurrentUser());

                        for (ParseUser u : users) {
                            usernames.add(u.getString(USER_FULLNAME));
                        }

                        usersInProject.put(p.getString(PROJECT_NAME), usernames);

                        Set<String> keys = project.keySet();
                        for (String key : keys) {
                            projectKey.add(key);
                        }
                    }

                }
            }
        });
        

        projectSpinnerInput.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, projectKey));

        prioritySpinnerInput.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, issuePriority));

        projectSpinnerInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorySpinnerInput.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, project.get(projectKey.get(position))));

                assignToSpinnerInput.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, usersInProject.get(projectKey.get(position))));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        if (projectSpinnerInput.getSelectedItem()!= null) {
            // lay parse object project tu project name
            ParseQuery<ParseObject> queryP = ParseQuery.getQuery(PROJECT_TABLE);
            queryP.whereEqualTo(PROJECT_NAME, projectSpinnerInput.getSelectedItem());
            queryP.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    projectOfIssue = object;
                }
            });
        }

        if (assignToSpinnerInput.getSelectedItem() != null) {
            // lay parse user tu full name assign to
            ParseQuery<ParseUser> queryU = ParseQuery.getQuery(USER_TABLE);
            queryU.whereEqualTo(USER_FULLNAME, assignToSpinnerInput.getSelectedItem().toString());
            queryU.getFirstInBackground(new GetCallback<ParseUser>() {
                @Override
                public void done(ParseUser object, ParseException e) {
                    userAssignTo = object;
                }
            });
        }

        issue = new Issue();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                issue.put(ISSUE_PROJECT, projectOfIssue);
                issue.put(ISSUE_CATEGORY,categorySpinnerInput.getSelectedItem().toString());
                issue.put(ISSUE_PRIORITY, prioritySpinnerInput.getSelectedItem().toString());
                issue.put(ISSUE_STATUS, issueStatus[0]);
                issue.put(ISSUE_ASSIGN_TO, userAssignTo);
                issue.put(ISSUE_SUMMARY, editTextSummary.getText().toString());
                issue.put(ISSUE_DESCRIPTION, editTextDescription.getText().toString());

                // use invoker
                IssueAction action = new IssueAction();
                action.action(new CreateIssueCommand(getBaseContext(), issue));
                setResult(RESULT_OK);
                finish();
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
            finish();
        }
        return true;
    }


}
