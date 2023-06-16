package macchiato.Instructions;

import macchiato.Expressions.Addition;
import macchiato.Expressions.Constant;
import macchiato.Expressions.VariableExpression;
import macchiato.Builders.*;
import macchiato.Runtime.Program;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IfInstructionTest {

    @Test
    public void ifTest() {
        // Given
        Program program = new ProgramBuilder()
            .block(
                new BlockBuilder()
                .declarations(
                    new DeclarationBuilder()
                    .declareVariable('a', Constant.of(0))
                    .build()
                )
                .instructions(
                    new InstructionBuilder()
                    .ifInstruction(
                        new IfBuilder(Constant.of(6), Constant.of(5), ">=")
                        .instructionsIfFulfilled(
                            new InstructionBuilder()
                            .assign('a',
                                Addition.of(
                                    VariableExpression.named('a'),
                                    Constant.of(1)
                                )
                            )
                            .build()
                        )
                        .build()
                    )
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
