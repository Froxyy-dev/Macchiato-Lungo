package macchiato.Commands.Instructions;

import macchiato.Context.Context;
import macchiato.Runtime.Contractor;
import macchiato.Expressions.Expression;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.VariableFrame;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class IfInstruction extends Instruction {

    private final Expression operand1;
    private final Expression operand2;
    private final String operator;
    private final ArrayList<Instruction> instructionsIfFulfilled;
    private final ArrayList<Instruction> instructionsIfNotFulfilled;

    public IfInstruction(Expression operand1, String operator,
                         Expression operand2,
                         ArrayList<Instruction> instructionsIfFulfilled,
                         ArrayList<Instruction> instructionsIfNotFulfilled) {
        this.operand1 = operand1;
        this.operator = operator;
        this.operand2 = operand2;
        this.instructionsIfFulfilled = instructionsIfFulfilled;
        this.instructionsIfNotFulfilled = instructionsIfNotFulfilled;
    }

    @Override
    public void execute(ArrayDeque<Context> contexts,
                        Contractor contractor) throws MacchiatoException {
        // Wyliczamy wyrażenia.
        VariableFrame variableFrame = contexts.getLast().getVariableFrame();
        int result1 = operand1.compute(variableFrame);
        int result2 = operand2.compute(variableFrame);

        ArrayList<Instruction> instructionsToExecute;

        // Sprawdzamy warunek i ustawiamy odpowiednie instrukcje.
        if (conditionFulfilled(result1, result2, operator)) {
            instructionsToExecute = instructionsIfFulfilled;
        }
        else {
            instructionsToExecute = instructionsIfNotFulfilled;
        }

        // Wykonujemy instrukcje.
        for (Instruction instruction : instructionsToExecute) {
            contractor.executeCommand(instruction, contexts);
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