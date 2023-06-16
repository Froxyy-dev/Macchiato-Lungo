package macchiato.Expressions;

import macchiato.Context.VariableFrame;

public class Constant extends Expression {

    private final int value;

    public Constant(int value) {
        this.value = value;
    }

    @Override
    public int compute(VariableFrame variableFrame) {
        return value;
    }

    @Override
    public void print() {
        if (value >= 0) {
            System.out.print(value);
        }
        else {
            System.out.print("(");
            System.out.print(value);
            System.out.print(")");
        }
    }
}
