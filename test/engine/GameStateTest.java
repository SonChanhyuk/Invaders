package engine;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @org.junit.jupiter.api.Test
    void getLevel() {
        GameState gs = new GameState(1,0,3,0,0);
        assertEquals(gs.getLevel(), 1);
    }
}