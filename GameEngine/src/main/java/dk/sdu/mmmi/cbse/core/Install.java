package dk.sdu.mmmi.cbse.core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.openide.modules.ModuleInstall;

public class Install extends ModuleInstall{
    
    @Override
    public void restored(){
        LwjglApplicationConfiguration cfg =
                    new LwjglApplicationConfiguration();
            cfg.title = "Asteroids";
            cfg.width = 500;
            cfg.height = 400;
            cfg.useGL30 = false;
            cfg.resizable = false;

            new LwjglApplication(new Game(), cfg);
    }
	
}
