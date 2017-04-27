package me.virco.youtubeplayer;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private static final String TAG = "YoutubeActivity";

    static final String GOOGLE_API_KEY = "AIzaSyArznOWQgP3nK4w-3SxccskCy3hsg5MKeU";
    static final String YOUTUBE_VIDEO_ID = "xcSOGYqQ_kY";
    static final String YOUTUBE_PLAYLIST = "PLBsP89CPrMeMKjHnLfO3WAhiOOxiv5Xqo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);

        YouTubePlayerView youTubePlayerView = new YouTubePlayerView(this);
        youTubePlayerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(youTubePlayerView);

        youTubePlayerView.initialize(GOOGLE_API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG, "onInitializationSuccess: provider is " + provider.getClass().toString());
        Toast.makeText(this, "Initialized Youtube Player succesfully", Toast.LENGTH_LONG).show();

        if (!wasRestored) {
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YoutubePlayer (%1$s)",
                    youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
