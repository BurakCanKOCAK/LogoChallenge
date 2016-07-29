package com.bkocak.logochallenge;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

public class Share extends Activity implements OnClickListener {
	SharedPreferences sp;

	ImageView profil;
	private TextView tvInfo;
	private LoginButton loginButton;

	private CallbackManager callbackManager;
	private AccessTokenTracker accessTokenTracker;
	private AccessToken accessToken;
	String FILENAME = "AndroidSSO_data";
	private SharedPreferences mPrefs;
	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	private GoogleApiClient client;
	//private static String APP_ID = "203580729841505";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		FacebookSdk.sdkInitialize(this.getApplicationContext());
		AppEventsLogger.activateApp(this);
		callbackManager = CallbackManager.Factory.create();
		setContentView(R.layout.share_layout);
		InitializeComponents();
		//////////////////////////////////////////////////////
		accessTokenTracker = new AccessTokenTracker() {
			@Override
			protected void onCurrentAccessTokenChanged(
					AccessToken oldAccessToken,
					AccessToken currentAccessToken) {
				accessToken = currentAccessToken;
			}
		};
		// If the access token is available already assign it.
		accessToken = AccessToken.getCurrentAccessToken();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
	}

	private void InitializeComponents() {
		tvInfo = (TextView) findViewById(R.id.tvInfo);
		profil = (ImageView) findViewById(R.id.ivProfil);
		loginButton = (LoginButton) findViewById(R.id.login_button);
		loginButton.setReadPermissions("email","public_profile","user_status");
		loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

			@Override
			public void onSuccess(LoginResult loginResult) {
				accessToken = loginResult.getAccessToken();
				Profile profile = Profile.getCurrentProfile();
				GraphRequest request = GraphRequest.newMeRequest(accessToken,
						new GraphRequest.GraphJSONObjectCallback() {
							@Override
							public void onCompleted(JSONObject object, GraphResponse response) {
								try {
									tvInfo.setText("Hi, " + object.getString("name"));
								} catch (JSONException ex) {
									ex.printStackTrace();
								}
							}
						});
				Bundle parameters = new Bundle();
				parameters.putString("fields", "id,name,email,gender, birthday");
				request.setParameters(parameters);
				request.executeAsync();
			}

			@Override
			public void onCancel() {
				tvInfo.setText("Login attempt canceled.");
			}

			@Override
			public void onError(FacebookException e) {
				tvInfo.setText("Login attempt failed.");
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	// @SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	////////////////////////////////////////////////////////////////////////////////
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		callbackManager.onActivityResult(requestCode, resultCode, data);

		GraphRequest request = GraphRequest.newMeRequest(
				accessToken,
				new GraphRequest.GraphJSONObjectCallback() {
					@Override
					public void onCompleted(
							final JSONObject object,
							GraphResponse response) {
						// Application code
						final JSONObject jsonObject = response.getJSONObject();
						String name = "";
						String email = "";
						String id = "";
						try {
							name = jsonObject.getString("name");
							email = jsonObject.getString("email");

							System.out.println(name);
							System.out.println(email);
							System.out.println(id);

						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				});
		Bundle parameters = new Bundle();
		parameters.putString("fields", "id,name,email");
		request.setParameters(parameters);
		request.executeAsync();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		accessTokenTracker.stopTracking();
	}

	@Override
	public void onStart() {
		super.onStart();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client.connect();
		Action viewAction = Action.newAction(
				Action.TYPE_VIEW, // TODO: choose an action type.
				"Share Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app URL is correct.
				Uri.parse("android-app://com.bkocak.logochallenge/http/host/path")
		);
		AppIndex.AppIndexApi.start(client, viewAction);
	}

	@Override
	public void onStop() {
		super.onStop();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		Action viewAction = Action.newAction(
				Action.TYPE_VIEW, // TODO: choose an action type.
				"Share Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app URL is correct.
				Uri.parse("android-app://com.bkocak.logochallenge/http/host/path")
		);
		AppIndex.AppIndexApi.end(client, viewAction);
		client.disconnect();
	}
}
