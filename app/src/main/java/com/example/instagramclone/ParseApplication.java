package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("aQd8x4mbG9oxumrWHbFJOoLbh6eVmJjkTs5N5W12")
                .clientKey("K5ChEuLF0jxewyIY6HU0pzDkn4AQir5tnJ2AEHSQ")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
