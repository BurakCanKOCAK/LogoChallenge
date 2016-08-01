package com.bkocak.logochallenge.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.bkocak.logochallenge.BuildConfig;
import com.bkocak.logochallenge.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import static android.content.ContentValues.TAG;

/**
 * Created by BurakCan on 01/08/2016.
 */

public class FirebaseConfig {
    FirebaseRemoteConfig mFirebaseRemoteConfig;
    private String game_room_typ1_mp;
    private String game_room_typ2_mp;

    public FirebaseConfig() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config);

        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(BuildConfig.DEBUG).build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);

        mFirebaseRemoteConfig.fetch(3).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(!task.isSuccessful())
                {
                    Log.d(":FirebaseRemoteConfig:","Fetch Failure");
                }else
                {
                    Log.d(":FirebaseRemoteConfig:","Fetch Done");
                    mFirebaseRemoteConfig.activateFetched();
                    getGame_room_typ1_mp();
                    getGame_room_typ2_mp();
                }
            }
        });
        Log.d(":::Firebase CONFIG :::", getGame_room_typ1_mp()+" - "+getGame_room_typ2_mp());
    }

    public String getGame_room_typ1_mp() {
        game_room_typ1_mp = mFirebaseRemoteConfig.getString("game_room_type1_mp");
        return game_room_typ1_mp;
    }
    public String getGame_room_typ2_mp(){
        game_room_typ2_mp = mFirebaseRemoteConfig.getString("game_room_type2_mp");
        return game_room_typ2_mp;
    }
}
