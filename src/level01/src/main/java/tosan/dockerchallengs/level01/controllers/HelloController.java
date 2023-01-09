package tosan.dockerchallengs.level01.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sajad Salimzadeh
 * @since 1/9/2023
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String sayHello(@RequestParam(required = false) String name) {
        if(name == null || name.isEmpty()) {
            return "Hello Stranger";
        }

        return "Hello " + name;
    }
}
