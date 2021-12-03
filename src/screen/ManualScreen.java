package screen;

import engine.Cooldown;
import engine.Core;

import java.awt.event.KeyEvent;

public class ManualScreen extends Screen{
    private Cooldown selectionCooldown;
    private static final int SELECTION_TIME = 200;

    public ManualScreen(final int width, final int height, final int fps) {
        super(width, height, fps);
        this.returnCode = 1;
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
                && this.inputDelay.checkFinished())
            this.isRunning = false;
    }
}
