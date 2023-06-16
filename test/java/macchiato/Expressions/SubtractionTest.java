package macchiato.Expressions;

import macchiato.Context.VariableFrame;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtractionTest {

    public record TestData(
            int num1,
            int num2,
            int num3,
            int num4,
            int expectedNum
    ) {}

    public static Stream<SubtractionTest.TestData> getSubtractionTestData() {
        return Stream.of(
                new SubtractionTest.TestData(2, 3, 4, 5, -4),
                new SubtractionTest.TestData(2, 4, 0, 2, -4),
                new SubtractionTest.TestData(1, -5, 4, 0, 0),
                new SubtractionTest.TestData(0, 0, 0, 0, 0),
                new SubtractionTest.TestData(1, 2, 3, 4, -2),
                new SubtractionTest.TestData(-3, -1,-2, 0, 6)
        );
    }

    @ParameterizedTest
    @MethodSource("getSubtractionTestData")
    public void subtractionTest(SubtractionTest.TestData testData) {
        // Given
        Subtraction subtraction =
                Subtraction.of(
                        Subtraction.of(
                                Subtraction.of(
                                        Constant.of(testData.num4),
                                        Constant.of(testData.num1)
                                ),
                                Constant.of(testData.num2)
                        ),
                        Constant.of(testData.num3)
                );

        VariableFrame variableFrame = new VariableFrame();
        int result = -1;

        // When
        try {
            result = subtraction.compute(variableFrame);
        } catch (Exception e){
            e.printStackTrace();
        }

        // Then
        assertEquals(result, testData.expectedNum);
    }
}

