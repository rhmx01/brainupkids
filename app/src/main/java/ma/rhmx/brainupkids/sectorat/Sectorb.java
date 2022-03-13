package ma.rhmx.brainupkids.sectorat;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import ma.rhmx.brainupkids.Quistions;
import ma.rhmx.brainupkids.Quistions_b;
import ma.rhmx.brainupkids.R;

/**
 * Created by RHMX on 15/03/2018.
 */

public class Sectorb extends Activity {
    MediaPlayer click_sound;
    AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.sectora);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        final TextView level1 = (TextView)findViewById(R.id.L1);
        final  TextView level2 = (TextView)findViewById(R.id.L2);
        final TextView level3 = (TextView)findViewById(R.id.L3);
        final TextView level4 = (TextView)findViewById(R.id.L4);
        final  TextView level5 = (TextView)findViewById(R.id.L5);
        final TextView level6 = (TextView)findViewById(R.id.L6);
        final TextView level7 = (TextView)findViewById(R.id.L7);
        final  TextView level8 = (TextView)findViewById(R.id.L8);
        final  TextView level9 = (TextView)findViewById(R.id.L9);
        final  TextView level10 = (TextView)findViewById(R.id.L10);
        final  TextView level11 = (TextView)findViewById(R.id.L11);
        final  TextView level12 = (TextView)findViewById(R.id.L12);
        final  TextView level13 = (TextView)findViewById(R.id.L13);
        final  TextView level14 = (TextView)findViewById(R.id.L14);
        final  TextView level15 = (TextView)findViewById(R.id.L15);

        level1.setBackgroundResource(R.drawable.taghzot1);
        level2.setBackgroundResource(R.drawable.ammas);
        level3.setBackgroundResource(R.drawable.taghzot2);
        level4.setBackgroundResource(R.drawable.ammas);
        level5.setBackgroundResource(R.drawable.ammas);
        level6.setBackgroundResource(R.drawable.ammas);
        level7.setBackgroundResource(R.drawable.ammas);
        level8.setBackgroundResource(R.drawable.ammas);
        level9.setBackgroundResource(R.drawable.ammas);
        level10.setBackgroundResource(R.drawable.ammas);
        level11.setBackgroundResource(R.drawable.ammas);
        level12.setBackgroundResource(R.drawable.ammas);
        level13.setBackgroundResource(R.drawable.taghzot4);
        level14.setBackgroundResource(R.drawable.ammas);
        level15.setBackgroundResource(R.drawable.taghzot3);


    }

    public void startqst (View view){

        click_sound = MediaPlayer.create(getApplicationContext(), R.raw.click);
        click_sound.start();
        String lvl;
        lvl = ((TextView) view).getText().toString();



        Intent gtint=new Intent(this,Quistions_b.class);
        gtint.putExtra("key",lvl);
        startActivity(gtint);
        Toast.makeText(getApplicationContext(),"Swipe up to choose the correct answer", Toast.LENGTH_LONG).show();

    }




}
