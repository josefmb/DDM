package com.ifsc.sorteio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int sorteiaNumeros(int iInferior, int iSuperior) {
        Random random = new Random();

        int iSorteado = random.nextInt((iSuperior - iInferior) + 1) + iInferior;
        return iSorteado;
    }

    public void getResultadoSorteio(android.view.View view) {
        TextView textSorteado = findViewById(R.id.textNumeroSorteado);

        EditText editInferior = findViewById(R.id.editNumberInferior);
        EditText editSuperior = findViewById(R.id.editNumberSuperior);

        int iInferior = Integer.valueOf(editInferior.getText().toString());
        int iSuperior = Integer.valueOf(editSuperior.getText().toString());

        int iSorteado = sorteiaNumeros(iInferior, iSuperior);
        String sResposta = String.format("%d", iSorteado);
        textSorteado.setText(sResposta);
    }
}