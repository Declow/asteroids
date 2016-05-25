package dk.sdu.mmmi.cbse.osgicollision;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anon
 */
public class CollisionControlSystemTest {

    private static Entity player;
    private static Entity enemy;
    private static Entity asteroid;
    private static GameData gameData;
    private static Map<String, Entity> world;
    private static Factory fc = new Factory();

    public CollisionControlSystemTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        gameData = new GameData();
        gameData.setDisplayHeight(1000);
        gameData.setDisplayWidth(1000);

        world = new ConcurrentHashMap<>();

        enemy = fc.createEnemy();
        world.put(enemy.getID(), enemy);

        player = fc.createPlayer(gameData);
        world.put(player.getID(), player);

        asteroid = fc.createAsteroid(player.getX(), player.getY());
        world.put(asteroid.getID(), asteroid);

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of process method, of class CollisionControlSystem player and asteroid.
     */
    @Test
    public void testProcess() {
        /**
         * If the players position is changed to the center of the screen then
         * the player has collided with an asteroid.
         */
        CollisionControlSystem instance = new CollisionControlSystem();
        instance.process(gameData, world, player);
        if (player.getX() != gameData.getDisplayHeight() / 2 && player.getY() != gameData.getDisplayWidth() / 2) {
            fail();
        }
    }

    @Test
    public void testProcessEnemy() {
        /**
         * If a bullet and an enemy collide then both should set removed to
         * true.
         */
        CollisionControlSystem instance = new CollisionControlSystem();

        Entity bullet = fc.createBullet(player, enemy.getX(), enemy.getY());
        world.put(bullet.getID(), bullet);
        instance.process(gameData, world, enemy);
        if (!enemy.isRemoved() && !bullet.isRemoved()) {
            fail();
        }
    }

    @Test
    public void testProcessAsteroid() {
        CollisionControlSystem instance = new CollisionControlSystem();

        /**
         * If an asteroid and a bullet collide then both should set removed to
         * true.
         */
        Entity bullet2 = fc.createBullet(enemy, asteroid.getX(), asteroid.getY());
        world.put(bullet2.getID(), bullet2);
        instance.process(gameData, world, asteroid);
        if (asteroid.isRemoved() != true && bullet2.isRemoved() != true) {
            fail();
        }
    }
}
