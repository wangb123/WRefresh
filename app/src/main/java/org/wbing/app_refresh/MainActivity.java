package org.wbing.app_refresh;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.wbing.app_refresh.databinding.ActivityMainBinding;
import org.wbing.base.ui.impl.WAct;

public class MainActivity extends WAct<ActivityMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void loadData() {
        getBinding().content.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int i) {
                return i == 0 ? new SingleListFragment() : new MultiListFragment();
            }
        });
    }

    @Override
    public void recycle() {

    }

}
