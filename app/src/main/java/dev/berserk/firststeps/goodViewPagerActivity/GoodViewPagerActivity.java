package dev.berserk.firststeps.goodViewPagerActivity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.goodViewPagerActivity.fragments.FragmentOne;
import dev.berserk.firststeps.goodViewPagerActivity.fragments.FragmentThree;
import dev.berserk.firststeps.goodViewPagerActivity.fragments.FragmentTwo;
import dev.berserk.firststeps.util.Util;

public class GoodViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = GoodViewPagerActivity.class.getSimpleName();

    @BindView(R.id.viewPagerMain)
    ViewPager mViewPagerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_view_pager);

        ButterKnife.bind(this);
        setUpViewPager();
    }

    private void setUpViewPager() {
        GoodPagerAdapter goodPagerAdapter = new GoodPagerAdapter(getSupportFragmentManager());
        goodPagerAdapter.addFragment(FragmentOne.newInstance(), FragmentOne.TAG);
        goodPagerAdapter.addFragment(FragmentTwo.newInstance("Sending data to fragments test"),
                FragmentTwo.TAG);
        goodPagerAdapter.addFragment(FragmentThree.newInstance(), FragmentThree.TAG);

        mViewPagerMain.setAdapter(goodPagerAdapter);
        mViewPagerMain.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) { Util.showLog(TAG, "Page selected "+i); }

    @Override
    public void onPageScrollStateChanged(int i) {
        Util.showLog(TAG, "On page scroll "+i);
    }

    @OnClick(R.id.buttonFragmentOne) public void clickFragmentOne(View view){
        Util.showToast("Fragment one", GoodViewPagerActivity.this);

        mViewPagerMain.setCurrentItem(0);
    }

    @OnClick(R.id.buttonFragmentTwo) public void clickFragmentTwo(View view){
        Util.showToast("Fragment two", GoodViewPagerActivity.this);

        mViewPagerMain.setCurrentItem(1);
    }

    @OnClick(R.id.buttonFragmentThree) public void clickFragmentThree(View view){
        Util.showToast("Fragment three", GoodViewPagerActivity.this);

        mViewPagerMain.setCurrentItem(2);
    }
}
