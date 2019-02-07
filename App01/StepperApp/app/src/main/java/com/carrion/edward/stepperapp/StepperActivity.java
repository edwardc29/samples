package com.carrion.edward.stepperapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class StepperActivity extends AppCompatActivity {
    private static final int NUM_PAGES = 5;
    private ViewPager pager;
    private TextView stepperHeaderTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepper);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        stepperHeaderTextView = findViewById(R.id.tv_stepper_header);
        stepperHeaderTextView.setText(String.format(getString(R.string.stepper_header), 1, NUM_PAGES));

        pager = findViewById(R.id.vp_steps);
        PagerAdapter pagerAdapter = new StepPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    public void onBackClick(View view) {
        pager.setCurrentItem(pager.getCurrentItem() - 1);
        updateStepperHeader(pager.getCurrentItem());
    }

    public void onNextClick(View view) {
        pager.setCurrentItem(pager.getCurrentItem() + 1);
        updateStepperHeader(pager.getCurrentItem());
    }

    private void updateStepperHeader(int currentStep) {
        stepperHeaderTextView.setText(String.format(getString(R.string.stepper_header), currentStep + 1, NUM_PAGES));
    }

    private class StepPagerAdapter extends FragmentStatePagerAdapter {
        StepPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return StepFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
