package macchiato.Commands.Declarations;

import macchiato.Commands.Command;
import macchiato.Runtime.Contractor;
import macchiato.Expressions.Expression;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.VariableFrame;

import java.util.ArrayDeque;

public class VariableDeclaration extends Command {

    private final char variable;
    private final Expression expression;

    public VariableDeclaration(char variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public void execute(ArrayDeque<VariableFrame> variableFrames,
                        Contractor contractor) throws MacchiatoException {
        VariableFrame variableFrame = variableFrames.getLast();

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
