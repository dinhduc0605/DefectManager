package com.example.nguyendinhduc.myapplication.issue;

import android.content.Context;

import com.parse.ParseObject;

/**
 * Created by ginks on 11/27/2015.
 */
public class EditIssueCommand implements IssueCommand {

    IssueController issueController;
    ParseObject issue;

    public EditIssueCommand(Context context, ParseObject issue) {
        issueController = IssueController.getInstance();
        issueController.setContext(context);
        this.issue = issue;
    }

    @Override
    public void excute() {
        issueController.editIssue(issue);
    }
}
