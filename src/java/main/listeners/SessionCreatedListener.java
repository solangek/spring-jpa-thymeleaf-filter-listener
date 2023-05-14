package main.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.session.events.SessionCreatedEvent;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * this class is a listener for session creation
 * it will not work in case of spring session because the session is created in the listener itself
 */
@Component
public class SessionCreatedListener implements ApplicationListener<SessionCreatedEvent> {
//        ,ApplicationListener<SessionDestroyedEvent> {

    private final AtomicInteger counter = new AtomicInteger();

    private void updateSessionCounter(HttpSessionEvent e){
        //Let's set in the context
        e.getSession().getServletContext()
                .setAttribute("activeSession", counter.get());
        System.out.println("!!!! Total active session are " + counter.get());
    }

    @Override
    public void onApplicationEvent(SessionCreatedEvent sce) {
        counter.incrementAndGet();
        System.out.println("!!!! Total active session are " + counter.get());
    }

    //@Override
//    public void onApplicationEvent(SessionDestroyedEvent sessionDestroyedEvent) {
//        counter.decrementAndGet();
//        System.out.println("Total active session are {} " + counter.get());
//    }
}