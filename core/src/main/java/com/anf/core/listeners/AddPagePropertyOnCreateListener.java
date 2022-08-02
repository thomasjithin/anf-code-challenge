package com.anf.core.listeners;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anf.core.constants.Constants;

/**
 * This listener class is responsible to add an additional boolean property for
 * all pages created under the specific content path. Please refer to
 * addEventListener method to understand the various parameters used in this
 * specific usecase.
 */
@Component(immediate = true, service = EventListener.class)
public class AddPagePropertyOnCreateListener implements EventListener {

    private static final Logger LOG = LoggerFactory.getLogger(AddPagePropertyOnCreateListener.class);
    private Session session;

    @Reference
    SlingRepository slingRepository;

    @Activate
    public void activate() throws Exception {
        session = slingRepository.loginService(Constants.ANF_CODE_CHALLENGE_SUB_SERVICE_NAME, null);
        String[] monitoredNodeTypes = { "cq:Page" };
        session.getWorkspace().getObservationManager().addEventListener(
                this,
                Event.NODE_ADDED,
                Constants.ANF_CODE_CHALLENGE_ROOT_CONTENT_PATH,
                true,
                null, monitoredNodeTypes, false);

        LOG.info(
                "AddPagePropertyOnCreate Event Listener initialized successfully for NODE_ADDED event type and monitoring all {} on content path {}",
                monitoredNodeTypes.toString(), Constants.ANF_CODE_CHALLENGE_ROOT_CONTENT_PATH);
    }

    @Override
    public void onEvent(EventIterator eventIterator) {
        try {
            while (eventIterator.hasNext()) {
                Event event = eventIterator.nextEvent();
                LOG.info("\n Event Type: {}, Node: {}, Path: {}", event.getType(),
                        event.getIdentifier(), event.getPath());

                session = slingRepository.loginService(Constants.ANF_CODE_CHALLENGE_SUB_SERVICE_NAME, null);
                Node node = session.getNode(event.getPath() + "/jcr:content");
                if (node != null) {
                    node.setProperty("pageCreated", Boolean.TRUE.booleanValue());
                    session.save();
                }
            }
        } catch (Exception ex) {
            LOG.error("Exception occurred while adding property during page creation: ", ex);
        }
    }
}
