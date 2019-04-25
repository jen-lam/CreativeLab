package com.example.creativelab.learn.lesson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.creativelab.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class LessonActivity extends AppCompatActivity {

    private static final String TAG = "LogInActivity";
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        Log.d(TAG, "onCreate: Starting.");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String card = (String) extras.get("card");

            final String youtube = (String) extras.get("youtube");

            Log.d(TAG, "onClick: Initialising YouTube Player.");
            YouTubePlayerFragment youtubeFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeFragment);
            youtubeFragment.initialize("AIzaSyABzb30H7e266HUeV4iYRVjfrvwM_xAgi0", new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.cueVideo(youtube);
                    Log.d(TAG, "onClick: Done Initialising.");
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                    Log.d(TAG, "onClick: Done Initialising.");
                }
            });

            back = findViewById(R.id.goBackButton);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });


        }
    }
}