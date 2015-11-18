package com.example.nguyendinhduc.myapplication;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {
    ListView projectList;
    ProjectAdapter adapter;
    Context context;
    FloatingActionButton createProject;

    public ProjectFragment() {
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
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        projectList = (ListView) view.findViewById(R.id.projectList);
        createProject = (FloatingActionButton) view.findViewById(R.id.createProject);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ProjectAdapter(context, R.layout.item_project_list, null);
        projectList.setAdapter(adapter);
        projectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetailProjectActivity.class);
                startActivity(intent);
            }
        });
        createProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateProjectActivity.class);
                startActivity(intent);
            }
        });
    }
}
