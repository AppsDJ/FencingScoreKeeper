package com.appsdj.fencingscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    int scoreFencerA = 0;
    int scoreFencerB = 0;
    int yellowCardsToA = 0;
    int yellowCardsToB = 0;
    int redCardsToA = 0;
    int redCardsToB = 0;
    int timePassed = 0;
    long millis = 0;
    long timeIntoGame = 180000;
    String hms = "";
    CountDownTimer timer = null;
    Button btnStart, btnPause, btnResume, btnReset;
    Button btnYellowCardToA, btnYellowCardToB, btnRedCardToA, btnRedCardToB;
    Button imgBtnToucheOnA, imgBtnToucheOnB;
    TextView textViewTime;
    private boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnResume = (Button) findViewById(R.id.btnResume);
        btnReset = (Button) findViewById(R.id.btnReset);
        imgBtnToucheOnA = (Button) findViewById(R.id.touche_on_A);
        imgBtnToucheOnB = (Button) findViewById(R.id.touche_on_B);
        btnYellowCardToA = (Button) findViewById(R.id.yellow_card_to_A_btn);
        btnYellowCardToB = (Button) findViewById(R.id.yellow_card_to_B_btn);
        btnRedCardToA = (Button) findViewById(R.id.red_card_to_A_btn);
        btnRedCardToB = (Button) findViewById(R.id.red_card_to_B_btn);
        btnPause.setEnabled(false);
        btnResume.setEnabled(false);
        btnReset.setEnabled(false);
        imgBtnToucheOnA.setEnabled(false);
        imgBtnToucheOnB.setEnabled(false);
        btnYellowCardToA.setEnabled(false);
        btnYellowCardToB.setEnabled(false);
        btnRedCardToA.setEnabled(false);
        btnRedCardToB.setEnabled(false);
        textViewTime = (TextView) findViewById(R.id.text_view_time);
        textViewTime.setText("03:00");

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPaused = false;
                timer = new CountDownTimer(timeIntoGame, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (isPaused) {
                            cancel();
                        } else {
                            millis = millisUntilFinished;
                            hms = String.format("%02d:%02d",
                                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                            textViewTime.setText(hms);
                            //timePassed ++;
                        }
                    }

                    @Override
                    public void onFinish() {
                        textViewTime.setText("00:00");
                        btnPause.setEnabled(false);
                        btnResume.setEnabled(false);
                        imgBtnToucheOnA.setEnabled(false);
                        imgBtnToucheOnB.setEnabled(false);
                        btnYellowCardToA.setEnabled(false);
                        btnYellowCardToB.setEnabled(false);
                        btnRedCardToA.setEnabled(false);
                        btnRedCardToB.setEnabled(false);
                    }
                }.start();

                imgBtnToucheOnA.setEnabled(true);
                imgBtnToucheOnB.setEnabled(true);
                btnYellowCardToA.setEnabled(true);
                btnYellowCardToB.setEnabled(true);
                btnRedCardToA.setEnabled(true);
                btnRedCardToB.setEnabled(true);
                btnStart.setEnabled(false);
                btnPause.setEnabled(true);
                btnReset.setEnabled(true);

            }
        });

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPaused = false;
                timeIntoGame = millis;
                timer = new CountDownTimer(timeIntoGame, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (isPaused) {
                            cancel();
                        } else {
                            millis = millisUntilFinished;
                            hms = String.format("%02d:%02d",
                                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                            textViewTime.setText(hms);
                            //timePassed ++;
                        }
                    }

                    @Override
                    public void onFinish() {
                        textViewTime.setText("00:00");
                    }
                }.start();

                imgBtnToucheOnA.setEnabled(true);
                imgBtnToucheOnB.setEnabled(true);
                btnYellowCardToA.setEnabled(true);
                btnYellowCardToB.setEnabled(true);
                btnRedCardToA.setEnabled(true);
                btnRedCardToB.setEnabled(true);
                btnStart.setEnabled(false);
                btnPause.setEnabled(true);
                btnResume.setEnabled(false);
            }
        });


        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //timer.cancel();
                isPaused = true;
                imgBtnToucheOnA.setEnabled(false);
                imgBtnToucheOnB.setEnabled(false);
                btnYellowCardToA.setEnabled(false);
                btnYellowCardToB.setEnabled(false);
                btnRedCardToA.setEnabled(false);
                btnRedCardToB.setEnabled(false);
                btnStart.setEnabled(false);
                btnPause.setEnabled(false);
                btnResume.setEnabled(true);
            }
        });
    }

    // FENCER A scoring methods

    /**
     * Increase number of points for Fencer A by 1.
     */
    public void toucheOnA(View view) {
        scoreFencerA += 1;
        displayForFencerA(scoreFencerA);
        ImageView imageViewRed = (ImageView) findViewById(R.id.red_flag);
        imageViewRed.setImageResource(R.drawable.red_flag_small_bright);

        ImageView imageViewGreen = (ImageView) findViewById(R.id.green_flag);
        imageViewGreen.setImageResource(R.drawable.green_flag_small);
    }

    /**
     * Increase number of yellow cards for Fencer A by 1.
     * If the fencer receives 2 yellow cards then a red card is issued - which
     * deducts a point from the fencer's score
     */
    public void giveYellowCardToA(View view) {
        yellowCardsToA += 1;
        displayYellowCardsForA(yellowCardsToA);
        if (yellowCardsToA > 0 && yellowCardsToA % 2 == 0) {
            giveRedCardToA(null);
        }
    }

    /**
     * Increase number of red cards for Fencer A by 1.
     */
    public void giveRedCardToA(View view) {
        redCardsToA += 1;
        displayRedCardsForA(redCardsToA);
        if (scoreFencerA != 0) {
            scoreFencerA -= 1;
            displayForFencerA(scoreFencerA);
        } else {
            return;
        }
    }

    // END FENCER A scoring methods

    // FENCER B scoring methods

    /**
     * Increase number of points for Fencer B by 1.
     */
    public void toucheOnB(View view) {
        scoreFencerB += 1;
        displayForFencerB(scoreFencerB);

        ImageView imageViewRed = (ImageView) findViewById(R.id.red_flag);
        imageViewRed.setImageResource(R.drawable.red_flag_small);

        ImageView imageViewGreen = (ImageView) findViewById(R.id.green_flag);
        imageViewGreen.setImageResource(R.drawable.green_flag_small_bright);
    }

    /**
     * Increase number of yellow cards for Fencer B by 1.
     * If the fencer receives 2 yellow cards then a red card is issued - which
     * deducts a point from the fencer's score
     */
    public void giveYellowCardToB(View view) {
        yellowCardsToB += 1;
        displayYellowCardsForB(yellowCardsToB);
        if (yellowCardsToB > 0 && yellowCardsToB % 2 == 0) {
            giveRedCardToB(null);
        }
    }

    /**
     * Increase number of red cards for Fencer B by 1.
     */
    public void giveRedCardToB(View view) {
        redCardsToB += 1;
        displayRedCardsForB(redCardsToB);
        if (scoreFencerB != 0) {
            scoreFencerB -= 1;
            displayForFencerB(scoreFencerB);
        } else {
            return;
        }
    }

    // END FENCER B scoring methods

    // Reset scores to 0
    public void resetScore(View view) {
        scoreFencerA = 0;
        scoreFencerB = 0;
        yellowCardsToA = 0;
        redCardsToA = 0;
        yellowCardsToB = 0;
        redCardsToB = 0;
        timer.cancel();
        timeIntoGame = 180000;
        textViewTime.setText("03:00");
        imgBtnToucheOnA.setEnabled(false);
        imgBtnToucheOnB.setEnabled(false);
        btnYellowCardToA.setEnabled(false);
        btnYellowCardToB.setEnabled(false);
        btnRedCardToA.setEnabled(false);
        btnRedCardToB.setEnabled(false);
        btnStart.setEnabled(true);
        btnPause.setEnabled(false);
        btnResume.setEnabled(false);
        btnReset.setEnabled(false);
        displayForFencerA(scoreFencerA);
        displayForFencerB(scoreFencerB);
        displayYellowCardsForA(yellowCardsToA);
        displayRedCardsForA(redCardsToA);
        displayYellowCardsForB(yellowCardsToA);
        displayRedCardsForB(redCardsToA);

        TextView tv1 = (TextView) findViewById(R.id.fencer_a_score);
        tv1.setText("00");

        TextView tv2 = (TextView) findViewById(R.id.fencer_b_score);
        tv2.setText("00");

        ImageView redFlagView = (ImageView) findViewById(R.id.red_flag);
        redFlagView.setImageResource(R.drawable.red_flag_small);

        ImageView greenFlagView = (ImageView) findViewById(R.id.green_flag);
        greenFlagView.setImageResource(R.drawable.green_flag_small);

    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForFencerA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.fencer_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayYellowCardsForA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.yellow_card_to_A);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayRedCardsForA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.red_card_to_A);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayYellowCardsForB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.yellow_card_to_B);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayRedCardsForB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.red_card_to_B);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForFencerB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.fencer_b_score);
        scoreView.setText(String.valueOf(score));
    }
}

