package com.vinicius.menu;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// A classe Time é usada para mostrar uma contagem regressiva com base no tempo estimado de preparo do pedido.
public class Time extends AppCompatActivity {
    private TextView txtTimer; // TextView para exibir o tempo restante.
    private ProgressBar progressBar; // ProgressBar para visualizar o progresso da contagem regressiva.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time); // Define o layout para esta atividade.

        // Inicializa os componentes da view.
        txtTimer = findViewById(R.id.txt_timer);
        progressBar = findViewById(R.id.progress_bar);

        // Obtém o tempo estimado de preparo passado pela atividade Checkout.
        int estimatedTime = getIntent().getIntExtra("estimatedTime", 0) * 60000; // Convertido para milissegundos.

        // Define o valor máximo da ProgressBar com base no tempo estimado.
        progressBar.setMax(estimatedTime);

        // Cria um novo CountDownTimer para gerenciar a contagem regressiva.
        new CountDownTimer(estimatedTime, 1000) { // Conta de trás para frente em intervalos de 1 segundo.
            public void onTick(long millisUntilFinished) {
                // Atualiza o progresso da ProgressBar a cada tick.
                int progress = (int) (estimatedTime - millisUntilFinished);
                progressBar.setProgress(progress);

                // Atualiza o TextView para mostrar o tempo restante no formato MM:SS.
                txtTimer.setText(String.format("%02d:%02d", (millisUntilFinished / 60000), (millisUntilFinished % 60000) / 1000));
            }

            public void onFinish() {
                // Quando o temporizador termina, atualiza o TextView para mostrar 00:00.
                txtTimer.setText("00:00");
            }
        }.start(); // Inicia a contagem regressiva.
    }
}
