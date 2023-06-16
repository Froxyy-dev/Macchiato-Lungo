package macchiato.Expressions;

import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.VariableFrame;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class DivisionTest {

    public record TestData(
            int divider,
            int divisor,
            int expectedValue
    ) {}

    public static Stream<DivisionTest.TestData> getTestDivisionByZeroData() {
        return Stream.of(
                new DivisionTest.TestData(1, 0,-1),
                new DivisionTest.TestData(-2, 0,-1),
                new DivisionTest.TestData(0, 0,-1)
        );
    }

    @ParameterizedTest
    @MethodSource("getTestDivisionByZeroData")
    public void divisionByZeroTest(DivisionTest.TestData testData) {
        // Given
        Division division = Division.of(Constant.of(testData.divider),
                Constant.of(testData.divisor));
        VariableFrame variableFrame = new VariableFrame();

        // When
        Throwable exception = assertThrows(MacchiatoException.class,
                () -> division.compute(variableFrame));

        // Then
        assertEquals(exception.getMessage(), "Division by zero.");
    }

    public record NestedTestData(
            int divider,
            int divisor1,
            int divisor2,
            int divisor3,
            int expectedValue
    ) {}

    public static Stream<DivisionTest.NestedTestData> getTestNestedDivisionData() {
        return Stream.of(
                new DivisionTest.NestedTestData(60, 5,2, 3, 2),
                new DivisionTest.NestedTestData(144, 12,-2, -1, 6),
                new DivisionTest.NestedTestData(5, 1,1, 1, 5),
                new DivisionTest.NestedTestData(0, 7,33, 21, 0),
                new DivisionTest.NestedTestData(256, -8,4, 4, -2)
        );
    }

    @ParameterizedTest
    @MethodSource("getTestNestedDivisionData")
    public void divisionTest(DivisionTest.NestedTestData testData) {
        // Given
        Division division =
                Division.of(
                        Division.of(
                                Division.of(
                                        Constant.of(testData.divider),
                                        Constant.of(testData.divisor1)
                                ),
                                Constant.of(testData.divisor2)
                        ),
                        Constant.of(testData.divisor3)
                );

        VariableFrame variableFrame = new VariableFrame();
        int result = -1;

        // When
        try {
            result = division.compute(variableFrame);
        } catch (Exception e){
            e.printStackTrace();
        }

        // Then
        assertEquals(result, testData.expectedValue);
    }
}
