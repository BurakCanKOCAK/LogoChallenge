package com.bkocak.logochallenge;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.HtmlAd;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

//import com.searchboxsdk.android.StartAppSearch;

public class Opening extends Activity implements OnClickListener,AdEventListener  {
	//MENU PAGE
	//::PROPERTY DECLARATION::
	//ADS
	private StartAppAd startAppAd = new StartAppAd(this);
	private HtmlAd htmlAd = null;

	//INTENTS
	private String intentGame = "com.bkocak.logochallenge.GAME";
	private String intentShare = "com.bkocak.logochallenge.LOGINSIGNUPACTIVITY";
	private String intentSettings ="com.bkocak.logochallenge.SETTINGS";
	Intent baslaintent, skorintent, paylasintent;

	//OBJECTS ON LAYOUTS
	TextView tv;
	Button basla, skor, paylas;
	MediaPlayer Music;
	Boolean soundIsOn;
	SharedPreferences sharedPref ;
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		soundIsOn=sharedPref.getBoolean("soundIsOn", true);
		startAppAd.onResume();
	}
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_open);
		//new TapContextSDK(getApplicationContext()).initialize();  
		//new TapContextSDK(getApplicationContext()).showAd();    
		initialize();
	}

	private void initialize() {
		StartAppSDK.init(this, "201512543", true);
		Music = MediaPlayer.create(Opening.this, R.raw.click);
		basla = (Button) findViewById(R.id.bBasla);
		skor = (Button) findViewById(R.id.bSettings);
		paylas = (Button) findViewById(R.id.bPaylas);
		basla.setOnClickListener(this);
		skor.setOnClickListener(this);
		paylas.setOnClickListener(this);
	tv=(TextView)findViewById(R.id.textView2);
	sharedPref = getSharedPreferences("data",MODE_PRIVATE);
	String gotName = sharedPref.getString("name", "null");
	soundIsOn=sharedPref.getBoolean("soundIsOn", true);
	tv.setText("Hi , "+ gotName+" !");
	}

	@Override
	public void onClick(View v) {
		Music.release();
		Music = MediaPlayer.create(Opening.this, R.raw.click);
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bBasla:
			if(soundIsOn){
			Music.start();
			}
			Intent openMain=new Intent (intentGame);
				startActivity(openMain);
			
			
			break;

		case R.id.bPaylas:
			if(soundIsOn){
				Music.start();
				
				}
			Intent openShare=new Intent (intentShare);
			startActivity(openShare);
			break;
			
		case R.id.bSettings:

			if(soundIsOn){
				Music.start();
				}
			Intent openSettings=new Intent (intentSettings);
			startActivity(openSettings);
			break;

		}
	}

	
	@Override
	public void onBackPressed() { 
		startAppAd.onBackPressed();
	      super.onBackPressed();
	}
	@Override
	public void onPause() {
	   super.onPause(); 
	   //startAppAd.onPause();
		soundIsOn=sharedPref.getBoolean("sound", true);
	}
	@Override
	public void onFailedToReceiveAd(Ad arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReceiveAd(Ad arg0) {
		// TODO Auto-generated method stub
		
	}
}
