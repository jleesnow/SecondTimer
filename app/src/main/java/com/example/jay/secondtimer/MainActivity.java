package com.example.jay.secondtimer;


import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
    private TextView textTimer;
    private Button startButton;
    private Button pauseButton;
    private Button resetButton;
    private long startTime;
    private Handler myHandler = new Handler();
    long timeInMsecs;
    long timeSwap;
    long finalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UI();

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startTime = SystemClock.uptimeMillis();
                myHandler.postDelayed(updateTimerMethod, 0);
            }
        });


        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timeSwap += timeInMsecs;
                myHandler.removeCallbacks(updateTimerMethod);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                textTimer.setText("0");
                startTime = SystemClock.uptimeMillis();
                timeSwap = 0;
            }
        });
    }

    public void UI() {
        textTimer = (TextView) findViewById(R.id.textTimer);
        startButton = (Button) findViewById(R.id.btnStart);
        pauseButton = (Button) findViewById(R.id.btnPause);
        resetButton = (Button) findViewById(R.id.btnReset);
    }


    private Runnable updateTimerMethod = new Runnable() {
        @Override
        public void run() {
            timeInMsecs = SystemClock.uptimeMillis() - startTime;
            finalTime = timeSwap + timeInMsecs;

            int seconds = (int) (finalTime / 1000);
            textTimer.setText(String.format("%d", seconds));
            myHandler.postDelayed(this, 0);
        }
    };
}


