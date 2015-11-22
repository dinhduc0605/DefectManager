package com.example.nguyendinhduc.myapplication.account;


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
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_TABLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    ListView accountList;
    AccountAdapter adapter;
    Context context;
    FloatingActionButton createAccount;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        accountList = (ListView) view.findViewById(R.id.accountList);
        createAccount = (FloatingActionButton) view.findViewById(R.id.createAccount);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ParseQuery<ParseObject> query = ParseQuery.getQuery(PROJECT_TABLE);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> projects, ParseException e) {
                adapter = new AccountAdapter(context, R.layout.item_account_list, projects);
                accountList.setAdapter(adapter);
                accountList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, DetailAccountActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }

}
