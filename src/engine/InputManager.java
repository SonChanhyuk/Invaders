package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Manages keyboard input for the provided screen.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public final class InputManager implements KeyListener {

	/** Number of recognised keys. */
	private static final int NUM_KEYS = 256;
	/** Array with the keys marked as pressed or not. */
	private static boolean[] keys;
	/** Singleton instance of the class. */
	private static InputManager instance;
	
	private static int keycode;

	/**
	 * Private constructor.
	 */
	private InputManager() {
		keys = new boolean[NUM_KEYS];
	}

	/**
	 * Returns shared instance of InputManager.
	 * 
	 * @return Shared instance of InputManager.
	 */
	protected static InputManager getInstance() {
		if (instance == null)
			instance = new InputManager();
		return instance;
	}

	/**
	 * Returns true if the provided key is currently pressed.
	 * 
	 * @param keyCode
	 *            Key number to check.
	 * @return Key state.
	 */
	public boolean isKeyDown(final int keyCode) {
		return keys[keyCode];
	}

	/**
	 * @return the key code.
	 */
	public int getKeyCode() {
		return keycode;
	}

	/**
	 * Changes the state of the key to pressed.
	 * 
	 * @param key
	 *            Key pressed.
	 */
	@Override
	public void keyPressed(final KeyEvent key) {
		if (key.getKeyCode() >= 0 && key.getKeyCode() < NUM_KEYS)
			keys[key.getKeyCode()] = true;
			keycode = key.getKeyCode();
	}

	/**
	 * Changes the state of the key to not pressed.
	 * 
	 * @param key
	 *            Key released.
	 */
	@Override
	public void keyReleased(final KeyEvent key) {
		if (key.getKeyCode() >= 0 && key.getKeyCode() < NUM_KEYS)
			keys[key.getKeyCode()] = false;
			keycode = -0;
	}

	/**
	 * Does nothing.
	 * 
	 * @param key
	 *            Key typed.
	 */
	@Override
	public void keyTyped(final KeyEvent key) {

	}
}