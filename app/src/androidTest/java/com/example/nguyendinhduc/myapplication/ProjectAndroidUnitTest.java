package com.example.nguyendinhduc.myapplication;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.example.nguyendinhduc.myapplication.project.Project;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;

import static com.example.nguyendinhduc.myapplication.Constant.CREATED_AT;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_DESCRIPTION;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_STATUS;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.USER_TABLE;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertNull;

/**
 * Created by nguyendinhduc on 11/27/15.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class ProjectAndroidUnitTest {
    Project project;
    ParseObject user;

    @Before
    public void initProject() {
        project = new Project();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(USER_TABLE);
        try {
            user = query.getFirst();
        } catch (ParseException e) {
            assertNull(e);
        }
        project.setData("test", 0, "This is a test", Arrays.asList("a", "b"), Collections.singletonList(user));
    }

    @Test
    public void project_create() {
        ParseQuery<ParseObject> query1 = ParseQuery.getQuery(PROJECT_TABLE);
        query1.orderByDescending(CREATED_AT);
        try {
            project.save();
            project = (Project) query1.getFirst();
            assertEquals("test", project.getString(PROJECT_NAME));
            assertEquals(0, project.getInt(PROJECT_STATUS));
            assertEquals("This is a test", project.getString(PROJECT_DESCRIPTION));
        } catch (ParseException e) {
            assertNull(e);
        }

    }

    @Test
    public void project_edit() {



        ParseQuery<ParseObject> query1 = ParseQuery.getQuery(PROJECT_TABLE);
        query1.orderByDescending(CREATED_AT);
        project.setData("test_edit", 1, "This content is changed", Arrays.asList("c", "d"), Collections.singletonList(user));
        try {
            project.save();
            project = (Project) query1.getFirst();
            assertEquals("test_edit", project.get(PROJECT_NAME));
            assertEquals(1, project.getInt(PROJECT_STATUS));
            assertEquals("This content is changed", project.getString(PROJECT_DESCRIPTION));
        } catch (ParseException e) {
            assertNull(e);
        }
    }

    @Test
    public void project_remove() {
        Project testProject = new Project();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(USER_TABLE);
        try {
            user = query.getFirst();
            testProject.setData("test", 0, "This is a test", Arrays.asList("a", "b"), Collections.singletonList(user));
            testProject.save();
        } catch (ParseException e) {
            assertNull(e);
        }

        ParseQuery<ParseObject> query1 = ParseQuery.getQuery(PROJECT_TABLE);
        query1.orderByDescending(CREATED_AT);
        String id = testProject.getObjectId();
        try {
            testProject.delete();
            testProject = (Project) query1.getFirst();
            assertNotSame(id, testProject.getObjectId());
        } catch (ParseException e) {
            assertNull(e);
        }
    }

    @After
    public void removeProject() {
        try {
            project.delete();
        } catch (ParseException e) {
            assertNull(e);
        }
    }
}
