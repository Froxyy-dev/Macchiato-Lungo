package macchiato.Expressions;

import macchiato.Context.VariableFrame;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class MultiplicationTest {

    public record TestData(
            int num1,
            int num2,
            int num3,
            int num4,
            int expectedNum
    ) {}

    public static Stream<MultiplicationTest.TestData> getMultiplicationTestData() {
        return Stream.of(
                new MultiplicationTest.TestData(2, 3, 4, 5, 120),
                new MultiplicationTest.TestData(0, 0, 0, 0, 0),
                new MultiplicationTest.TestData(32, 32,32, 32, 1048576),
                new MultiplicationTest.TestData(1, -1, -1, 42, 42),
                new MultiplicationTest.TestData(2, -3, -4, -5, -120),
                new MultiplicationTest.TestData(42, 17, 22, 32, 502656)
        );
    }

    @ParameterizedTest
    @MethodSource("getMultiplicationTestData")
    public void multiplicationTest(MultiplicationTest.TestData testData) {
        // Given
        Multiplication multiplication =
                Multiplication.of(
                        Multiplication.of(
                                Multiplication.of(
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
            result = multiplication.compute(variableFrame);
        } catch (Exception e){
            e.printStackTrace();
        }

        // Then
        assertEquals(result, testData.expectedNum);
    }
}
