package com.example.nguyendinhduc.myapplication;

/**
 * Lop chua cac bien final
 * Created by nguyendinhduc on 11/8/15.
 */
public class Constant {
    public static final String CREATED_AT = "createdAt";

    public static final String ROLE_ADMIN = "Admin";
    public static final String ROLE_MANAGER = "Manager";
    public static final String ROLE_DEVELOPER = "Developer";
    public static final String ROLE_QUALITY_CONTROL_STAFF = "QualityControlStaff";
    public static final String ROLE_TESTER = "Tester";

    public static final int DETAIL_PROJECT_REQUEST_CODE = 0;
    public static final int EDIT_PROJECT_REQUEST_CODE = 1;
    public static final int CREATE_PROJECT_REQUEST_CODE = 2;

    public static final int DELETE_PROJECT_RESULT_CODE = 0;
    public static final int DETAIL_PROJECT_RESULT_CODE = 1;
    public static final int CREATE_PROJECT_RESULT_CODE = 2;

    public static final int DETAIL_ISSUE_REQUEST_CODE = 0;
    public static final int EDIT_ISSUE_REQUEST_CODE = 1;
    public static final int CREATE_ISSUE_REQUEST_CODE = 2;

    public static final int DELETE_ISSUE_RESULT_CODE = 0;
    public static final int DETAIL_ISSUE_RESULT_CODE = 1;
    public static final int CREATE_ISSUE_RESULT_CODE = 2;

    public static final int ADMIN_ROLE = 0;
    public static final int MANAGER_ROLE = 1;
    public static final int QUALITY_CONTROL_ROLE = 2;
    public static final int DEVELOPER_ROLE = 3;
    public static final int TESTER_ROLE = 4;

    public static final int PROJECT_STATUS_STABLE = 0;
    public static final int PROJECT_STATUS_RELEASE = 3;
    public static final int PROJECT_STATUS_DEVELOPMENT = 1;
    public static final int PROJECT_STATUS_TEST = 2;

    public static final String PROJECT_TABLE = "Project";
    public static final String PROJECT_ID = "objectId";
    public static final String PROJECT_NAME = "projectName";
    public static final String PROJECT_DESCRIPTION = "description";
    public static final String PROJECT_STATUS = "projectStatus";
    public static final String PROJECT_USER = "userID";
    public static final String PROJECT_CATEGORY = "categoryID";

    public static final String USER_TABLE = "_User";
    public static final String USER_ID = "objectId";
    public static final String USER_NAME = "username";
    public static final String USER_EMAIL = "email";
    public static final String USER_FULLNAME = "fullname";
    public static final String USER_ACCESS_LEVEL = "accessLevel";
    public static final String PASS="password";

    public static final String ISSUE_TABLE = "Issue";
    public static final String ISSUE_ID = "objectId";
    public static final String ISSUE_STATUS = "status";
    public static final String ISSUE_PRIORITY = "priority";
    public static final String ISSUE_ASSIGN_TO = "assignTo";
    public static final String ISSUE_SUMMARY = "summary";
    public static final String ISSUE_DESCRIPTION = "description";
    public static final String ISSUE_CATEGORY = "category";
    public static final String ISSUE_PROJECT = "project";
}
