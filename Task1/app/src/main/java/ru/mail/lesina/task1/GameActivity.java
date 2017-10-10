package ru.mail.lesina.task1;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    public int counter = 1;
    public boolean isStop = false;
    protected long totalTime = 11 * 1000;
    public long currentTime = totalTime;
    Button button;
    TextView textView;
    CountDownTimer timer;
    Num num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView2);
        timer = newTimer(currentTime, 1000);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStop) {
                    button.setText(getString(R.string.button_start));
                    isStop = false;
                    counter = 1;
                    currentTime = 10 * 1000;
                    textView.setText("");
                    timer.cancel();
                } else {
                    button.setText(getString(R.string.button_stop));
                    isStop = true;
                    timer.start();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(getString(R.string.count), counter);
        outState.putBoolean(getString(R.string.button), isStop);
        outState.putString(getString(R.string.text), textView.getText().toString());
        outState.putString(getString(R.string.button_text), button.getText().toString());
        outState.putLong(getString(R.string.current_time), totalTime - (counter - 1) * 1000);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt(getString(R.string.count));
        isStop = savedInstanceState.getBoolean(getString(R.string.button));
        textView.setText(savedInstanceState.getString(getString(R.string.text)));
        button.setText(savedInstanceState.getString(getString(R.string.button_text)));
        currentTime = savedInstanceState.getLong(getString(R.string.current_time));
        timer = newTimer(currentTime, 1000);
        if (isStop) {
            timer.start();
        }
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isStop) {
            currentTime = totalTime - (counter - 1) * 1000;
            timer.cancel();
        }
    }

    @Override
    protected void onStart() {
        if (isStop) {
            timer = newTimer(currentTime, 1000);
            timer.start();
        }
        super.onStart();
    }

    public CountDownTimer newTimer(long time, long miliseccondsSTOP) {
        return new CountDownTimer(time, miliseccondsSTOP) {
            public void onTick(long millisUntilFinished) {
                textView.setText(num.toString(counter));
                counter++;
            }

            public void onFinish() {
                button.setText(getString(R.string.button_start));
                isStop = false;
                counter = 1;
                currentTime = 10 * 1000;
                textView.setText("");
            }
        };
    }
}
