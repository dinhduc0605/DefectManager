package com.example.nguyendinhduc.myapplication;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by nguyendinhduc on 11/20/15.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "uQxiHr6VHD7rbPlzptFjyA40g9le9lub0UsfJJe0", "fMMg6PoGWdfjAO4fApIpn7j0s3E2byNvn07xZnmG");
    }
}
