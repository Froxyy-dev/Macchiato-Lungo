package macchiato.Expressions;

public class Subtraction extends BinaryExpression {

    public Subtraction(Expression operand1, Expression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected int operation(int operand1, int operand2) {
        return operand1 - operand2;
    }

    @Override
    protected void printOperatorCharacter() {
        System.out.print('-');
    }
}
