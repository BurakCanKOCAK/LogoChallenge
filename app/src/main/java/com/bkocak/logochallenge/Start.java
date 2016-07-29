package com.bkocak.logochallenge;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


public class Start extends Activity {
	// Opening Page
	String userName, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);

		/*
		 * ParseUser currentUser = ParseUser.getCurrentUser(); String struser =
		 * currentUser.getUsername().toString(); Log.e(":::::::::::USER :::::",
		 * struser);
		 */
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(2000);

				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					 getPref("name");


				}
			}

			private void getPref(String key) {
				// TODO Auto-generated method stub
				SharedPreferences sharedPref = getSharedPreferences("data",
						MODE_PRIVATE);
				String gotName = sharedPref.getString(key, "");
				if (gotName.equals("")) {
					Intent NAMEINT = new Intent("com.bkocak.logochallenge.NAME");
					startActivity(NAMEINT);
				} else {

					Intent openMain = new Intent(
							"com.bkocak.logochallenge.OPENING");
					startActivity(openMain);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		finish();

	}

}
