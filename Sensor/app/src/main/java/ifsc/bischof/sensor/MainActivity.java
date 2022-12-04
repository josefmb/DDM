package ifsc.bischof.sensor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    CameraManager cameraManager;
    SeekBar seekbarHIGH, seekbarLOW;
    Switch switchPiscar;
    ToggleButton btnToggleOnOff;
    String idCamera;
    boolean lanternaLigada = false;
    int intensityHIGH = 1000;
    int intensityLOW = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchPiscar = findViewById(R.id.switchPiscar);
        btnToggleOnOff = findViewById(R.id.btnToggle);
        seekbarHIGH = findViewById(R.id.seekBarAlto);
        seekbarLOW = findViewById(R.id.seekBarBaixo);

        intensityHIGH = seekbarHIGH.getMax();
        seekbarHIGH.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                intensityHIGH = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        intensityLOW = seekbarLOW.getMax();
        seekbarLOW.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                intensityLOW = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            idCamera = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void btnToggleLanterna(View view) {
        VerificaLanterna();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void switchPiscar(View view) {
        VerificaLanterna();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void VerificaLanterna(){

        if (btnToggleOnOff.isChecked() == true) {
            if (switchPiscar.isChecked())
                Inicia_Piscar_HIGH();
            else
                setLanternaON();
        }
        else
        {
            setLanternaOFF();
            setPiscarOFF();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setLanternaON()    {
        try {
            lanternaLigada = true;
            cameraManager.setTorchMode(idCamera, true);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setLanternaOFF()    {
        try {
            lanternaLigada = false;
            cameraManager.setTorchMode(idCamera, false);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void Inicia_Piscar_HIGH()
    {
        if (!btnToggleOnOff.isChecked()) return;
        if (!switchPiscar.isChecked()) return;

        Timer timerHIGH = new Timer();
        timerHIGH.schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run()
            {
                try {
                    lanternaLigada = true;
                    cameraManager.setTorchMode(idCamera, true);
                    Inicia_Piscar_LOW();
                }
                catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        }, intensityLOW);
    }

    public void Inicia_Piscar_LOW()
    {
        Timer timerLOW = new Timer();
        timerLOW.schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run()
            {
                try {
                    lanternaLigada = false;
                    cameraManager.setTorchMode(idCamera, false);
                    Inicia_Piscar_HIGH();
                }
                catch (CameraAccessException e) {
                    e.printStackTrace();
                }

            }
        }, intensityHIGH);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setPiscarOFF()
    {
        try {
            cameraManager.setTorchMode(idCamera, false);
            lanternaLigada = false;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
