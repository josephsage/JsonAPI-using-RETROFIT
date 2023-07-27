package com.example.recycler;

public class Posts {
    private String title;
    private String Body;

    public Posts(String title, String body) {
        this.title = title;
        Body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return Body;
    }
}
