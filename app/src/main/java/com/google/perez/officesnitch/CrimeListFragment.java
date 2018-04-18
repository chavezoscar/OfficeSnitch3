package com.google.perez.officesnitch;

import android.content.Intent;
import android.os.Bundle;
import android.print.PrinterId;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {

private RecyclerView mCrimeRecylerView;
private CrimeAdapter mAdapter;

//BEGIN VIEW HOLDER CLASS

    private class CrimeHolder extends RecyclerView.ViewHolder
                              implements View.OnClickListener{

        private TextView mTitletextView;
        private TextView mDateTextView;
        //private CheckBox mSolvedCheckBox;
        private Crime mCrime;

        private ImageView mSolvedImageView;
        private ImageView mImageView; //End image
        private ImageView mImageView12; //2nd to End image
        private ImageView mImageView8; //3rd to End image
        private ImageView mImageView9; //Start image


        //add fields for images


        public CrimeHolder(View itemView){
            super(itemView);
            mTitletextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            //mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_checkbox);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);

            //inflated criminal images here

            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mImageView12 = (ImageView) itemView.findViewById(R.id.imageView12);
            mImageView8 = (ImageView) itemView.findViewById(R.id.imageView8);
            mImageView9 = (ImageView) itemView.findViewById(R.id.imageView9);

            //end inflated criminal images here

            itemView.setOnClickListener(this);
        }

        public void bindCrime(Crime c){

            mCrime = c;
            mTitletextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            //mSolvedCheckBox.setChecked(mCrime.isSolved());
            mSolvedImageView.setVisibility(mCrime.isSolved()? View.VISIBLE : View.INVISIBLE);

            //if statment depending on crime severity, set visible images or set inivis

            if(mCrime.getSeverity() == 0){
                mImageView9.setVisibility(View.VISIBLE);
                mImageView8.setVisibility(View.INVISIBLE);
                mImageView12.setVisibility(View.INVISIBLE);
                mImageView.setVisibility(View.INVISIBLE);

            }

            if(mCrime.getSeverity() == 1){
                mImageView9.setVisibility(View.VISIBLE);
                mImageView8.setVisibility(View.VISIBLE);
                mImageView12.setVisibility(View.INVISIBLE);
                mImageView.setVisibility(View.INVISIBLE);

            }
            if(mCrime.getSeverity() == 2){
                mImageView9.setVisibility(View.VISIBLE);
                mImageView8.setVisibility(View.VISIBLE);
                mImageView12.setVisibility(View.VISIBLE);
                mImageView.setVisibility(View.INVISIBLE);

            }
            if(mCrime.getSeverity() == 3){
                mImageView9.setVisibility(View.VISIBLE);
                mImageView8.setVisibility(View.VISIBLE);
                mImageView12.setVisibility(View.VISIBLE);
                mImageView.setVisibility(View.VISIBLE);

            }

            //End if image if statement

        }

        @Override
        public void onClick(View v) {

            Intent i = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(i);

        }
    }

//END VIEW HOLDER CLASS


    //BEGIN ADAPTER CLASS FOR

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Crime> mCrimes;


        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }


        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(v);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime c = mCrimes.get(position);
            holder.bindCrime(c);
        }
    }
    //END ADAPTER CLASS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecylerView = v.findViewById(R.id.crime_recycler_view);
        mCrimeRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));//whenever something wants context use getactity to get instance of activity




        updateUI();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        CrimeLab lab = CrimeLab.getCrimeLab(getActivity());

        mAdapter =  new CrimeAdapter(lab.getCrimes());
        mCrimeRecylerView.setAdapter(mAdapter);


        if(mAdapter == null){
            mAdapter = new CrimeAdapter(lab.getCrimes());
            mCrimeRecylerView.setAdapter(mAdapter);
        }

        else {
            mAdapter.notifyDataSetChanged();
        }
    }

}
