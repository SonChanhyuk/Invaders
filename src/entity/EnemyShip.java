package entity;

import java.awt.Color;

import engine.Cooldown;
import engine.Core;
import engine.DrawManager.SpriteType;

/**
 * Implements a enemy ship, to be destroyed by the player.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class EnemyShip extends Entity {
	
	/** Point value of a type A enemy. */
	private static final int A_TYPE_POINTS = 10;
	/** Point value of a type B enemy. */
	private static final int B_TYPE_POINTS = 20;
	/** Point value of a type C enemy. */
	private static final int C_TYPE_POINTS = 30;
	/** Point value of a bonus enemy. */
	private static final int BONUS_TYPE_POINTS = 100;
	/** Point value of a boss enemy. */
	private static final int BOSS_TYPE_POINTS = 1000;

	/** Cooldown between sprite changes. */
	private Cooldown animationCooldown;
	/** Checks if the ship has been hit by a bullet. */
	private boolean isDestroyed;
	/** Values of the ship, in points, when destroyed. */
	private int pointValue;
	/** hp of the ship */
	private int life;
	/** is boss. */
	private boolean isBoss;

	/**
	 * Constructor, establishes the ship's properties.
	 * 
	 * @param positionX
	 *            Initial position of the ship in the X axis.
	 * @param positionY
	 *            Initial position of the ship in the Y axis.
	 * @param spriteType
	 *            Sprite type, image corresponding to the ship.
	 */
	public EnemyShip(final int positionX, final int positionY,
			final SpriteType spriteType) {
		super(positionX, positionY, 12 * 2, 8 * 2, Color.WHITE);

		this.spriteType = spriteType;
		this.animationCooldown = Core.getCooldown(500);
		this.isDestroyed = false;
		this.life = 1;
		this.isBoss = false;

		switch (this.spriteType) {
		case EnemyShipA1:
		case EnemyShipA2:
			this.pointValue = A_TYPE_POINTS;
			break;
		case EnemyShipB1:
		case EnemyShipB2:
			this.pointValue = B_TYPE_POINTS;
			this.life = 2;
			setColor(Color.ORANGE);
			break;
		case EnemyShipC1:
		case EnemyShipC2:
			this.pointValue = C_TYPE_POINTS;
			this.life = 3;
			setColor(Color.RED);
			break;
		default:
			this.pointValue = 0;
			break;
		}
	}

	/**
	 * Constructor, establishes the ship's properties for a special ship, with
	 * known starting properties.
	 */
	public EnemyShip() {
		super(-32, 60, 16 * 2, 7 * 2, Color.RED);

		this.spriteType = SpriteType.EnemyShipSpecial;
		this.isDestroyed = false;
		this.pointValue = BONUS_TYPE_POINTS;
		this.life = 1;
		this.isBoss = false;
	}

	/**
	 * Constructor, establishes the ship's properties for a boss ship
	 *
	 * @param life
	 * 			Set boss ship's HP with life.
	 */
	public EnemyShip(int life) {
		super(20, 100, 36 * 2, 24 * 2, Color.GREEN);

		this.spriteType = SpriteType.BossShip1;
		this.animationCooldown = Core.getCooldown(500);
		this.isDestroyed = false;
		this.pointValue = BOSS_TYPE_POINTS;
		this.life = life;
		this.isBoss = true;
	}

	/**
	 * Getter for the score bonus if this ship is destroyed.
	 * 
	 * @return Value of the ship.
	 */
	public final int getPointValue() {
		return this.pointValue;
	}

	/**
	 * Moves the ship the specified distance.
	 * 
	 * @param distanceX
	 *            Distance to move in the X axis.
	 * @param distanceY
	 *            Distance to move in the Y axis.
	 */
	public final void move(final int distanceX, final int distanceY) {
		this.positionX += distanceX;
		this.positionY += distanceY;
	}

	/**
	 * Updates attributes, mainly used for animation purposes.
	 */
	public final void update() {
		if (this.animationCooldown.checkFinished()) {
			this.animationCooldown.reset();

			switch (this.spriteType) {
			case EnemyShipA1:
				this.spriteType = SpriteType.EnemyShipA2;
				break;
			case EnemyShipA2:
				this.spriteType = SpriteType.EnemyShipA1;
				break;
			case EnemyShipB1:
				this.spriteType = SpriteType.EnemyShipB2;
				break;
			case EnemyShipB2:
				this.spriteType = SpriteType.EnemyShipB1;
				break;
			case EnemyShipC1:
				this.spriteType = SpriteType.EnemyShipC2;
				break;
			case EnemyShipC2:
				this.spriteType = SpriteType.EnemyShipC1;
				break;
			case BossShip1:
				this.spriteType = SpriteType.BossShip2;
				break;
			case BossShip2:
				this.spriteType = SpriteType.BossShip1;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Checks if the ship is boss ship.
	 *
	 * @return True if the ship is boss ship.
	 */
	public boolean getIsBoss() { return this.isBoss; }

	/**
	 * @return ship's life
	 */
	public int getLife() { return this.life; }

	/**
	 * spend a life
	 */
	public void spendLife() { this.life--; }

	/**
	 * Destroys the ship, causing an explosion.
	 */
	public final void destroy() {
		this.isDestroyed = true;
		this.spriteType = SpriteType.Explosion;
	}

	/**
	 * Checks if the ship has been destroyed.
	 * 
	 * @return True if the ship has been destroyed.
	 */
	public final boolean isDestroyed() {
		return this.isDestroyed;
	}
}
