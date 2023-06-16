package macchiato.Builders;

import macchiato.Commands.Instructions.Block;
import macchiato.Runtime.Program;

public class ProgramBuilder {

    private Block block;

    public ProgramBuilder() {
        block = new BlockBuilder().build();
    }

    public ProgramBuilder block(Block block) {
        this.block = block;
        return this;
    }

    public Program build() {
        return new Program(block);
    }
}
