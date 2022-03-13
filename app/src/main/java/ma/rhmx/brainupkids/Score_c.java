package ma.rhmx.brainupkids;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import ma.rhmx.brainupkids.sectorat.Sectorb;
import ma.rhmx.brainupkids.sectorat.Sectorc;
import ma.rhmx.brainupkids.sectorat.Sectord;

/**
 * Created by RHMX on 12/04/2018.
 */

public class Score_c extends AppCompatActivity implements RewardedVideoAdListener {
    Animation uptodown,downtoup;
    LinearLayout lin;
    MediaPlayer click_sound;
    AdView mAdView,mAdViewa;
    private RewardedVideoAd mRewardedVideoAd;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.score);
        mAdView = findViewById(R.id.adView);
        mAdViewa = findViewById(R.id.adViewa);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdViewa.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.inter));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        click_sound = MediaPlayer.create(getApplicationContext(), R.raw.click);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
       // loadRewardedVideoAd();
        //mRewardedVideoAd.show();

        final TextView nxt = (TextView)findViewById(R.id.next_level);
        final TextView levels = (TextView)findViewById(R.id.levels);
        final TextView groups = (TextView)findViewById(R.id.groups);
        final TextView rate = (TextView)findViewById(R.id.rateus);

        final TextView ccc = (TextView)findViewById(R.id.crcttxt);
        final TextView vvv = (TextView)findViewById(R.id.lvltxt);
        final TextView www = (TextView)findViewById(R.id.wrngtxt);
        lin=(LinearLayout)findViewById(R.id.lin1);

        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        lin.setAnimation(downtoup);
        nxt.setAnimation(uptodown);
        levels.setAnimation(uptodown);
        groups.setAnimation(uptodown);
        rate.setAnimation(uptodown);
        Bundle extras= getIntent().getExtras();
        String lvl = extras.getString("levels");
        String crct = extras.getString("crct");

        final  int ll = Integer.parseInt(lvl);
        int crr = Integer.parseInt(crct);
        int wrg = 10 - crr;
        ccc.setText("CORRECT ANSEWRS : "+crr);
        vvv.setText("Quiz : "+lvl);
        www.setText("WRONG ANSWERS : "+wrg);
        final int lll= ll + 1;
        final String llvv = String.valueOf(lll);
        if (ll==2 || ll==5 || ll==9 || ll == 13) {
            loadRewardedVideoAd();
          //  mInterstitialAd.loadAd(new AdRequest.Builder().build());
          //  mInterstitialAd.show();

            //mRewardedVideoAd.show();

        }
        if (ll==15){
            nxt.setBackgroundResource(R.drawable.next_group);
            nxt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    click_sound.start();

                    Intent gtint = new Intent(getApplicationContext(), Sectord.class);
                    startActivity(gtint);
                }
            });
        }




        else {
            nxt.setBackgroundResource(R.drawable.next_level);

            nxt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    click_sound.start();

                    Intent gtint = new Intent(getApplicationContext(), Quistions_c.class);
                    gtint.putExtra("key", llvv);
                    startActivity(gtint);

                }
            });
        }




        groups.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                click_sound.start();
                Intent gtint = new Intent(getApplicationContext(), Sectors.class);
                startActivity(gtint);
            }
        });


        levels.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                click_sound.start();

                Intent gtint = new Intent(getApplicationContext(), Sectorc.class);
                startActivity(gtint);
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                click_sound.start();

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=ma.rhmx.brainupkids"));
                startActivity(browserIntent);

            }
        });


    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(getString(R.string.rewards),
                new AdRequest.Builder().build());
    }


    @Override
    public void onRewardedVideoAdLoaded() {
        mRewardedVideoAd.show();
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        loadRewardedVideoAd();

    }

    @Override
    public void onRewardedVideoCompleted() {
//mRewardedVideoAd.show();
    }
}
