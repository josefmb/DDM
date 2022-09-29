package com.ifsc.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setandoTexto(android.view.View view) {
        TextView texto = findViewById(R.id.textViewTexto);
        texto.setText("Camila Te Amo s2");
    }

    public void contandoClicks(android.view.View view) {
        TextView texto = findViewById(R.id.textViewTexto);
        int iQtdClicks = Integer.valueOf(texto.getText().toString());

        ++iQtdClicks;

        String sRes = String.format("%d", iQtdClicks);
        texto.setText(sRes);
    }
}