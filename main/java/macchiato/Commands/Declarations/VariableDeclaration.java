package macchiato.Commands.Declarations;

import macchiato.Context.Context;
import macchiato.Runtime.Contractor;
import macchiato.Expressions.Expression;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.VariableFrame;

import java.util.ArrayDeque;

public class VariableDeclaration extends Declaration {

    private final char variable;
    private final Expression expression;

    public VariableDeclaration(char variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public void execute(ArrayDeque<Context> contexts,
                        Contractor contractor) throws MacchiatoException {
        VariableFrame variableFrame = contexts.getLast().getVariableFrame();

        int computationResult = expression.compute(variableFrame);

        variableFrame.initializeVariable(variable, computationResult);
    }

    @Override
    public void print() {
        System.out.print("var " + variable + " ");
        expression.print();
        System.out.println();
    }
}
