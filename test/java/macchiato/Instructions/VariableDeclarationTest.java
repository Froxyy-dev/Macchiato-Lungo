package macchiato.Instructions;

import macchiato.Expressions.Constant;
import macchiato.Builders.*;
import macchiato.Runtime.Program;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VariableDeclarationTest {

    @Test
    public void variableDeclarationTest() {
        // Given
        Program program = new ProgramBuilder()
            .block(
                new BlockBuilder()
                .declarations(
                    new DeclarationBuilder()
                    .declareVariable('a', Constant.of(1))
                    .build()
                )
                .build()
            )
            .build();

        // When
        String result = program.testRun();
        result = result.split(System.lineSeparator())[1];

        // Then
        assertEquals("a = 1", result);
    }
}
