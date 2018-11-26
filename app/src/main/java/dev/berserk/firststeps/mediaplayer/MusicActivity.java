package dev.berserk.firststeps.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.util.Util;

public class MusicActivity extends AppCompatActivity {

    @BindView(R.id.buttonPlay)
    Button mButtonPlay;

    @BindView(R.id.buttonPause)
    Button mButtonPause;

    @BindView(R.id.buttonStop)
    Button mButtonStop;

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonPlay) public void play(View view) {
        if (player == null){
            player = MediaPlayer.create(this, R.raw.moon_men);
            player.setOnCompletionListener(mp -> stopPlayer());
        }

        player.start();
    }

    @OnClick(R.id.buttonPause) public void pause(View view) {
        if (player != null) {
            player.pause();
        }
    }

    @OnClick(R.id.buttonStop) public void stop(View view) {
        stopPlayer();
    }

    public void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;

            Util.showToast("Media player released", this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}
