package com.google.perez.officesnitch;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private ArrayList<Crime> mCrimes;//google conventions look at s at the end



    private CrimeLab(Context context){

        mCrimes = new ArrayList<Crime>();

        //BEGIN DUMB THING
        Random rando = new Random();

        ArrayList<String> dumbVerbs = new ArrayList<>();
        dumbVerbs.add("Belched ");
        dumbVerbs.add("Spat ");
        dumbVerbs.add("Threw ");
        dumbVerbs.add("Hit ");
        dumbVerbs.add("Stole ");
        dumbVerbs.add("Killed ");
        dumbVerbs.add("Put ");

        ArrayList<String> dumbNouns = new ArrayList<>();
        dumbNouns.add("Corn Nuts");
        dumbNouns.add("Sally");
        dumbNouns.add("Lunch");
        dumbNouns.add("Wallet");
        dumbNouns.add("Red SwingLine Stapler");
        dumbNouns.add("The Boss");
        dumbNouns.add("Phone");
        dumbNouns.add("Facebook");

        for(int i = 0; i < 1000; i++){

            Crime noob = new Crime();
            noob.setSeverity(rando.nextInt(4));
            noob.setTitle(dumbVerbs.get(rando.nextInt(dumbVerbs.size())) +
                    dumbNouns.get(rando.nextInt(dumbNouns.size())));
            noob.setSolved(rando.nextBoolean());
            mCrimes.add(noob);

        }




        //END DUMB THING

    }


    public static CrimeLab getCrimeLab(Context context){ //conotext is reference to instance of activity that made the call

        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;

    }

    public ArrayList<Crime> getCrimes(){

        return mCrimes;

    }


    public Crime getCrime(UUID id){

        for(Crime c : mCrimes) {

            if (c.getId().equals(id)) {
                return c;
            }

        }

        return null; //no cimre

    }


}
