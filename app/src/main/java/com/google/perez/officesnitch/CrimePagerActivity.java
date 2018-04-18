package com.google.perez.officesnitch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends FragmentActivity{


    private static final String EXTRA_CRIME_ID = "com.google.perez.officesnitch";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context context, UUID crimeId){
        Intent i = new Intent(context, CrimePagerActivity.class);
        i.putExtra(EXTRA_CRIME_ID, crimeId);
        return i;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);


        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes = CrimeLab.getCrimeLab(this).getCrimes();
        FragmentManager fm = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime c = mCrimes.get(position);
                return CrimeFragment.newInstance(c.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();


            }
        });

        for (int i = 0; i < mCrimes.size(); i++){
            if(mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i, true);
                break;
            }
        }



    }





}
