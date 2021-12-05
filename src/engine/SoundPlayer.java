package engine;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Initialize both the clip and streams.
 */
public class SoundPlayer {

    //define storage for start position
    Long nowFrame;
    Clip clip;

    // get the clip status
    String thestatus;

    AudioInputStream audioStream;

    static String thePath;

    /**
     * Constructor.
     *
     * @param music
     *          Filename of a sound.
     * @throws UnsupportedAudioFileException
     *          In case of loading problems.
     * @throws IOException
     *          In case of loading problems.
     * @throws LineUnavailableException
     *          In case of loading problems.
     */
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

    /**
     * Play the clip.
     */
    public void play()
    {
        //start the clip
        clip.start();

        thestatus = "play";
    }

    /**
     * Stop the clip.
     *
     * @throws UnsupportedAudioFileException
     *          In case of loading problems.
     * @throws IOException
     *          In case of loading problems.
     * @throws LineUnavailableException
     *          In case of loading problems.
     */
    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        nowFrame = 0L;
        clip.stop();
        clip.close();
    }
}


