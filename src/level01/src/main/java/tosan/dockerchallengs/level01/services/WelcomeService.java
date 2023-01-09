package tosan.dockerchallengs.level01.services;

import org.springframework.stereotype.Service;

/**
 * @author Sajad Salimzadeh
 * @since 1/9/2023
 */
@Service
public class WelcomeService {

    public String sayHello(String name) {
        if(name == null || name.isEmpty()) {
            return "Hello Stranger";
        }

        return "Hello " + name;
    }
}
