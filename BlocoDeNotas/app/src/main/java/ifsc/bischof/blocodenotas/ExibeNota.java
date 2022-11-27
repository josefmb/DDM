package ifsc.bischof.blocodenotas;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExibeNota extends AppCompatActivity {

    TextView textViewTitulo;
    TextView textViewDescricao;
    NotasController notasController;
    Nota nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_nota);

        int idNota = getIntent().getExtras().getInt("id");

        this.textViewTitulo    = findViewById(R.id.textViewTitulo   );
        this.textViewDescricao = findViewById(R.id.textViewDescricao);

        this.notasController = new NotasController(getBaseContext());

        this.nota = notasController.getNotas().get(idNota);

        this.textViewTitulo   .setText(this.nota.getTitulo());
        this.textViewDescricao.setText(this.nota.getTexto ());
    }
}
