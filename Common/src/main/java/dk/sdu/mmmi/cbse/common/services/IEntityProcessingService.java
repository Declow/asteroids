package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import java.util.Map;

public interface IEntityProcessingService {

    /**
     * TODO: Describe the contract using pre- and post-conditions.
     * Preconditions: We want to update the state of an entity.
     * An entity can be of type Player, enemy, bullet and astroid.
     * 
     * 
     * Postconditions: The entity's data has been manipulated based on the component's implementation.
     * This could be the positions of a entity that has changed.
     * 
     * 
     * @param gameData
     * @param world
     * @param entity
     */
    void process(GameData gameData, Map<String, Entity> world, Entity entity);
}
