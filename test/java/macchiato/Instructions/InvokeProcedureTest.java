package macchiato.Instructions;

import macchiato.Expressions.Constant;
import macchiato.Expressions.VariableExpression;
import macchiato.Builders.*;
import macchiato.Runtime.Program;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InvokeProcedureTest {

    @Test
    public void invokeProcedureTest() {
        // Given
        Program program = new ProgramBuilder()
            .block(
                new BlockBuilder()
                .declarations(
                    new DeclarationBuilder()
                    .declareProcedure("a",
                        new ArrayList<>(List.of('b')),
                            new BlockBuilder()
                            .instructions(
                                new InstructionBuilder()
                                .assign('c', VariableExpression.named('b'))
                                .build()
                            )
                            .build()
                    )
                    .declareVariable('c', Constant.of(0))
                    .build()
                )
                .instructions(
                    new InstructionBuilder()
                    .invoke("a", List.of(Constant.of(1)))
                    .build()
                )
                .build()
            )
            .build();

        // When
        String result = program.testRun();
        result = result.split(System.lineSeparator())[1];

        // Then
        assertEquals("c = 1", result);
    }
}
