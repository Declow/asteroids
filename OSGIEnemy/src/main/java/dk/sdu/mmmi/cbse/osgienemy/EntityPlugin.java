/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgienemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Map;

/**
 *
 * @author anon
 */
public class EntityPlugin implements IGamePluginService {

    private Map<String, Entity> world;

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        this.world = world;
    }

    @Override
    public void stop(GameData gameData) {
        for (Entity e : world.values()) {
            if (e.getType() == EntityType.ENEMY) {
                world.remove(e.getID());
            }
        }
    }

}
