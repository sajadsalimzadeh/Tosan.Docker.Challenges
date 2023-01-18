package tosan.dockerchallengs.level01.services;

import org.springframework.stereotype.Service;

/**
 * @author Sajad Salimzadeh
 * @since 1/9/2023
 */
@Service
public class WelcomeService {

    private boolean isUpperCase(Character character) {
        var charCode = Integer.valueOf(character);
        return charCode >= 65 && charCode <= 90;
    }

    private boolean isLowerCharacter(Character character) {
        var charCode = Integer.valueOf(character);
        return charCode >= 97 && charCode <= 122;
    }

    private String getCamelSpaceCutString(String input) {
        // Can Implement with list and split by space but is not performed
        var sb = new StringBuilder(input);
        var len = input.length();
        var isCamelWord = false;
        var wordCharIndex = 0; // Index of character in word
        for (int i = 0; i < len; i++, wordCharIndex++) {

            var ch = sb.charAt(i);
            if (ch == ' ') {  // detect new word
                wordCharIndex = -1;
                isCamelWord = false;
                continue;
            } else if (!isCamelWord && wordCharIndex == 0) { // detect new word is camelcase
                if (isLowerCharacter(ch)) {
                    isCamelWord = true;
                }
                continue;
            }

            //Insert space if word is camelcase and detect uppercase character in it
            if (isCamelWord && isUpperCase(ch)) {
                sb.insert(i, " ");
                len = sb.length();
                i++;
            }
        }

        return sb.toString();
    }

    public String sayHello(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Hello Stranger";
        }

        return "Hello " + getCamelSpaceCutString(name);
    }
}
