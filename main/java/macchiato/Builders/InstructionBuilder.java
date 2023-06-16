package macchiato.Builders;

import macchiato.Commands.Instructions.*;
import macchiato.Expressions.Expression;

import java.util.ArrayList;
import java.util.List;

public class InstructionBuilder {

    private final ArrayList<Instruction> instructions;

    public InstructionBuilder() {
        this.instructions = new ArrayList<>();
    }

    public InstructionBuilder block(Block block) {
        instructions.add(block);
        return this;
    }

    public InstructionBuilder forLoop(ForLoop forLoop) {
        instructions.add(forLoop);
        return this;
    }

    public InstructionBuilder ifInstruction(IfInstruction ifInstruction) {
        instructions.add(ifInstruction);
        return this;
    }

    public InstructionBuilder invoke(String procedureName,
                                           List<Expression> arguments) {
        instructions.add(new InvokeProcedure(procedureName,
                new ArrayList<>(arguments)));
        return this;
    }

    public InstructionBuilder print(Expression expression) {
        instructions.add(new PrintInstruction(expression));
        return this;
    }

    public InstructionBuilder assign(char variable, Expression expression) {
        instructions.add(new VariableAssignment(variable, expression));
        return this;
    }

    public ArrayList<Instruction> build() {
        return instructions;
    }
}
