package macchiato.Commands.Instructions;

import macchiato.Context.Context;
import macchiato.Runtime.Contractor;
import macchiato.Expressions.Expression;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.VariableFrame;

import java.util.ArrayDeque;

public class VariableAssignment extends Instruction {

    private final char variable;
    private final Expression expression;

    public VariableAssignment(char variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public void execute(ArrayDeque<Context> contexts,
                        Contractor contractor) throws MacchiatoException {
        VariableFrame variableFrame = contexts.getLast().getVariableFrame();

        int computationResult = expression.compute(variableFrame);

        variableFrame.setVariableValue(variable, computationResult);
    }

    @Override
    public void print() {
        System.out.print(variable + " := ");
        expression.print();
        System.out.println();
    }
}
