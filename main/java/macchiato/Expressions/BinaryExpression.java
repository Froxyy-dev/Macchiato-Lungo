package macchiato.Expressions;

import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.VariableFrame;

public abstract class BinaryExpression extends Expression {

    private final Expression operand1;
    private final Expression operand2;

    public BinaryExpression(Expression operand1, Expression operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    protected abstract int operation(int operand1, int operand2)
            throws MacchiatoException;

    @Override
    public int compute(VariableFrame variableFrame) throws MacchiatoException {
        int result1 = operand1.compute(variableFrame);

        int result2 = operand2.compute(variableFrame);

        return operation(result1, result2);
    }

    protected abstract void printOperatorCharacter();

    @Override
    public void print() {
        System.out.print("(");
        operand1.print();
        System.out.print(" ");
        printOperatorCharacter();
        System.out.print(" ");
        operand2.print();
        System.out.print(")");
    }
}
