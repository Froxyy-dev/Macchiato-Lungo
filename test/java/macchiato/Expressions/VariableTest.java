package macchiato.Expressions;

import macchiato.Context.VariableFrame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VariableTest {

    @Test
    public void variableTest() {
        // Given
        VariableExpression variableExpression = VariableExpression.named('a');

        VariableFrame variableFrame = new VariableFrame();
        int result = -1;

        // When
        try {
            variableFrame.initializeVariable('a', 42);
            result = variableExpression.compute(variableFrame);
        } catch (Exception e){
            e.printStackTrace();
        }

        // Then
        assertEquals(result, 42);
    }
}
