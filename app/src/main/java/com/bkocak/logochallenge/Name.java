package com.bkocak.logochallenge;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Name extends Activity implements OnClickListener {
	//�S�M G�RME SAYFASI
	
	EditText et;
	Button bt;
	String name2="",key="name";
	
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
		 et=(EditText)findViewById(R.id.editText1);
		 bt=(Button)findViewById(R.id.button1);
		 bt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		name2=et.getText().toString();
		SharedPreferences sharedPref = getSharedPreferences("data",MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(key, name2);
		editor.commit();
		Intent OPEN = new Intent("com.bkocak.logochallenge.OPENING");
		startActivity(OPEN);
	}

}
