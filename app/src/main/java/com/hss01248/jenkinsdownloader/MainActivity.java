package com.hss01248.jenkinsdownloader;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hss01248.jenkins.JenkinsTool;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void download(View view) {
        JenkinsTool.showBuildList(this);
    }
}