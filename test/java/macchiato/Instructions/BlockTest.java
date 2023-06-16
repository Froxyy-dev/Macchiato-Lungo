package macchiato.Instructions;

import macchiato.Builders.*;
import macchiato.Runtime.Program;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {

    @Test
    public void blockTest() {
        // Given
        Program program = new ProgramBuilder()
            .block(
                new BlockBuilder()
                .build()
            )
            .build();

        // When
        String result = program.testRun();
        result = result.split(System.lineSeparator())[1];

        // Then
        assertEquals("Procedures:", result);
    }
}
