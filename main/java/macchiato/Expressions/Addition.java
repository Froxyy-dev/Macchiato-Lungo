package macchiato.Expressions;

public class Addition extends BinaryExpression {

    public Addition(Expression operand1, Expression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected int operation(int operand1, int operand2) {
        return operand1 + operand2;
    }

    @Override
    protected void printOperatorCharacter() {
        System.out.print('+');
    }

    public static Addition of(Expression operand1, Expression operand2) {
        return new Addition(operand1, operand2);
    }
}
