package macchiato.Expressions;

import macchiato.Context.VariableFrame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConstantTest {

    @Test
    public void constantTest() {
        // Given
        Constant constant = Constant.of(42);

        VariableFrame variableFrame = new VariableFrame();
        int result = -1;

        // When
        try {
            result = constant.compute(variableFrame);
        } catch (Exception e){
            e.printStackTrace();
        }

        // Then
        assertEquals(result, 42);
    }
}
