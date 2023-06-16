package macchiato.Commands.Instructions;

import macchiato.Contractors.Contractor;
import macchiato.Expressions.Expression;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Helpers.VariableFrame;

import java.util.ArrayDeque;

public class PrintInstruction extends Instruction {

    private final Expression expression;

    public PrintInstruction(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute(ArrayDeque<VariableFrame> variableFrames,
                        Contractor contractor) throws MacchiatoException {
        int result = expression.compute(variableFrames.getLast());

        System.out.println(result);
    }

    @Override
    public void print() {
        System.out.print("print ");
        expression.print();
        System.out.println();
    }
}
