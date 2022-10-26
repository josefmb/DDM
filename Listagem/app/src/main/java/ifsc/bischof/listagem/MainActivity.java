package ifsc.bischof.listagem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    PackageManager packageManager;

    String[] aPaises = new String[]{
            "Alemanha", "Argentina", "Brasil", "Colombia", "Dinamarca",
            "Equador", "Estonia", "Finlandia", "Guatemala", "Islandia"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listView);

        packageManager = getPackageManager();
        ArrayAdapter<PackageInfo> aPackageInfo = (ArrayAdapter<PackageInfo>) packageManager.getInstalledPackages(packageManager.GET_ACTIVITIES);

        ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                aPaises
        );

        lv.setAdapter(aAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), Integer.toString(i), Toast.LENGTH_LONG).show();
            }
        });
    }
}