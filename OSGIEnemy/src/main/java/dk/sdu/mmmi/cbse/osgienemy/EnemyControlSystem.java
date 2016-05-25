/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgienemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.Wrap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author anon
 */
public class EnemyControlSystem implements IEntityProcessingService {

    int SPEED = 70;

    float spawnTimer = 0f;
    float spawnRate = 30f;

    int enemyCount = 0;
    float fireTimer = 0.0f;
    float fireTime = 1f;

    float pathTimer = 0;
    float pathTime1 = 2;
    float pathTime2 = pathTime1 + 2;

    Entity player;

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        if (entity.getType() == EntityType.PLAYER) {
            this.player = entity;
        }
        if (enemyCount == 0) {
            spawnTimer += gameData.getDelta();
        }

        if (enemyCount < 1 && spawnTimer > spawnRate) {
            createEnemy(gameData, world);
            enemyCount += 1;
            spawnTimer = 0f;
        } else if (entity.getType() == EntityType.ENEMY) {
            update(gameData, entity, world);
        }
    }

    private void update(GameData gameData, Entity enemy, Map<String, Entity> world) {
        if (enemy.isRemoved()) {
            world.remove(enemy.getID());
            enemyCount -= 1;
        } else if (player != null && !player.isHit()) {
            fireTimer += gameData.getDelta();
            if (fireTimer > fireTime) {
                fireTimer = 0;
                enemy.setRadians((float) new Random().nextInt((int) (2 * 3.1414)));
                enemy.setShoot(true);
            }
        }

        pathTimer += gameData.getDelta();

        if (pathTimer < pathTime1) {
            enemy.setDy(0f);
        }

        if (pathTimer > pathTime1 && pathTimer < pathTime2) {
            enemy.setDy(-enemy.getMaxSpeed());
        }

        if (pathTimer > pathTime1 + pathTime2) {
            enemy.setDy(0f);
        }
        enemy.setPosition(enemy.getX() + enemy.getDx() * gameData.getDelta(), enemy.getY() + enemy.getDy() * gameData.getDelta());

        setShape(enemy, enemy.getSizeType());
        Wrap.wrap(gameData, enemy);
    }

    private void createEnemy(GameData gameData, Map<String, Entity> world) {
        Entity enemy = new Entity();
        enemy.setType(EntityType.ENEMY);

        float y = (float) new Random().nextInt(gameData.getDisplayHeight());

        if (Math.random() < 0.5) {
            enemy.setDx(-SPEED);
            enemy.setPosition(gameData.getDisplayWidth(), y);
            enemy.setSizeType(EntityType.SMALL);
            setShape(enemy, EntityType.SMALL);
        } else {
            enemy.setDx(SPEED);
            enemy.setPosition(0.0f, y);
            enemy.setSizeType(EntityType.LARGE);
            setShape(enemy, EntityType.LARGE);
        }
        world.put(enemy.getID(), enemy);
    }

    private void setShape(Entity enemy, EntityType type) {

        float[] shapex = new float[6];
        float[] shapey = new float[6];

        if (type == EntityType.LARGE) {
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
        } else if (type == EntityType.SMALL) {
            shapex[0] = enemy.getX() - 6;
            shapey[0] = enemy.getY();

            shapex[1] = enemy.getX() - 2;
            shapey[1] = enemy.getY() - 3;

            shapex[2] = enemy.getX() + 2;
            shapey[2] = enemy.getY() - 3;

            shapex[3] = enemy.getX() + 6;
            shapey[3] = enemy.getY();

            shapex[4] = enemy.getX() + 2;
            shapey[4] = enemy.getY() + 3;

            shapex[5] = enemy.getX() - 2;
            shapey[5] = enemy.getY() + 3;
        }
        enemy.setShapeX(shapex);
        enemy.setShapeY(shapey);
    }
}
