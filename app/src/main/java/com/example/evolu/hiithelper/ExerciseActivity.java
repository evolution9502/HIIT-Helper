package com.example.evolu.hiithelper;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import static com.example.evolu.hiithelper.Utlities.ReferenceStrings.CALM_TIME;
import static com.example.evolu.hiithelper.Utlities.ReferenceStrings.CIRCLES;
import static com.example.evolu.hiithelper.Utlities.ReferenceStrings.EXERCISE_TIME;
import static com.example.evolu.hiithelper.Utlities.ReferenceStrings.MOVE_COUNT;
import static com.example.evolu.hiithelper.Utlities.ReferenceStrings.PREPARE_TIME;
import static com.example.evolu.hiithelper.Utlities.ReferenceStrings.REST_TIME;

public class ExerciseActivity extends AppCompatActivity {
    private int intPrepareTime, intExerciseTime, intRestTime, intCalmTime, intMoveCount, intCircles;
    private TextView showingCurrentStatus, showingTime, showingMoveCount, showingCircles;
    private int currentMoveCount=1, currentCircle=1;
    private long currentTime;
    private Button pause, restart;
    private CountDownTimer countDownTimer;
    private boolean isPause=false, isExercising=false, isExerciseStarted=false;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        intPrepareTime = Integer.parseInt(getIntent().getStringExtra(PREPARE_TIME));
        intExerciseTime = Integer.parseInt(getIntent().getStringExtra(EXERCISE_TIME));
        intRestTime = Integer.parseInt(getIntent().getStringExtra(REST_TIME));
        intCalmTime = Integer.parseInt(getIntent().getStringExtra(CALM_TIME));
        intMoveCount = Integer.parseInt(getIntent().getStringExtra(MOVE_COUNT));
        intCircles = Integer.parseInt(getIntent().getStringExtra(CIRCLES));

        showingCurrentStatus = findViewById(R.id.tv_status);
        showingTime = findViewById(R.id.tv_time);
        showingMoveCount = findViewById(R.id.tv_move_count);
        showingCircles = findViewById(R.id.tv_circles);
        pause = findViewById(R.id.btn_pause);
        restart = findViewById(R.id.btn_restart);

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countDownTimer!=null && isExerciseStarted) {
                    if (!isPause) {
                        isPause=true;
                        showingCurrentStatus.setText("暫停中...");
                        countDownTimer.cancel();
                    } else {
                        isPause=false;
                        if (isExercising) {
                            doExercise((int) (currentTime/1000));
                        } else {
                            doRest((int) (currentTime/1000));
                        }
                    }
                }
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countDownTimer!=null) {
                    countDownTimer.cancel();
                    currentMoveCount=1;
                    currentCircle=1;
                    showingCircles.setText("1");
                    showingMoveCount.setText("1");
                    doPrepare(intPrepareTime);
                }
            }
        });

        doPrepare(intPrepareTime);
    }

    private void doExercise (int time) {
        countDownTimer = new CountDownTimer(time*1000,1000){

            @Override
            public void onFinish() {
                showingCurrentStatus.setText("完成!");
                doRest(intRestTime);
            }

            @Override
            public void onTick(long millisUntilFinished) {
                if (mp!=null)
                    mp.stop();
                isExerciseStarted=true;
                isExercising=true;
                currentTime = millisUntilFinished;
                showingCurrentStatus.setText("運動中!!");
                showingTime.setText("" + millisUntilFinished / 1000);
                if (millisUntilFinished/1000 < 4 ) {
                    mp = MediaPlayer.create(ExerciseActivity.this, R.raw.beep3);
                    mp.start();
                }
            }

        }.start();
    }

    private void doRest (int time) {
        countDownTimer = new CountDownTimer(time*1000,1000){

            @Override
            public void onFinish() {
                showingCurrentStatus.setText("繼續!");
                currentMoveCount++;
                if (currentMoveCount>intMoveCount) {
                    currentMoveCount=1;
                    currentCircle++;
                }
                showingMoveCount.setText(""+currentMoveCount);
                showingCircles.setText(""+currentCircle);
                if (currentMoveCount<=intMoveCount && currentCircle<=intCircles){
                    doExercise(intExerciseTime);
                } else {
                    doCalm(intCalmTime);
                }
            }

            @Override
            public void onTick(long millisUntilFinished) {
                if (mp!=null)
                    mp.stop();
                isExercising=false;
                currentTime = millisUntilFinished;
                showingCurrentStatus.setText("喘氣中...");
                showingTime.setText(""+millisUntilFinished/1000);
                if (millisUntilFinished/1000 < 4 ) {
                    mp = MediaPlayer.create(ExerciseActivity.this, R.raw.beep3);
                    mp.start();
                }
            }

        }.start();
    }

    private void doPrepare (int time) {
        countDownTimer = new CountDownTimer(time*1000,1000){
            @Override
            public void onFinish() {
                doExercise(intExerciseTime);
            }
            @Override
            public void onTick(long millisUntilFinished) {
                if (mp!=null)
                    mp.stop();
                currentTime = millisUntilFinished;
                showingTime.setText(""+millisUntilFinished/1000+" 後開始");
                if (millisUntilFinished/1000 < 4 ) {
                    mp = MediaPlayer.create(ExerciseActivity.this, R.raw.beep3);
                    mp.start();
                }
            }
        }.start();
    }

    private void doCalm (int time) {
        countDownTimer = new CountDownTimer(time*1000,1000){
            @Override
            public void onFinish() {
                showingCurrentStatus.setText("結束!");
                showingTime.setText("休息嘍~");
                finish();
            }
            @Override
            public void onTick(long millisUntilFinished) {
                isExerciseStarted=false;
                currentTime = millisUntilFinished;
                showingCurrentStatus.setText("緩和動作");
                showingTime.setText("緩和 "+millisUntilFinished/1000);
                showingMoveCount.setText(""+intMoveCount);
                showingCircles.setText(""+intCircles);
            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }
}
