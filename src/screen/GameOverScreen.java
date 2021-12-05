package screen;

import engine.Cooldown;

import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

/**
 * Implements the game over screen.
 */
public class GameOverScreen extends Screen{
    
    private long gameStartTime;
    
    private static final int SEPARATION_LINE_HEIGHT = 40;
    
   // private static final int INPUT_DELAY = 6000;

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
    public GameOverScreen(final int width, final int height, final int fps) {
        super(width, height, fps);
        
        //this.gameStartTime = System.currentTimeMillis();
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
    public final void update() {
        super.update();
        
        draw();
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.isRunning = false;
    }

    /**
     * Draws the elements associated with the screen.
     */
    private void draw() {
        drawManager.drawHorizontalLine(this,SEPARATION_LINE_HEIGHT - 1);
        
        if (!this.inputDelay.checkFinished()) {
			drawManager.drawGameOver(this);
			drawManager.drawHorizontalLine(this, this.height / 2 - this.height
					/ 12);
			drawManager.drawHorizontalLine(this, this.height / 2 + this.height
					/ 12);
			logger.info("Game Over!");
		}

        drawManager.completeDrawing(this);
    }
}
