package dk.sdu.mmmi.cbse.asteroidsgame;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestCase;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

public class ApplicationTest extends NbTestCase {

    public static Test suite() {
        return NbModuleSuite.createConfiguration(ApplicationTest.class).
                gui(false).
                failOnMessage(Level.WARNING). // works at least in RELEASE71
                failOnException(Level.INFO).
                enableClasspathModules(false).
                clusters(".*").
                suite(); // RELEASE71+, else use NbModuleSuite.create(NbModuleSuite.createConfiguration(...))
    }

    public ApplicationTest(String n) {
        super(n);
    }

    public void testApplication() throws FileNotFoundException, IOException {
        // pass if there are merely no warnings/exceptions
        /* Example of using Jelly Tools (additional test dependencies required) with gui(true):
        new ActionNoBlock("Help|About", null).performMenu();
        new NbDialogOperator("About").closeByButton();
         */
        List<IEntityProcessingService> list = new CopyOnWriteArrayList<>();
        list.addAll(Lookup.getDefault().lookupAll(IEntityProcessingService.class));
        assertEquals(4, list.size());
        list.clear();
        try {
            sleep(7000);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }
        list.addAll(Lookup.getDefault().lookupAll(IEntityProcessingService.class));

        int serviceSizeBeforeUpdate = list.size();
        assertNotSame(4, list.size());
        list.clear();

        File updates = new File("/home/anon/projects/nmb_site/updates.xml");
        File missingAsteroid = new File("/home/anon/projects/nmb_site/updatesOutAsteroid.xml");
        File updateAll = new File("/home/anon/projects/nmb_site/updatesAll.xml");

        FileInputStream fis = new FileInputStream(missingAsteroid);
        FileOutputStream fos = new FileOutputStream(updates, false);

        int read = fis.read();
        while (read != -1) {
            fos.write(read);
            read = fis.read();
        }

        try {
            sleep(6000);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }
        list.addAll(Lookup.getDefault().lookupAll(IEntityProcessingService.class));

        int serviceSizeAfterUpdate = list.size();

        fis = new FileInputStream(updateAll);
        fos = new FileOutputStream(updates, false);

        read = fis.read();
        while (read != -1) {
            fos.write(read);
            read = fis.read();
        }

        assertEquals(serviceSizeBeforeUpdate - 1, serviceSizeAfterUpdate);

    }

}
