package com.example.nguyendinhduc.myapplication.issue;

import com.parse.GetCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_ASSIGN_TO;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_CATEGORY;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_DESCRIPTION;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_PRIORITY;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_STATUS;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_SUMMARY;
import static com.example.nguyendinhduc.myapplication.Constant.ISSUE_PROJECT;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.USER_FULLNAME;
import static com.example.nguyendinhduc.myapplication.Constant.USER_TABLE;

/**
 * Created by ginks on 11/26/2015.
 */

@ParseClassName("Issue")
public class Issue extends ParseObject {

    ParseObject project;
    ParseUser userAssignTo;

    public Issue() {
    }

    /**
     *
     * @param selectedCategory danh muc cac category
     * @param selectedProject ten project
     * @param priority thu tu uu tien loi
     * @param status trang thai loi
     * @param assignTo gan cho nguoi chiu trach nhiem sua loi
     * @param summary tom tat loi
     * @param description mo ta chi tiet loi
     */
    public void setData(String selectedProject, String selectedCategory,
                        String priority, String status, String assignTo,
                        String summary, String description)
    {


        put(ISSUE_PROJECT, getProject(selectedProject));
        put(ISSUE_CATEGORY, selectedCategory);
        put(ISSUE_PRIORITY, priority);
        put(ISSUE_STATUS, status);
        put(ISSUE_ASSIGN_TO, getAssignTo(assignTo));
        put(ISSUE_SUMMARY, summary);
        put(ISSUE_DESCRIPTION, description);
    }

    private ParseUser getAssignTo(String assginTo) {
        ParseQuery<ParseUser> query = ParseQuery.getQuery(USER_TABLE);
        query.whereEqualTo(USER_FULLNAME, assginTo);
        query.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e) {
                if (e == null) {
                    userAssignTo = object;
                }
            }
        });

        return userAssignTo;
    }

    private ParseObject getProject(String projectName) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery(PROJECT_TABLE);
        query.whereEqualTo(PROJECT_NAME, projectName);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    project = object;
                }
            }
        });
        return project;
    }
}
