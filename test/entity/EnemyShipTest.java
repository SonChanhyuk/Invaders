package entity;

import engine.DrawManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class EnemyShipTest {

    EnemyShip enemyShipA;
    EnemyShip enemyShipB;
    EnemyShip enemyShipC;
    EnemyShip specialShip;

    @BeforeEach
    void setUp() {
        enemyShipA = new EnemyShip(100,100, DrawManager.SpriteType.EnemyShipA1);
        enemyShipB = new EnemyShip(100,100, DrawManager.SpriteType.EnemyShipB1);
        enemyShipC = new EnemyShip(100,100, DrawManager.SpriteType.EnemyShipC1);
        specialShip = new EnemyShip();
    }

    @Test
    void pointValue() {
        assertEquals(10,enemyShipA.getPointValue());
        assertEquals(20,enemyShipB.getPointValue());
        assertEquals(30,enemyShipC.getPointValue());
        assertEquals(100,specialShip.getPointValue());
    }

    @Test
    void move() {
        enemyShipA.move(100,10);
        assertEquals(200,enemyShipA.getPositionX());
        assertEquals(110,enemyShipA.getPositionY());
    }

    @Test
    void destroy() {
        assertFalse(enemyShipA.isDestroyed());
        enemyShipA.destroy();
        assertTrue(enemyShipA.isDestroyed());
    }

    @Test
    void destroyFail() {
        assertTrue(enemyShipA.isDestroyed());
    }

}