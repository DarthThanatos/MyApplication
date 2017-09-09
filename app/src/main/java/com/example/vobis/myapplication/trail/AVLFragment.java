package com.example.vobis.myapplication.trail;

import android.os.Bundle;

/**
 * Created by Vobis on 2015-08-11.
 */
public class AVLFragment extends android.app.Fragment {

    // data object we want to retain
    private Storage data;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(Storage data) {
        this.data = data;
    }

    public Storage getData() {
        return data;
    }
}
