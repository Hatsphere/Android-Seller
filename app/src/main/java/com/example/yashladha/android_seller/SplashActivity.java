package com.example.yashladha.android_seller;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yashladha.android_seller.classes.LoadingTask;

import static java.lang.Thread.sleep;

public class SplashActivity extends Activity implements LoadingTask.LoadingTaskFinishedListener {

    String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));

        // Start your loading
        new LoadingTask(progressBar, this).execute("Loading");
        SharedPreferences myPrefs = getSharedPreferences("myprfs", MODE_PRIVATE);
        uid = myPrefs.getString("UID", "");

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
        if (uid == "") {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            Intent i = new Intent(SplashActivity.this, HomePageActivity.class);
            startActivity(i);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
