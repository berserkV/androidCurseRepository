package dev.berserk.firststeps.emailActivity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.profileActivity.ProfileActivity;
import dev.berserk.firststeps.util.KeyConstants;
import dev.berserk.firststeps.util.Util;

public class EmailActivity extends AppCompatActivity {

    @BindView(R.id.imageViewHeader)
    ImageView mImageViewHeader;

    @BindView(R.id.textInputLayoutEmail)
    TextInputLayout mTextInputMail;

    @BindView(R.id.editTextEmail)
    EditText mEditTextMail;

    @BindView(R.id.textInputLayoutPassword)
    TextInputLayout mTextInputPassword;

    @BindView(R.id.editTextPassword)
    EditText mEditTextPassword;

    @BindView(R.id.buttonLogin)
    Button mButtonLogin;

    @BindView(R.id.scrollViewParentLayout)
    ScrollView mParentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonLogin) public void logIn(View view){

        String email = mEditTextMail.getText().toString();
        String password = mEditTextPassword.getText().toString();
        boolean bothCorrect = true;

        if (email.isEmpty()) {
            Util.showSnackBar(getString(R.string.empty_email), mParentLayout);

            bothCorrect = false;
        }
        else if (!Util.isEmailAddressValid(email)){
            mTextInputMail.setError(getString(R.string.incorrect_email));

            bothCorrect = false;
        }

        if (password.isEmpty()) {
            Util.showToast(getString(R.string.empty_password), EmailActivity.this);

            bothCorrect = false;
        }
        /*
        else if (!Util.isPasswordValid(password)){
            mTextInputPassword.setError(getString(R.string.incorrect_password));

            bothCorrect = false;
        }
        */

        if (bothCorrect) {
            successLogin(email);
        }
    }

    private void successLogin(String email) {
        Util.showToast(getString(R.string.success_login), EmailActivity.this);

        HashMap<String, Object> extraData = new HashMap<>();
        extraData.put(KeyConstants.PROFILE, email);

        Util.changeActivityAndFinish(EmailActivity.this, ProfileActivity.class, extraData,
                true);

        /*
        Intent intent = new Intent(EmailActivity.this, ProfileActivity.class);
        intent.putExtra(KeyConstants.PROFILE, email);
        startActivity(intent);

        EmailActivity.this.finish();
        */
    }
}
