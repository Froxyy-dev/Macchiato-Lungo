package macchiato.Expressions;

import macchiato.Exceptions.MacchiatoException;

public class Modulo extends BinaryExpression {

    public Modulo(Expression operand1, Expression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected int operation(int operand1, int operand2)
            throws MacchiatoException {
        if (operand2 == 0) {
            throw new MacchiatoException("Modulo by zero.");
        }
        else {
            return operand1 % operand2;
        }
    }

    @Override
    protected void printOperatorCharacter() {
        System.out.print('%');
    }
}
