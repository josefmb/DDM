package ifsc.bischof.sorteioApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<Integer> aHistorico = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sortear(android.view.View view) {
        TextView textSorteado = findViewById(R.id.textNumeroSorteado);

        EditText editInferior = findViewById(R.id.editNumberInferior);
        EditText editSuperior = findViewById(R.id.editNumberSuperior);

        int iInferior = Integer.valueOf(editInferior.getText().toString());
        int iSuperior = Integer.valueOf(editSuperior.getText().toString());

        Intervalo intervalo = new Intervalo(iInferior, iSuperior);
        Intervalo.Sorteador sorteador = new Intervalo.Sorteador();

        int iSorteado = sorteador.sorteio(intervalo);
        aHistorico.add(iSorteado);

        String sResposta = String.format("%d", iSorteado);
        textSorteado.setText(sResposta);

        Log.d("Número sorteado ", sResposta);
        verificaHistorico();
    }

    private String montaHistorico() {
        String sRes = "";

        for (int historico : aHistorico)
            sRes += String.format("-- %d --\n", historico);

        return sRes;
    }

    private void verificaHistorico() {
        String sHistorico = montaHistorico();
        Log.d("Histórico sorteios ", sHistorico);
    }
}