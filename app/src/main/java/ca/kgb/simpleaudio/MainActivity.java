package ca.kgb.simpleaudio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Uri myUri = "...."; // initialize Uri here
//        MediaPlayer mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        mediaPlayer.setDataSource(getApplicationContext(), myUri);
//// or just mediaPlayer.setDataSource(mFileName);
//        mediaPlayer.prepare(); // must call prepare first

        String url = "https://dl.dropboxusercontent.com/u/10281242/sample_audio.mp3";
        mMediaPlayer = new MediaPlayer();
// Set type to streaming
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
// Listen for if the audio file can't be prepared
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                // ... react appropriately ...
                // The MediaPlayer has moved to the Error state, must be reset!
                return false;
            }
        });
// Attach to when audio file is prepared for playing
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mMediaPlayer.start();
            }
        });
// Set the data source to the remote URL
        try {
            mMediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
// Trigger an async preparation which will file listener when completed
        mMediaPlayer.prepareAsync();

        //mMediaPlayer = MediaPlayer.create(this, R.raw.kalimba);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMediaPlayer.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }
}
