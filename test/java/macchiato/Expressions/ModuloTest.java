package macchiato.Expressions;

import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.VariableFrame;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class ModuloTest {

    public record TestData(
            int divider,
            int divisor,
            int expectedValue
    ) {}

    public static Stream<ModuloTest.TestData> getTestModuloByZeroData() {
        return Stream.of(
                new ModuloTest.TestData(42, 0,-1),
                new ModuloTest.TestData(17, 0,-1),
                new ModuloTest.TestData(0, 0,-1)
        );
    }

    @ParameterizedTest
    @MethodSource("getTestModuloByZeroData")
    public void moduloByZeroTest(ModuloTest.TestData testData) {
        // Given
        Modulo modulo = Modulo.of(Constant.of(testData.divider),
                Constant.of(testData.divisor));
        VariableFrame variableFrame = new VariableFrame();

        // When
        Throwable exception = assertThrows(MacchiatoException.class,
                () -> modulo.compute(variableFrame));

        // Then
        assertEquals(exception.getMessage(), "Modulo by zero.");
    }

    public record NestedTestData(
            int divider,
            int divisor1,
            int divisor2,
            int divisor3,
            int expectedValue
    ) {}

    public static Stream<ModuloTest.NestedTestData> getTestNestedModuloData() {
        return Stream.of(
                new ModuloTest.NestedTestData(2, 5,3, 17, 1),
                new ModuloTest.NestedTestData(144, 12,-2, 12, 0),
                new ModuloTest.NestedTestData(8, 6, 7, 36, 4),
                new ModuloTest.NestedTestData(-2, 2, -2, 2,0),
                new ModuloTest.NestedTestData(123, 5215, 8, 1244, 6),
                new ModuloTest.NestedTestData(2121, 5, 6, 7, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("getTestNestedModuloData")
    public void moduloTest(ModuloTest.NestedTestData testData) {
        // Given
        Modulo modulo =
                Modulo.of(
                        Modulo.of(
                                Modulo.of(
                                        Constant.of(testData.divisor3),
                                        Constant.of(testData.divider)
                                ),
                                Constant.of(testData.divisor1)
                        ),
                        Constant.of(testData.divisor2)
                );

        VariableFrame variableFrame = new VariableFrame();
        int result = -1;

        // When
        try {
            result = modulo.compute(variableFrame);
        } catch (Exception e){
            e.printStackTrace();
        }

        // Then
        assertEquals(result, testData.expectedValue);
    }
}
