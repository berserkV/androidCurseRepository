package dev.berserk.firststeps.avengersActivity;

import android.graphics.PorterDuff;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import dev.berserk.firststeps.R;
import dev.berserk.firststeps.util.URL;
import dev.berserk.firststeps.util.Util;

public class AvengersActivity extends AppCompatActivity {

    private ScrollView layoutParent;

    private Spinner mSpinnerName;

    private Button mButtonShow;

    private Button mButtonEnd;

    private EditText mEditTextPhrase;

    private Switch mIsAlive;

    private RadioGroup mHasGem;

    private ImageView mImageViewAvenger;

    private TextView mTextViewName;

    private TextView mTextViewPhrase;

    private ConstraintLayout mConstraintLayoutView;

    private ConstraintLayout mConstraintLayoutForm;

    private CheckBox mCheckBoxAlive;

    private CheckBox mCheckBoxGem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avengers);

        setUpFormViews();
        addListenerOnButtonShow();
        addListenerOnButtonEnd();
    }

    private void setUpFormViews() {
        layoutParent = findViewById(R.id.scrollViewParent);
        mSpinnerName = findViewById(R.id.spinnerAvenger);
        mButtonShow = findViewById(R.id.buttonShow);
        mButtonEnd = findViewById(R.id.buttonEnd);
        mEditTextPhrase = findViewById(R.id.editTextPhrase);
        mIsAlive = findViewById(R.id.switchAlive);
        mHasGem = findViewById(R.id.radioGroupGem);
        mImageViewAvenger = findViewById(R.id.imageViewAvenger);
        mConstraintLayoutView = findViewById(R.id.constraintLayoutView);
        mConstraintLayoutForm = findViewById(R.id.constraintLayoutForm);
        mTextViewName = findViewById(R.id.textViewName);
        mTextViewPhrase = findViewById(R.id.textViewPhrase);
        mCheckBoxAlive = findViewById(R.id.checkBoxAlive);
        mCheckBoxGem = findViewById(R.id.checkBoxGem);
    }

    // get the selected dropdown list value
    private void addListenerOnButtonShow() {
        mButtonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditTextPhrase.getText().toString().isEmpty()) {
                    fillFields();
                }
                else {
                    Util.showSnackBar("Phrase missing", layoutParent);
                }
            }
        });
    }

    // get the selected dropdown list value
    private void addListenerOnButtonEnd() {
        mButtonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstraintLayoutForm.setVisibility(View.VISIBLE);
                mConstraintLayoutView.setVisibility(View.GONE);
            }
        });
    }

    public void fillFields() {

        mConstraintLayoutForm.setVisibility(View.GONE);
        mConstraintLayoutView.setVisibility(View.VISIBLE);

        mTextViewName.setText(mSpinnerName.getSelectedItem().toString());
        mTextViewPhrase.setText(mEditTextPhrase.getText());

        mCheckBoxAlive.setChecked( mIsAlive.isChecked( ) );

        int index = mHasGem.indexOfChild(findViewById(mHasGem.getCheckedRadioButtonId()));
        mCheckBoxGem.setChecked(index == 0 ? true : false);

        switch(mSpinnerName.getSelectedItem().toString()) {
            case "Thor":
                Util.setUpImageViewLoad(URL.THOR_URL, mImageViewAvenger, AvengersActivity.this);
                break;
            case "Spider-Man":
                Util.setUpImageViewLoad(URL.SPIDER_MAN_URL, mImageViewAvenger, AvengersActivity.this);
                break;
            case "Iron Man":
                Util.setUpImageViewLoad(URL.IRON_MAN_URL, mImageViewAvenger, AvengersActivity.this);
                break;
            case "Captain America":
                Util.setUpImageViewLoad(URL.CAPTAIN_AMERICIA, mImageViewAvenger, AvengersActivity.this);
                break;
        }
    }
}
