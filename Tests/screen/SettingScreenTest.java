package screen;

import engine.Frame;
import org.junit.jupiter.api.Test;

class SettingScreenTest {

    @Test
    void generateSettingScreen() {
        SettingScreen setting = new SettingScreen(500,500,60);
        Frame frame = new Frame(500,500);
        setting.drawManager.setFrame(frame);
        setting.run();
    }

}
