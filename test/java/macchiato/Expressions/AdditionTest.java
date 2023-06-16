package macchiato.Expressions;

import macchiato.Context.VariableFrame;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class AdditionTest {

    public record TestData(
            int num1,
            int num2,
            int num3,
            int num4,
            int expectedNum
    ) {}

    public static Stream<AdditionTest.TestData> getAdditionTestData() {
        return Stream.of(
                new AdditionTest.TestData(60, 5,3, 3, 71),
                new AdditionTest.TestData(144, 12,-2, -1, 153),
                new AdditionTest.TestData(1, 2, 3, 4, 10),
                new AdditionTest.TestData(-2, 2, -2, 2,0),
                new AdditionTest.TestData(0, 0, 0, 0, 0),
                new AdditionTest.TestData(2121, 5, 6, 7, 2139)
        );
    }

    @ParameterizedTest
    @MethodSource("getAdditionTestData")
    public void additionTest(AdditionTest.TestData testData) {
        // Given
        Addition addition =
            Addition.of(
                Addition.of(
                    Addition.of(
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
            result = addition.compute(variableFrame);
        } catch (Exception e){
            e.printStackTrace();
        }

        // Then
        assertEquals(result, testData.expectedNum);
    }
}
