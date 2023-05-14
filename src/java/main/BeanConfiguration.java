package main;

import jakarta.servlet.http.HttpSessionListener;
import main.beans.Messages;
import main.listeners.SessionListenerCounter;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;


@Configuration
public class BeanConfiguration {


    /* we declare a bean 'sessionBeanExample' with session scope
    * originally
    * @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    * */
    @Bean
    @SessionScope
    public Messages sessionBeanExample () {
        return new Messages();
    }

    @Bean
    @ApplicationScope
    public Messages applicationBeanExample () {
        return new Messages();
    }


//    @Bean
//    public ServletListenerRegistrationBean<HttpSessionListener> sessionListener() {
//        return new ServletListenerRegistrationBean<HttpSessionListener>(new SessionListenerCounter());
//    }

    /*
    we declare a bean 'ServletListenerRegistrationBean' to count sessions
    this is not necessary anymore since the annotation @WebListener that does the same thing
     */
//    @Bean
//    public ServletListenerRegistrationBean<SessionListenerCounter> sessionListenerWithMetrics() {
//        ServletListenerRegistrationBean<SessionListenerCounter> listenerRegBean = new ServletListenerRegistrationBean<>();
//
//        listenerRegBean.setListener(new SessionListenerCounter());
//        return listenerRegBean;
//    }

}
