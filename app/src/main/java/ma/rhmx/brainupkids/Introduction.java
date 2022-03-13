package ma.rhmx.brainupkids;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.ads.consent.*;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.ads.mediation.admob.AdMobAdapter;

import java.net.MalformedURLException;
import java.net.URL;

public class Introduction extends AppCompatActivity {
    ViewDialog alert = new ViewDialog();
     TextView text ;
     ImageView start,rate,email;
     Animation uptodown,afasiy,azelmad;
    MediaPlayer click_sound;
    AdView mAdView,mAdViewa;
  private   ConsentForm form;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.animat);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                setContentView(R.layout.introduction);




                ConsentInformation consentInformation = ConsentInformation.getInstance(Introduction.this);
                String[] publisherIds = {"ca-app-pub-6365946657421973"};
                consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
                    @Override
                    public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                        // User's consent status successfully updated.

                        boolean ineu = ConsentInformation.getInstance(Introduction.this).isRequestLocationInEeaOrUnknown();

                        if(ineu){
                            if(consentStatus == ConsentStatus.PERSONALIZED || consentStatus == ConsentStatus.NON_PERSONALIZED){

                                Bundle extras = new Bundle();
                                extras.putString("npa", "1");

                               PublisherAdRequest request = new PublisherAdRequest.Builder()
                                        .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                                        .build();

                            }

                            else{

                                URL privacyUrl = null;
                                try {
                                    // TODO: Replace with your app's privacy policy URL.
                                    privacyUrl = new URL("https://rhmxstudio.blogspot.com");
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                    // Handle error.
                                }
                                 form = new ConsentForm.Builder(Introduction.this, privacyUrl)
                                        .withListener(new ConsentFormListener() {
                                            @Override
                                            public void onConsentFormLoaded() {
                                                // Consent form loaded successfully.
                                                form.show();

                                            }

                                            @Override
                                            public void onConsentFormOpened() {
                                                // Consent form was displayed.
                                            }

                                            @Override
                                            public void onConsentFormClosed(
                                                    ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                                                // Consent form was closed.
                                                Bundle extras = new Bundle();
                                                extras.putString("npa", "1");

                                                PublisherAdRequest request = new PublisherAdRequest.Builder()
                                                        .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                                                        .build();

                                            }

                                            @Override
                                            public void onConsentFormError(String errorDescription) {
                                                // Consent form error.
                                            }
                                        })
                                        .withPersonalizedAdsOption()
                                        .withNonPersonalizedAdsOption()
                                        .withAdFreeOption()
                                        .build();
                                form.load();



                            }




                        }
                        else {



                        }


                    }

                    @Override
                    public void onFailedToUpdateConsentInfo(String errorDescription) {
                        // User's consent status failed to update.
                    }
                });
















                mAdView = findViewById(R.id.adView);
                mAdViewa = findViewById(R.id.adViewa);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);
                mAdViewa.loadAd(adRequest);
text= (TextView) findViewById(R.id.introduction);
start = (ImageView)findViewById(R.id.next);
rate = (ImageView)findViewById(R.id.rateus);
//moreaps = (ImageView)findViewById(R.id.moreapps);
email = (ImageView)findViewById(R.id.contactus);
        uptodown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.uptodown);
        afasiy = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.ghofasiy);
        azelmad = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.ghozelmad);


        text.setAnimation(uptodown);
        start.setAnimation(afasiy);
        rate.setAnimation(azelmad);
       // moreaps.setAnimation(afasiy);
        email.setAnimation(azelmad);


                click_sound = MediaPlayer.create(getApplicationContext(), R.raw.click);




                rate.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        click_sound.start();

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=ma.rhmx.brainupkids"));
                        startActivity(browserIntent);

                    }
                });



                email.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        click_sound.start();

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"rhmxstudio@gmail.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "BRAIN UP KIDS GK");
                        intent.putExtra(Intent.EXTRA_TEXT,"");
                        startActivity(Intent.createChooser(intent, "send E-mail"));


                    }
                });

            }
        }, 6000);



    }


    public boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else
                return false;
        } else
            return false;
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {


        }

        return super.onKeyDown(keyCode, event);
    }



    public void start (View view) {



        if (!isConnected(getApplicationContext())) {

                    click_sound.start();
                    alert.showDialog(Introduction.this);
        }

        else
        {

                    click_sound.start();
                    Intent gtint=new Intent(getApplicationContext(),Sectors.class);
                    startActivity(gtint);
        }



    }

    private void showForm(){ form.show(); }
}


final class ViewDialog {

    public void showDialog(final Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        final MediaPlayer   click_sound = MediaPlayer.create(activity, R.raw.click);

        TextView dialogButton = (TextView) dialog.findViewById(R.id.ok);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_sound.start();

                dialog.dismiss();
               // activity.finish();
              //  System.exit(0);
            }
        });

        dialog.show();

    }
}
