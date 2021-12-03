package engine;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer {

    //define storage for start position
    Long nowFrame;
    Clip clip;

    // get the clip status
    String thestatus;

    AudioInputStream audioStream;

    static String thePath;

    // initialize both the clip and streams
    public SoundPlayer(String music)
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        InputStream inputStream = null;
        inputStream = FileManager.class.getClassLoader().getResourceAsStream(music);
        // the input stream object
        audioStream =
                AudioSystem.getAudioInputStream(inputStream);

        // the reference to the clip
        clip = AudioSystem.getClip();

        clip.open(audioStream);

    }
    public void play()
    {
        //start the clip
        clip.start();

        thestatus = "play";
    }
    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        nowFrame = 0L;
        clip.stop();
        clip.close();
    }
}


