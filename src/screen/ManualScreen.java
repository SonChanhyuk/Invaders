package screen;

import engine.Cooldown;
import engine.Core;
import engine.SoundPlayer;

import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ManualScreen extends Screen{
    private Cooldown selectionCooldown;
    private static final int SELECTION_TIME = 200;

    public ManualScreen(final int width, final int height, final int fps) {
        super(width, height, fps);
        this.returnCode = 1;
        this.ismusic = true;
        try {
			sound();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
		}
        this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
        this.selectionCooldown.reset();
    }

    public final int run() {
        super.run();

        return this.returnCode;
    }

    private void draw() {
        drawManager.initDrawing(this);
        drawManager.drawManualTitle(this);
        drawManager.drawManual(this);
        drawManager.completeDrawing(this);
    }

    protected final void update() {
        super.update();

        draw();
        if (inputManager.isKeyDown(KeyEvent.VK_SPACE)
                && this.inputDelay.checkFinished()) {
        	try {
    			sound();
    		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
    			// TODO Auto-generated catch block
    		}
        	this.isRunning = false;
        }
    }
    
    private void sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if(this.ismusic) {
			music = new SoundPlayer("menues.wav");
			music.play();
			logger.info("Start Music");
		}
		else if(!this.ismusic) {
			music.stop();
			logger.info("End Music");
		}
		this.ismusic = false;
	}
}
