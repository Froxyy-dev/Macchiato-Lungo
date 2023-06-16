package macchiato.Builders;

import macchiato.Commands.Declarations.Declaration;
import macchiato.Commands.Instructions.Block;
import macchiato.Commands.Instructions.Instruction;

import java.util.ArrayList;

public class BlockBuilder extends Builder {

    private ArrayList<Declaration> declarations;
    private ArrayList<Instruction> instructions;

    public BlockBuilder() {
        declarations = new ArrayList<>();
        instructions = new ArrayList<>();
    }

    public BlockBuilder declarations(ArrayList<Declaration> declarations) {
        this.declarations = declarations;
        return this;
    }

    public BlockBuilder instructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
        return this;
    }

    @Override
    public Block build() {
        return new Block(declarations, instructions);
    }
}
