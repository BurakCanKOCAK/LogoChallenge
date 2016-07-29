package com.bkocak.logochallenge;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.Switch;

public class Settings extends Activity implements OnClickListener{
	public CheckBox cSound,cVibration;
	SharedPreferences.Editor editor;
	Boolean soundIsOn,vibrationIsOn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_scores);
		initialize();
		 cSound.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if (buttonView.isChecked()) { 
						//checkededitor.putInt("sound", 1);
						editor.putBoolean("soundIsOn", true);
						editor.commit();
		            	Log.v("Sound Checked", "==1");
						} 
						else 
						{
						//not checked
							editor.putBoolean("soundIsOn", false);
			    			editor.commit();
			    			Log.v("Sound Checked", "==0");
						} 

				}
			});
			  cVibration.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if (buttonView.isChecked()) { 
						//checkededitor.putInt("sound", 1);
						editor.putBoolean("vibrationIsOn", true);
		    			editor.commit();
		    			Log.v("Vibration Checked", "==1");
						} 
						else 
						{
						//not checked
							editor.putBoolean("vibration", false);
			    			editor.commit();
			    			Log.v("Vibration Checked", "==0");
						} 

				}
			});
	}

	private void initialize() {
		// TODO Auto-generated method stub
		cSound=(CheckBox)findViewById(R.id.cbSound);
		cVibration=(CheckBox)findViewById(R.id.cbVibration);
		
		SharedPreferences sharedPref = getSharedPreferences("data",
				MODE_PRIVATE);
		editor = sharedPref.edit();
		soundIsOn = sharedPref.getBoolean("soundIsOn", true);
		vibrationIsOn= sharedPref.getBoolean("vibrationIsOn", true);
		if(soundIsOn)
		{
			Log.v("soundIsOn", " :: true");
			cSound.setChecked(true);
		}else
		{
			Log.v("soundIsOn", " :: false");
			cSound.setChecked(false);
		}
		if(vibrationIsOn)
		{
			Log.v("vibrationIsOn", "  ::  true");
			cVibration.setChecked(true);
		}else
		{
			Log.v("vibrationIsOn", "  ::  false");
			cVibration.setChecked(false);
		}
		
	}

	
	
	
	    // Is the view now checked?
	  
	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
