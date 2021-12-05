package screen;

import engine.Cooldown;

import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

/**
 * Implements the pause screen.
 */
public class PauseScreen extends Screen{
    /** time that the game pause was started */
    private long startTime;
    /** how long the game was paused */
    private long pauseTime;

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
    public PauseScreen(final int width, final int height, final int fps) {
        super(width, height, fps);
        this.isRunning = false;
        //GameScreen's return code
        this.returnCode = 2;
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
        pauseTime = System.currentTimeMillis() - startTime;
        draw();
    }

    /**
     * Draws the elements associated with the screen.
     */
    private void draw() {
        drawManager.initDrawing(this);

        drawManager.drawPause(this);

        drawManager.completeDrawing(this);
    }

    /**
     * Check the ESC key was pressed and Initialize PauseScreen
     *
     * @param countdown
     *          Check if it is doing countdown.
     */
    public void checkPause(boolean countdown) {
        //esc key listener
        boolean esc = inputManager.isKeyDown(KeyEvent.VK_ESCAPE);
        if(esc && countdown) {
            this.isRunning = !this.isRunning;
            if (this.isRunning){
                this.startTime = System.currentTimeMillis();
                this.logger.info("Screen is paused.");
            }
            while(esc){
                esc = inputManager.isKeyDown(KeyEvent.VK_ESCAPE);
                try {
                    TimeUnit.MILLISECONDS.sleep(1000/this.fps);
                } catch (InterruptedException ignored) {
                }
            }
            if (!this.isRunning){
                this.logger.info("Screen is resumed.");
                this.applyPauseTime();
            }
        }
    }

    /**
     * set start time when screen is paused.
     *
     * @param startTime
     *          start time that screen is paused.
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * apply paused time to cool down class.
     */
    public void applyPauseTime(){
        Cooldown.addPauseMilli(pauseTime);
    }
}
