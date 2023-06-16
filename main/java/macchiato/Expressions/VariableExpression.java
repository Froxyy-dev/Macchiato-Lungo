package macchiato.Expressions;

import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.VariableFrame;

public class VariableExpression extends Expression {

    private final char variable;

    public VariableExpression(char variable) {
        this.variable = variable;
    }

    @Override
    public int compute(VariableFrame variableFrame) throws MacchiatoException {
        return variableFrame.getVariableValue(variable);
    }

    @Override
    public void print() {
        System.out.print(variable);
    }
}
