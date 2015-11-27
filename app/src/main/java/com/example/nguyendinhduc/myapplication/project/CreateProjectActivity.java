package com.example.nguyendinhduc.myapplication.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.nguyendinhduc.myapplication.R;
import com.example.nguyendinhduc.myapplication.SetHeightListView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.nguyendinhduc.myapplication.Constant.USER_ACCESS_LEVEL;
import static com.example.nguyendinhduc.myapplication.Constant.USER_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.USER_TABLE;

//Lop xu ly giao dien them du an moi
public class CreateProjectActivity extends AppCompatActivity {
    String[] statuses;
    List<String> accessAccounts = new ArrayList<>();
    List<String> addedAccounts = new ArrayList<>();
    List<String> addedCategories = new ArrayList<>();
    List<ParseObject> addedAccountsParse = new ArrayList<>();
    ListViewAdapter addedCategoriesAdapter, addedAccountsAdapter;
    EditText projectNameInput, descriptionInput, categoryInput;
    Spinner statusInput, accessAccountInput;
    ListView listAccessAccount, listCategory;
    Button addCategoryBtn, addUserBtn, delCategoryBtn, delUserBtn, submitBtn;
    Project project;
    List<ParseObject> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        initView();
        getWidgetControl();
    }

    /**
     * Khoi tao cac thanh phan trong giao dien
     */
    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        statuses = getResources().getStringArray(R.array.project_status);
        projectNameInput = (EditText) findViewById(R.id.projectnameInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);
        categoryInput = (EditText) findViewById(R.id.categoryInput);
        listAccessAccount = (ListView) findViewById(R.id.listAccessAccount);
        listCategory = (ListView) findViewById(R.id.listCategories);
        statusInput = (Spinner) findViewById(R.id.statusInput);
        accessAccountInput = (Spinner) findViewById(R.id.accessAccountInput);
        addCategoryBtn = (Button) findViewById(R.id.addCategory);
        addUserBtn = (Button) findViewById(R.id.addAccount);
        delCategoryBtn = (Button) findViewById(R.id.delCategory);
        delUserBtn = (Button) findViewById(R.id.delAccount);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        project = new Project();

        //Lenh truy van cac user trong bang user
        ParseQuery<ParseObject> query = ParseQuery.getQuery(USER_TABLE);

        //Chi lay ra cot username
        query.selectKeys(Collections.singletonList(USER_NAME));

        query.whereGreaterThan(USER_ACCESS_LEVEL, 0);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> objects, ParseException e) {
                //neu khong co loi tra ve
                if (e == null) {
                    users = objects;
                    for (ParseObject user : users) {
                        accessAccounts.add(user.getString(USER_NAME));
                    }
                    statusInput.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, statuses));
                    accessAccountInput.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, accessAccounts));

                    addedAccountsAdapter = new ListViewAdapter(getBaseContext(), R.layout.item_lv_layout, addedAccounts);
                    listAccessAccount.setAdapter(addedAccountsAdapter);
                    SetHeightListView.setListViewHeightBasedOnChildren(listAccessAccount);

                    addedCategoriesAdapter = new ListViewAdapter(getBaseContext(), R.layout.item_lv_layout, addedCategories);
                    listCategory.setAdapter(addedCategoriesAdapter);
                    SetHeightListView.setListViewHeightBasedOnChildren(listCategory);


                }

            }
        });

    }

    private void getWidgetControl() {
        //xu ly su kien them project
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                project.setData(projectNameInput.getText().toString(), statusInput.getSelectedItemPosition(), descriptionInput.getText().toString(), addedCategories, addedAccountsParse);

                //Su dung Invoker
                ProjectAction action = new ProjectAction();
                action.action(new CreateProjectCommand(getBaseContext(), project));
                setResult(RESULT_OK);
                finish();
            }
        });
        //xu ly su kien them category cho project
        addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = categoryInput.getText().toString();
                addedCategories.add(s);
                addedCategoriesAdapter.notifyDataSetChanged();
                SetHeightListView.setListViewHeightBasedOnChildren(listCategory);
                categoryInput.setText("");
            }

        });

        //xu ly su kien them tai khoan cua nhan vien duoc truy cap vao project
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = (String) accessAccountInput.getSelectedItem();
                addedAccounts.add(s);
                addedAccountsAdapter.notifyDataSetChanged();
                ParseObject account = users.get(accessAccountInput.getSelectedItemPosition());
                addedAccountsParse.add(account);
                SetHeightListView.setListViewHeightBasedOnChildren(listAccessAccount);
            }
        });

        delCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = addedCategories.size() - 1; i >= 0; i--) {
                    View view = listCategory.getChildAt(i);
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.removeBtn);
                    if (checkBox.isChecked()) {
                        addedCategories.remove(i);
                        addedCategoriesAdapter.notifyDataSetChanged();
                        SetHeightListView.setListViewHeightBasedOnChildren(listCategory);
                    }
                }
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
            setResult(RESULT_CANCELED);
            finish();
        }
        return true;
    }


}
