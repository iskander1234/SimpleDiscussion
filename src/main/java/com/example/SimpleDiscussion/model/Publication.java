package com.example.SimpleDiscussion.model;

public class Publication {
    private int id;
    private String name;
    private String description;
    private String comments;

    public Publication(int id, String name, String description, String comments) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.comments = comments;
    }

    public Publication(String name, String description, String comments) {
        super();
        this.name = name;
        this.description = description;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
