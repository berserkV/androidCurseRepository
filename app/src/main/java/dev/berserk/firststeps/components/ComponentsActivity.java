package dev.berserk.firststeps.components;

import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;

import com.bumptech.glide.Glide;

import dev.berserk.firststeps.R;
import dev.berserk.firststeps.util.Util;

public class ComponentsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ComponentsActivity.class.getSimpleName();

    ScrollView mParentLayout;

    private LinearLayout mLinearLayout;

    private Button mBuTest;

    private Button mButest2;

    private ProgressBar mProgressBar;

    private Switch mNotification;

    private RadioGroup mRadioGroupTrukutru;

    private CheckBox mCheckBoxLegalTerms;

    private EditText mEditTextNumber;

    private ImageView mImageViewLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components);

        getAllViews();

        setUpImageViewLoad();
        setUpCheckBoxLegalTerms();
        setUpRadioGroupTrukutru();
        setUpButtonOne();
        setUpNotification();
    }

    private void getAllViews() {
        mLinearLayout = findViewById(R.id.linearLayout);
        /*
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.showLog(TAG, "Linear layout clicked");
            }
        });
        */

        mBuTest = findViewById(R.id.button4);
        //mBuTest.setOnClickListener(this);

        mButest2 = findViewById(R.id.button3);
        mProgressBar = findViewById(R.id.progressBar);
        mNotification = findViewById(R.id.notifications);
        mRadioGroupTrukutru = findViewById(R.id.radioGroupTrukutru);
        mCheckBoxLegalTerms = findViewById(R.id.checkBoxLegalTerms);
        mEditTextNumber = findViewById(R.id.editTextNumber);
        mImageViewLoad = findViewById(R.id.imageViewLoad);
        mParentLayout = findViewById(R.id.parentLayout);
    }


    private void setUpImageViewLoad() {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(this);
        circularProgressDrawable.setStrokeWidth(20f);
        circularProgressDrawable.setCenterRadius(60f);
        circularProgressDrawable.setColorFilter(ContextCompat.getColor(this,R.color.colorAccent),
                PorterDuff.Mode.SRC_ATOP);
        circularProgressDrawable.start();
        Glide.with(this)
                .load("https://media.giphy.com/media/ymzkqhy08FYZi/giphy.gif")
                .fitCenter()
                .placeholder(circularProgressDrawable)
                .crossFade( 5000 )
                .into(mImageViewLoad);
    }

    private void setUpCheckBoxLegalTerms() {
        mCheckBoxLegalTerms.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Util.showSnackBar("Check box is "+isChecked, mParentLayout);
            Util.showLog(TAG, "Check box is "+isChecked);
        }
    };

    private void setUpRadioGroupTrukutru() {
        mRadioGroupTrukutru.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonTrukutru1:
                        Util.showToast("Trukutru1", ComponentsActivity.this);
                        break;
                    case R.id.radioButtonTrukutru2:
                        Util.showToast("Trukutru2", ComponentsActivity.this);
                        break;
                }
            }
        });
    }

    private void setUpButtonOne() {
        mBuTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditTextNumber.getText().toString().isEmpty()) {
                    Util.showSnackBar(mEditTextNumber.getText().toString(), mParentLayout);
                }
            }
        });
    }

    private void setUpNotification() {
        mNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    Util.showToast("Switch has turned on", ComponentsActivity.this);
                }
                else {
                    mProgressBar.setVisibility(View.GONE);
                    Util.showToast("Switch has turned off", getApplicationContext());
                }
            }
        });
    }

    /*
    public void clicked(View view) {
        Util.showLog(TAG, "Click on the button");
    }
    */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button4:
                Util.showLog(TAG, "Third button click");
                break;
        }
    }
}