package com.bkocak.logochallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class YanlisDialog extends Activity implements OnClickListener {
TextView TV;
Button Yes,No;
Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yanlisdialogxml);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		No=(Button)findViewById(R.id.bno);
		Yes=(Button)findViewById(R.id.byes);
		Yes.setOnClickListener(this);
		No.setOnClickListener(this);
		intent=getIntent();
		intent.putExtra("answer", "no");
		setResult(RESULT_OK,intent);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch ( v.getId())
		{
		case R.id.bno:
			intent=getIntent();
			intent.putExtra("answer", "no");
			setResult(RESULT_OK,intent);
			finish();
			break;
		case R.id.byes:
			intent=getIntent();
			intent.putExtra("answer", "yes");
			setResult(RESULT_OK,intent);
			finish();
			break;
			
		
		}
	}

}
