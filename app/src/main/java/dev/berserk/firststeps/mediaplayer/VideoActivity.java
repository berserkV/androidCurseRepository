package dev.berserk.firststeps.mediaplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.berserk.firststeps.R;

public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.videoView)
    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        ButterKnife.bind(this);

        // Set MediaController to enable play, pause, forward, etc. options.
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mVideoView);

        // Location media file
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.rick_and_morty);

        // Starting VideoView by setting MediaController and URI
        mVideoView.setMediaController(mediaController);
        mVideoView.setVideoURI(uri);
        mVideoView.requestFocus();
        mVideoView.start();
    }
}
