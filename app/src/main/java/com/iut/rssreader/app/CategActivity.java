package com.iut.rssreader.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;

/**
 * Created by corentin on 17/04/14.
 */
public class CategActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categ);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
