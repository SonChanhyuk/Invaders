package screen;

import engine.Core;
import engine.Score;
import engine.SoundPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

/**
 * Implements the high scores screen, it shows player records.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class HighScoreScreen extends Screen {

	/** List of past high scores. */
	private List<Score> highScores;

	/**
	 * Constructor, establishes the properties of the screen.
	 * 
	 * @param width
	 *            Screen width.
	 * @param height
	 *            Screen height.
	 * @param fps
	 *            Frames per second, frame rate at which the game is run.
	 */
	public HighScoreScreen(final int width, final int height, final int fps) {
		super(width, height, fps);

		this.returnCode = 1;
		this.ismusic = true;
		
		try {
			sound();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			this.highScores = Core.getFileManager().loadHighScores();
		} catch (NumberFormatException | IOException e) {
			logger.warning("Couldn't load high scores!");
		}
	}

	/**
	 * Starts the action.
	 * 
	 * @return Next screen code.
	 */
	public final int run() {
		super.run();

		return this.returnCode;
	}

	/**
	 * Updates the elements on screen and checks for events.
	 */
	protected final void update() {
		super.update();

		draw();
		if (inputManager.isKeyDown(KeyEvent.VK_SPACE)
				&& this.inputDelay.checkFinished()) {
			try {
				sound();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.isRunning = false;
		}
	}

	/**
	 * Draws the elements associated with the screen.
	 */
	private void draw() {
		drawManager.initDrawing(this);

		drawManager.drawHighScoreMenu(this);
		drawManager.drawHighScores(this, this.highScores);

		drawManager.completeDrawing(this);
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
