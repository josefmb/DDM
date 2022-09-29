package ifsc.bischof.calculoIMC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculaIMC(android.view.View view) {
        Intent resultadoIMC = new Intent(this, resultadoIMC.class);

        EditText edtNome   = findViewById(R.id.editTextNome        );
        EditText edtPeso   = findViewById(R.id.editTextNumberPeso  );
        EditText edtAltura = findViewById(R.id.editTextNumberAltura);

        String sNome   = edtNome.getText().toString();

        Double dPeso   = Double.valueOf(edtPeso  .getText().toString());
        Double dAltura = Double.valueOf(edtAltura.getText().toString());

        String sPeso   = String.format("%.2f", dPeso  );
        String sAltura = String.format("%.2f", dAltura);

        Double dIMC = dPeso / (Math.pow(dAltura, 2));
        String sIMC = String.format("%.2f", dIMC);

        resultadoIMC.putExtra("nome"  , sNome  );
        resultadoIMC.putExtra("peso"  , sPeso  );
        resultadoIMC.putExtra("altura", sAltura);
        resultadoIMC.putExtra("imc"   , sIMC   );

        startActivity(resultadoIMC);
    }
}