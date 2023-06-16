package macchiato.Instructions;

import macchiato.Builders.BlockBuilder;
import macchiato.Builders.InstructionBuilder;
import macchiato.Builders.ProgramBuilder;
import org.junit.jupiter.api.Test;
import macchiato.Expressions.Constant;
import macchiato.Expressions.Division;
import macchiato.Runtime.Program;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintTest {

    @Test
    public void printTest() {
        // Given
        Program program = new ProgramBuilder()
            .block(
                new BlockBuilder()
                .instructions(
                    new InstructionBuilder()
                    .print(
                        Division.of(
                            Constant.of(42), Constant.of(0)
                        )
                    )
                    .build()
                )
                .build()
            )
            .build();

        // When
        String result = program.testRun();

        // Then
        assertEquals("Division by zero.", result);
    }
}
