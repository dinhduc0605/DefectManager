package com.example.nguyendinhduc.myapplication.issue;

import android.content.Context;

import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by ginks on 11/27/2015.
 */
public class CreateIssueCommand implements IssueCommand {

    //Lop concrete command dung de them issue
    IssueController issueController;
    ParseObject issue;

    public CreateIssueCommand(Context context, ParseObject  issue) {
        issueController = IssueController.getInstance();
        issueController.setContext(context);
        this.issue = issue;
    }

    @Override
    public void excute() {
        issueController.createIssue(issue);
    }
}
