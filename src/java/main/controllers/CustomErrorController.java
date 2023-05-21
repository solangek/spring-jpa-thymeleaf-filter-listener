package main.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
  this controller handles all exceptions/errors and displays a friendly
  error page/

  for example try the URL:
            http://localhost:8080/delete/456

  where 456 is an ID that does not exist in the database

  note that this error page is also used in the UserController to return
  specific errors (see the @ExceptionHandler annotation in UserController)
 */
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("error", "Some error occured");
        return "error";
    }

}