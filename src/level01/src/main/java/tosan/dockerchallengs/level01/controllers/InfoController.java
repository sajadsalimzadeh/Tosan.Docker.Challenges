package tosan.dockerchallengs.level01.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sajad Salimzadeh
 * @since 1/18/2023
 */
@RestController
@RequestMapping
public class InfoController {
    
    @GetMapping("author")
    public String author() {
        return "Sajad Salimzadeh";
    }
}
