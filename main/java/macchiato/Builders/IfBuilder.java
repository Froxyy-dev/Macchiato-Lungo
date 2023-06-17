package macchiato.Builders;

import macchiato.Commands.Instructions.IfInstruction;
import macchiato.Commands.Instructions.Instruction;
import macchiato.Expressions.Expression;

import java.util.ArrayList;

public class IfBuilder extends Builder {

    private final Expression operand1;
    private final Expression operand2;
    private final String operator;
    private ArrayList<Instruction> instructionsIfFulfilled;
    private ArrayList<Instruction> instructionsIfNotFulfilled;

    public IfBuilder(Expression operand1, Expression operand2, String operator) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
        instructionsIfFulfilled = new ArrayList<>();
        instructionsIfNotFulfilled = new ArrayList<>();
    }

    public IfBuilder instructionsIfFulfilled(ArrayList<Instruction>
                                                     instructionsIfFulfilled) {
        this.instructionsIfFulfilled = instructionsIfFulfilled;
        return this;
    }

    public IfBuilder instructionsIfNotFulfilled(ArrayList<Instruction>
                                                  instructionsIfNotFulfilled) {
        this.instructionsIfNotFulfilled = instructionsIfNotFulfilled;
        return this;
    }

    @Override
    public IfInstruction build() {
        return new IfInstruction(operand1, operator, operand2,
                instructionsIfFulfilled, instructionsIfNotFulfilled);
    }
}
