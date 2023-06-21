package main;

import jakarta.annotation.Resource;
import main.beans.Messages;
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
 */
@EnableWebMvc
@Configuration
public class FiltersConfig implements WebMvcConfigurer {

    // we can inject a bean in the config class then pass it to other classes such as filter
    @Resource(name = "sessionBeanExample")
    private Messages messages;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // if you want to apply filter only for REST controller: change the "/**" pattern

        // no args ctor
        // define the URL to intercept with the pattern you want
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");

        // if we want to pass some bean to the filter
        // registry.addInterceptor(new LoggingInterceptor(messages)).addPathPatterns("/**");

        // excluding patterns
        //registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/add-user/**").excludePathPatterns("/static/**");
    }

// STATIC FOLDER IS NOT REACHABLE WHEN ADDING FILTER, you need this piece of code to enable access:
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/static/**")
//                .addResourceLocations("/static/");
//    }
}