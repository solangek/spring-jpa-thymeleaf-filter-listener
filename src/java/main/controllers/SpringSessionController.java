package main.controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import main.beans.Messages;
import main.listeners.SessionListenerCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;

@Controller
public class SpringSessionController {

    //@Resource(name="sessionListenerWithMetrics")
    //private ServletListenerRegistrationBean<SessionListenerCounter> metrics;

    // named bean injection - session scope
    @Resource(name = "sessionBeanExample")
    private Messages sessionMessages;

    // named bean injection - application scope
    @Resource(name = "applicationBeanExample")
    private Messages appMessages;

    // OR autowired by type
    //@Autowired
    //private Messages messageBean;

    @GetMapping("/session")
    public String process(Model model) {

        // we modify an application scoped bean
        appMessages.add("hello"); // just for demo

        model.addAttribute("sessionMessages", sessionMessages.getMessages());
        return "session";
    }

    @PostMapping("/persistMessage")
    public String persistMessage(@RequestParam(required = false) String msg ) {
        if (msg == null)
            System.out.println("null message !");
        else
            sessionMessages.add(msg);

        return "redirect:/session";
    }

    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        // we could also empty the session bean
        //sessionMessages.clear();
        return "redirect:/session";
    }
}