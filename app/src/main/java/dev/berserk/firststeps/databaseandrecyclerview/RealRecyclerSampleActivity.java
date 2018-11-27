package dev.berserk.firststeps.databaseandrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.databaseandrecyclerview.adapter.DogShopAdapter;
import dev.berserk.firststeps.databaseandrecyclerview.adapter.DogShopClick;
import dev.berserk.firststeps.databaseandrecyclerview.adapter.DogShopLongClick;
import dev.berserk.firststeps.databaseandrecyclerview.models.DogShop;
import dev.berserk.firststeps.util.KeysConstants;
import dev.berserk.firststeps.util.Util;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealRecyclerSampleActivity extends AppCompatActivity implements DogShopClick, DogShopLongClick {

    private static String TAG = RealRecyclerSampleActivity.class.getSimpleName();

    private RealmList<DogShop> dogShops = new RealmList<>();

    private DogShopAdapter dogShopAdapter;

    @BindView(R.id.shopsRecyclerView)
    RecyclerView mDogShopsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_recycler_sample);

        ButterKnife.bind(this);
        setUpRecyclerView(mDogShopsRecyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getRealmObjects();
    }

    private void getRealmObjects() {
        dogShops.clear();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<DogShop> dogShopsResults = realm.where(DogShop.class).findAll();

        //dogShops.addAll(dogShopsResults.subList(0, dogShopsResults.size()));
        dogShops.addAll(dogShopsResults);
        dogShopAdapter.notifyDataSetChanged();
    }

    private void setUpRecyclerView(RecyclerView mDogShopsRecyclerView) {
        dogShopAdapter = new DogShopAdapter(
                dogShops,
                getApplicationContext(),
                this,
                this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mDogShopsRecyclerView.setAdapter(dogShopAdapter);
        mDogShopsRecyclerView.setLayoutManager(linearLayoutManager);
        //mDogShopsRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
    }

    @Override
    public void onDogShopClickListener(DogShop dogShop) {
        Util.showLog(TAG, dogShop.name+" "+dogShop.address+" short");

        HashMap<String, Object> extraData = new HashMap<>();
        extraData.put(KeysConstants.DOG_SHOP_ID, dogShop.dogShopID);
        extraData.put(KeysConstants.MODE_KEY, KeysConstants.EDIT_MODE);

        Util.changeActivityAndFinish(this, EditShopActivity.class, extraData, false);
    }

    @Override
    public void onDogShopLongClickListener(DogShop dogShop) {
        Util.showLog(TAG, dogShop.name+" "+dogShop.address+" long");

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            dogShops.remove(dogShop);
            dogShopAdapter.notifyDataSetChanged();
            RealmResults<DogShop> shops = realm.where(DogShop.class)
                    .equalTo(KeysConstants.DOG_SHOP_ID, dogShop.dogShopID)
                    .findAll();
            shops.deleteAllFromRealm();
        });
    }

    public DogShop createDoomie() {
        return new DogShop(String.valueOf(Util.getRandomID())
                , "Test Name"
                , "Test Address"
                , Util.setRandomImage());
    }

    @OnClick(R.id.floatingActionButton) public void createDogShop() {
        HashMap<String, Object> extraData = new HashMap<>();
        extraData.put(KeysConstants.MODE_KEY, KeysConstants.CREATE_MODE);

        Util.changeActivityAndFinish(this, EditShopActivity.class, extraData,
                false);

        dogShopAdapter.notifyDataSetChanged();
    }

    public void createDoomies() {

    }
}
