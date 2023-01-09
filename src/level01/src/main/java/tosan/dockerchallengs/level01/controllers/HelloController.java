package tosan.dockerchallengs.level01.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tosan.dockerchallengs.level01.services.WelcomeService;

/**
 * @author Sajad Salimzadeh
 * @since 1/9/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hello")
public class HelloController {

    private final WelcomeService welcomeService;

    @GetMapping
    public String sayHello(@RequestParam(required = false) String name) {
        return welcomeService.sayHello(name);
    }
}
