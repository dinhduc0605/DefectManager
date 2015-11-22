package com.example.nguyendinhduc.myapplication.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nguyendinhduc.myapplication.R;

public class DetailProjectActivity extends AppCompatActivity {
    ListView accessAccountList;
    String[] accessAccounts = {"mafiaboss0605", "boydeptrai", "boyxauxi", "girlxinh", "girlxau", "girlbinhthuong","dsfadf","erte","gdf","sdfdf"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_project);
        initView();
        getWidgetControl();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        accessAccountList = (ListView) findViewById(R.id.listAccessAccount);
        accessAccountList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accessAccounts));
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

    public void editProject(View view) {
        Intent intent = new Intent(this, EditProjectActivity.class);
        startActivity(intent);
    }
}
