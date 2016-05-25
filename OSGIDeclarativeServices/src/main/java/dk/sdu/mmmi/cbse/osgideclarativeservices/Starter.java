package dk.sdu.mmmi.cbse.osgideclarativeservices;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;

public class Starter {

    private ServiceReference reference;
    private IEntityProcessingService service;

    protected void activate(ComponentContext context) {
        if (reference != null) {
            service = (IEntityProcessingService) context.locateService("IEntity", reference);
        }
    }

    public void gotService(ServiceReference reference) {
        this.reference = reference;
        System.out.println("Got reference: " + reference);
    }

    public void lostService(ServiceReference reference) {
        System.out.println("Lost reference: " + reference);
        this.reference = null;
    }

}
