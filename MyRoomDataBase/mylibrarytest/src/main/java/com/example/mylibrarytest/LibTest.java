package com.example.mylibrarytest;

import android.support.v4.content.LocalBroadcastManager;

public class LibTest {


    public static String mTag = "TAG";

    public static String setMessage(String s) {

        String output = s + ":From Lib";
        return output;
    }
}
