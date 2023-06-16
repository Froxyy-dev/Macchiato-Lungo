package macchiato.Builders;

import macchiato.Commands.Instructions.ForLoop;
import macchiato.Commands.Instructions.Instruction;
import macchiato.Expressions.Expression;

import java.util.ArrayList;

public class ForLoopBuilder extends Builder {

    private final char variable;
    private final Expression expression;
    private ArrayList<Instruction> instructions;

    public ForLoopBuilder(char variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
        instructions = new ArrayList<>();
    }

    public ForLoopBuilder instructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
        return this;
    }

    @Override
    public ForLoop build() {
        return new ForLoop(variable, expression, instructions);
    }
}