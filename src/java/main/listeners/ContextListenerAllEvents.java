package main.listeners;

import org.springframework.context.event.*;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

/*
   first method to implement a listener - using @EventListener annotation
   on method. Note that the method name does not matter, but the method signature
    must be the same of the event we want to listen to.
 */
@Component
public class ContextListenerAllEvents {

    /** a method to handle the ContextRefreshedEvent event */
    @EventListener
    public void handleContextRefreshed (ContextRefreshedEvent event) {
        System.out.print(">>>> 1 >>> ContextRefreshedEvent event fired (class ContextListenerAllEvents)");
        System.out.println(event);
    }

    /** a method to handle the ContextStartedEvent event */
    @EventListener
    public void handleContextStarted (ContextStartedEvent event) {
        System.out.print(">>>> 2 >>> ContextStartedEvent event fired (class ContextListenerAllEvents) ");
        System.out.println(event);
    }

    @EventListener
    public void handleContextStopped (ContextStoppedEvent event) {
        System.out.print(">>>> 3 >>> ContextStoppedEvent event fired (class ContextListenerAllEvents)");
        System.out.println(event);
    }

    @EventListener
    public void handleContextClosed (ContextClosedEvent event) {
        System.out.print(">>>> 4 >>> ContextClosedEvent event fired (class ContextListenerAllEvents)");
        System.out.println(event);
    }

    // does not work in some configurations (e.g. spring session)
    @EventListener
    public void handleSessionCreatedEvent (SessionCreatedEvent event) {
        System.out.print(">>>> 5 >>> SessionCreatedEvent fired: ");
        System.out.println(event);
    }

    // does not work in some configurations (e.g. spring session)
    @EventListener
    public void handleSessionDestroyedEvent (SessionDestroyedEvent event) {
        System.out.print(">>>> 6 >>> SessionDestroyedEvent fired: ");
        System.out.println(event);
    }
}
