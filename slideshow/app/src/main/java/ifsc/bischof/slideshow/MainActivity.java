package ifsc.bischof.slideshow;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv_background = findViewById(R.id.iv_background);
        AnimationDrawable animationDrawable = (AnimationDrawable) iv_background.getDrawable();
        animationDrawable.start();
    }
}