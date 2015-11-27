package com.example.nguyendinhduc.myapplication.issue;

import android.content.Context;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

/**
 * Created by ginks on 11/27/2015.
 */
public class IssueController {
    Context context;
    private static IssueController issueController = new IssueController();

    private IssueController() {

    }

    public static IssueController getInstance() {
        return issueController;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Phuong thuc them issue
     * @param issue can them
     * @return
     */
    public ParseObject createIssue(ParseObject issue) {
        issue.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(context, "Issue Created", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return issue;
    }

    /**
     * Phuong thuc sua issue
     * @param issue issue can sua
     * @return
     */
    public ParseObject editIssue(ParseObject issue) {
        issue.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(context, "Issue Edited", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return issue;
    }

    /**
     * Phuong thuc xoa issue
     * @param issue issue can xoa
     */
    public void removeIssue(ParseObject issue) {
        issue.deleteInBackground(new DeleteCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(context, "Issue Removed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
