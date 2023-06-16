package macchiato.Commands.Instructions;

import macchiato.Context.Context;
import macchiato.Runtime.Contractor;
import macchiato.Expressions.Expression;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.VariableFrame;

import java.util.ArrayDeque;

public class PrintInstruction extends Instruction {

    private final Expression expression;

    public PrintInstruction(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute(ArrayDeque<Context> contexts,
                        Contractor contractor) throws MacchiatoException {
        VariableFrame variableFrame = contexts.getLast().getVariableFrame();
        int result = expression.compute(variableFrame);

        System.out.println(result);
    }

    @Override
    public void print() {
        System.out.print("print ");
        expression.print();
        System.out.println();
    }
}