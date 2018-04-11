package com.google.perez.officesnitch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public abstract class SingleFragmentActivity extends AppCompatActivity{ //abstract means we can only access sub class

    protected abstract Fragment createFragment(); //no implementation for this method, only implemented in subclasses


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null) {

            fragment = createFragment(); //method that require to create if we subclass this abstract class
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();

        }
    }

}
