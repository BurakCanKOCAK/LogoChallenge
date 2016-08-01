package com.bkocak.logochallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bkocak.logochallenge.model.User;
import com.bkocak.logochallenge.utils.FirebaseConfig;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.ArrayList;
import java.util.List;

public class LoginSignUpActivity extends Activity {
    FirebaseConfig firebaseConfig;

    Button signupButton,loginButton;
    ListView lvUsers;
    EditText password;
    EditText username;
    TextView data1, data2;

    String passwordtxt;
    String usernametxt;

    private ChildEventListener cEventListener;
    List<String> userList = new ArrayList<>();

    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    long userCount = 0;

    boolean userRegistred = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        firebaseConfig = new FirebaseConfig();
        ////////////////////////////////////////////////////////////////
        final Firebase myFirebaseRef = new Firebase("https://logochallenge-e4634.firebaseio.com/");

        myFirebaseRef.child("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                System.out.println("Changed Data : "+s);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {


            }
            //Test
        });
        ///////////////////////////////////////////////////////////////////////////////
        myFirebaseRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userCount = snapshot.getChildrenCount();
                System.out.println("There are " + snapshot.getChildrenCount() + " users");
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    userList.add("Username :"+user.getUserName() + " - Level :" + user.getLevel());
                    System.out.println(user.getUserName() + " - " + user.getPassword());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        setContentView(R.layout.login_signup_activity);

        lvUsers = (ListView) findViewById(R.id.lvUsers);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,userList);
        lvUsers.setAdapter(adapter);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        data1 = (TextView) findViewById(R.id.tvData1);
        data2 = (TextView) findViewById(R.id.tvData2);
        data1.setText(firebaseConfig.getGame_room_typ1_mp());
        data2.setText(firebaseConfig.getGame_room_typ2_mp());
        signupButton = (Button) findViewById(R.id.signup);
        loginButton = (Button) findViewById(R.id.login);
        //////////////////////////////////////////////////////Login
        loginButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                User user = new User(usernametxt, passwordtxt, "user000"+userCount, 0);
                Firebase fbUsers = myFirebaseRef.child("Users").child("User"+userCount);
                fbUsers.setValue(user);

            }
        });
        //////////////////////////////////////////////////////SignUp
        signupButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }


}
