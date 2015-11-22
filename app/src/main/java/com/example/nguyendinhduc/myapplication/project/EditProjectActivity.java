package com.example.nguyendinhduc.myapplication.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.nguyendinhduc.myapplication.R;
import com.example.nguyendinhduc.myapplication.account.AccountAdapter;

public class EditProjectActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<String> adapter;
    String[] statuses = {"Stable", "Development", "Test", "Release"};
    String[] accessAccounts = {"mafiaboss0605", "boydeptrai", "boyxauxi", "girlxinh", "girlxau", "girlbinhthuong"};
    String[] accessAccountsAdded = {"mafiaboss0605", "boydeptrai", "boyxauxi"};
    EditText projectNameInput, descriptionInput;
    Spinner statusInput, accessAccountInput;
    ListView listAccessAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);
        initView();
        getWidgetControll();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        projectNameInput = (EditText) findViewById(R.id.projectnameInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);
        listAccessAccount = (ListView) findViewById(R.id.listAccessAccount);
        statusInput = (Spinner) findViewById(R.id.statusInput);
        accessAccountInput = (Spinner) findViewById(R.id.accessAccountInput);

        projectNameInput.setText("Mobile Online");
        descriptionInput.setText("A project develop mobile shop application on Android");
        listAccessAccount.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accessAccountsAdded));
        statusInput.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, statuses));
        accessAccountInput.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accessAccounts));
    }

    private void getWidgetControll() {

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
        Intent intent = new Intent(this, AccountAdapter.EditAccountActivity.class);
        startActivity(intent);
    }

}
