/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullets;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;

/**
 *
 * @author anon
 */
public class BulletFactory implements IBulletFactory {

    @Override
    public Entity createBullet(Entity e) {
        Entity bullet = new Entity();
        bullet.setType(EntityType.BULLET);

        bullet.setMaxSpeed(350);
        bullet.setRadians(e.getRadians());
        bullet.setPosition(e.getX(), e.getY());

        bullet.setLifeTimer(0);
        bullet.setLifeTime(1);

        bullet.setDx((float) Math.cos(bullet.getRadians()) * bullet.getMaxSpeed());
        bullet.setDy((float) Math.sin(bullet.getRadians()) * bullet.getMaxSpeed());

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
