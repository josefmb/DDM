package ifsc.bischof.laucher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import ifsc.bischof.laucher.controller.FrutaController;

public class ExibeFruta extends AppCompatActivity {
    TextView  textViewNomeFruta;
    ImageView imageViewFruta   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_fruta);

        int id = getIntent().getExtras().getInt("id");

        textViewNomeFruta = findViewById(R.id.textViewNome);
        imageViewFruta    = findViewById(R.id.imageView   );

        FrutaController frutaController = new FrutaController();

        textViewNomeFruta.setText         (frutaController.getFrutas()[id].getNome  ());
        imageViewFruta   .setImageResource(frutaController.getFrutas()[id].getImagem());
    }
}
