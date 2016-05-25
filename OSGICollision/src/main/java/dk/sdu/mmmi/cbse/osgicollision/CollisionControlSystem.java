/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgicollision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Map;

/**
 *
 * @author anon
 */

public class CollisionControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        checkPlayer(gameData, world, entity);
        checkAsteroid(gameData, world, entity);
        checkEnemy(gameData, world, entity);
    }

    private void checkPlayer(GameData gameData, Map<String, Entity> world, Entity entity) {
        if (entity.getType() == EntityType.PLAYER) {
            for (Entity e : world.values()) {
                if (e.getType() == EntityType.ASTEROIDS || e.getType() == EntityType.ENEMY) {
                    if (intersects(e, entity)) {
                        entity.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
                    }
                }
                if (e.getType() == EntityType.BULLET && !e.getOwnerID().equals(entity.getID())) {
                    if (contains(entity, e.getX(), e.getY())) {
                        entity.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
                    }
                }
            }
        }
    }

    private void checkEnemy(GameData gameData, Map<String, Entity> world, Entity entity) {
        if (entity.getType() == EntityType.ENEMY) {
            for (Entity e : world.values()) {
                if (e.getType() == EntityType.BULLET && !e.getOwnerID().equals(entity.getID())) {
                    if (intersects(entity, e)) {
                        entity.setRemove(true);
                        e.setRemove(true);
                    }
                }
            }
        }
    }

    private void checkAsteroid(GameData gameData, Map<String, Entity> world, Entity entity) {
        if (entity.getType() == EntityType.ASTEROIDS) {
            for (Entity e : world.values()) {
                if (e.getType() == EntityType.BULLET) {
                    if (contains(entity, e.getX(), e.getY())) {
                        entity.setRemove(true);
                        e.setRemove(true);
                    }
                }
            }
        }
    }

    // Check if two entities intersects each other
    private boolean intersects(Entity obj, Entity object) {
        float[] sx = object.getShapeX();
        float[] sy = object.getShapeY();
        for (int i = 0; i < sx.length; i++) {
            if (contains(obj, sx[i], sy[i])) {
                return true;
            }
        }
        return false;
    }

    // Check if one entity contains another entity
    private boolean contains(Entity obj, float x, float y) {

        float[] shapeX = obj.getShapeX();
        float[] shapeY = obj.getShapeY();

        boolean b = false;
        for (int i = 0, j = shapeX.length - 1;
                i < shapeX.length;
                j = i++) {
            if ((shapeY[i] > y) != (shapeY[j] > y)
                    && (x < (shapeX[j] - shapeX[i])
                    * (y - shapeY[i]) / (shapeY[j] - shapeY[i])
                    + shapeX[i])) {
                b = !b;
            }
        }
        return b;
    }

}
