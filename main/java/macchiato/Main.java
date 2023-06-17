package macchiato;

import macchiato.Expressions.*;

import macchiato.Builders.BlockBuilder;
import macchiato.Builders.DeclarationBuilder;
import macchiato.Builders.InstructionBuilder;
import macchiato.Builders.ProgramBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Kwestie techniczne:
        // Program składa się z jednego bloku.
        // program.run() -> zwykłe uruchomienie
        // program.runWithDebugger() -> uruchomienie z debuggerem

        // Program z tresci zadania zaliczeniowego.

        var program = new ProgramBuilder()
            .block(
                new BlockBuilder()
                .declarations(
                    new DeclarationBuilder()
                    .declareVariable('x', Constant.of(101))
                    .declareVariable('y', Constant.of(1))
                    .declareProcedure("out", List.of('a'),
                        new BlockBuilder()
                        .instructions(
                            new InstructionBuilder()
                            .print(Addition.of(VariableExpression.named('a'),
                                VariableExpression.named('x')))
                            .build()
                        )
                        .build()
                    )
                    .build()
                )
                .instructions(
                    new InstructionBuilder()
                    .assign('x', Subtraction.of(VariableExpression.named('x'), VariableExpression.named('y')))
                    .invoke("out", List.of(VariableExpression.named('x')))
                    .invoke("out", List.of(Constant.of(100)))
                    .block(
                        new BlockBuilder()
                        .declarations(
                            new DeclarationBuilder()
                            .declareVariable('x', Constant.of(10))
                            .build()
                        )
                        .instructions(
                            new InstructionBuilder()
                            .invoke("out", List.of(Constant.of(100)))
                            .build()
                        )
                        .build()
                    )
                    .build()
                )
                .build()
            )
            .build();

        program.run();                 // Wywołanie bez odpluskiwacza.
        program.runWithDebugger();     // Wywołanie z odpluskiwaczem.
    }
}