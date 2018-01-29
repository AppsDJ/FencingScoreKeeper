package com.appsdj.fencingscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    private static final String SCORE_FENCER_A_VALUE = "ScoreFencerA";
    private static final String SCORE_FENCER_B_VALUE = "ScoreFencerB";
    private static final String YELLOW_CARDS_TO_A_VALUE = "YellowCardsToAValue";
    private static final String YELLOW_CARDS_TO_B_VALUE = "YellowCardsToBValue";
    private static final String RED_CARDS_TO_A_VALUE = "RedCardsToBValue";
    private static final String RED_CARDS_TO_B_VALUE = "RedCardsToBValue";
    private static final String BUTTON_YELLOW_CARD_TO_A = "YellowCardToAButton";
    private static final String BUTTON_YELLOW_CARD_TO_B = "YellowCardToBButton";
    private static final String BUTTON_RED_CARD_TO_A = "RedCardToAButton";
    private static final String BUTTON_RED_CARD_TO_B = "RedCardToBButton";
    private static final String IMAGE_BUTTON_TOUCHE_TO_A = "ImageButttonToucheToA";
    private static final String IMAGE_BUTTON_TOUCHE_TO_B = "ImageButttonToucheToB";
    private static final String BUTTON_START_VALUE = "StartButton";
    private static final String BUTTON_PAUSE_VALUE = "PauseButton";
    private static final String BUTTON_RESUME_VALUE = "ResumeButton";
    private static final String BUTTON_RESET_VALUE = "ResetButton";
    private static final String MILLISECONDS_LEFT = "MillisecondsLeft";
    private static final String TIME_INTO_GAME = "timeIntoGame";
    private static final String HMS = "hms";
    private static final String ORIENTATION_CHANGED = "orientationChanged";

    int scoreFencerA = 0;
    int scoreFencerB = 0;
    int yellowCardsToA = 0;
    int yellowCardsToB = 0;
    int redCardsToA = 0;
    int redCardsToB = 0;
    long millis = 0;
    long timeIntoGame = 180000;
    String hms = "";
    CountDownTimer timer = null;
    CountDownTimer orientationTimer = null;
    Button btnStart, btnPause, btnResume, btnReset;
    Button btnYellowCardToA, btnYellowCardToB, btnRedCardToA, btnRedCardToB;
    Button imgBtnToucheOnA, imgBtnToucheOnB;
    TextView textViewTime;
    private boolean isPaused = false;
    private boolean isReset = false;
    private boolean orientationChanged = false;

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

        timeIntoGame = 180000;
        textViewTime = (TextView) findViewById(R.id.text_view_time);
        textViewTime.setText("03:00");

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPaused = false;

                // create a new countdown timer
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

                // re-create countdown timer from values at point of 'pause'
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

                if (timer != null) {
                    timer.cancel();
                }

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
        isReset = true;
        scoreFencerA = 0;
        scoreFencerB = 0;
        yellowCardsToA = 0;
        redCardsToA = 0;
        yellowCardsToB = 0;
        redCardsToB = 0;

        if ( timer != null) {
            timer.cancel();
        }

        if (orientationTimer != null) {
            orientationTimer.cancel();
        }

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


    //** SAVE STATE ON ROTATION **//
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("TAG","orient in saved : " + String.valueOf(orientationChanged));

        //orientationChanged = true;

        // Save the state of items
        outState.putInt(SCORE_FENCER_A_VALUE, scoreFencerA);
        outState.putInt(SCORE_FENCER_B_VALUE, scoreFencerB);
        outState.putInt(YELLOW_CARDS_TO_A_VALUE, yellowCardsToA);
        outState.putInt(YELLOW_CARDS_TO_B_VALUE, yellowCardsToB);
        outState.putInt(RED_CARDS_TO_A_VALUE, redCardsToA);
        outState.putInt(RED_CARDS_TO_B_VALUE, redCardsToB);
        outState.putBoolean(BUTTON_START_VALUE, btnStart.isEnabled());
        outState.putBoolean(BUTTON_PAUSE_VALUE, btnPause.isEnabled());
        outState.putBoolean(BUTTON_RESUME_VALUE, btnResume.isEnabled());
        outState.putBoolean(BUTTON_RESET_VALUE, btnReset.isEnabled());
        outState.putBoolean(IMAGE_BUTTON_TOUCHE_TO_A, imgBtnToucheOnA.isEnabled());
        outState.putBoolean(IMAGE_BUTTON_TOUCHE_TO_B, imgBtnToucheOnB.isEnabled());
        outState.putBoolean(BUTTON_YELLOW_CARD_TO_A, btnYellowCardToA.isEnabled());
        outState.putBoolean(BUTTON_YELLOW_CARD_TO_B, btnYellowCardToB.isEnabled());
        outState.putBoolean(BUTTON_RED_CARD_TO_A, btnRedCardToA.isEnabled());
        outState.putBoolean(BUTTON_RED_CARD_TO_B, btnRedCardToB.isEnabled());
        outState.putLong(TIME_INTO_GAME, timeIntoGame);
        outState.putLong(MILLISECONDS_LEFT, millis);
        outState.putString(HMS, hms);
        outState.putBoolean(ORIENTATION_CHANGED, true);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Read the state of item position
        scoreFencerA = savedInstanceState.getInt(SCORE_FENCER_A_VALUE);
        scoreFencerB = savedInstanceState.getInt(SCORE_FENCER_B_VALUE);
        yellowCardsToA = savedInstanceState.getInt(YELLOW_CARDS_TO_A_VALUE);
        yellowCardsToB = savedInstanceState.getInt(YELLOW_CARDS_TO_B_VALUE);
        redCardsToA = savedInstanceState.getInt(RED_CARDS_TO_A_VALUE);
        redCardsToB = savedInstanceState.getInt(RED_CARDS_TO_B_VALUE);
        timeIntoGame = savedInstanceState.getLong(TIME_INTO_GAME);
        millis = savedInstanceState.getLong(MILLISECONDS_LEFT);
        hms = savedInstanceState.getString(HMS);
        orientationChanged = savedInstanceState.getBoolean(ORIENTATION_CHANGED);

        //Log.d("TAG","orient in restore : " + String.valueOf(orientationChanged));

        btnStart.setEnabled(savedInstanceState.getBoolean(BUTTON_START_VALUE));
        btnPause.setEnabled(savedInstanceState.getBoolean(BUTTON_PAUSE_VALUE));
        btnResume.setEnabled(savedInstanceState.getBoolean(BUTTON_RESUME_VALUE));
        btnReset.setEnabled(savedInstanceState.getBoolean(BUTTON_RESET_VALUE));

        imgBtnToucheOnA.setEnabled(savedInstanceState.getBoolean(IMAGE_BUTTON_TOUCHE_TO_A));
        imgBtnToucheOnB.setEnabled(savedInstanceState.getBoolean(IMAGE_BUTTON_TOUCHE_TO_B));
        btnYellowCardToA.setEnabled(savedInstanceState.getBoolean(BUTTON_YELLOW_CARD_TO_A));
        btnYellowCardToB.setEnabled(savedInstanceState.getBoolean(BUTTON_YELLOW_CARD_TO_B));
        btnRedCardToA.setEnabled(savedInstanceState.getBoolean(BUTTON_RED_CARD_TO_A));
        btnRedCardToB.setEnabled(savedInstanceState.getBoolean(BUTTON_RED_CARD_TO_B));

        // at orientation change activity is killed and recreated. Therefore:
        // if orientation changed without start of countdown then redisplay initial count
        if (timeIntoGame == 180000 && millis == 0) {
            textViewTime.setText("03:00");
         //else start new activity with values saved in Bundle
        } else {
            // if a reset has been pressed
            if ( isReset ) {
                timeIntoGame = millis;
                // an alltogeter new countdown timer is created
                orientationTimer = new CountDownTimer(timeIntoGame, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (isPaused) {
                            cancel();
                        } else {
                            millis = millisUntilFinished;
                            hms = String.format("%02d:%02d",
                                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

                            Log.i("hms if 0 : ", String.valueOf(hms));
                            textViewTime.setText(hms);

                        }
                    }

                    @Override
                    public void onFinish() {
                        if (timeIntoGame == 0) {
                            textViewTime.setText("03:00");
                        } else {
                            textViewTime.setText("00:00");
                        }
                    }
                }.start();

                // or if it's started and not paused:
            } else if (btnStart.isEnabled() == false && btnPause.isEnabled() == true  ) {
                timeIntoGame = millis;
                orientationTimer = new CountDownTimer(timeIntoGame, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (isPaused) {
                            cancel();
                        } else {
                            millis = millisUntilFinished;
                            hms = String.format("%02d:%02d",
                                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

                            Log.i("hms if 0 : ", String.valueOf(hms));
                            textViewTime.setText(hms);

                        }
                    }

                    @Override
                    public void onFinish() {
                        if (timeIntoGame == 0) {
                            textViewTime.setText("03:00");
                        } else {
                            textViewTime.setText("00:00");
                        }
                    }
                }.start();
            }

        }

        if (btnReset.isEnabled()) {
            displayForFencerA(scoreFencerA);
            displayForFencerB(scoreFencerB);
            displayYellowCardsForA(yellowCardsToA);
            displayYellowCardsForB(yellowCardsToB);
            displayRedCardsForA(redCardsToA);
            displayRedCardsForB(redCardsToB);
            textViewTime.setText(hms);
        }

    }
}
