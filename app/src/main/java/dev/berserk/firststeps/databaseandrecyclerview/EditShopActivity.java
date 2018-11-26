package dev.berserk.firststeps.databaseandrecyclerview;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.databaseandrecyclerview.models.DogShop;
import dev.berserk.firststeps.util.KeyConstants;
import dev.berserk.firststeps.util.Util;
import io.realm.Realm;
import io.realm.RealmQuery;

public class EditShopActivity extends AppCompatActivity {

    private static String TAG = EditShopActivity.class.getSimpleName();

    @BindView(R.id.imageViewShop)
    ImageView mImgShop;

    @BindView(R.id.textInputLayout)
    TextInputLayout mTextInputLayoutName;

    @BindView(R.id.etName)
    EditText mEtName;

    @BindView(R.id.textInputLayoutAddress)
    TextInputLayout mTextInputLayoutAddress;

    @BindView(R.id.etAddress)
    EditText mEtAddress;

    @BindView(R.id.parent)
    ConstraintLayout mParentLayout;

    private String MODE;

    private String SHOP_ID;

    private DogShop dogShop;

    private String name;

    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shop);

        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        MODE = getIntent().getStringExtra(KeyConstants.MODE_KEY);
        Util.showLog(TAG, "Mode: "+MODE);
        setUpMode(MODE);
    }

    private void setUpMode(String mode) {
        if (mode.equals(KeyConstants.EDIT_MODE)) {
            updateMode();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }

    private void updateMode() {
        SHOP_ID = getIntent().getStringExtra(KeyConstants.DOG_SHOP_ID);
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<DogShop> query = realm.where(DogShop.class);
        query.equalTo(KeyConstants.DOG_SHOP_ID, SHOP_ID);
        dogShop = query.findFirst();
        if (dogShop != null) {
            mEtName.setText(dogShop.name);
            mEtAddress.setText(dogShop.address);
            Glide.with(this)
                    .load(dogShop.image)
                    .crossFade(1500)
                    .into(mImgShop);
        }
    }

    @OnClick(R.id.buttonEnd) public void finalize(View view) {
        name = mEtName.getText().toString();
        address = mEtAddress.getText().toString();
        Util.showLog(TAG, name+" "+address);
        if (areInputsEmpty()) {
            if (MODE.equals(KeyConstants.CREATE_MODE)){
                createShop();
            }
            else {
                updateShop();
            }
        }
    }

    private boolean areInputsEmpty() {
        boolean isAnyEmpty = true;

        if (name.isEmpty()) {
            //Util.showSnackBar(getString(R.string.empty_name), parentLayout);
            mTextInputLayoutName.setError(getString(R.string.empty_name));

            isAnyEmpty = false;
        }

        if (address.isEmpty()) {
            //Util.showToast(getString(R.string.empty_address), getApplicationContext());
            mTextInputLayoutAddress.setError(getString(R.string.empty_address));

            isAnyEmpty = false;
        }

        return isAnyEmpty;
    }
    private void updateShop() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            dogShop.name = mEtName.getText().toString();
            dogShop.address = mEtAddress.getText().toString();
            realm1.copyToRealmOrUpdate(dogShop);
        });

        finish();
    }

    private void createShop() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm1.copyToRealm(new DogShop(String.valueOf(Util.getRandomID()),
                    mEtName.getText().toString(),
                    mEtAddress.getText().toString(),
                    Util.setRandomImage()));
        });

        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
