package dev.berserk.firststeps.newPagerActivity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.berserk.firststeps.R;

public class ViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.viewPagerMain)
    ViewPager mViewPagerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        ButterKnife.bind(this);

        mViewPagerMain.setAdapter(new CustomPagerAdapter(this));
    }
}
