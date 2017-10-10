package ru.mail.lesina.task1;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    CountDownTimer timer;
    public long totalTime = 3 * 1000;
    public long currentTime = totalTime;
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = newTimer(currentTime, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        timer = newTimer(currentTime, 1000);
        onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        currentTime = totalTime - count * 1000;
        timer.cancel();
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(getString(R.string.count), count);
        outState.putLong(getString(R.string.current_time), totalTime - count * 1000);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt(getString(R.string.count));
        currentTime = savedInstanceState.getLong(getString(R.string.current_time));
        timer = newTimer(currentTime, 1000);
    }

    public void createGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public CountDownTimer newTimer(long time, long miliseccondsSTOP) {
        return new CountDownTimer(time, miliseccondsSTOP) {
            public void onTick(long millisUntilFinished) {
                count++;
            }

            public void onFinish() {
                createGame();
                onDestroy();
            }
        };
    }
}