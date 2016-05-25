/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.util;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 *
 * @author anon
 */
public class Wrap {
        public static void wrap(GameData gameData, Entity e) {

        float x = e.getX();
        float y = e.getY();

        if (x < 0) {
            x = gameData.getDisplayWidth();
        }
        if (x > gameData.getDisplayWidth()) {
            x = 0;
        }
        if (y < 0) {
            y = gameData.getDisplayHeight();
        }
        if (y > gameData.getDisplayHeight()) {
            y = 0;
        }

        e.setPosition(x, y);
    }
}
