package main.controllers;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import main.repo.UserInfo;
import main.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {

    /* we inject a property from the application.properties  file */
    @Value( "${demo.coursename}" )
    private String someProperty;

    /* we need it so  inject the User repo bean */
    @Autowired
    private UserRepository repository;

    private UserRepository getRepo() {
        return repository;
    }

    /*
    a controller that acts as a middleware (all requests go thru it before reaching others
     */
//    @GetMapping ("/**")
//    public String main1(User user, Model model) {
//        //model.addAttribute("course", someProperty);
//        //model.addAttribute("users", getRepo().findAll());
//        System.out.println("in /**");
//        // you can return any view, it is not really happening since next controller does it
//        return "error";
//    }

    @GetMapping("/")
    public String main(UserInfo userInfo, Model model) {
        model.addAttribute("course", someProperty);
        model.addAttribute("users", getRepo().findAll());
        System.out.println("in /");
        return "index";
    }

    /**
     * display the form for adding a new user
     * @param userInfo this is the model attribute that will be bound to the form for thymeleaf error handling
     * @return the view name
     */
    @GetMapping("/signup")
    public String showSignUpForm(UserInfo userInfo) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid UserInfo userInfo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        getRepo().save(userInfo);
        //  option 1: return a view and stay in POST
        model.addAttribute("users", getRepo().findAll());
        return "index";
        // option 2, redirect to avoid form resubmission
        // return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        UserInfo userInfo = getRepo().findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", userInfo);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid UserInfo userInfo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            userInfo.setId(id);
            return "update-user";
        }

        getRepo().save(userInfo);
        model.addAttribute("users", getRepo().findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {

        UserInfo userInfo = getRepo().findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        getRepo().delete(userInfo);
        model.addAttribute("users", getRepo().findAll());
        return "index";
    }

    @GetMapping(value="/json")
    public String json (Model model) {
        return "json";
    }
    /**
     * a sample controller return the content of the DB in JSON format
     * @param model
     * @return
     */
    @GetMapping(value="/getjson")
    public @ResponseBody List<UserInfo> getAll(Model model) {

        return getRepo().findAll();
    }

    /** our controller throws an exception so we define a handler
     * to catch it and return a custom error page
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler({Exception.class})
    public String handleValidationExceptions(Exception ex, Model model) {
        // we can insert the message into the model
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}

