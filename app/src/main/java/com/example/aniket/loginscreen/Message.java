package com.example.aniket.loginscreen;

/**
 * Created by aniket on 6/1/18.
 */

public class Message {
    private String body;
    private String title;

    public Message() {}

    public Message(String body, String title)
    {
        this.body = body;
        this.title = title;
    }

    public void setTitle(String s)
    {
        this.title = s;
    }

    public void setBody(String s)
    {
        this.body = s;
    }
    public String getTitle()
    {
        return title;
    }

    public String getBody()
    {
        return body;
    }
}
