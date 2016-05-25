/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullets;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.Wrap;
import java.util.Map;

/**
 *
 * @author anon
 */
import org.openide.util.lookup.ServiceProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@ServiceProvider(service = IEntityProcessingService.class)
public class BulletControlSystem implements IEntityProcessingService {

    Map<String, Entity> world;
    AnnotationConfigApplicationContext ctx;

    boolean t = true;

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {

        this.world = world;

        if (t) {
            createAnnotation();
            t = false;
        }

        createBullet(gameData, world, entity);
        if (entity.getType() == EntityType.BULLET) {
            update(gameData, entity);
            setShape(entity);
        }
    }

    public void createBullet(GameData gameData, Map<String, Entity> world, Entity e) {
        IBulletFactory bf = getFactory();
        
        if (gameData.getKeys().isPressed(GameKeys.SPACE)) {
            if (e.getType() == EntityType.PLAYER) {
                Entity bullet = bf.createBullet(e);
                world.put(bullet.getID(), bullet);
            }
        }

        if (e.isShoothing()) {
            Entity bullet = bf.createBullet(e);
            world.put(bullet.getID(), bullet);
            e.setShoot(false);
        }
    }

    public void update(GameData gameData, Entity bullet) {

        if (bullet.isRemoved()) {
            world.remove(bullet.getID());
        } else {

            float x = bullet.getX() + bullet.getDx() * gameData.getDelta();
            float y = bullet.getY() + bullet.getDy() * gameData.getDelta();

            bullet.setPosition(x, y);
            Wrap.wrap(gameData, bullet);

            setShape(bullet);

            bullet.setLifeTimer(bullet.getLifeTimer() + gameData.getDelta());

            if (bullet.getLifeTimer() > bullet.getLifeTime()) {
                world.remove(bullet.getID());
            }
        }
    }

    public void setShape(Entity bullet) {

        float[] shapex = new float[2];
        float[] shapey = new float[2];

        shapex[0] = (float) (bullet.getX() + 1);
        shapey[0] = (float) (bullet.getY() + 1);

        shapex[1] = (float) (bullet.getX());
        shapey[1] = (float) (bullet.getY());

        bullet.setShapeX(shapex);
        bullet.setShapeY(shapey);
    }

    private void createAnnotation() {
        ctx = new AnnotationConfigApplicationContext(BeanFactory.class);
    }

    private IBulletFactory getFactory() {
        return ctx.getBean(IBulletFactory.class);
    }

}
