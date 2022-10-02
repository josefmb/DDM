package ifsc.bischof.mypaint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {
    SimplePaint simplePaint;
    ImageView ivColorPicker;
    ImageView ivBack       ;
    ImageView ivClear      ;
    ImageView ivSquare     ;
    ImageView ivCircle     ;
    ImageView ivLine       ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simplePaint   = findViewById(R.id.simplePaint  );
        ivColorPicker = findViewById(R.id.ivColorPicker);
        ivBack        = findViewById(R.id.back         );
        ivClear       = findViewById(R.id.clear        );
        ivSquare      = findViewById(R.id.square       );
        ivCircle      = findViewById(R.id.circle       );
        ivLine        = findViewById(R.id.line         );

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.line         : simplePaint.setStyleType(EStyleType.eLine  ); break;
                    case R.id.square       : simplePaint.setStyleType(EStyleType.eSquare); break;
                    case R.id.circle       : simplePaint.setStyleType(EStyleType.eCircle); break;
                    case R.id.clear        : simplePaint.removeDraw()                    ; break;
                    case R.id.back         : simplePaint.backDraw()                      ; break;
                    case R.id.ivColorPicker: colorPickerSelectColor()                    ; break;
                }
            }
        };

        ivColorPicker.setOnClickListener(onClickListener);
        ivBack       .setOnClickListener(onClickListener);
        ivClear      .setOnClickListener(onClickListener);
        ivSquare     .setOnClickListener(onClickListener);
        ivCircle     .setOnClickListener(onClickListener);
        ivLine       .setOnClickListener(onClickListener);
    }

    public void colorPickerSelectColor() {
        new ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPreferenceName("MyColorPickerDialog")
                .setPositiveButton(getString(R.string.confirm),
                        new ColorEnvelopeListener() {
                            @Override
                            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                setLayoutColor(envelope);
                            }
                        })
                .setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                .attachAlphaSlideBar(true) // the default value is true.
                .attachBrightnessSlideBar(true)  // the default value is true.
                .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                .show();
    }

    private void setLayoutColor(ColorEnvelope envelope) {
        simplePaint.setColor(Color.valueOf(envelope.getColor()));
        ivColorPicker.setColorFilter(Color.valueOf(envelope.getColor()).toArgb());
    }
}