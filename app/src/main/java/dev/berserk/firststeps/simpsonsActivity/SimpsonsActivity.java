package dev.berserk.firststeps.simpsonsActivity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.berserk.firststeps.R;
import dev.berserk.firststeps.util.Util;

public class SimpsonsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = SimpsonsActivity.class.getSimpleName();

    private ImageView imageViewHeader;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        imageViewHeader = findViewById(R.id.imageViewHeader);
        textView = findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Util.showLogInfo(TAG, "This is Lisa");
                setSimpson( "Lisa", R.drawable.lisa, R.color.colorAccent );
                break;
            case R.id.button2:
                Util.showLogInfo(TAG, "This is Moe");
                setSimpson( "Moe", R.drawable.moe, R.color.colorPrimary );
                break;
            case R.id.linearLayout1:
                Util.showLogInfo(TAG, "This is Bart");
                setSimpson( "Bart", R.drawable.bart, R.color.colorPrimaryDark );
                break;
            case R.id.linearLayout2:
                Util.showLogInfo(TAG, "This is Homer");
                setSimpson( "Homer", R.drawable.homer, R.color.colorPrimaryDark );
                break;
        }
    }

    public void setSimpson( String message, int imgId, int color ) {
        imageViewHeader.setImageDrawable(
                ContextCompat.getDrawable(SimpsonsActivity.this,imgId));
        textView.setText(message);
        textView.setTextColor(color);
        imageViewHeader.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }
}