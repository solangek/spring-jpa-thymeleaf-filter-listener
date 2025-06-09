package main.filters;

import main.beans.Messages;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Enumeration;


/**
 * this class intercepts all requests and displays statistics: request processing duration
 *
 * it also demonstrates how to inject a bean (you cannot use Spring @Autowired in a
 * HandlerInterceptor but you can receive the bean via the ctor - from the configuration class)
 */

public class LoggingInterceptor implements HandlerInterceptor {

    // we can use a private member to store a bean received by ctor
    private Messages messages;

    public LoggingInterceptor() {}

    // let's say we want access to some bean, a solution is to pass the bean to the ctor
    // or define some setter method to pass it
    public LoggingInterceptor(Messages m) {
        messages = m;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        long startTime = System.currentTimeMillis();

        System.out.print("-------- preHandle --- ");
        System.out.print("Request URL: " + request.getRequestURL());
        System.out.println("; Start Time: " + System.currentTimeMillis());

        request.setAttribute("startTime", startTime);

        // if you are curious to see how beans are named under the session, uncomment the following lines:
//        Enumeration keys = request.getSession().getAttributeNames();
//        while (keys.hasMoreElements()){
//            String key = (String)keys.nextElement();
//            System.out.println((key + ": " + request.getSession().getAttribute(key)));
//        }

        // you may try to access the bean through the request object but be aware that
        // the bean name is prefixed with some string like "scopedTarget." (enable the 4 lines above to print the bean name)
        // System.out.println( "sessionBeanExample:" + request.getSession().getAttribute("scopedTarget.sessionBeanExample"));

        // that's why it's better to inject the bean through the ctor, you don't need to know its name:
        // System.out.println("Session bean in filter: " + messages);

        // filter can redirect response to a specific page
        // response.sendRedirect("/error");

        // return FALSE will block the request chaining!

        return true; // continue with the request to next filter or to controller
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
                           Object handler, ModelAndView modelAndView) throws Exception {

        System.out.print("-------- postHandle ---: ");
        System.out.println("Request URL: " + request.getRequestURL());

        // You can add attributes in the modelAndView
        // and use that in the view page
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
                                Object handler, Exception ex) throws Exception {
        //System.out.print("-------- afterCompletion ---: ");

        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        System.out.print("Request URL: " + request.getRequestURL());
        //System.out.println("; End Time: " + endTime);

        System.out.println("Time Taken: " + (endTime - startTime));
    }

}