package dev.berserk.firststeps.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.berserk.firststeps.R;

public class AnimationSampleActivity extends AppCompatActivity {

    @BindView(R.id.imageViewHeader)
    ImageView mImageViewHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_sample);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonZoom) public void clickZoom(View view) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        mImageViewHeader.startAnimation(animation);
    }

    @OnClick(R.id.buttonClockwise) public void clickClockwise(View view) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.clockwise);
        mImageViewHeader.startAnimation(animation);
    }

    @OnClick(R.id.buttonBlink) public void clickBlink(View view) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        mImageViewHeader.startAnimation(animation);
    }

    @OnClick(R.id.buttonFade) public void clickFade(View view) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade);
        mImageViewHeader.startAnimation(animation);
    }

    @OnClick(R.id.buttonMove) public void clickMove(View view) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        mImageViewHeader.startAnimation(animation);
    }

    @OnClick(R.id.buttonSlide) public void clickSlide(View view) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide);
        mImageViewHeader.startAnimation(animation);
    }
}
