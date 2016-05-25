package dk.sdu.mmmi.cbse.osgicollision;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;
import java.util.Random;

/**
 *
 * @author anon
 */
public class Factory {

    public Entity createPlayer(GameData gameData) {
        Entity player = new Entity();
        player.setType(PLAYER);

        player.setPosition(100, 100);

        player.setMaxSpeed(300);
        player.setAcceleration(200);
        player.setDeacceleration(10);

        player.setRadians(3.1415f / 2);
        player.setRotationSpeed(3);

        float[] shapex = new float[4];
        float[] shapey = new float[4];

        shapex[0] = (float) (player.getX() + Math.cos(player.getRadians()) * 8);
        shapey[0] = (float) (player.getY() + Math.sin(player.getRadians()) * 8);

        shapex[1] = (float) (player.getX() + Math.cos(player.getRadians() - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (player.getY() + Math.sin(player.getRadians() - 4 * 3.1415f / 5) * 8);

        shapex[2] = (float) (player.getX() + Math.cos(player.getRadians() + 3.1415f) * 5);
        shapey[2] = (float) (player.getY() + Math.sin(player.getRadians() + 3.1415f) * 5);

        shapex[3] = (float) (player.getX() + Math.cos(player.getRadians() + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (player.getY() + Math.sin(player.getRadians() + 4 * 3.1415f / 5) * 8);

        player.setShapeX(shapex);
        player.setShapeY(shapey);

        return player;
    }

    public Entity createAsteroid(float x, float y) {
        Random generator = new Random();

        Entity asteroid = new Entity();
        asteroid.setType(EntityType.ASTEROIDS);

        int numpoints;

        asteroid.setPosition(x, y);
        asteroid.setRotationSpeed(generator.nextInt(2) + 1);
        asteroid.setRadians(generator.nextInt((int) (2 * 3.1415f)));

        numpoints = 12;
        asteroid.setSizeType(EntityType.LARGE);
        asteroid.setRect(40, 40);

        asteroid.setShapeX(new float[numpoints]);
        asteroid.setShapeY(new float[numpoints]);

        asteroid.setMaxSpeed(generator.nextInt(10));

        float dist[] = new float[numpoints];
        for (int j = 0; j < numpoints; j++) {
            dist[j] = generator.nextInt((asteroid.getWidth() - asteroid.getWidth() / 2)) + asteroid.getWidth() / 2;
        }
        asteroid.setDist(dist);

        asteroid.setDx((float) Math.cos(asteroid.getRadians()) * asteroid.getMaxSpeed());
        asteroid.setDy((float) Math.sin(asteroid.getRadians()) * asteroid.getMaxSpeed());

        float angle = 0;
        float[] shapex = new float[dist.length];
        float[] shapey = new float[dist.length];

        for (int i = 0; i < dist.length; i++) {
            shapex[i] = asteroid.getX() + (float) Math.cos(angle + asteroid.getRadians()) * dist[i];
            shapey[i] = asteroid.getY() + (float) Math.sin(angle + asteroid.getRadians()) * dist[i];
            angle += 2 * 3.1415f / dist.length;
        }

        asteroid.setShapeX(shapex);
        asteroid.setShapeY(shapey);

        return asteroid;
    }

    public Entity createEnemy() {
        Entity enemy = new Entity();
        enemy.setType(EntityType.ENEMY);

        float y = 500;
        float x = 500;

        enemy.setPosition(x, y);
        enemy.setSizeType(EntityType.LARGE);
        float[] shapex = new float[6];
        float[] shapey = new float[6];

        shapex[0] = enemy.getX() - 10;
        shapey[0] = enemy.getY();

        shapex[1] = enemy.getX() - 3;
        shapey[1] = enemy.getY() - 5;

        shapex[2] = enemy.getX() + 3;
        shapey[2] = enemy.getY() - 5;

        shapex[3] = enemy.getX() + 10;
        shapey[3] = enemy.getY();

        shapex[4] = enemy.getX() + 3;
        shapey[4] = enemy.getY() + 5;

        shapex[5] = enemy.getX() - 3;
        shapey[5] = enemy.getY() + 5;

        enemy.setShapeX(shapex);
        enemy.setShapeY(shapey);

        return enemy;
    }

    public Entity createBullet(Entity e, float x, float y) {
        Entity bullet = new Entity();
        bullet.setType(EntityType.BULLET);

        bullet.setMaxSpeed(350);
        bullet.setRadians(e.getRadians());
        bullet.setPosition(x, y);

        bullet.setLifeTimer(0);
        bullet.setLifeTime(1);

        bullet.setRect(2, 2);

        bullet.setOwnerID(e.getID());

        float[] shapex = new float[2];
        float[] shapey = new float[2];

        shapex[0] = (float) (bullet.getX() + 1);
        shapey[0] = (float) (bullet.getY() + 1);

        shapex[1] = (float) (bullet.getX());
        shapey[1] = (float) (bullet.getY());

        bullet.setShapeX(shapex);
        bullet.setShapeY(shapey);
        return bullet;
    }

}
