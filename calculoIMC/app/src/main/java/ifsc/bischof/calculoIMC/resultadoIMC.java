package ifsc.bischof.calculoIMC;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class resultadoIMC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultadoimc);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle bundle = getIntent().getExtras();

        String sNome   = bundle.getString("nome"  );
        String sPeso   = bundle.getString("peso"  );
        String sAltura = bundle.getString("altura");

        sPeso   += " Kg";
        sAltura += " m" ;

        TextView textNome   = findViewById(R.id.textViewNomeResultado  );
        TextView textPeso   = findViewById(R.id.textViewPesoResultado  );
        TextView textAltura = findViewById(R.id.textViewAlturaResultado);
        TextView textIMC    = findViewById(R.id.textViewIMC            );

        String sIMC = textIMC.getText().toString() + bundle.getString("imc");

        textNome  .setText(sNome  );
        textPeso  .setText(sPeso  );
        textAltura.setText(sAltura);
        textIMC   .setText(sIMC   );
    }
}
