package main;

import main.filters.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
    EXAMPLE: CONTROLLING THE STATIC FOLDER

  this is a class for configuring SringMVC
  here we register our interceptor class and the session listener
  WebMvcConfigurer allows configuring all of the MVC:

  TODO: CHECK WHY STATIC FOLDER IS NOT REACHABLE WHEN ADDING FILTER
 */
@EnableWebMvc
@Configuration
public class FiltersConfig implements WebMvcConfigurer {

    // we can inject a bean in the config class then pass it to other classes such as filter
    //@Resource(name = "sessionBeanExample")
    //private Messages messages;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // if you want to apply filter only for REST controller: change the "/**" pattern

        // if we want to pass some bean to the filter
        //registry.addInterceptor(new LoggingInterceptor(messages)).addPathPatterns("/**");

        // no args ctor
        registry.addInterceptor(new LoggingInterceptor()); //.addPathPatterns("/signup");

        // excluding patterns
        //registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/add-user/**").excludePathPatterns("/static/**");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/static/**")
//                .addResourceLocations("/static/");
//    }
}