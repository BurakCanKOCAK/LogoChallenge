package com.bkocak.logochallenge;

import java.util.Random;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

public class Main extends Activity implements OnClickListener,
        AnimationListener {

    //GAME PAGE
    // :: PROPERTY DECLARATION ::
    Intent intent;
    Vibrator v;
    SharedPreferences.Editor editor;
    Button A, B, C, D;
    MediaPlayer Music_D, Music_Y;
    TextView Soru, Skor, TVStar, HSkor;
    Animation anim_blink, anim_star;
    private static final String TAG = ":: Main.java :: ";
    private static int SPLASH_TIME_OUT = 1000, star_saved = 0,
            h_skor_saved = 0;

    private StartAppAd startAppAd = new StartAppAd(this);
    String Dcevap = "";
    Random Rand;
    private int n, puan = 0, star = 0;
    private Boolean soundIsOn, vibrationIsOn;
    String[] cevaplar;
    ImageView imageView;
    Integer[] pics = {R.drawable.f1, R.drawable.f2, R.drawable.f3,
            R.drawable.f4, R.drawable.f5, R.drawable.f6, R.drawable.f7,
            R.drawable.f8, R.drawable.f9, R.drawable.f10, R.drawable.f11,
            R.drawable.f12, R.drawable.f13, R.drawable.f14, R.drawable.f15,
            R.drawable.f16, R.drawable.f17, R.drawable.f18, R.drawable.f19,
            R.drawable.f20, R.drawable.f21, R.drawable.f22, R.drawable.f23,
            R.drawable.f24, R.drawable.f25, R.drawable.f26, R.drawable.f27,
            R.drawable.f28, R.drawable.f29, R.drawable.f30, R.drawable.f31,
            R.drawable.f32, R.drawable.f33, R.drawable.f34, R.drawable.f35,
            R.drawable.f36, R.drawable.f37, R.drawable.f38, R.drawable.f39,
            R.drawable.f40, R.drawable.f41, R.drawable.f42, R.drawable.f43,
            R.drawable.f44, R.drawable.f45, R.drawable.f46, R.drawable.f47,
            R.drawable.f48, R.drawable.f49, R.drawable.f50, R.drawable.f51,
            R.drawable.f52, R.drawable.f53, R.drawable.f54, R.drawable.f55,
            R.drawable.f56, R.drawable.f57, R.drawable.f58, R.drawable.f59,
            R.drawable.f60, R.drawable.f61, R.drawable.f62, R.drawable.f63,
            R.drawable.f64, R.drawable.f65, R.drawable.f66, R.drawable.f67,
            R.drawable.f68, R.drawable.f69, R.drawable.f70, R.drawable.f71,
            R.drawable.f72, R.drawable.f73, R.drawable.f74, R.drawable.f75,
            R.drawable.f76, R.drawable.f77, R.drawable.f78, R.drawable.f79,
            R.drawable.f80, R.drawable.f81, R.drawable.f82, R.drawable.f83,
            R.drawable.f84, R.drawable.f85, R.drawable.f86, R.drawable.f87,
            R.drawable.f88, R.drawable.f89, R.drawable.f90, R.drawable.f91,
            R.drawable.f92, R.drawable.f93, R.drawable.f94, R.drawable.f95,
            R.drawable.f96, R.drawable.f97, R.drawable.f98, R.drawable.f99,
            R.drawable.f100, R.drawable.f101, R.drawable.f102, R.drawable.f103,
            R.drawable.f104, R.drawable.f105, R.drawable.f106, R.drawable.f107,
            R.drawable.f108, R.drawable.f109, R.drawable.f110, R.drawable.f111,
            R.drawable.f112, R.drawable.f113, R.drawable.f114, R.drawable.f115,
            R.drawable.f116, R.drawable.f117, R.drawable.f118, R.drawable.f119,
            R.drawable.f120, R.drawable.f121, R.drawable.f122, R.drawable.f123,
            R.drawable.f124, R.drawable.f125, R.drawable.f126, R.drawable.f127,
            R.drawable.f128, R.drawable.f129, R.drawable.f130, R.drawable.f131,
            R.drawable.f132, R.drawable.f133, R.drawable.f134, R.drawable.f135,
            R.drawable.f136, R.drawable.f137, R.drawable.f138, R.drawable.f139,
            R.drawable.f140, R.drawable.f141, R.drawable.f142, R.drawable.f143,
            R.drawable.f144, R.drawable.f145, R.drawable.f146, R.drawable.f147,
            R.drawable.f148, R.drawable.f149, R.drawable.f150, R.drawable.f151,
            R.drawable.f152, R.drawable.f153, R.drawable.f154, R.drawable.f155,
            R.drawable.f156, R.drawable.f157, R.drawable.f158, R.drawable.f159,
            R.drawable.f160, R.drawable.f161, R.drawable.f162, R.drawable.f163,
            R.drawable.f164, R.drawable.f165, R.drawable.f166, R.drawable.f167,
            R.drawable.f168, R.drawable.f169, R.drawable.f170, R.drawable.f171,
            R.drawable.f172, R.drawable.f173, R.drawable.f174, R.drawable.f175,
            R.drawable.f176, R.drawable.f177, R.drawable.f178, R.drawable.f179,
            R.drawable.f180, R.drawable.f181, R.drawable.f182, R.drawable.f183,
            R.drawable.f184, R.drawable.f185, R.drawable.f186, R.drawable.f187,
            R.drawable.f188, R.drawable.f189, R.drawable.f190, R.drawable.f191,
            R.drawable.f192, R.drawable.f193, R.drawable.f194, R.drawable.f195,
            R.drawable.f196, R.drawable.f197, R.drawable.f198, R.drawable.f199,
            R.drawable.f200, R.drawable.f201, R.drawable.f202, R.drawable.f203,
            R.drawable.f204, R.drawable.f205, R.drawable.f206, R.drawable.f207,
            R.drawable.f208, R.drawable.f209, R.drawable.f210, R.drawable.f211,
            R.drawable.f212, R.drawable.f213, R.drawable.f214, R.drawable.f215,
            R.drawable.f216, R.drawable.f217, R.drawable.f218, R.drawable.f219,
            R.drawable.f220, R.drawable.f221, R.drawable.f222, R.drawable.f223,
            R.drawable.f224, R.drawable.f225, R.drawable.f226, R.drawable.f227,
            R.drawable.f228, R.drawable.f229, R.drawable.f230,

    };

    /*
     * R.drawable.f26, R.drawable.f27, R.drawable.f28, R.drawable.f29,
     * R.drawable.f30,R.drawable.f41, R.drawable.f42, R.drawable.f43,
     * R.drawable.f44, R.drawable.f45, R.drawable.f46, R.drawable.f47,
     * R.drawable.f48, R.drawable.f49, R.drawable.f50,
     */
    // -------------------------------------------------ON CREATE
    // ----------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Music_D = MediaPlayer.create(Main.this, R.raw.dogru);
        Music_Y = MediaPlayer.create(Main.this, R.raw.yanlis);
        // StartAppAd.init(this, "103151300", "201512543");
        StartAppSDK.init(this, "201512543", true);
        // StartAppSearch.init(this, "103151300", "201512543");
        initialize();
        SoruGetir();
        // startAppAd.showAd(); // show the ad
        // startAppAd.loadAd();
    }

    // --------------------------------------------------SORU
    // GETIR---------------------------------------------------------
    private void SoruGetir() {
        Music_D = MediaPlayer.create(Main.this, R.raw.correct);
        Music_Y = MediaPlayer.create(Main.this, R.raw.wrong);

        Skor.setText("Score :" + String.valueOf(puan));

        // TODO Auto-generated method stub
        // sayi = Rand.nextInt(3);
        n = Rand.nextInt(pics.length);
        // n = Rand.nextInt(9);
        // Soruyazi = sorular[n];
        // Soru.setText(Soruyazi);
        A.setBackgroundDrawable(getResources().getDrawable(R.drawable.b1b));
        B.setBackgroundDrawable(getResources().getDrawable(R.drawable.b2b));
        C.setBackgroundDrawable(getResources().getDrawable(R.drawable.b3b));
        D.setBackgroundDrawable(getResources().getDrawable(R.drawable.b4b));
        //Setting answers
        Dcevap = cevaplar[(5 * n) + 4];
        A.setText(cevaplar[(5 * n)]);
        B.setText(cevaplar[(5 * n) + 1]);
        C.setText(cevaplar[(5 * n) + 2]);
        D.setText(cevaplar[(5 * n) + 3]);
        imageView.setImageResource(pics[n]);
        A.setClickable(true);
        B.setClickable(true);
        C.setClickable(true);
        D.setClickable(true);
    }

    // ---------------------------------------------INITIALIZE-------------------------------------------------------
    private void initialize() {
        setContentView(R.layout.layout);
        SharedPreferences sharedPref = getSharedPreferences("data",
                MODE_PRIVATE);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        editor = sharedPref.edit();
        soundIsOn = sharedPref.getBoolean("soundIsOn", true);
        vibrationIsOn = sharedPref.getBoolean("vibrationIsOn", true);
        star_saved = sharedPref.getInt("star", 0);
        h_skor_saved = sharedPref.getInt("hscore", 0);
        // TODO Auto-generated method stub

        HSkor = (TextView) findViewById(R.id.HSkor);
        Skor = (TextView) findViewById(R.id.Skor);
        Skor.setText(String.valueOf(puan));
        HSkor.setText("High Score: " + String.valueOf(h_skor_saved));
        A = (Button) findViewById(R.id.A);
        B = (Button) findViewById(R.id.B);
        C = (Button) findViewById(R.id.C);
        D = (Button) findViewById(R.id.D);
        TVStar = (TextView) findViewById(R.id.Star);
        TVStar.setText("x" + String.valueOf(star_saved));
        // Soru = (TextView) findViewById(R.id.Soru);
        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        Resources res = getResources();
        cevaplar = res.getStringArray(R.array.as);
        // sorular = res.getStringArray(R.array.qs);
        Rand = new Random();
        imageView = (ImageView) findViewById(R.id.imageView1);
        anim_blink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        // anim_star = AnimationUtils.loadAnimation(getApplicationContext(),
        // R.anim.blink);
        anim_blink.setAnimationListener(this);

    }

    //--------------------------------------------------------------------//
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.A:

                if (Dcevap.contentEquals("A")) {
                    // Dogru
                    A.setClickable(false);
                    B.setClickable(false);
                    C.setClickable(false);
                    D.setClickable(false);
                    A.setBackgroundDrawable(getResources().getDrawable(
                            R.drawable.btrue));
                    A.startAnimation(anim_blink);

                    dogrucevap();
                } else {
                    // Yanlis
                    A.setClickable(false);
                    B.setClickable(false);
                    C.setClickable(false);
                    D.setClickable(false);
                    A.setBackgroundDrawable(getResources().getDrawable(
                            R.drawable.falsexm));
                    A.startAnimation(anim_blink);
                    yanliscevap();
                }

                break;
            case R.id.B:
                if (Dcevap.contentEquals("B")) {
                    // Dogru
                    A.setClickable(false);
                    B.setClickable(false);
                    C.setClickable(false);
                    D.setClickable(false);
                    B.setBackgroundDrawable(getResources().getDrawable(
                            R.drawable.btrue));
                    B.startAnimation(anim_blink);

                    dogrucevap();

                } else {
                    // Yanl�s
                    A.setClickable(false);
                    B.setClickable(false);
                    C.setClickable(false);
                    D.setClickable(false);
                    B.setBackgroundDrawable(getResources().getDrawable(
                            R.drawable.bfalse));
                    B.startAnimation(anim_blink);

                    yanliscevap();
                }

                break;
            case R.id.C:
                if (Dcevap.contentEquals("C")) {
                    // Dogru
                    A.setClickable(false);
                    B.setClickable(false);
                    C.setClickable(false);
                    D.setClickable(false);
                    C.setBackgroundDrawable(getResources().getDrawable(
                            R.drawable.btrue));
                    C.startAnimation(anim_blink);

                    dogrucevap();
                } else {
                    A.setClickable(false);
                    B.setClickable(false);
                    C.setClickable(false);
                    D.setClickable(false);
                    C.setBackgroundDrawable(getResources().getDrawable(
                            R.drawable.bfalse));
                    // Yanl�s
                    C.startAnimation(anim_blink);

                    yanliscevap();
                }

                break;
            case R.id.D:
                if (Dcevap.contentEquals("D")) {
                    // Dogru
                    A.setClickable(false);
                    B.setClickable(false);
                    C.setClickable(false);
                    D.setClickable(false);
                    D.setBackgroundDrawable(getResources().getDrawable(
                            R.drawable.btrue));
                    D.startAnimation(anim_blink);

                    dogrucevap();
                } else {
                    A.setClickable(false);
                    B.setClickable(false);
                    C.setClickable(false);
                    D.setClickable(false);
                    D.setBackgroundDrawable(getResources().getDrawable(
                            R.drawable.bfalse));
                    // Yanl�s
                    D.startAnimation(anim_blink);
                    yanliscevap();
                }

                break;

        }

    }

    // -------------------------------------------------DO�RU
    // CEVAP----------------------------------------------------
    private void dogrucevap() {
        if (soundIsOn) {
            Music_D.start();
        }
        if (vibrationIsOn) {
            v.vibrate(150);
        }

        puan++;
        star++;
        if (star == 5) {
            star = 0;
            star_saved++;
            TVStar.setText("x" + String.valueOf(star_saved));
            TVStar.startAnimation(anim_blink);
            editor.putInt("star", star_saved);
            editor.commit();
        }
        new Handler().postDelayed(new Runnable() {

			/*
             * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (soundIsOn) {
                    Music_D.release();
                }
                SoruGetir();
                // close this activity

            }
        }, SPLASH_TIME_OUT);

    }

    // ----------------------------------------------------YANLI� CEVAP
    // -----------------------------------------------
    private void yanliscevap() {
        if (soundIsOn) {
            Music_Y.start();
        }
        if (vibrationIsOn) {
            v.vibrate(750);
        }
        if (puan > h_skor_saved) {
            h_skor_saved = puan;
            editor.putInt("hscore", h_skor_saved);
            editor.commit();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (soundIsOn) {
                    Music_Y.release();
                }
                // SoruGetir();
                // close this activity
                if (star_saved > 4) {
                    intent = new Intent(Main.this, YanlisDialog.class);
                    startActivityForResult(intent, 1);
                } else {
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data.getExtras().containsKey("answer")) {
            String answer = data.getExtras().getString("answer");
            if (answer.contains("yes")) {
                star_saved = star_saved - 5;
                TVStar.setText("x" + String.valueOf(star_saved));
                editor.putInt("star", star_saved);
                editor.commit();
                SoruGetir();
            } else if (answer.contains("no")) {
                star = 0;
                finish();

            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------
    @Override
    public void onAnimationEnd(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        if (puan > h_skor_saved) {
            h_skor_saved = puan;
            editor.putInt("hscore", h_skor_saved);
            editor.commit();

            // startAppAd.onBackPressed();

        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        startAppAd.onResume();
        // startAppAd.showAd(); // show the ad
        // startAppAd.loadAd();
        Log.v(TAG, " ON RESUME *****");
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (puan > h_skor_saved) {
            h_skor_saved = puan;
            editor.putInt("hscore", h_skor_saved);
            editor.commit();
        }
        finish();
        Log.v(TAG, "ON DESTROY *****");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (puan > h_skor_saved) {
            h_skor_saved = puan;
            editor.putInt("hscore", h_skor_saved);
            editor.commit();
        }
        startAppAd.onPause();
        // finish();
        Log.v(TAG, "ON PAUSE *****");
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }

}
