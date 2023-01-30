package it.proactivity.demospringboot.controller;

import it.proactivity.demospringboot.service.DemoService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
public class DemoController {

    @Autowired
    DemoService demoService;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/landing-page")
    public String landingPage() {
        return "Greetings from Spring Boot landing page";
    }

    //http://localhost:8080/welcome-page/aNAme
    @GetMapping("/welcome-page/{name}")
    public String welcomePage(@PathVariable String name ) {
        return demoService.nameToUpperCase(name);
    }

    //http://localhost:8080/welcome-page?name=aValue
    @GetMapping("/welcome-page-parameter")
    public String welcomePageParameter(@RequestParam(required = false) String name, @RequestParam String surname) {
        return demoService.nameSurnameToUpperCase(name, surname);
    }

    @GetMapping("/list")
    public List<String> list() {
        return demoService.createFakeList();
    }
}
