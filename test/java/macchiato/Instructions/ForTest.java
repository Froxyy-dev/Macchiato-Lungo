package macchiato.Instructions;

import macchiato.Expressions.Addition;
import macchiato.Expressions.Constant;
import macchiato.Expressions.VariableExpression;
import macchiato.Builders.*;
import macchiato.Runtime.Program;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ForTest {

    @Test
    public void forTest() {
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
                    .forLoop(
                        new ForLoopBuilder('i', Constant.of(6))
                        .instructions(
                            new InstructionBuilder()
                            .assign('a',
                                Addition.of(
                                    VariableExpression.named('a'),
                                    VariableExpression.named('i')
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
        assertEquals("a = 15", result);
    }
}
