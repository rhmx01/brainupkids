package ma.rhmx.brainupkids;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by RHMX on 10/03/2018.
 */

public class Quistions extends Activity {
    Animation uptodown,afasiy,azelmad;
    ViewDialog2 alert = new ViewDialog2();
    MediaPlayer click_sound;
    AdView mAdView;
    private InterstitialAd mInterstitialAd;

    public Integer c =0 , q=1 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.quistions);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.inter));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        click_sound = MediaPlayer.create(getApplicationContext(), R.raw.click);

        final MediaPlayer corct = MediaPlayer.create(this, R.raw.sound_correct);
        final MediaPlayer wrng = MediaPlayer.create(this, R.raw.error);

        final TextView test = (TextView) findViewById(R.id.qstnumber);
        final TextView level = (TextView) findViewById(R.id.level);
        final TextView corect = (TextView) findViewById(R.id.numbercorrects);
        final TextView quist = (TextView) findViewById(R.id.quistion);

        final TextView ansr1 = (TextView) findViewById(R.id.ansr1);
        final TextView ansr2 = (TextView) findViewById(R.id.ansr2);
        final TextView ansr3 = (TextView) findViewById(R.id.ansr3);
        final TextView ansr4 = (TextView) findViewById(R.id.ansr4);

        final TextView next = (TextView) findViewById(R.id.next);
        final ImageView img = (ImageView) findViewById(R.id.image);

        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        afasiy = AnimationUtils.loadAnimation(this,R.anim.ghofasiy);
        azelmad = AnimationUtils.loadAnimation(this,R.anim.ghozelmad);


        Bundle extras = getIntent().getExtras();
        final String lvl = extras.getString("key");
        final int lvv = Integer.parseInt(lvl);
        level.setText("Quiz: " + lvv);


        test.setText("Question: "+q + " of 10");

        corect.setText("Correct:  " + c);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                mInterstitialAd.show();


            }
        }, 3000);

        if (lvv == 1) {
            alert.showDialog(Quistions.this);
            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);
            img.setImageResource(R.drawable.planets);

            quist.setText(getString(R.string.g1_l1_q1_text));
            ansr1.setText(getString(R.string.g1_l1_q1_option1));
            ansr2.setText(getString(R.string.g1_l1_q1_option2));
            ansr3.setText(getString(R.string.g1_l1_q1_option3));
            ansr4.setText(getString(R.string.g1_l1_q1_option4));


            // correct anser -----------------------
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr3.setTextColor(Color.GREEN);
                    c++;
                    ansr3.setClickable(false);
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            //---------------------------

            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr3.setTextColor(Color.GREEN);
                    ansr2.setTextColor(Color.RED);

                    ansr3.setClickable(false);
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr3.setTextColor(Color.GREEN);
                    ansr1.setTextColor(Color.RED);

                    ansr3.setClickable(false);
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr3.setTextColor(Color.GREEN);
                    ansr4.setTextColor(Color.RED);

                    ansr3.setClickable(false);
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr4.setClickable(false);
                }
            });


            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    click_sound.start();
                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setAnimation(afasiy);
                    ansr2.setAnimation(azelmad);
                    ansr3.setAnimation(afasiy);
                    ansr4.setAnimation(azelmad);
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.fish);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l1_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l1_q2_option1));
                    ansr2.setText(getString(R.string.g1_l1_q2_option2));
                    ansr3.setText(getString(R.string.g1_l1_q2_option3));
                    ansr4.setText(getString(R.string.g1_l1_q2_option4));

                    // correct anser -----------------------
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr3.setTextColor(Color.GREEN);
                            c++;
                            ansr3.setClickable(false);
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr3.setTextColor(Color.GREEN);
                            ansr2.setTextColor(Color.RED);

                            ansr3.setClickable(false);
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr3.setTextColor(Color.GREEN);
                            ansr1.setTextColor(Color.RED);

                            ansr3.setClickable(false);
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr3.setTextColor(Color.GREEN);
                            ansr4.setTextColor(Color.RED);

                            ansr3.setClickable(false);
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });


                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.q3);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l1_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l1_q3_option1));
                            ansr2.setText(getString(R.string.g1_l1_q3_option2));
                            ansr3.setText(getString(R.string.g1_l1_q3_option3));
                            ansr4.setText(getString(R.string.g1_l1_q3_option4));


                            // correct anser -----------------------
                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr1.setTextColor(Color.GREEN);
                                    c++;
                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr1.setTextColor(Color.GREEN);
                                    ansr2.setTextColor(Color.RED);

                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr1.setTextColor(Color.GREEN);
                                    ansr3.setTextColor(Color.RED);

                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr1.setTextColor(Color.GREEN);
                                    ansr4.setTextColor(Color.RED);

                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.q4);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l1_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l1_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l1_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l1_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l1_q4_option4));


                                    // correct anser -----------------------
                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            c++;
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            ansr1.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            ansr3.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr2.setTextColor(Color.GREEN);
                                            ansr4.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.q5);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l1_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l1_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l1_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l1_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l1_q5_option4));


                                            // correct anser -----------------------
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr2.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr1.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr4.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });


                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.morroco);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l1_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l1_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l1_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l1_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l1_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr2.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });
                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr1.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });


                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.ibird3);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l1_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l1_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l1_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l1_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l1_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr1.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr3.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr4.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);


                                                                            img.setImageResource(R.drawable.earth);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l1_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l1_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l1_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l1_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l1_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr2.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr3.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l1_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l1_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l1_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l1_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l1_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr1.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr4.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });


                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l1_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l1_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l1_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l1_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l1_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr2.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr4.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });

        }

