package macchiato.Commands.Instructions;

import macchiato.Contractors.Contractor;
import macchiato.Expressions.Expression;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Helpers.VariableFrame;

import java.util.ArrayDeque;

public class IfInstruction extends Instruction {

    private final Expression operand1;
    private final Expression operand2;
    private final String operator;
    private final Instruction[] instructionsIfFulfilled;
    private final Instruction[] instructionsIfNotFulfilled;

    public IfInstruction(Expression operand1, String operator,
                         Expression operand2,
                         Instruction[] instructionsIfFulfilled,
                         Instruction[] instructionsIfNotFulfilled) {
        this.operand1 = operand1;
        this.operator = operator;
        this.operand2 = operand2;
        this.instructionsIfFulfilled = instructionsIfFulfilled;
        this.instructionsIfNotFulfilled = instructionsIfNotFulfilled;
    }

    // Konstruktor, gdy pomijamy część else <instrukcje>
    public IfInstruction(Expression operand1, String operator,
                         Expression operand2,
                         Instruction[] instructionsIfFulfilled) {
        this.operand1 = operand1;
        this.operator = operator;
        this.operand2 = operand2;
        this.instructionsIfFulfilled = instructionsIfFulfilled;
        this.instructionsIfNotFulfilled = new Instruction[]{};
    }

    @Override
    public void execute(ArrayDeque<VariableFrame> variableFrames,
                        Contractor contractor) throws MacchiatoException {
        // Wyliczamy wyrażenia.
        int result1 = operand1.compute(variableFrames.getLast());
        int result2 = operand2.compute(variableFrames.getLast());

        Instruction[] instructionsToExecute;

        // Sprawdzamy warunek i ustawiamy odpowiednie instrukcje.
        if (conditionFulfilled(result1, result2, operator)) {
            instructionsToExecute = instructionsIfFulfilled;
        }
        else {
            instructionsToExecute = instructionsIfNotFulfilled;
        }

        // Wykonujemy instrukcje.
        for (Instruction instruction : instructionsToExecute) {
            contractor.executeCommand(instruction, variableFrames);
        }
    }

    @Override
    public void print() {
        System.out.print("if ");
        operand1.print();
        System.out.print(" " + operator + " ");
        operand2.print();
        System.out.println();
    }

    private boolean conditionFulfilled(int computation1, int computation2,
                                    String operator) throws MacchiatoException {
        switch (operator) {
            case "=" -> {
                return computation1 == computation2;
            }
            case "<>" -> {
                return computation1 != computation2;
            }
            case "<" -> {
                return computation1 < computation2;
            }
            case ">" -> {
                return computation1 > computation2;
            }
            case "<=" -> {
                return computation1 <= computation2;
            }
            case ">=" -> {
                return computation1 >= computation2;
            }
            default ->
                 throw new MacchiatoException("Incorrect comparison operator.");
        }
    }
}
