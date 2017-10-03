package ru.mail.lesina.task1;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new CountDownTimer(3000, 1000){
            @Override
            public void onTick(long l) {

            }

            public  void onFinish(){
                createGame();
            }
        }.start();
    }

    public void createGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}