package ifsc.bischof.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Ciclo de Vida Activity ", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Ciclo de Vida Activity ", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Ciclo de Vida Activity ", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Ciclo de Vida Activity ", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Ciclo de Vida Activity ", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Ciclo de Vida Activity ", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Ciclo de Vida Activity ", "onDestroy");
    }

    public void contandoClicks(android.view.View view) {
        TextView texto = findViewById(R.id.textView);

        String sTexto = texto.getText().toString();
        int iQtdClicks = 0;

        if (!sTexto.equals("Josef"))
            iQtdClicks = Integer.valueOf(sTexto);

        ++iQtdClicks;

        String sRes = String.format("%d", iQtdClicks);
        texto.setText(sRes);
    }
}