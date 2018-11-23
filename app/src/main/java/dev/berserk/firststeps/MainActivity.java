package dev.berserk.firststeps;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import dev.berserk.firststeps.util.Util;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName( );

    private ImageView mImageView;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Util.showLog(TAG,"On create");

        mImageView = findViewById(R.id.imageView);
        mTextView =  findViewById(R.id.textView);

        setUpViews();
        readAssets();
    }


    private void setUpViews() {
        mTextView.setText(R.string.test_string);
        mTextView.setTextColor(ContextCompat.getColor(this,R.color.colorText));
        mTextView.setBackgroundResource(R.color.colorTextBackground);

        mImageView.setImageDrawable(
                ContextCompat.getDrawable(MainActivity.this,R.drawable.light_saber));
    }

    private void readAssets() {
        try {
            InputStream inputStream = getAssets().open("textSample.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close( );

            String textObtained = new String(buffer);

            Util.showLog(TAG, textObtained);
        } catch (IOException e) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Util.showLog(TAG,"On start");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Util.showLog(TAG,"On resume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Util.showLog(TAG,"On pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Util.showLog(TAG,"On stop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Util.showLog(TAG,"On destroy");
    }
}
