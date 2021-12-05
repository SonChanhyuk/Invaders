package screen;

import engine.Cooldown;
import engine.Core;

import java.awt.event.KeyEvent;

/**
 * Implements the manual screen.
 */
public class ManualScreen extends Screen{
    private Cooldown selectionCooldown;
    private static final int SELECTION_TIME = 200;

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
    public ManualScreen(final int width, final int height, final int fps) {
        super(width, height, fps);
        this.returnCode = 1;
        this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
        this.selectionCooldown.reset();
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
     * Draws the elements associated with the screen.
     */
    private void draw() {
        drawManager.initDrawing(this);
        drawManager.drawManualTitle(this);
        drawManager.drawManual(this);
        drawManager.completeDrawing(this);
    }

    /**
     * Updates the elements on screen and checks for events.
     */
    protected final void update() {
        super.update();

        draw();
        if (inputManager.isKeyDown(KeyEvent.VK_SPACE)
                && this.inputDelay.checkFinished())
            this.isRunning = false;
    }
}
