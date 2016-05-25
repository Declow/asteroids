package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.Wrap;
import java.util.Map;

/**
 *
 * @author jcs
 */
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = IEntityProcessingService.class)
public class PlayerControlSystem implements IEntityProcessingService {
    
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        if (entity.getType() == EntityType.PLAYER) {
            update(gameData, entity);
            setShape(entity);
        }
    }
    
    public void update(GameData gameData, Entity player) {
        if (gameData.getKeys().isDown(GameKeys.LEFT)) {
            player.setRadians(player.getRadians() + player.getRotationSpeed() * gameData.getDelta());
        } else if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
            player.setRadians(player.getRadians() - player.getRotationSpeed() * gameData.getDelta());
        }
        
        if (gameData.getKeys().isDown(GameKeys.UP)) {
            player.setDx((float) (player.getDx() + Math.cos(player.getRadians()) * player.getAcceleration() * gameData.getDelta()));
            player.setDy((float) (player.getDy() + Math.sin(player.getRadians()) * player.getAcceleration() * gameData.getDelta()));
        }
        
        float vec = (float) Math.sqrt(player.getDx() * player.getDx() + player.getDy() * player.getDy());
        
        if (vec > 0) {
            player.setDx(player.getDx() - (player.getDx() / vec) * player.getDeacceleration() * gameData.getDelta());
            player.setDy(player.getDy() - (player.getDy() / vec) * player.getDeacceleration() * gameData.getDelta());
        }
        
        if (vec > player.getMaxSpeed()) {
            player.setDx((player.getDx() / vec) * player.getMaxSpeed());
            player.setDy((player.getDy() / vec) * player.getMaxSpeed());
        }
        
        float setX = player.getX() + player.getDx() * gameData.getDelta();
        float setY = player.getY() + player.getDy() * gameData.getDelta();
        
        player.setPosition(setX, setY);
        
        Wrap.wrap(gameData, player);
    }
    
    public void setShape(Entity player) {
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
        
    }
    
}
