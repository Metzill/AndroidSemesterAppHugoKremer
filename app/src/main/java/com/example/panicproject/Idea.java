package com.example.panicproject;

public class Idea {
    private String mName;
    private String mDescription;

    Idea(String name, String description) {
        mName = name;
        mDescription = description;
    }

    public String getName(){
        return mName;
    }

    public String getDescription(){
        return   mDescription;
    }
}
