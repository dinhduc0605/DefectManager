package com.example.nguyendinhduc.myapplication.issue;

/**
 * Created by ginks on 11/26/2015.
 */
public class IssueAction {

    public void action(IssueCommand command) {
        command.excute();
    }
}
