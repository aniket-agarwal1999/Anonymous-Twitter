package com.example.aniket.loginscreen;

/**
 * Created by aniket on 7/1/18.
 */

public class MessageImage {
    private String title;
    private String body;
    private String imageName;

    public MessageImage() {}

    public MessageImage(String title, String body, String imageName)
    {
        this.title = title;
        this.body = body;
        this.imageName = imageName;
    }

    public void setTitle(String s)
    {
        this.title = s;
    }

    public void setBody(String s)
    {
        this.body = s;
    }

    public void setSrc(String s)
    {
        this.imageName= s;
    }

    public String getTitle()
    {
        return title;
    }

    public String getBody()
    {
        return body;
    }

    public String getImageName()
    {
        return imageName;
    }
}
