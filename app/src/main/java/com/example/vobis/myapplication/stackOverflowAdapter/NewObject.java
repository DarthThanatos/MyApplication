package com.example.vobis.myapplication.stackOverflowAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vobis on 2015-08-02.
 */
public class NewObject {
    public String title; // use getters and setters instead
    public List<NewObject> children; // same as above

    public NewObject() {
        children = new ArrayList<NewObject>();
    }
}
