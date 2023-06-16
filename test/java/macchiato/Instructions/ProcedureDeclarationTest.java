package macchiato.Instructions;

import macchiato.Builders.*;
import macchiato.Runtime.Program;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProcedureDeclarationTest {

    @Test
    public void procedureDeclarationTest() {
        // Given
        Program program = new ProgramBuilder()
            .block(
                new BlockBuilder()
                .declarations(
                    new DeclarationBuilder()
                    .declareProcedure("a", new ArrayList<>(List.of('b')),
                            new BlockBuilder().build()
                    )
                    .build()
                )
                .build()
            )
            .build();

        // When
        String result = program.testRun();
        result = result.split(System.lineSeparator())[2];

        // Then
        assertEquals("a (b)", result);
    }
}
