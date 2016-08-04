package com.bkocak.logochallenge;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.bkocak.logochallenge.utils.RegisterUser;

public class Name extends Activity implements OnClickListener {
	//NAME ENTRANCES
	
	EditText etUsername,etMail,etPassword;
	Button bRegister;
	String keyUsername="username",keyPassword="password",keyMail="mail";

	boolean userIsRegistered=false;
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		setContentView(R.layout.name_layout);
		etUsername=(EditText)findViewById(R.id.etUsername);
		etPassword=(EditText)findViewById(R.id.etPassword);
		etMail=(EditText) findViewById(R.id.etMail);
		bRegister=(Button)findViewById(R.id.bRegister);
		bRegister.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		SharedPreferences sharedPref = getSharedPreferences("data",MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(keyUsername, etUsername.getText().toString());
		editor.putString(keyPassword, etPassword.getText().toString());
		editor.putString(keyMail, etMail.getText().toString());
		RegisterUser rUser = new RegisterUser(this.getBaseContext(),etUsername.getText().toString(),etMail.getText().toString(),etPassword.getText().toString());

		while(!userIsRegistered)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally
			{
				userIsRegistered=rUser.checkUserRegistration(etUsername.getText().toString(),etMail.getText().toString(),etPassword.getText().toString());
			}
		}
		editor.commit();
		Intent OPEN = new Intent("com.bkocak.logochallenge.OPENING");
		startActivity(OPEN);
	}

}
