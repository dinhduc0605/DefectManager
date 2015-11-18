package com.example.nguyendinhduc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateAccountActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<String> adapter;
    String[] accessLv = {"Admin", "Manager", "Tester", "Developer", "QualityController"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        initView();
        getWidgetControll();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner = (Spinner) findViewById(R.id.accessLvInput);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, accessLv);
        spinner.setAdapter(adapter);


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
        Intent intent = new Intent(this, EditAccountActivity.class);
        startActivity(intent);
    }

}
