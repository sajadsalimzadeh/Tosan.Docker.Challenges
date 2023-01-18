package tosan.dockerchallengs.level01.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

/**
 * @author Sajad Salimzadeh
 * @since 1/9/2023
 */
@ExtendWith(MockitoExtension.class)
public class WelcomeServiceTest {

    @InjectMocks
    private WelcomeService welcomeService;

    private static Stream<Arguments> getTestNameSet() {
        return Stream.of(
                Arguments.of(null, "Stranger"),
                Arguments.of("", "Stranger"),
                Arguments.of(" ", "Stranger"),
                Arguments.of("SajadSalimzadeh", "SajadSalimzadeh"),
                Arguments.of("sajadSalimzadeh", "sajad Salimzadeh"),
                Arguments.of("Mohammad Almasi", "Mohammad Almasi"),
                Arguments.of("mohammad Almasi", "mohammad Almasi"),
                Arguments.of("MohammadAmin AlmasiRad", "MohammadAmin AlmasiRad"),
                Arguments.of("MohammadReza almasiRad", "MohammadReza almasi Rad"),
                Arguments.of("mohammadReza almasiRad", "mohammad Reza almasi Rad")
        );
    }

    @ParameterizedTest
    @MethodSource("getTestNameSet")
    public void shouldReturn_HelloStranger_WithNameEqualsParameter(String input, String expected) {
        var result = welcomeService.sayHello(input);
        assert result.equals("Hello " + expected);
    }
}
