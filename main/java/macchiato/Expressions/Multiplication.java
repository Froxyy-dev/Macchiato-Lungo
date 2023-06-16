package macchiato.Expressions;

public class Multiplication extends BinaryExpression {

    public Multiplication(Expression operand1, Expression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected int operation(int operand1, int operand2) {
        return operand1 * operand2;
    }

    @Override
    protected void printOperatorCharacter() {
        System.out.print('*');
    }

    public static Multiplication of(Expression operand1, Expression operand2) {
        return new Multiplication(operand1, operand2);
    }
}
