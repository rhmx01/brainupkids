package ma.rhmx.brainupkids;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import ma.rhmx.brainupkids.sectorat.Sectora;
import ma.rhmx.brainupkids.sectorat.Sectorb;
import ma.rhmx.brainupkids.sectorat.Sectorc;
import ma.rhmx.brainupkids.sectorat.Sectord;

/**
 * Created by RHMX on 10/03/2018.
 */

public class Sectors extends Activity  {
    Animation uptodown,downtoup,afasiy,azelmad;
TextView t1,t2,t3,t4;
    MediaPlayer click_sound;
    AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sectors);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.inter));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        t1=(TextView)findViewById(R.id.G1);
        t2=(TextView)findViewById(R.id.G2);
        t3=(TextView)findViewById(R.id.G3);
        t4=(TextView)findViewById(R.id.G4);

        click_sound = MediaPlayer.create(getApplicationContext(), R.raw.click);

        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        afasiy = AnimationUtils.loadAnimation(this,R.anim.ghofasiy);
        azelmad = AnimationUtils.loadAnimation(this,R.anim.ghozelmad);

        t1.setAnimation(uptodown);
        t2.setAnimation(afasiy);
        t3.setAnimation(azelmad);
        t4.setAnimation(downtoup);




         t1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                click_sound.start();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                mInterstitialAd.show();

                Intent gtint=new Intent(getApplicationContext(),Sectora.class);
                startActivity(gtint);
                mInterstitialAd.show();


            }
        });


          t2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                click_sound.start();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

                Intent gtint=new Intent(getApplicationContext(),Sectorb.class);
                startActivity(gtint);
                mInterstitialAd.show();


            }
        });


           t3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                click_sound.start();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

                Intent gtint=new Intent(getApplicationContext(),Sectorc.class);
                startActivity(gtint);

                mInterstitialAd.show();


            }
        });


            t4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                click_sound.start();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

                Intent gtint=new Intent(getApplicationContext(),Sectord.class);
                startActivity(gtint);

                mInterstitialAd.show();


            }
        });

    }







}
