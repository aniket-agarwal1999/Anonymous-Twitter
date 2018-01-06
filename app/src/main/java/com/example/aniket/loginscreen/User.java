package com.example.aniket.loginscreen;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by aniket on 6/1/18.
 */

@IgnoreExtraProperties
public class User {
    public String email;
    public String name;

    //Default constructor required for calls to DataSnapshot.getValue(User.class)
    public User() {}

    public User(String email, String name)
    {
        this.name = name;
        this.email = email;
    }
}
