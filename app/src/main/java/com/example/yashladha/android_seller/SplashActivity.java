package com.example.yashladha.android_seller;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.yashladha.android_seller.classes.LoadingTask;

public class SplashActivity extends Activity implements LoadingTask.LoadingTaskFinishedListener {

    String uid = "", registered = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgressTintList(ColorStateList.valueOf(Color.BLUE));

        // Start your loading
        new LoadingTask(progressBar, this).execute("Loading");
        SharedPreferences myPrefs = getSharedPreferences("myprfs", MODE_PRIVATE);
        uid = myPrefs.getString("UID", "");
        registered = myPrefs.getString("Registered", "");

       /* Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
        */
    }

    // This is the callback for when your async task has finished
    @Override
    public void onTaskFinished() {
        completeSplash();
    }

    private void completeSplash() {
        startApp();
        finish(); // Don't forget to finish this Splash Activity so the user can't return to it!
    }

    private void startApp() {
        if (uid == "" || registered == "") {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent i = new Intent(SplashActivity.this, HomePageActivity.class);
            startActivity(i);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
