package dev.berserk.firststeps.viewPagerFormActivity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.util.KeysConstants;
import dev.berserk.firststeps.util.Util;

public class FormActivity extends AppCompatActivity {

    private static String TAG = FormActivity.class.getSimpleName();

    @BindView(R.id.editTextName)
    EditText editTextName;

    @BindView(R.id.editTextLastName)
    EditText editTextLastName;

    @BindView(R.id.textInputLayoutEmail)
    TextInputLayout textInputLayoutEmail;

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;

    @BindView(R.id.parentLayout)
    ScrollView parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonSendForm) public void logIn(View view){

        String name = editTextName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();

        if (areInputsEmpty(name, lastName, email)) {
            if (Util.isEmailAddressValid(email)) {
                sendInformation(name, lastName, email);
            }
            else {
                textInputLayoutEmail.setError(getString(R.string.incorrect_email));
            }
        }

    }

    private boolean areInputsEmpty(String name, String lastName, String email) {
        boolean isAnyEmpty = true;

        if (name.isEmpty()) {
            Util.showSnackBar(getString(R.string.empty_name), parentLayout);

            isAnyEmpty = false;
        }

        if (lastName.isEmpty()) {
            Util.showToast(getString(R.string.empty_last_name), getApplicationContext());

            isAnyEmpty = false;
        }

        if (email.isEmpty()) {
            Util.showSnackBar(getString(R.string.empty_email), parentLayout);

            isAnyEmpty = false;
        }

        return isAnyEmpty;
    }

    private void sendInformation(String name, String lastName, String email){
        Util.showToast(getString(R.string.sending_info), FormActivity.this);

        Util.showLog(TAG, "Message received "+name+" "+lastName+" "+email);
        HashMap<String, Object> extraData = new HashMap<>();
        extraData.put(KeysConstants.NAME, name);
        extraData.put(KeysConstants.LAST_NAME, lastName);
        extraData.put(KeysConstants.EMAIL, email);

        Util.changeActivityAndFinish(FormActivity.this, ViewPagerActivity.class, extraData,
                false);
    }
}
