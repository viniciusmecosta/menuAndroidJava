package com.vinicius.menu;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class Time extends AppCompatActivity {
    private TextView txtTimer;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        txtTimer = findViewById(R.id.txt_timer);
        progressBar = findViewById(R.id.progress_bar);
        int estimatedTime = getIntent().getIntExtra("estimatedTime", 0) * 60000;
        progressBar.setMax(estimatedTime);
        CountDownTimer countDownTimer = new CountDownTimer(estimatedTime, 1000) {
            public void onTick(long millisUntilFinished) {
                int progress = (int) (estimatedTime - millisUntilFinished);
                progressBar.setProgress(progress);
                txtTimer.setText(String.format("%02d:%02d", (millisUntilFinished / 60000), (millisUntilFinished % 60000) / 1000));
            }
            public void onFinish() {
                txtTimer.setText("00:00");
            }
        }.start();
    }
}