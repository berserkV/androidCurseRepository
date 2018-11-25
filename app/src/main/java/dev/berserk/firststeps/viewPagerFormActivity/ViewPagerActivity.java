package dev.berserk.firststeps.viewPagerFormActivity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.util.KeyConstants;
import dev.berserk.firststeps.util.Util;
import dev.berserk.firststeps.viewPagerFormActivity.fragments.DataFragment;
import dev.berserk.firststeps.viewPagerFormActivity.fragments.EmailFragment;
import dev.berserk.firststeps.viewPagerFormActivity.fragments.ImageFragment;

public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = ViewPagerActivity.class.getSimpleName();

    @BindView(R.id.parentLayout)
    ScrollView parentLayout;

    @BindView(R.id.viewPagerMain)
    ViewPager viewPagerMain;

    @BindView(R.id.floatingActionButtonTest)
    FloatingActionButton testButton;

    private String name;

    private String lastName;

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_form);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra(KeyConstants.NAME);
            lastName = intent.getStringExtra(KeyConstants.LAST_NAME);
            email = intent.getStringExtra(KeyConstants.EMAIL);
        }
        Util.showLog(TAG, "Message received "+name+" "+lastName+" "+email);
        setUpViewPager();
        tellFragments();
    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(ImageFragment.newInstance(), ImageFragment.TAG);
        viewPagerAdapter.addFragment(DataFragment.newInstance(name, lastName), DataFragment.TAG);
        viewPagerAdapter.addFragment(EmailFragment.newInstance(email), EmailFragment.TAG);

        viewPagerMain.setAdapter(viewPagerAdapter);
        viewPagerMain.addOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.view_pager_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.imageProfile:
                testButton.show();
                viewPagerMain.setCurrentItem(0);

                Util.showSnackBar("Image profile", parentLayout);
                return true;
            case R.id.data:
                testButton.hide();
                viewPagerMain.setCurrentItem(1);

                Util.showToast("Data profile", getApplicationContext());
                return true;
            case R.id.email:
                testButton.hide();
                viewPagerMain.setCurrentItem(2);

                Util.showToast("Email", getApplicationContext());
                return true;
            case R.id.logout:
                Util.showToast("Logout", getApplicationContext());
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        Util.showToast(getString(R.string.success_logout), getApplicationContext());

        Util.changeActivityAndFinish(ViewPagerActivity.this, FormActivity.class, null);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (i == 2 || i == 1){
            testButton.hide();
        }
        else{
            testButton.show();
        }

        Util.showLog(TAG, "Page selected "+i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        Util.showLog(TAG, "On page scroll "+i);
    }

    @Override
    public void onBackPressed() {
        tellFragments();
        super.onBackPressed();
    }

    private void tellFragments(){
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for(Fragment f : fragments){
            if(f != null && f instanceof ImageFragment) {
                ((ImageFragment) f).onBackPressed();
            }
            if(f != null && f instanceof DataFragment) {
                ((DataFragment) f).onBackPressed();
            }
            if(f != null && f instanceof EmailFragment) {
                ((EmailFragment) f).onBackPressed();
            }
        }
    }
}
