package com.bkocak.logochallenge.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bkocak.logochallenge.R;
import com.bkocak.logochallenge.model.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bkocak on 04/08/2016.
 */

public class RegisterUser  {
    private String username, mail, password;
    List<String> userList = new ArrayList<String>();

    long userCount = 0;
    boolean userRegistred = false;
    Firebase myFirebaseRef;

    public RegisterUser(Context context,String username, String mail, String password) {
        SetProperty(username, mail, password);
        InitFirebase(context);
    }

    private void SetProperty(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }

    void InitFirebase(Context context) {
        Firebase.setAndroidContext(context);
        myFirebaseRef = new Firebase("https://logochallenge-e4634.firebaseio.com/");

        myFirebaseRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userCount = snapshot.getChildrenCount();
                System.out.println("There are " + snapshot.getChildrenCount() + " users");
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    userList.add(user.getUserName() + "-"+ user.getMail()+"-"+user.getPassword());
                    System.out.println(user.getUserName() + " - " + user.getMail() + " - " + user.getPassword());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        //TODO add listener and register user through firebase to database.
        registerUserName(this.username,this.mail,this.password);
    }
    void registerUserName(String userName, String mail, String password) {
        User user = new User(userName, mail, password, "user000" + userCount, 0);
        Firebase fbUsers = myFirebaseRef.child("Users").child("User" + userCount);
        fbUsers.setValue(user);
    }

    public boolean checkUserRegistration(String username,String mail,String password)
    {
        String criteria = username +"-"+mail+"-"+password;
            for(int i=0;i<userList.size();i++)
            {
                if(userList.toArray()[i].equals(criteria))
                {
                    return true;
                }
            }
        return  false;
    }

}
