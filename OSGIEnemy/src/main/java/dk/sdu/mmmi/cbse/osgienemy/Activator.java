package dk.sdu.mmmi.cbse.osgienemy;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting OSGI bundle enemy");
        context.registerService(IGamePluginService.class, new EntityPlugin(), null);
        context.registerService(IEntityProcessingService.class, new EnemyControlSystem(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        /*
        ServiceReference srProcessing = context.getServiceReference(IEntityProcessingService.class);
        ServiceReference srPlugin = context.getServiceReference(IGamePluginService.class);
        context.ungetService(srProcessing);
        context.ungetService(srPlugin);
        */
    }

}
