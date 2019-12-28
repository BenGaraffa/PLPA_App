package com.plpa_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    private Vibrator haptic;
    private boolean switchValue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        haptic = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
    }

    public void startStoryActivity(View view) {
        Intent story = new Intent(MainActivity.this, StoryScreen.class);
        story.putExtra("undoable", switchValue);
        startActivity(story);
        haptic.vibrate(50);
    }

    public void updateSwitchValue(View view) {
        switchValue = !switchValue;
        System.out.println(switchValue);
    }
}