//-------------------------------------------------------------------------------------

        if (lvv == 2) {
            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);


            quist.setText("Q : " + getString(R.string.g1_l2_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l2_q1_option1));
            ansr2.setText(getString(R.string.g1_l2_q1_option2));
            ansr3.setText(getString(R.string.g1_l2_q1_option3));
            ansr4.setText(getString(R.string.g1_l2_q1_option4));
            img.setImageResource(R.drawable.earth);


            // correct anser -----------------------
            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr2.setTextColor(Color.GREEN);
                    c++;
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            //---------------------------

            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr2.setTextColor(Color.GREEN);
                    ansr1.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr2.setTextColor(Color.GREEN);
                    ansr3.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr2.setTextColor(Color.GREEN);
                    ansr4.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });


            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.veagetables);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l2_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l2_q2_option1));
                    ansr2.setText(getString(R.string.g1_l2_q2_option2));
                    ansr3.setText(getString(R.string.g1_l2_q2_option3));
                    ansr4.setText(getString(R.string.g1_l2_q2_option4));


                    // correct anser -----------------------
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr3.setTextColor(Color.GREEN);
                            c++;
                            ansr3.setClickable(false);
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr3.setTextColor(Color.GREEN);
                            ansr2.setTextColor(Color.RED);

                            ansr3.setClickable(false);
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr3.setTextColor(Color.GREEN);
                            ansr1.setTextColor(Color.RED);

                            ansr3.setClickable(false);
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr3.setTextColor(Color.GREEN);
                            ansr4.setTextColor(Color.RED);

                            ansr3.setClickable(false);
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });


                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l2_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l2_q3_option1));
                            ansr2.setText(getString(R.string.g1_l2_q3_option2));
                            ansr3.setText(getString(R.string.g1_l2_q3_option3));
                            ansr4.setText(getString(R.string.g1_l2_q3_option4));


                            // correct anser -----------------------
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr4.setTextColor(Color.GREEN);
                                    c++;
                                    ansr4.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr1.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr4.setTextColor(Color.GREEN);
                                    ansr2.setTextColor(Color.RED);

                                    ansr4.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr1.setClickable(false);
                                }
                            });
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr4.setTextColor(Color.GREEN);
                                    ansr3.setTextColor(Color.RED);

                                    ansr4.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr1.setClickable(false);
                                }
                            });
                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr4.setTextColor(Color.GREEN);
                                    ansr1.setTextColor(Color.RED);

                                    ansr4.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr1.setClickable(false);
                                }
                            });

                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l2_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l2_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l2_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l2_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l2_q4_option4));


                                    // correct anser -----------------------
                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            c++;
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            ansr1.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            ansr3.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr2.setTextColor(Color.GREEN);
                                            ansr4.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.l2_q5);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l2_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l2_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l2_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l2_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l2_q5_option4));


                                            // correct anser -----------------------
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr2.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr1.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr4.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });


                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);

                                                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                                    mInterstitialAd.show();
                                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l2_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l2_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l2_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l2_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l2_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr1.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr4.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l2_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l2_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l2_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l2_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l2_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr2.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr3.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr4.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);


                                                                            img.setImageResource(R.drawable.fish);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l2_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l2_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l2_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l2_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l2_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr2.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr3.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.earth);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l2_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l2_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l2_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l2_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l2_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr1.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr4.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });


                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.sport);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l2_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l2_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l2_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l2_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l2_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr2.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr2.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr2.setTextColor(Color.GREEN);
                                                                                            ansr1.setTextColor(Color.RED);

                                                                                            ansr2.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr2.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr2.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr2.setTextColor(Color.GREEN);
                                                                                            ansr4.setTextColor(Color.RED);

                                                                                            ansr2.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });

        }


        //-------------------------------------------------------------------------------------

        if (lvv == 3) {
            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);



            quist.setText("Q : " + getString(R.string.g1_l3_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l3_q1_option1));
            ansr2.setText(getString(R.string.g1_l3_q1_option2));
            ansr3.setText(getString(R.string.g1_l3_q1_option3));
            ansr4.setText(getString(R.string.g1_l3_q1_option4));

            img.setImageResource(R.drawable.fish);

            // correct anser -----------------------
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr4.setTextColor(Color.GREEN);
                    c++;
                    ansr4.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr1.setClickable(false);
                }
            });

            //---------------------------

            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr4.setTextColor(Color.GREEN);
                    ansr2.setTextColor(Color.RED);

                    ansr4.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr1.setClickable(false);
                }
            });
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr4.setTextColor(Color.GREEN);
                    ansr3.setTextColor(Color.RED);

                    ansr4.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr1.setClickable(false);
                }
            });
            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr4.setTextColor(Color.GREEN);
                    ansr1.setTextColor(Color.RED);

                    ansr4.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr1.setClickable(false);
                }
            });


            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.birds);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l3_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l3_q2_option1));
                    ansr2.setText(getString(R.string.g1_l3_q2_option2));
                    ansr3.setText(getString(R.string.g1_l3_q2_option3));
                    ansr4.setText(getString(R.string.g1_l3_q2_option4));


                    // correct anser -----------------------
                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr2.setTextColor(Color.GREEN);
                            c++;
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr2.setTextColor(Color.GREEN);
                            ansr1.setTextColor(Color.RED);

                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr2.setTextColor(Color.GREEN);
                            ansr3.setTextColor(Color.RED);

                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr2.setTextColor(Color.GREEN);
                            ansr4.setTextColor(Color.RED);

                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {                    click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.l3_q3);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l3_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l3_q3_option1));
                            ansr2.setText(getString(R.string.g1_l3_q3_option2));
                            ansr3.setText(getString(R.string.g1_l3_q3_option3));
                            ansr4.setText(getString(R.string.g1_l3_q3_option4));


                            // correct anser -----------------------
                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr2.setTextColor(Color.GREEN);
                                    c++;
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr2.setTextColor(Color.GREEN);
                                    ansr1.setTextColor(Color.RED);

                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr2.setTextColor(Color.GREEN);
                                    ansr3.setTextColor(Color.RED);

                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr2.setTextColor(Color.GREEN);
                                    ansr4.setTextColor(Color.RED);

                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.earth);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l3_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l3_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l3_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l3_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l3_q4_option4));


                                    // correct anser -----------------------
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr4.setTextColor(Color.GREEN);
                                            c++;
                                            ansr4.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr1.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr4.setTextColor(Color.GREEN);
                                            ansr2.setTextColor(Color.RED);

                                            ansr4.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr1.setClickable(false);
                                        }
                                    });
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr4.setTextColor(Color.GREEN);
                                            ansr3.setTextColor(Color.RED);

                                            ansr4.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr1.setClickable(false);
                                        }
                                    });
                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr4.setTextColor(Color.GREEN);
                                            ansr1.setTextColor(Color.RED);

                                            ansr4.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr1.setClickable(false);
                                        }
                                    });


                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {                    click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.l3_q5);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l3_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l3_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l3_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l3_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l3_q5_option4));


                                            // correct anser -----------------------
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr4.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr4.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr1.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr4.setTextColor(Color.GREEN);
                                                    ansr2.setTextColor(Color.RED);

                                                    ansr4.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr1.setClickable(false);
                                                }
                                            });
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr4.setTextColor(Color.GREEN);
                                                    ansr3.setTextColor(Color.RED);

                                                    ansr4.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr1.setClickable(false);
                                                }
                                            });
                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr4.setTextColor(Color.GREEN);
                                                    ansr1.setTextColor(Color.RED);

                                                    ansr4.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr1.setClickable(false);
                                                }
                                            });


                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l3_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l3_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l3_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l3_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l3_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr1.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr4.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l3_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l3_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l3_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l3_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l3_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr1.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr3.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr4.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);


                                                                            img.setImageResource(R.drawable.road);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l3_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l3_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l3_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l3_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l3_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr2.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr2.setTextColor(Color.GREEN);
                                                                            ansr1.setTextColor(Color.RED);

                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr2.setTextColor(Color.GREEN);
                                                                            ansr3.setTextColor(Color.RED);

                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr2.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l3_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l3_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l3_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l3_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l3_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr1.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr1.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr1.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr1.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr1.setTextColor(Color.GREEN);
                                                                                    ansr3.setTextColor(Color.RED);

                                                                                    ansr1.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr1.setTextColor(Color.GREEN);
                                                                                    ansr4.setTextColor(Color.RED);

                                                                                    ansr1.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });


                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.planets);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l3_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l3_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l3_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l3_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l3_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr2.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr1.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });


                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });
        }
        //-------------------------------------------------------------------------------------

        if (lvv == 4) {
            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);



            quist.setText("Q : " + getString(R.string.g1_l4_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l4_q1_option1));
            ansr2.setText(getString(R.string.g1_l4_q1_option2));
            ansr3.setText(getString(R.string.g1_l4_q1_option3));
            ansr4.setText(getString(R.string.g1_l4_q1_option4));
            img.setImageResource(R.drawable.fruit);


            // correct anser -----------------------
            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr1.setTextColor(Color.GREEN);
                    c++;
                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            //---------------------------

            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr1.setTextColor(Color.GREEN);
                    ansr2.setTextColor(Color.RED);

                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr1.setTextColor(Color.GREEN);
                    ansr3.setTextColor(Color.RED);

                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr1.setTextColor(Color.GREEN);
                    ansr4.setTextColor(Color.RED);

                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.veagetables);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l4_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l4_q2_option1));
                    ansr2.setText(getString(R.string.g1_l4_q2_option2));
                    ansr3.setText(getString(R.string.g1_l4_q2_option3));
                    ansr4.setText(getString(R.string.g1_l4_q2_option4));


                    // correct anser -----------------------
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr3.setTextColor(Color.GREEN);
                            c++;
                            ansr3.setClickable(false);
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr3.setTextColor(Color.GREEN);
                            ansr2.setTextColor(Color.RED);

                            ansr3.setClickable(false);
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr3.setTextColor(Color.GREEN);
                            ansr1.setTextColor(Color.RED);

                            ansr3.setClickable(false);
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr3.setTextColor(Color.GREEN);
                            ansr4.setTextColor(Color.RED);

                            ansr3.setClickable(false);
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });


                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {                    click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.earth);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l4_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l4_q3_option1));
                            ansr2.setText(getString(R.string.g1_l4_q3_option2));
                            ansr3.setText(getString(R.string.g1_l4_q3_option3));
                            ansr4.setText(getString(R.string.g1_l4_q3_option4));


                            // correct anser -----------------------
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    c++;
                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    ansr2.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    ansr1.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr3.setTextColor(Color.GREEN);
                                    ansr4.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.currency);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l4_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l4_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l4_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l4_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l4_q4_option4));


                                    // correct anser -----------------------
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr4.setTextColor(Color.GREEN);
                                            c++;
                                            ansr4.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr1.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr4.setTextColor(Color.GREEN);
                                            ansr2.setTextColor(Color.RED);

                                            ansr4.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr1.setClickable(false);
                                        }
                                    });
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr4.setTextColor(Color.GREEN);
                                            ansr3.setTextColor(Color.RED);

                                            ansr4.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr1.setClickable(false);
                                        }
                                    });
                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr4.setTextColor(Color.GREEN);
                                            ansr1.setTextColor(Color.RED);

                                            ansr4.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr1.setClickable(false);
                                        }
                                    });


                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {                    click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.animal_baby);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l4_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l4_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l4_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l4_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l4_q5_option4));


                                            // correct anser -----------------------
                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr2.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr2.setTextColor(Color.GREEN);
                                                    ansr1.setTextColor(Color.RED);

                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr2.setTextColor(Color.GREEN);
                                                    ansr3.setTextColor(Color.RED);

                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr2.setTextColor(Color.GREEN);
                                                    ansr4.setTextColor(Color.RED);

                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.transport);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l4_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l4_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l4_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l4_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l4_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr1.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr1.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr1.setTextColor(Color.GREEN);
                                                            ansr2.setTextColor(Color.RED);

                                                            ansr1.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr1.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr1.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr1.setTextColor(Color.GREEN);
                                                            ansr4.setTextColor(Color.RED);

                                                            ansr1.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });


                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l4_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l4_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l4_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l4_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l4_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr1.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr3.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr4.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);
                                                                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                                                    mInterstitialAd.show();

                                                                            img.setImageResource(R.drawable.transport);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l4_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l4_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l4_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l4_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l4_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr2.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr1.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });


                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l4_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l4_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l4_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l4_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l4_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr1.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr4.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });


                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.l4_q10);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l4_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l4_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l4_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l4_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l4_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr2.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr1.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });


                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });
        }
        //-------------------------------------------------------------------------------------

        if (lvv == 5) {
            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);


            quist.setText("Q : " + getString(R.string.g1_l5_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l5_q1_option1));
            ansr2.setText(getString(R.string.g1_l5_q1_option2));
            ansr3.setText(getString(R.string.g1_l5_q1_option3));
            ansr4.setText(getString(R.string.g1_l5_q1_option4));
            img.setImageResource(R.drawable.defult_think);


            // correct anser -----------------------
            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr2.setTextColor(Color.GREEN);
                    c++;
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            //---------------------------

            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr2.setTextColor(Color.GREEN);
                    ansr1.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr2.setTextColor(Color.GREEN);
                    ansr3.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr2.setTextColor(Color.GREEN);
                    ansr4.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });


            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.birds);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l5_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l5_q2_option1));
                    ansr2.setText(getString(R.string.g1_l5_q2_option2));
                    ansr3.setText(getString(R.string.g1_l5_q2_option3));
                    ansr4.setText(getString(R.string.g1_l5_q2_option4));


                    // correct anser -----------------------
                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr1.setTextColor(Color.GREEN);
                            c++;
                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr1.setTextColor(Color.GREEN);
                            ansr2.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr1.setTextColor(Color.GREEN);
                            ansr3.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr1.setTextColor(Color.GREEN);
                            ansr4.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {                    click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.l5_q3);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l5_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l5_q3_option1));
                            ansr2.setText(getString(R.string.g1_l5_q3_option2));
                            ansr3.setText(getString(R.string.g1_l5_q3_option3));
                            ansr4.setText(getString(R.string.g1_l5_q3_option4));


                            // correct anser -----------------------
                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr1.setTextColor(Color.GREEN);
                                    c++;
                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr1.setTextColor(Color.GREEN);
                                    ansr2.setTextColor(Color.RED);

                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr1.setTextColor(Color.GREEN);
                                    ansr3.setTextColor(Color.RED);

                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr1.setTextColor(Color.GREEN);
                                    ansr4.setTextColor(Color.RED);

                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.computer);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l5_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l5_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l5_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l5_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l5_q4_option4));


                                    // correct anser -----------------------
                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            c++;
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            ansr1.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            ansr3.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr2.setTextColor(Color.GREEN);
                                            ansr4.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {                    click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.l5_q5);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l5_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l5_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l5_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l5_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l5_q5_option4));


                                            // correct anser -----------------------
                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr1.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr1.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr1.setTextColor(Color.GREEN);
                                                    ansr2.setTextColor(Color.RED);

                                                    ansr1.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr1.setTextColor(Color.GREEN);
                                                    ansr3.setTextColor(Color.RED);

                                                    ansr1.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr1.setTextColor(Color.GREEN);
                                                    ansr4.setTextColor(Color.RED);

                                                    ansr1.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l5_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l5_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l5_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l5_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l5_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr2.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });
                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr1.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });


                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l5_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l5_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l5_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l5_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l5_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr2.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr3.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr4.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);


                                                                            img.setImageResource(R.drawable.math);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l5_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l5_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l5_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l5_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l5_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr2.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr1.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });


                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");
                                                                            mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                                                            mInterstitialAd.show();
                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l5_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l5_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l5_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l5_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l5_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr1.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr4.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });


                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.planets);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l5_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l5_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l5_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l5_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l5_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr2.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr1.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });


                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });

        }
        //-------------------------------------------------------------------------------------

        if (lvv == 6) {

            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);

            quist.setText("Q : " + getString(R.string.g1_l6_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l6_q1_option1));
            ansr2.setText(getString(R.string.g1_l6_q1_option2));
            ansr3.setText(getString(R.string.g1_l6_q1_option3));
            ansr4.setText(getString(R.string.g1_l6_q1_option4));

            img.setImageResource(R.drawable.fruit);

            // correct anser -----------------------
            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr2.setTextColor(Color.GREEN);
                    c++;
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            //---------------------------

            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr2.setTextColor(Color.GREEN);
                    ansr1.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr2.setTextColor(Color.GREEN);
                    ansr3.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr2.setTextColor(Color.GREEN);
                    ansr4.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.veagetables);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l6_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l6_q2_option1));
                    ansr2.setText(getString(R.string.g1_l6_q2_option2));
                    ansr3.setText(getString(R.string.g1_l6_q2_option3));
                    ansr4.setText(getString(R.string.g1_l6_q2_option4));


                    // correct anser -----------------------
                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr2.setTextColor(Color.GREEN);
                            c++;
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr2.setTextColor(Color.GREEN);
                            ansr1.setTextColor(Color.RED);

                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr2.setTextColor(Color.GREEN);
                            ansr3.setTextColor(Color.RED);

                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr2.setTextColor(Color.GREEN);
                            ansr4.setTextColor(Color.RED);

                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });


                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {                    click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.currency);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l6_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l6_q3_option1));
                            ansr2.setText(getString(R.string.g1_l6_q3_option2));
                            ansr3.setText(getString(R.string.g1_l6_q3_option3));
                            ansr4.setText(getString(R.string.g1_l6_q3_option4));


                            // correct anser -----------------------
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr4.setTextColor(Color.GREEN);
                                    c++;
                                    ansr4.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr1.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr4.setTextColor(Color.GREEN);
                                    ansr2.setTextColor(Color.RED);

                                    ansr4.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr1.setClickable(false);
                                }
                            });
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr4.setTextColor(Color.GREEN);
                                    ansr3.setTextColor(Color.RED);

                                    ansr4.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr1.setClickable(false);
                                }
                            });
                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr4.setTextColor(Color.GREEN);
                                    ansr1.setTextColor(Color.RED);

                                    ansr4.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr1.setClickable(false);
                                }
                            });

                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l6_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l6_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l6_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l6_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l6_q4_option4));


                                    // correct anser -----------------------
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr3.setTextColor(Color.GREEN);
                                            c++;
                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr3.setTextColor(Color.GREEN);
                                            ansr2.setTextColor(Color.RED);

                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr3.setTextColor(Color.GREEN);
                                            ansr1.setTextColor(Color.RED);

                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr3.setTextColor(Color.GREEN);
                                            ansr4.setTextColor(Color.RED);

                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {                    click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.animal_baby);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l6_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l6_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l6_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l6_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l6_q5_option4));


                                            // correct anser -----------------------
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr2.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr1.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr4.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });


                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.transport);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l6_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l6_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l6_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l6_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l6_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr2.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });
                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr1.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });


                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l6_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l6_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l6_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l6_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l6_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr2.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr3.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr4.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);


                                                                            img.setImageResource(R.drawable.planets);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l6_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l6_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l6_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l6_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l6_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr2.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr1.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });


                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l6_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l6_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l6_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l6_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l6_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr1.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr4.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });


                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);

                                                                                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                                                                    mInterstitialAd.show();
                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.sport);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l6_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l6_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l6_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l6_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l6_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr2.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr4.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });
        }
        //-------------------------------------------------------------------------------------

        if (lvv == 7) {
            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);
            quist.setText("Q : " + getString(R.string.g1_l7_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l7_q1_option1));
            ansr2.setText(getString(R.string.g1_l7_q1_option2));
            ansr3.setText(getString(R.string.g1_l7_q1_option3));
            ansr4.setText(getString(R.string.g1_l7_q1_option4));

            img.setImageResource(R.drawable.animal_baby);

            // correct anser -----------------------
            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr1.setTextColor(Color.GREEN);
                    c++;
                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            //---------------------------

            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr1.setTextColor(Color.GREEN);
                    ansr2.setTextColor(Color.RED);

                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr1.setTextColor(Color.GREEN);
                    ansr3.setTextColor(Color.RED);

                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr1.setTextColor(Color.GREEN);
                    ansr4.setTextColor(Color.RED);

                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.birds);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l7_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l7_q2_option1));
                    ansr2.setText(getString(R.string.g1_l7_q2_option2));
                    ansr3.setText(getString(R.string.g1_l7_q2_option3));
                    ansr4.setText(getString(R.string.g1_l7_q2_option4));


                    // correct anser -----------------------
                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr1.setTextColor(Color.GREEN);
                            c++;
                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr1.setTextColor(Color.GREEN);
                            ansr2.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr1.setTextColor(Color.GREEN);
                            ansr3.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr1.setTextColor(Color.GREEN);
                            ansr4.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {                    click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.earth);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l7_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l7_q3_option1));
                            ansr2.setText(getString(R.string.g1_l7_q3_option2));
                            ansr3.setText(getString(R.string.g1_l7_q3_option3));
                            ansr4.setText(getString(R.string.g1_l7_q3_option4));


                            // correct anser -----------------------
                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr1.setTextColor(Color.GREEN);
                                    c++;
                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr1.setTextColor(Color.GREEN);
                                    ansr2.setTextColor(Color.RED);

                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr1.setTextColor(Color.GREEN);
                                    ansr3.setTextColor(Color.RED);

                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr1.setTextColor(Color.GREEN);
                                    ansr4.setTextColor(Color.RED);

                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.computer);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l7_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l7_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l7_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l7_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l7_q4_option4));


                                    // correct anser -----------------------
                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr1.setTextColor(Color.GREEN);
                                            c++;
                                            ansr1.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr1.setTextColor(Color.GREEN);
                                            ansr2.setTextColor(Color.RED);

                                            ansr1.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr1.setTextColor(Color.GREEN);
                                            ansr3.setTextColor(Color.RED);

                                            ansr1.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr1.setTextColor(Color.GREEN);
                                            ansr4.setTextColor(Color.RED);

                                            ansr1.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {                    click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.q6);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l7_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l7_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l7_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l7_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l7_q5_option4));


                                            // correct anser -----------------------
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr4.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr4.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr1.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr4.setTextColor(Color.GREEN);
                                                    ansr2.setTextColor(Color.RED);

                                                    ansr4.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr1.setClickable(false);
                                                }
                                            });
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr4.setTextColor(Color.GREEN);
                                                    ansr3.setTextColor(Color.RED);

                                                    ansr4.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr1.setClickable(false);
                                                }
                                            });
                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr4.setTextColor(Color.GREEN);
                                                    ansr1.setTextColor(Color.RED);

                                                    ansr4.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr1.setClickable(false);
                                                }
                                            });


                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.l7_q6);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l7_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l7_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l7_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l7_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l7_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr1.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr1.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr1.setTextColor(Color.GREEN);
                                                            ansr2.setTextColor(Color.RED);

                                                            ansr1.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr1.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr1.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr1.setTextColor(Color.GREEN);
                                                            ansr4.setTextColor(Color.RED);

                                                            ansr1.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.animal_baby);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l7_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l7_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l7_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l7_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l7_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr3.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr3.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr3.setTextColor(Color.GREEN);
                                                                    ansr2.setTextColor(Color.RED);

                                                                    ansr3.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr3.setTextColor(Color.GREEN);
                                                                    ansr1.setTextColor(Color.RED);

                                                                    ansr3.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr3.setTextColor(Color.GREEN);
                                                                    ansr4.setTextColor(Color.RED);

                                                                    ansr3.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });


                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);


                                                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l7_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l7_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l7_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l7_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l7_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr2.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr1.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });


                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l7_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l7_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l7_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l7_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l7_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    ansr3.setTextColor(Color.RED);

                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    ansr1.setTextColor(Color.RED);

                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });


                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.planets);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l7_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l7_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l7_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l7_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l7_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr2.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr4.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });

        }
        //-------------------------------------------------------------------------------------

        if (lvv == 8) {
            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);
            quist.setText("Q : " + getString(R.string.g1_l8_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l8_q1_option1));
            ansr2.setText(getString(R.string.g1_l8_q1_option2));
            ansr3.setText(getString(R.string.g1_l8_q1_option3));
            ansr4.setText(getString(R.string.g1_l8_q1_option4));

            img.setImageResource(R.drawable.fruit);

            // correct anser -----------------------
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr3.setTextColor(Color.GREEN);
                    c++;
                    ansr3.setClickable(false);
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            //---------------------------

            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr3.setTextColor(Color.GREEN);
                    ansr2.setTextColor(Color.RED);

                    ansr3.setClickable(false);
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr3.setTextColor(Color.GREEN);
                    ansr1.setTextColor(Color.RED);

                    ansr3.setClickable(false);
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr3.setTextColor(Color.GREEN);
                    ansr4.setTextColor(Color.RED);

                    ansr3.setClickable(false);
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr4.setClickable(false);
                }
            });


            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.veagetables);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l8_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l8_q2_option1));
                    ansr2.setText(getString(R.string.g1_l8_q2_option2));
                    ansr3.setText(getString(R.string.g1_l8_q2_option3));
                    ansr4.setText(getString(R.string.g1_l8_q2_option4));


                    // correct anser -----------------------
                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr1.setTextColor(Color.GREEN);
                            c++;
                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr1.setTextColor(Color.GREEN);
                            ansr2.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr1.setTextColor(Color.GREEN);
                            ansr3.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr1.setTextColor(Color.GREEN);
                            ansr4.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {                    click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l8_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l8_q3_option1));
                            ansr2.setText(getString(R.string.g1_l8_q3_option2));
                            ansr3.setText(getString(R.string.g1_l8_q3_option3));
                            ansr4.setText(getString(R.string.g1_l8_q3_option4));


                            // correct anser -----------------------
                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr2.setTextColor(Color.GREEN);
                                    c++;
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr2.setTextColor(Color.GREEN);
                                    ansr1.setTextColor(Color.RED);

                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr2.setTextColor(Color.GREEN);
                                    ansr3.setTextColor(Color.RED);

                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr2.setTextColor(Color.GREEN);
                                    ansr4.setTextColor(Color.RED);

                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l8_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l8_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l8_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l8_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l8_q4_option4));


                                    // correct anser -----------------------
                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            c++;
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            ansr1.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            ansr3.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr2.setTextColor(Color.GREEN);
                                            ansr4.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {                    click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.currency);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l8_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l8_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l8_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l8_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l8_q5_option4));


                                            // correct anser -----------------------
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr2.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr1.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr4.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });


                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.flags);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l8_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l8_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l8_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l8_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l8_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr2.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });
                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr1.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });


                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.flags);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l8_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l8_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l8_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l8_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l8_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr1.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr3.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr4.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);


                                                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l8_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l8_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l8_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l8_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l8_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr2.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr3.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l8_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l8_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l8_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l8_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l8_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    ansr3.setTextColor(Color.RED);

                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    ansr1.setTextColor(Color.RED);

                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });


                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);
                                                                                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                                                                    mInterstitialAd.show();

                                                                                            img.setImageResource(R.drawable.planets);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l8_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l8_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l8_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l8_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l8_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr2.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr1.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });


                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });

        }
        //-------------------------------------------------------------------------------------

        if (lvv == 9) {

            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);
            quist.setText("Q : " + getString(R.string.g1_l9_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l9_q1_option1));
            ansr2.setText(getString(R.string.g1_l9_q1_option2));
            ansr3.setText(getString(R.string.g1_l9_q1_option3));
            ansr4.setText(getString(R.string.g1_l9_q1_option4));

            img.setImageResource(R.drawable.animal_baby);

            // correct anser -----------------------
            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr2.setTextColor(Color.GREEN);
                    c++;
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            //---------------------------

            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr2.setTextColor(Color.GREEN);
                    ansr1.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr2.setTextColor(Color.GREEN);
                    ansr3.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr2.setTextColor(Color.GREEN);
                    ansr4.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.birds);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l9_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l9_q2_option1));
                    ansr2.setText(getString(R.string.g1_l9_q2_option2));
                    ansr3.setText(getString(R.string.g1_l9_q2_option3));
                    ansr4.setText(getString(R.string.g1_l9_q2_option4));


                    // correct anser -----------------------
                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr1.setTextColor(Color.GREEN);
                            c++;
                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr1.setTextColor(Color.GREEN);
                            ansr2.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr1.setTextColor(Color.GREEN);
                            ansr3.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr1.setTextColor(Color.GREEN);
                            ansr4.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {                    click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.earth);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l9_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l9_q3_option1));
                            ansr2.setText(getString(R.string.g1_l9_q3_option2));
                            ansr3.setText(getString(R.string.g1_l9_q3_option3));
                            ansr4.setText(getString(R.string.g1_l9_q3_option4));


                            // correct anser -----------------------
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr4.setTextColor(Color.GREEN);
                                    c++;
                                    ansr4.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr1.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr4.setTextColor(Color.GREEN);
                                    ansr2.setTextColor(Color.RED);

                                    ansr4.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr1.setClickable(false);
                                }
                            });
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr4.setTextColor(Color.GREEN);
                                    ansr3.setTextColor(Color.RED);

                                    ansr4.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr1.setClickable(false);
                                }
                            });
                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr4.setTextColor(Color.GREEN);
                                    ansr1.setTextColor(Color.RED);

                                    ansr4.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr1.setClickable(false);
                                }
                            });

                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.computer);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l9_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l9_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l9_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l9_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l9_q4_option4));


                                    // correct anser -----------------------
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr3.setTextColor(Color.GREEN);
                                            c++;
                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr3.setTextColor(Color.GREEN);
                                            ansr2.setTextColor(Color.RED);

                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr3.setTextColor(Color.GREEN);
                                            ansr1.setTextColor(Color.RED);

                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr3.setTextColor(Color.GREEN);
                                            ansr4.setTextColor(Color.RED);

                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });


                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {                    click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.flags);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l9_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l9_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l9_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l9_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l9_q5_option4));


                                            // correct anser -----------------------
                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr1.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr1.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr1.setTextColor(Color.GREEN);
                                                    ansr2.setTextColor(Color.RED);

                                                    ansr1.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr1.setTextColor(Color.GREEN);
                                                    ansr3.setTextColor(Color.RED);

                                                    ansr1.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr1.setTextColor(Color.GREEN);
                                                    ansr4.setTextColor(Color.RED);

                                                    ansr1.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l9_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l9_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l9_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l9_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l9_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr1.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr4.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l9_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l9_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l9_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l9_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l9_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr2.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr3.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr4.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);
                                                                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                                                    mInterstitialAd.show();

                                                                            img.setImageResource(R.drawable.computer);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l9_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l9_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l9_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l9_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l9_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr2.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr3.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l9_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l9_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l9_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l9_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l9_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr1.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr4.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });


                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.fruit);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l9_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l9_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l9_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l9_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l9_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr2.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr4.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });


                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });
        }
        //-------------------------------------------------------------------------------------

        if (lvv == 10) {

            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);
            quist.setText("Q : " + getString(R.string.g1_l10_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l10_q1_option1));
            ansr2.setText(getString(R.string.g1_l10_q1_option2));
            ansr3.setText(getString(R.string.g1_l10_q1_option3));
            ansr4.setText(getString(R.string.g1_l10_q1_option4));

            img.setImageResource(R.drawable.flags);

            // correct anser -----------------------
            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr1.setTextColor(Color.GREEN);
                    c++;
                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            //---------------------------

            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr1.setTextColor(Color.GREEN);
                    ansr2.setTextColor(Color.RED);

                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr1.setTextColor(Color.GREEN);
                    ansr3.setTextColor(Color.RED);

                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr1.setTextColor(Color.GREEN);
                    ansr4.setTextColor(Color.RED);

                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.currency);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l10_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l10_q2_option1));
                    ansr2.setText(getString(R.string.g1_l10_q2_option2));
                    ansr3.setText(getString(R.string.g1_l10_q2_option3));
                    ansr4.setText(getString(R.string.g1_l10_q2_option4));


                    // correct anser -----------------------
                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr2.setTextColor(Color.GREEN);
                            c++;
                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr2.setTextColor(Color.GREEN);
                            ansr1.setTextColor(Color.RED);

                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr2.setTextColor(Color.GREEN);
                            ansr3.setTextColor(Color.RED);

                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr2.setTextColor(Color.GREEN);
                            ansr4.setTextColor(Color.RED);

                            ansr2.setClickable(false);
                            ansr1.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {                    click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.flags);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l10_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l10_q3_option1));
                            ansr2.setText(getString(R.string.g1_l10_q3_option2));
                            ansr3.setText(getString(R.string.g1_l10_q3_option3));
                            ansr4.setText(getString(R.string.g1_l10_q3_option4));


                            // correct anser -----------------------
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    c++;
                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    ansr2.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    ansr1.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr3.setTextColor(Color.GREEN);
                                    ansr4.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l10_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l10_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l10_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l10_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l10_q4_option4));


                                    // correct anser -----------------------
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr3.setTextColor(Color.GREEN);
                                            c++;
                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr3.setTextColor(Color.GREEN);
                                            ansr2.setTextColor(Color.RED);

                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr3.setTextColor(Color.GREEN);
                                            ansr1.setTextColor(Color.RED);

                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr3.setTextColor(Color.GREEN);
                                            ansr4.setTextColor(Color.RED);

                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {                    click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.animal_baby);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l10_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l10_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l10_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l10_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l10_q5_option4));


                                            // correct anser -----------------------
                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr2.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr2.setTextColor(Color.GREEN);
                                                    ansr1.setTextColor(Color.RED);

                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr2.setTextColor(Color.GREEN);
                                                    ansr3.setTextColor(Color.RED);

                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr2.setTextColor(Color.GREEN);
                                                    ansr4.setTextColor(Color.RED);

                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.transport);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l10_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l10_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l10_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l10_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l10_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr1.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr4.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.computer);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l10_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l10_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l10_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l10_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l10_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr4.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr4.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr4.setTextColor(Color.GREEN);
                                                                    ansr2.setTextColor(Color.RED);

                                                                    ansr4.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                }
                                                            });
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr4.setTextColor(Color.GREEN);
                                                                    ansr3.setTextColor(Color.RED);

                                                                    ansr4.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                }
                                                            });
                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr4.setTextColor(Color.GREEN);
                                                                    ansr1.setTextColor(Color.RED);

                                                                    ansr4.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                }
                                                            });


                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);


                                                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l10_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l10_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l10_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l10_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l10_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr2.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr3.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.earth);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l10_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l10_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l10_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l10_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l10_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr1.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr4.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });


                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.earth);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l10_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l10_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l10_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l10_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l10_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr2.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr4.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });
        }
        //-------------------------------------------------------------------------------------

        if (lvv == 11) {
            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);
            quist.setText("Q : " + getString(R.string.g1_l11_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l11_q1_option1));
            ansr2.setText(getString(R.string.g1_l11_q1_option2));
            ansr3.setText(getString(R.string.g1_l11_q1_option3));
            ansr4.setText(getString(R.string.g1_l11_q1_option4));
            img.setImageResource(R.drawable.fruit);


            // correct anser -----------------------
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr4.setTextColor(Color.GREEN);
                    c++;
                    ansr4.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr1.setClickable(false);
                }
            });

            //---------------------------

            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr4.setTextColor(Color.GREEN);
                    ansr2.setTextColor(Color.RED);

                    ansr4.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr1.setClickable(false);
                }
            });
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr4.setTextColor(Color.GREEN);
                    ansr3.setTextColor(Color.RED);

                    ansr4.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr1.setClickable(false);
                }
            });
            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr4.setTextColor(Color.GREEN);
                    ansr1.setTextColor(Color.RED);

                    ansr4.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr1.setClickable(false);
                }
            });


            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l11_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l11_q2_option1));
                    ansr2.setText(getString(R.string.g1_l11_q2_option2));
                    ansr3.setText(getString(R.string.g1_l11_q2_option3));
                    ansr4.setText(getString(R.string.g1_l11_q2_option4));


                    // correct anser -----------------------
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr4.setTextColor(Color.GREEN);
                            c++;
                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr4.setTextColor(Color.GREEN);
                            ansr2.setTextColor(Color.RED);

                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr4.setTextColor(Color.GREEN);
                            ansr3.setTextColor(Color.RED);

                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });
                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr4.setTextColor(Color.GREEN);
                            ansr1.setTextColor(Color.RED);

                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });


                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {                    click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.flags);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l11_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l11_q3_option1));
                            ansr2.setText(getString(R.string.g1_l11_q3_option2));
                            ansr3.setText(getString(R.string.g1_l11_q3_option3));
                            ansr4.setText(getString(R.string.g1_l11_q3_option4));


                            // correct anser -----------------------
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    c++;
                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    ansr2.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    ansr1.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr3.setTextColor(Color.GREEN);
                                    ansr4.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.computer);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l11_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l11_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l11_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l11_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l11_q4_option4));


                                    // correct anser -----------------------
                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            c++;
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            ansr1.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr2.setTextColor(Color.GREEN);
                                            ansr3.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr2.setTextColor(Color.GREEN);
                                            ansr4.setTextColor(Color.RED);

                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {                    click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.flags);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l11_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l11_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l11_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l11_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l11_q5_option4));


                                            // correct anser -----------------------
                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr2.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr2.setTextColor(Color.GREEN);
                                                    ansr1.setTextColor(Color.RED);

                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr2.setTextColor(Color.GREEN);
                                                    ansr3.setTextColor(Color.RED);

                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr2.setTextColor(Color.GREEN);
                                                    ansr4.setTextColor(Color.RED);

                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });


                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.flags);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l11_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l11_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l11_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l11_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l11_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr2.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });
                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr1.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });


                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.flags);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l11_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l11_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l11_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l11_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l11_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr4.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr4.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr4.setTextColor(Color.GREEN);
                                                                    ansr2.setTextColor(Color.RED);

                                                                    ansr4.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                }
                                                            });
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr4.setTextColor(Color.GREEN);
                                                                    ansr3.setTextColor(Color.RED);

                                                                    ansr4.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                }
                                                            });
                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr4.setTextColor(Color.GREEN);
                                                                    ansr1.setTextColor(Color.RED);

                                                                    ansr4.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                }
                                                            });

                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);
                                                                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                                                    mInterstitialAd.show();

                                                                            img.setImageResource(R.drawable.math);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l11_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l11_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l11_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l11_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l11_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr2.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr3.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.flags);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l11_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l11_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l11_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l11_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l11_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr2.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr2.setTextColor(Color.GREEN);
                                                                                    ansr1.setTextColor(Color.RED);

                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr2.setTextColor(Color.GREEN);
                                                                                    ansr3.setTextColor(Color.RED);

                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr2.setTextColor(Color.GREEN);
                                                                                    ansr4.setTextColor(Color.RED);

                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });


                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.sport);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l11_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l11_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l11_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l11_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l11_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr2.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr4.setTextColor(Color.GREEN);
                                                                                            ansr1.setTextColor(Color.RED);

                                                                                            ansr4.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });

        }
        //-------------------------------------------------------------------------------------

        if (lvv == 12) {

            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);
            quist.setText("Q : " + getString(R.string.g1_l12_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l12_q1_option1));
            ansr2.setText(getString(R.string.g1_l12_q1_option2));
            ansr3.setText(getString(R.string.g1_l12_q1_option3));
            ansr4.setText(getString(R.string.g1_l12_q1_option4));

            img.setImageResource(R.drawable.fish);

            // correct anser -----------------------
            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr1.setTextColor(Color.GREEN);
                    c++;
                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            //---------------------------

            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr1.setTextColor(Color.GREEN);
                    ansr2.setTextColor(Color.RED);

                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr1.setTextColor(Color.GREEN);
                    ansr3.setTextColor(Color.RED);

                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr1.setTextColor(Color.GREEN);
                    ansr4.setTextColor(Color.RED);

                    ansr1.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.veagetables);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l12_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l12_q2_option1));
                    ansr2.setText(getString(R.string.g1_l12_q2_option2));
                    ansr3.setText(getString(R.string.g1_l12_q2_option3));
                    ansr4.setText(getString(R.string.g1_l12_q2_option4));


                    // correct anser -----------------------
                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr1.setTextColor(Color.GREEN);
                            c++;
                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr1.setTextColor(Color.GREEN);
                            ansr2.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr1.setTextColor(Color.GREEN);
                            ansr3.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr1.setTextColor(Color.GREEN);
                            ansr4.setTextColor(Color.RED);

                            ansr1.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr4.setClickable(false);
                        }
                    });


                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {                    click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l12_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l12_q3_option1));
                            ansr2.setText(getString(R.string.g1_l12_q3_option2));
                            ansr3.setText(getString(R.string.g1_l12_q3_option3));
                            ansr4.setText(getString(R.string.g1_l12_q3_option4));


                            // correct anser -----------------------
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    c++;
                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    ansr2.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    ansr1.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr3.setTextColor(Color.GREEN);
                                    ansr4.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l12_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l12_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l12_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l12_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l12_q4_option4));


                                    // correct anser -----------------------
                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr1.setTextColor(Color.GREEN);
                                            c++;
                                            ansr1.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr1.setTextColor(Color.GREEN);
                                            ansr2.setTextColor(Color.RED);

                                            ansr1.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr1.setTextColor(Color.GREEN);
                                            ansr3.setTextColor(Color.RED);

                                            ansr1.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr1.setTextColor(Color.GREEN);
                                            ansr4.setTextColor(Color.RED);

                                            ansr1.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {                    click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.animal_baby);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l12_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l12_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l12_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l12_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l12_q5_option4));


                                            // correct anser -----------------------
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr4.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr4.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr1.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr4.setTextColor(Color.GREEN);
                                                    ansr2.setTextColor(Color.RED);

                                                    ansr4.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr1.setClickable(false);
                                                }
                                            });
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr4.setTextColor(Color.GREEN);
                                                    ansr3.setTextColor(Color.RED);

                                                    ansr4.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr1.setClickable(false);
                                                }
                                            });
                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr4.setTextColor(Color.GREEN);
                                                    ansr1.setTextColor(Color.RED);

                                                    ansr4.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr1.setClickable(false);
                                                }
                                            });


                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.transport);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l12_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l12_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l12_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l12_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l12_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr1.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr4.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.math);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l12_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l12_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l12_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l12_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l12_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr3.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr3.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr3.setTextColor(Color.GREEN);
                                                                    ansr2.setTextColor(Color.RED);

                                                                    ansr3.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr3.setTextColor(Color.GREEN);
                                                                    ansr1.setTextColor(Color.RED);

                                                                    ansr3.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr3.setTextColor(Color.GREEN);
                                                                    ansr4.setTextColor(Color.RED);

                                                                    ansr3.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });


                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);


                                                                            img.setImageResource(R.drawable.computer);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l12_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l12_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l12_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l12_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l12_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr2.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr3.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr1.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr1.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.animal_baby);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l12_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l12_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l12_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l12_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l12_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    ansr3.setTextColor(Color.RED);

                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    ansr1.setTextColor(Color.RED);

                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });

                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.planets);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l12_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l12_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l12_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l12_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l12_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr2.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr4.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });
        }
        //-------------------------------------------------------------------------------------

        if (lvv == 13) {

            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);
            quist.setText("Q : " + getString(R.string.g1_l13_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l13_q1_option1));
            ansr2.setText(getString(R.string.g1_l13_q1_option2));
            ansr3.setText(getString(R.string.g1_l13_q1_option3));
            ansr4.setText(getString(R.string.g1_l13_q1_option4));
            img.setImageResource(R.drawable.fruit);


            // correct anser -----------------------
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr4.setTextColor(Color.GREEN);
                    c++;
                    ansr4.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr1.setClickable(false);
                }
            });

            //---------------------------

            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr4.setTextColor(Color.GREEN);
                    ansr2.setTextColor(Color.RED);

                    ansr4.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr1.setClickable(false);
                }
            });
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr4.setTextColor(Color.GREEN);
                    ansr3.setTextColor(Color.RED);

                    ansr4.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr1.setClickable(false);
                }
            });
            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr4.setTextColor(Color.GREEN);
                    ansr1.setTextColor(Color.RED);

                    ansr4.setClickable(false);
                    ansr2.setClickable(false);
                    ansr3.setClickable(false);
                    ansr1.setClickable(false);
                }
            });


            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.veagetables);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l13_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l13_q2_option1));
                    ansr2.setText(getString(R.string.g1_l13_q2_option2));
                    ansr3.setText(getString(R.string.g1_l13_q2_option3));
                    ansr4.setText(getString(R.string.g1_l13_q2_option4));


                    // correct anser -----------------------
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr4.setTextColor(Color.GREEN);
                            c++;
                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr4.setTextColor(Color.GREEN);
                            ansr2.setTextColor(Color.RED);

                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr4.setTextColor(Color.GREEN);
                            ansr3.setTextColor(Color.RED);

                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });
                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr4.setTextColor(Color.GREEN);
                            ansr1.setTextColor(Color.RED);

                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });


                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {                    click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l13_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l13_q3_option1));
                            ansr2.setText(getString(R.string.g1_l13_q3_option2));
                            ansr3.setText(getString(R.string.g1_l13_q3_option3));
                            ansr4.setText(getString(R.string.g1_l13_q3_option4));


                            // correct anser -----------------------
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    c++;
                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    ansr2.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr3.setTextColor(Color.GREEN);
                                    ansr1.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr3.setTextColor(Color.GREEN);
                                    ansr4.setTextColor(Color.RED);

                                    ansr3.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.computer);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l13_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l13_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l13_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l13_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l13_q4_option4));


                                    // correct anser -----------------------
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr4.setTextColor(Color.GREEN);
                                            c++;
                                            ansr4.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr1.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr4.setTextColor(Color.GREEN);
                                            ansr2.setTextColor(Color.RED);

                                            ansr4.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr1.setClickable(false);
                                        }
                                    });
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr4.setTextColor(Color.GREEN);
                                            ansr3.setTextColor(Color.RED);

                                            ansr4.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr1.setClickable(false);
                                        }
                                    });
                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr4.setTextColor(Color.GREEN);
                                            ansr1.setTextColor(Color.RED);

                                            ansr4.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr1.setClickable(false);
                                        }
                                    });

                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {                    click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.flags);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l13_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l13_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l13_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l13_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l13_q5_option4));


                                            // correct anser -----------------------
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr2.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr1.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr4.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.computer);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l13_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l13_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l13_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l13_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l13_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr2.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });
                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr4.setTextColor(Color.GREEN);
                                                            ansr1.setTextColor(Color.RED);

                                                            ansr4.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr1.setClickable(false);
                                                        }
                                                    });


                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);

                                                            mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                                            mInterstitialAd.show();
                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l13_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l13_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l13_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l13_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l13_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr2.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr3.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr1.setTextColor(Color.GREEN);
                                                                    ansr4.setTextColor(Color.RED);

                                                                    ansr1.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);


                                                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l13_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l13_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l13_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l13_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l13_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr2.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr2.setTextColor(Color.GREEN);
                                                                            ansr1.setTextColor(Color.RED);

                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr2.setTextColor(Color.GREEN);
                                                                            ansr3.setTextColor(Color.RED);

                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr2.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr3.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l13_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l13_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l13_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l13_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l13_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    ansr3.setTextColor(Color.RED);

                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr4.setTextColor(Color.GREEN);
                                                                                    ansr1.setTextColor(Color.RED);

                                                                                    ansr4.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                }
                                                                            });

                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.sport);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l13_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l13_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l13_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l13_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l13_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr2.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr4.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });
        }
        //-------------------------------------------------------------------------------------

        if (lvv == 14) {

            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);
            quist.setText("Q : " + getString(R.string.g1_l14_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l14_q1_option1));
            ansr2.setText(getString(R.string.g1_l14_q1_option2));
            ansr3.setText(getString(R.string.g1_l14_q1_option3));
            ansr4.setText(getString(R.string.g1_l14_q1_option4));

            img.setImageResource(R.drawable.animal_baby);

            // correct anser -----------------------
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr3.setTextColor(Color.GREEN);
                    c++;
                    ansr3.setClickable(false);
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            //---------------------------

            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr3.setTextColor(Color.GREEN);
                    ansr2.setTextColor(Color.RED);

                    ansr3.setClickable(false);
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr3.setTextColor(Color.GREEN);
                    ansr1.setTextColor(Color.RED);

                    ansr3.setClickable(false);
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr3.setTextColor(Color.GREEN);
                    ansr4.setTextColor(Color.RED);

                    ansr3.setClickable(false);
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr4.setClickable(false);
                }
            });


            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);


                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l14_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l14_q2_option1));
                    ansr2.setText(getString(R.string.g1_l14_q2_option2));
                    ansr3.setText(getString(R.string.g1_l14_q2_option3));
                    ansr4.setText(getString(R.string.g1_l14_q2_option4));


                    // correct anser -----------------------
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr4.setTextColor(Color.GREEN);
                            c++;
                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr4.setTextColor(Color.GREEN);
                            ansr2.setTextColor(Color.RED);

                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr4.setTextColor(Color.GREEN);
                            ansr3.setTextColor(Color.RED);

                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });
                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr4.setTextColor(Color.GREEN);
                            ansr1.setTextColor(Color.RED);

                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });

                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {                    click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l14_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l14_q3_option1));
                            ansr2.setText(getString(R.string.g1_l14_q3_option2));
                            ansr3.setText(getString(R.string.g1_l14_q3_option3));
                            ansr4.setText(getString(R.string.g1_l14_q3_option4));


                            // correct anser -----------------------
                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr2.setTextColor(Color.GREEN);
                                    c++;
                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr2.setTextColor(Color.GREEN);
                                    ansr1.setTextColor(Color.RED);

                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr2.setTextColor(Color.GREEN);
                                    ansr3.setTextColor(Color.RED);

                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr2.setTextColor(Color.GREEN);
                                    ansr4.setTextColor(Color.RED);

                                    ansr2.setClickable(false);
                                    ansr1.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l14_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l14_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l14_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l14_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l14_q4_option4));


                                    // correct anser -----------------------
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr3.setTextColor(Color.GREEN);
                                            c++;
                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr3.setTextColor(Color.GREEN);
                                            ansr2.setTextColor(Color.RED);

                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr3.setTextColor(Color.GREEN);
                                            ansr1.setTextColor(Color.RED);

                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr3.setTextColor(Color.GREEN);
                                            ansr4.setTextColor(Color.RED);

                                            ansr3.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr1.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });


                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {                    click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.animal_baby);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l14_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l14_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l14_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l14_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l14_q5_option4));


                                            // correct anser -----------------------
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr2.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr1.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr3.setTextColor(Color.GREEN);
                                                    ansr4.setTextColor(Color.RED);

                                                    ansr3.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr1.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });


                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.transport);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l14_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l14_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l14_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l14_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l14_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr1.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr3.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr2.setTextColor(Color.GREEN);
                                                            ansr4.setTextColor(Color.RED);

                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr3.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l14_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l14_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l14_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l14_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l14_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr3.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr3.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr3.setTextColor(Color.GREEN);
                                                                    ansr2.setTextColor(Color.RED);

                                                                    ansr3.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr3.setTextColor(Color.GREEN);
                                                                    ansr1.setTextColor(Color.RED);

                                                                    ansr3.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr3.setTextColor(Color.GREEN);
                                                                    ansr4.setTextColor(Color.RED);

                                                                    ansr3.setClickable(false);
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });


                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);


                                                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l14_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l14_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l14_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l14_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l14_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr2.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr1.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });


                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.planets);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l14_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l14_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l14_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l14_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l14_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr1.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr3.setTextColor(Color.GREEN);
                                                                                    ansr4.setTextColor(Color.RED);

                                                                                    ansr3.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr1.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });


                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.planets);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l14_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l14_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l14_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l14_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l14_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr2.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr2.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr2.setTextColor(Color.GREEN);
                                                                                            ansr1.setTextColor(Color.RED);

                                                                                            ansr2.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr2.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr2.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr2.setTextColor(Color.GREEN);
                                                                                            ansr4.setTextColor(Color.RED);

                                                                                            ansr2.setClickable(false);
                                                                                            ansr1.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();

                                                                                            ansr1.setTextColor(Color.WHITE);
                                                                                            ansr2.setTextColor(Color.WHITE);
                                                                                            ansr3.setTextColor(Color.WHITE);
                                                                                            ansr4.setTextColor(Color.WHITE);
                                                                                            ansr1.setClickable(true);
                                                                                            ansr2.setClickable(true);
                                                                                            ansr3.setClickable(true);
                                                                                            ansr4.setClickable(true);

                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });
        }

        //-------------------------------------------------------------------------------------

        if (lvv == 15) {
            quist.setAnimation(uptodown);
            ansr1.setAnimation(afasiy);
            ansr2.setAnimation(azelmad);
            ansr3.setAnimation(afasiy);
            ansr4.setAnimation(azelmad);
            quist.setText("Q : " + getString(R.string.g1_l15_q1_text) + " ?");
            ansr1.setText(getString(R.string.g1_l15_q1_option1));
            ansr2.setText(getString(R.string.g1_l15_q1_option2));
            ansr3.setText(getString(R.string.g1_l15_q1_option3));
            ansr4.setText(getString(R.string.g1_l15_q1_option4));
            img.setImageResource(R.drawable.fruit);


            // correct anser -----------------------
            ansr2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    corct.start();
                    ansr2.setTextColor(Color.GREEN);
                    c++;
                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });

            //---------------------------

            ansr1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr2.setTextColor(Color.GREEN);
                    ansr1.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();
                    ansr2.setTextColor(Color.GREEN);
                    ansr3.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            ansr4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrng.start();

                    ansr2.setTextColor(Color.GREEN);
                    ansr4.setTextColor(Color.RED);

                    ansr2.setClickable(false);
                    ansr1.setClickable(false);
                    ansr3.setClickable(false);
                    ansr4.setClickable(false);
                }
            });
            // next part

            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                    click_sound.start();

                    ansr3.setTextColor(Color.WHITE);
                    ansr2.setTextColor(Color.WHITE);
                    ansr1.setTextColor(Color.WHITE);
                    ansr4.setTextColor(Color.WHITE);
                    q++;
                    ansr1.setClickable(true);
                    ansr2.setClickable(true);
                    ansr3.setClickable(true);
                    ansr4.setClickable(true);


                    test.setText("Question: "+q + " of 10");

                    corect.setText("Correct : " + c);
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                    mInterstitialAd.show();

                            img.setImageResource(R.drawable.veagetables);
