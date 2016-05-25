/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.Wrap;
import java.util.Map;
import java.util.Random;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author anon
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class AsteroidControlSystem implements IEntityProcessingService {

    private int level = 0;
    Map<String, Entity> world;
    int asteroidCount = 0;
    private Entity asteroid;

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {

        this.asteroid = entity;
        this.world = world;
        if (entity.getType() == EntityType.ASTEROIDS) {
            update(gameData);
            setShape(entity);
        }

        if (asteroidCount < 1) {
            createAsteroids(gameData);
            level += 1;
        }

    }

    private void update(GameData gameData) {

        if (asteroid.isRemoved()) {
            world.remove(asteroid.getID());
            asteroidCount += -1;
            if (asteroid.getSizeType() == EntityType.LARGE) {
                createAsteroid(gameData, EntityType.MEDIUM);
            } else if (asteroid.getSizeType() == EntityType.MEDIUM) {
                createAsteroid(gameData, EntityType.SMALL);
            }
        } else {

            float x = asteroid.getX() + asteroid.getDx() * asteroid.getMaxSpeed() * gameData.getDelta();
            float y = asteroid.getY() + asteroid.getDy() * asteroid.getMaxSpeed() * gameData.getDelta();

            asteroid.setPosition(x, y);

            asteroid.setRadians(asteroid.getRadians() + asteroid.getRotationSpeed() * gameData.getDelta());

            Wrap.wrap(gameData, asteroid);
            setShape(asteroid);
        }
    }

    private void createAsteroids(GameData gameData) {
        if (level == 0) {
            for (int i = 0; i < 5; i++) {
                createAsteroid(gameData, EntityType.LARGE);
            }
        } else if (level == 1) {
            for (int i = 0; i < 7; i++) {
                createAsteroid(gameData, EntityType.LARGE);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                createAsteroid(gameData, EntityType.LARGE);
            }
        }
    }

    public void createAsteroid(GameData gameData, EntityType size) {

        if (size == EntityType.LARGE) {
            Asteroid(gameData, size);

        } else {

            for (int i = 0; i < 2; i++) {
                Asteroid(gameData, size);
            }
        }

    }

    private void Asteroid(GameData gameData, EntityType size) {
        Random generator = new Random();

        Entity newAsteroid = new Entity();
        newAsteroid.setType(EntityType.ASTEROIDS);

        float x = (float) (gameData.getDisplayWidth() / (generator.nextInt(10) + 1));
        float y = (float) (gameData.getDisplayHeight() / (generator.nextInt(10) + 1));

        int numpoints;

        if (EntityType.LARGE == size) {
            newAsteroid.setPosition(x, y);
            newAsteroid.setRotationSpeed(generator.nextInt(2) + 1);
            newAsteroid.setRadians(generator.nextInt((int) (2 * 3.1415f)));

            numpoints = 12;
            newAsteroid.setSizeType(EntityType.LARGE);
            newAsteroid.setRect(40, 40);

            newAsteroid.setShapeX(new float[numpoints]);
            newAsteroid.setShapeY(new float[numpoints]);

            newAsteroid.setMaxSpeed(generator.nextInt(10));
        } else if (EntityType.MEDIUM == size) {
            newAsteroid.setPosition(asteroid.getX(), asteroid.getY());
            newAsteroid.setRotationSpeed(generator.nextInt(2) + 1);
            newAsteroid.setRadians(generator.nextInt((int) (2 * 3.1415f)));

            numpoints = 10;
            newAsteroid.setSizeType(EntityType.MEDIUM);
            newAsteroid.setRect(20, 20);

            newAsteroid.setShapeX(new float[numpoints]);
            newAsteroid.setShapeY(new float[numpoints]);

            newAsteroid.setMaxSpeed(generator.nextInt(10) + 1);
        } else {
            newAsteroid.setPosition(asteroid.getX(), asteroid.getY());
            newAsteroid.setRotationSpeed(generator.nextInt(2) + 1);
            newAsteroid.setRadians(generator.nextInt((int) (2 * 3.1415f)));

            numpoints = 8;
            newAsteroid.setSizeType(EntityType.SMALL);
            newAsteroid.setRect(12, 12);

            newAsteroid.setShapeX(new float[numpoints]);
            newAsteroid.setShapeY(new float[numpoints]);

            newAsteroid.setMaxSpeed(generator.nextInt(10) + 2);
        }

        float dist[] = new float[numpoints];
        for (int j = 0; j < numpoints; j++) {
            dist[j] = generator.nextInt((newAsteroid.getWidth() - newAsteroid.getWidth() / 2)) + newAsteroid.getWidth() / 2;
        }
        newAsteroid.setDist(dist);

        newAsteroid.setDx((float) Math.cos(newAsteroid.getRadians()) * newAsteroid.getMaxSpeed());
        newAsteroid.setDy((float) Math.sin(newAsteroid.getRadians()) * newAsteroid.getMaxSpeed());
        
        setShape(newAsteroid);
        
        asteroidCount += 1;
        world.put(newAsteroid.getID(), newAsteroid);
    }

    private void setShape(Entity asteroid) {

        float angle = 0;
        float[] dist = asteroid.getDist();
        float[] shapex = new float[dist.length];
        float[] shapey = new float[dist.length];

        for (int i = 0; i < dist.length; i++) {
            shapex[i] = asteroid.getX() + (float) Math.cos(angle + asteroid.getRadians()) * dist[i];
            shapey[i] = asteroid.getY() + (float) Math.sin(angle + asteroid.getRadians()) * dist[i];
            angle += 2 * 3.1415f / dist.length;
        }

        asteroid.setShapeX(shapex);
        asteroid.setShapeY(shapey);
    }

}
