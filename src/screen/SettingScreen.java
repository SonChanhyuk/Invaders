package screen;

import engine.Cooldown;
import engine.Core;
import engine.SoundPlayer;

import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SettingScreen extends Screen {
	
	private static final int SELECTION_TIME = 200;
	
	private static final int RightKey = 1;
	private static final int LeftKey = 2;
	private static final int ShootKey = 3;
	private static final int Exit = 4;
	/**Represent current selection in setting screen*/
	private static int functionCode;
	/**Represent if the setting Screen is ready to accept new key input*/
	private static boolean functionCode2;
	
	private Cooldown selectionCooldown;

	public SettingScreen(final int width, final int height, final int fps) {
		super(width, height, fps);
		
		this.returnCode = 1;
		this.ismusic = true;
		try {
			sound();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
		this.selectionCooldown.reset();
		this.functionCode = 1;
		this.functionCode2 = false;
	}

	public final int run() {
		super.run();
		
		return this.returnCode;
	}
	
	private void draw() {
		drawManager.initDrawing(this);
		drawManager.drawSetting(this);
		drawManager.drawSelection(this, this.functionCode, this.functionCode2);
		drawManager.completeDrawing(this);
	}
	
	protected final void update() {
		super.update();
		
		draw();
		if (this.selectionCooldown.checkFinished() && this.inputDelay.checkFinished()) {
			int userInput = inputManager.getKeyCode();
			if (userInput == KeyEvent.VK_SPACE || this.functionCode2 == true) {
				if (userInput == KeyEvent.VK_SPACE && this.functionCode == Exit) {
					try {
						sound();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.logger.info("false");
					this.isRunning = false;
				}
				SwitchingKey(userInput);
			}
			else if (userInput == KeyEvent.VK_UP
					|| inputManager.isKeyDown(KeyEvent.VK_W)) {
				previousFunction();
				this.selectionCooldown.reset();
			}
			else if (userInput == KeyEvent.VK_DOWN
					|| inputManager.isKeyDown(KeyEvent.VK_S)) {
				nextFunction();
				this.selectionCooldown.reset();
			}

				
		}
	}
	/** next selection(占쎈툡占쎌삸 獄쎻뫚堉�)
	 * */
	private void nextFunction() {
		if (this.functionCode == Exit)
			this.functionCode = ShootKey;
		else
			this.functionCode++;
	}

	/** previous selection(占쎌맮 獄쎻뫚堉�)
	 * */
	private void previousFunction() {
		if (this.functionCode == RightKey)
			this.functionCode = Exit;
		else
			this.functionCode--;
	}
	
	private void SwitchingKey(int userInput) {
		if (this.functionCode2 == false) {
			this.functionCode2 = true;
			this.selectionCooldown.reset();
		}
		if (this.selectionCooldown.checkFinished()) {//functionCOde 2 is true and cooldown is finished
			if (this.functionCode == RightKey) {//
				if (userInput != 0) {
					key_R = userInput;
					this.logger.info("Right Key has been changed to " + key_R);
					this.functionCode2 = false;
					this.selectionCooldown.reset();
				}
			} else if (this.functionCode == LeftKey) {
				if (userInput != 0) {
					key_L = userInput;
					this.logger.info("Right Key has been changed to " + key_L);
					this.functionCode2 = false;
					this.selectionCooldown.reset();
				}
			} else if (this.functionCode == ShootKey) {
				if (userInput != 0) {
					key_Shoot = userInput;
					this.logger.info("Shoot Key has been changed to " + key_Shoot);
					this.functionCode2 = false;
					this.selectionCooldown.reset();
				}
			}
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