// QUISTIONS PART

                    quist.setText("Q : " + getString(R.string.g1_l15_q2_text) + " ?");
                    ansr1.setText(getString(R.string.g1_l15_q2_option1));
                    ansr2.setText(getString(R.string.g1_l15_q2_option2));
                    ansr3.setText(getString(R.string.g1_l15_q2_option3));
                    ansr4.setText(getString(R.string.g1_l15_q2_option4));


                    // correct anser -----------------------
                    ansr4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            corct.start();
                            ansr4.setTextColor(Color.GREEN);
                            c++;
                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });

                    //---------------------------

                    ansr2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr4.setTextColor(Color.GREEN);
                            ansr2.setTextColor(Color.RED);

                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });
                    ansr3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();
                            ansr4.setTextColor(Color.GREEN);
                            ansr3.setTextColor(Color.RED);

                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });
                    ansr1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            wrng.start();

                            ansr4.setTextColor(Color.GREEN);
                            ansr1.setTextColor(Color.RED);

                            ansr4.setClickable(false);
                            ansr2.setClickable(false);
                            ansr3.setClickable(false);
                            ansr1.setClickable(false);
                        }
                    });

                    // next part

                    next.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {                    click_sound.start();

                            ansr3.setTextColor(Color.WHITE);
                            ansr2.setTextColor(Color.WHITE);
                            ansr1.setTextColor(Color.WHITE);
                            ansr4.setTextColor(Color.WHITE);
                            q++;
                            ansr1.setClickable(true);
                            ansr2.setClickable(true);
                            ansr3.setClickable(true);
                            ansr4.setClickable(true);


                            test.setText("Question: "+q + " of 10");

                            corect.setText("Correct : " + c);


                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                            quist.setText("Q : " + getString(R.string.g1_l15_q3_text) + " ?");
                            ansr1.setText(getString(R.string.g1_l15_q3_option1));
                            ansr2.setText(getString(R.string.g1_l15_q3_option2));
                            ansr3.setText(getString(R.string.g1_l15_q3_option3));
                            ansr4.setText(getString(R.string.g1_l15_q3_option4));


                            // correct anser -----------------------
                            ansr1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    corct.start();
                                    ansr1.setTextColor(Color.GREEN);
                                    c++;
                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });

                            //---------------------------

                            ansr2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr1.setTextColor(Color.GREEN);
                                    ansr2.setTextColor(Color.RED);

                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr3.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();
                                    ansr1.setTextColor(Color.GREEN);
                                    ansr3.setTextColor(Color.RED);

                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            ansr4.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    wrng.start();

                                    ansr1.setTextColor(Color.GREEN);
                                    ansr4.setTextColor(Color.RED);

                                    ansr1.setClickable(false);
                                    ansr2.setClickable(false);
                                    ansr3.setClickable(false);
                                    ansr4.setClickable(false);
                                }
                            });
                            // next part


                            next.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {                    click_sound.start();

                                    ansr1.setTextColor(Color.WHITE);
                                    ansr2.setTextColor(Color.WHITE);
                                    ansr3.setTextColor(Color.WHITE);
                                    ansr4.setTextColor(Color.WHITE);
                                    q++;
                                    ansr1.setClickable(true);
                                    ansr2.setClickable(true);
                                    ansr3.setClickable(true);
                                    ansr4.setClickable(true);
                                    test.setText("Question: "+q + " of 10");

                                    corect.setText("Correct : " + c);

                                            img.setImageResource(R.drawable.computer);
// QUISTIONS PART


                                    quist.setText("Q : " + getString(R.string.g1_l15_q4_text) + " ?");
                                    ansr1.setText(getString(R.string.g1_l15_q4_option1));
                                    ansr2.setText(getString(R.string.g1_l15_q4_option2));
                                    ansr3.setText(getString(R.string.g1_l15_q4_option3));
                                    ansr4.setText(getString(R.string.g1_l15_q4_option4));


                                    // correct anser -----------------------
                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            corct.start();
                                            ansr1.setTextColor(Color.GREEN);
                                            c++;
                                            ansr1.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });

                                    //---------------------------

                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr1.setTextColor(Color.GREEN);
                                            ansr2.setTextColor(Color.RED);

                                            ansr1.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();
                                            ansr1.setTextColor(Color.GREEN);
                                            ansr3.setTextColor(Color.RED);

                                            ansr1.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            wrng.start();

                                            ansr1.setTextColor(Color.GREEN);
                                            ansr4.setTextColor(Color.RED);

                                            ansr1.setClickable(false);
                                            ansr2.setClickable(false);
                                            ansr3.setClickable(false);
                                            ansr4.setClickable(false);
                                        }
                                    });
                                    // next part


                                    next.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {                    click_sound.start();

                                            ansr2.setTextColor(Color.WHITE);
                                            ansr1.setTextColor(Color.WHITE);
                                            ansr3.setTextColor(Color.WHITE);
                                            ansr4.setTextColor(Color.WHITE);
                                            q++;
                                            ansr1.setClickable(true);
                                            ansr2.setClickable(true);
                                            ansr3.setClickable(true);
                                            ansr4.setClickable(true);

                                            test.setText("Question: "+q + " of 10");

                                            corect.setText("Correct : " + c);


                                                    img.setImageResource(R.drawable.flags);
// QUISTIONS PART

                                            quist.setText("Q : " + getString(R.string.g1_l15_q5_text) + " ?");
                                            ansr1.setText(getString(R.string.g1_l15_q5_option1));
                                            ansr2.setText(getString(R.string.g1_l15_q5_option2));
                                            ansr3.setText(getString(R.string.g1_l15_q5_option3));
                                            ansr4.setText(getString(R.string.g1_l15_q5_option4));


                                            // correct anser -----------------------
                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    corct.start();
                                                    ansr1.setTextColor(Color.GREEN);
                                                    c++;
                                                    ansr1.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            //---------------------------

                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr1.setTextColor(Color.GREEN);
                                                    ansr2.setTextColor(Color.RED);

                                                    ansr1.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();
                                                    ansr1.setTextColor(Color.GREEN);
                                                    ansr3.setTextColor(Color.RED);

                                                    ansr1.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });
                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    wrng.start();

                                                    ansr1.setTextColor(Color.GREEN);
                                                    ansr4.setTextColor(Color.RED);

                                                    ansr1.setClickable(false);
                                                    ansr2.setClickable(false);
                                                    ansr3.setClickable(false);
                                                    ansr4.setClickable(false);
                                                }
                                            });

                                            // next part

                                            next.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {                    click_sound.start();

                                                    ansr3.setTextColor(Color.WHITE);
                                                    ansr2.setTextColor(Color.WHITE);
                                                    ansr1.setTextColor(Color.WHITE);
                                                    ansr4.setTextColor(Color.WHITE);
                                                    q++;
                                                    ansr1.setClickable(true);
                                                    ansr2.setClickable(true);
                                                    ansr3.setClickable(true);
                                                    ansr4.setClickable(true);


                                                    test.setText("Question: "+q + " of 10");

                                                    corect.setText("Correct : " + c);


                                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                    quist.setText("Q : " + getString(R.string.g1_l15_q6_text) + " ?");
                                                    ansr1.setText(getString(R.string.g1_l15_q6_option1));
                                                    ansr2.setText(getString(R.string.g1_l15_q6_option2));
                                                    ansr3.setText(getString(R.string.g1_l15_q6_option3));
                                                    ansr4.setText(getString(R.string.g1_l15_q6_option4));


                                                    // correct anser -----------------------
                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            corct.start();
                                                            ansr3.setTextColor(Color.GREEN);
                                                            c++;
                                                            ansr3.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });

                                                    //---------------------------

                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr3.setTextColor(Color.GREEN);
                                                            ansr2.setTextColor(Color.RED);

                                                            ansr3.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();
                                                            ansr3.setTextColor(Color.GREEN);
                                                            ansr1.setTextColor(Color.RED);

                                                            ansr3.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });
                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            wrng.start();

                                                            ansr3.setTextColor(Color.GREEN);
                                                            ansr4.setTextColor(Color.RED);

                                                            ansr3.setClickable(false);
                                                            ansr2.setClickable(false);
                                                            ansr1.setClickable(false);
                                                            ansr4.setClickable(false);
                                                        }
                                                    });


                                                    // next part

                                                    next.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {                    click_sound.start();

                                                            ansr4.setTextColor(Color.WHITE);
                                                            ansr2.setTextColor(Color.WHITE);
                                                            ansr3.setTextColor(Color.WHITE);
                                                            ansr1.setTextColor(Color.WHITE);
                                                            q++;
                                                            ansr1.setClickable(true);
                                                            ansr2.setClickable(true);
                                                            ansr3.setClickable(true);
                                                            ansr4.setClickable(true);

                                                            test.setText("Question: "+q + " of 10");

                                                            corect.setText("Correct : " + c);


                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                            quist.setText("Q : " + getString(R.string.g1_l15_q7_text) + " ?");
                                                            ansr1.setText(getString(R.string.g1_l15_q7_option1));
                                                            ansr2.setText(getString(R.string.g1_l15_q7_option2));
                                                            ansr3.setText(getString(R.string.g1_l15_q7_option3));
                                                            ansr4.setText(getString(R.string.g1_l15_q7_option4));


                                                            // correct anser -----------------------
                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    corct.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    c++;
                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            //---------------------------

                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr1.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();
                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr3.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });
                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {
                                                                    wrng.start();

                                                                    ansr2.setTextColor(Color.GREEN);
                                                                    ansr4.setTextColor(Color.RED);

                                                                    ansr2.setClickable(false);
                                                                    ansr1.setClickable(false);
                                                                    ansr3.setClickable(false);
                                                                    ansr4.setClickable(false);
                                                                }
                                                            });

                                                            // next part


                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                public void onClick(View v) {                    click_sound.start();

                                                                    ansr2.setTextColor(Color.WHITE);
                                                                    ansr1.setTextColor(Color.WHITE);
                                                                    ansr3.setTextColor(Color.WHITE);
                                                                    ansr4.setTextColor(Color.WHITE);
                                                                    q++;
                                                                    ansr1.setClickable(true);
                                                                    ansr2.setClickable(true);
                                                                    ansr3.setClickable(true);
                                                                    ansr4.setClickable(true);

                                                                    test.setText("Question: "+q + " of 10");

                                                                    corect.setText("Correct : " + c);


                                                                            img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART

                                                                    quist.setText("Q : " + getString(R.string.g1_l15_q8_text) + " ?");
                                                                    ansr1.setText(getString(R.string.g1_l15_q8_option1));
                                                                    ansr2.setText(getString(R.string.g1_l15_q8_option2));
                                                                    ansr3.setText(getString(R.string.g1_l15_q8_option3));
                                                                    ansr4.setText(getString(R.string.g1_l15_q8_option4));


                                                                    // correct anser -----------------------
                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            corct.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            c++;
                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });

                                                                    //---------------------------

                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr2.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();
                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr1.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });
                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {
                                                                            wrng.start();

                                                                            ansr3.setTextColor(Color.GREEN);
                                                                            ansr4.setTextColor(Color.RED);

                                                                            ansr3.setClickable(false);
                                                                            ansr2.setClickable(false);
                                                                            ansr1.setClickable(false);
                                                                            ansr4.setClickable(false);
                                                                        }
                                                                    });


                                                                    // next part


                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                        public void onClick(View v) {                    click_sound.start();

                                                                            ansr1.setTextColor(Color.WHITE);
                                                                            ansr2.setTextColor(Color.WHITE);
                                                                            ansr3.setTextColor(Color.WHITE);
                                                                            ansr4.setTextColor(Color.WHITE);
                                                                            q++;
                                                                            ansr1.setClickable(true);
                                                                            ansr2.setClickable(true);
                                                                            ansr3.setClickable(true);
                                                                            ansr4.setClickable(true);
                                                                            test.setText("Question: "+q + " of 10");

                                                                            corect.setText("Correct : " + c);

                                                                                    img.setImageResource(R.drawable.defult_think);
// QUISTIONS PART


                                                                            quist.setText("Q : " + getString(R.string.g1_l15_q9_text) + " ?");
                                                                            ansr1.setText(getString(R.string.g1_l15_q9_option1));
                                                                            ansr2.setText(getString(R.string.g1_l15_q9_option2));
                                                                            ansr3.setText(getString(R.string.g1_l15_q9_option3));
                                                                            ansr4.setText(getString(R.string.g1_l15_q9_option4));


                                                                            // correct anser -----------------------
                                                                            ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    corct.start();
                                                                                    ansr1.setTextColor(Color.GREEN);
                                                                                    c++;
                                                                                    ansr1.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });

                                                                            //---------------------------

                                                                            ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr1.setTextColor(Color.GREEN);
                                                                                    ansr2.setTextColor(Color.RED);

                                                                                    ansr1.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();
                                                                                    ansr1.setTextColor(Color.GREEN);
                                                                                    ansr3.setTextColor(Color.RED);

                                                                                    ansr1.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });
                                                                            ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {
                                                                                    wrng.start();

                                                                                    ansr1.setTextColor(Color.GREEN);
                                                                                    ansr4.setTextColor(Color.RED);

                                                                                    ansr1.setClickable(false);
                                                                                    ansr2.setClickable(false);
                                                                                    ansr3.setClickable(false);
                                                                                    ansr4.setClickable(false);
                                                                                }
                                                                            });

                                                                            // next part

                                                                            next.setOnClickListener(new View.OnClickListener() {
                                                                                public void onClick(View v) {                    click_sound.start();

                                                                                    ansr3.setTextColor(Color.WHITE);
                                                                                    ansr2.setTextColor(Color.WHITE);
                                                                                    ansr1.setTextColor(Color.WHITE);
                                                                                    ansr4.setTextColor(Color.WHITE);
                                                                                    q++;
                                                                                    ansr1.setClickable(true);
                                                                                    ansr2.setClickable(true);
                                                                                    ansr3.setClickable(true);
                                                                                    ansr4.setClickable(true);


                                                                                    test.setText("Question: "+q + " of 10");

                                                                                    corect.setText("Correct : " + c);


                                                                                            img.setImageResource(R.drawable.sport);
// QUISTIONS PART


                                                                                    quist.setText("Q : " + getString(R.string.g1_l15_q10_text) + " ?");
                                                                                    ansr1.setText(getString(R.string.g1_l15_q10_option1));
                                                                                    ansr2.setText(getString(R.string.g1_l15_q10_option2));
                                                                                    ansr3.setText(getString(R.string.g1_l15_q10_option3));
                                                                                    ansr4.setText(getString(R.string.g1_l15_q10_option4));


                                                                                    // correct anser -----------------------
                                                                                    ansr1.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            corct.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            c++;
                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    //---------------------------

                                                                                    ansr2.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr2.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr3.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();
                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr3.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });
                                                                                    ansr4.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {
                                                                                            wrng.start();

                                                                                            ansr1.setTextColor(Color.GREEN);
                                                                                            ansr4.setTextColor(Color.RED);

                                                                                            ansr1.setClickable(false);
                                                                                            ansr2.setClickable(false);
                                                                                            ansr3.setClickable(false);
                                                                                            ansr4.setClickable(false);
                                                                                        }
                                                                                    });

                                                                                    // next part


                                                                                    next.setOnClickListener(new View.OnClickListener() {
                                                                                        public void onClick(View v) {                    click_sound.start();


                                                                                            mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                                                                            mInterstitialAd.show();
                                                                                            Intent gtint = new Intent(getApplicationContext(), Score.class);
                                                                                            gtint.putExtra("levels", lvl);
                                                                                            String crr = c.toString();
                                                                                            gtint.putExtra("crct", crr);
                                                                                            startActivity(gtint);


                                                                                        }
                                                                                    });

                                                                                }
                                                                            });

                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
            });

        }

    }

    public void swip(View view){
        Toast.makeText(getApplicationContext(),"Swipe up to choose the correct answer", Toast.LENGTH_SHORT).show();

    }



}

final class ViewDialog2 {

    public void showDialog(final Activity activity){
        final Dialog dialog = new Dialog(activity);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        TextView msg = (TextView) dialog.findViewById(R.id.mesag);
        msg.setText("Swipe up to choose the correct answer");
       final MediaPlayer   click_sound = MediaPlayer.create(activity, R.raw.click);


        TextView dialogButton = (TextView) dialog.findViewById(R.id.ok);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_sound.start();

                dialog.dismiss();

            }
        });

        dialog.show();

    }
}