package ifsc.bischof.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    SeekBar seekBarTime;
    SeekBar seekBarVolume;
    TextView textViewTime;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textViewTime = findViewById(R.id.textViewTime);
        this.seekBarTime  = findViewById(R.id.seekBar);
        this.seekBarVolume = findViewById(R.id.seekBarVolume);
        this.mediaPlayer  = MediaPlayer.create(getApplicationContext(), R.raw.audio);
        this.audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        seekBarVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

        this.seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, AudioManager.FLAG_SHOW_UI);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBar.setMax(mediaPlayer.getDuration());
                mediaPlayer.seekTo(i);

                atualizarTime();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void atualizarTime()
    {
        String strTime = convertDurationMillis(mediaPlayer.getDuration());
        strTime +=  " / " + convertDurationMillis(mediaPlayer.getCurrentPosition());

        textViewTime.setText(strTime);
        seekBarTime.setMax(mediaPlayer.getDuration());
        seekBarTime.setProgress(mediaPlayer.getCurrentPosition());

        seekBarVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
    }

    public void start(View v)
    {
        this.mediaPlayer.start();
        iniciaTimer();
    }

    public void pause(View v)
    {
        this.mediaPlayer.pause();
    }

    public void stop(View v)
    {
        this.mediaPlayer.stop();
        this.mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.audio);
    }

    public String convertDurationMillis(Integer getDurationInMillis){

        int getDurationMillis = getDurationInMillis;
        String convertHours = String.format("%02d", TimeUnit.MILLISECONDS.toHours(getDurationMillis));
        String convertMinutes = String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(getDurationMillis));
        String convertSeconds = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(getDurationMillis) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(getDurationMillis)));

        String getDuration = convertHours + ":" + convertMinutes + ":" + convertSeconds;

        return getDuration;
    }

    Timer timer;

    public void iniciaTimer()
    {
        this.timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        atualizarTime();
                    }
                });
            }
        };

        this.timer.schedule(timerTask, 0, 1000);
    }

    public void abreTelaVideo(View v)
    {
        Intent intent = new Intent(getApplicationContext(), PlayerVideoActivity.class);
        startActivity(intent);
    }
}
