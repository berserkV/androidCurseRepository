package dev.berserk.firststeps.profileActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.emailActivity.EmailActivity;
import dev.berserk.firststeps.util.KeysConstants;
import dev.berserk.firststeps.util.URL;
import dev.berserk.firststeps.util.Util;

public class ProfileActivity extends AppCompatActivity {

    private String email;

    @BindView(R.id.textViewInput)
    TextView mTextViewInput;

    @BindView(R.id.circleImageViewProfile)
    CircleImageView circleImageViewProfile;

    @BindView(R.id.parentLayout)
    ScrollView mParentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        Glide.with(this)
                .load(URL.VENOM_PROFILE_URL)
                .asGif()
                .centerCrop()
                .override(180, 180)
                .crossFade()
                .into(circleImageViewProfile);
        Intent intent = getIntent();
        if (intent != null) {
            email = intent.getStringExtra(KeysConstants.PROFILE);
            mTextViewInput.setText(email);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Util.showSnackBar("User", mParentLayout);
                return true;
            case R.id.item2:
                Util.showToast("Settings", getApplicationContext());
                return true;
            case R.id.item3:
                Util.showToast("Logout", getApplicationContext());
                logout();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        Util.showToast(getString(R.string.success_logout), getApplicationContext());

        Util.changeActivityAndFinish(ProfileActivity.this, EmailActivity.class, null,
                true);
        /*
        Intent intent = new Intent(ProfileActivity.this, EmailActivity.class);
        startActivity(intent);

        ProfileActivity.this.finish();
        */
    }
}
