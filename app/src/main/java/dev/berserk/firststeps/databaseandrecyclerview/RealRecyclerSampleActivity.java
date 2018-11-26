package dev.berserk.firststeps.databaseandrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.databaseandrecyclerview.adapter.DogShopAdapter;
import dev.berserk.firststeps.databaseandrecyclerview.adapter.DogShopClick;
import dev.berserk.firststeps.databaseandrecyclerview.adapter.DogShopLongClick;
import dev.berserk.firststeps.databaseandrecyclerview.models.DogShop;
import dev.berserk.firststeps.util.Util;

public class RealRecyclerSampleActivity extends AppCompatActivity implements DogShopClick, DogShopLongClick {

    private static String TAG = RealRecyclerSampleActivity.class.getSimpleName();

    private List<DogShop> dogShops = new ArrayList<>();

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
        Util.showLog(TAG, dogShop.name+" "+dogShop.address);
    }

    @Override
    public void onDogShopLongClickListener(DogShop dogShop) {
        Util.showLog(TAG, dogShop.name+" "+dogShop.address);
    }

    public DogShop createDoomie() {
        return new DogShop(String.valueOf(Util.getRandomID())
                , "Test Name"
                , "Test Address"
                , Util.setRandomImage());
    }

    @OnClick(R.id.floatingActionButton) public void addDoomie() {
        dogShops.add(createDoomie());

        dogShopAdapter.notifyDataSetChanged();
    }
}
