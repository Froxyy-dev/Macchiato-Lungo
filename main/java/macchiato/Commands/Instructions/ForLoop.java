package macchiato.Commands.Instructions;

import macchiato.Contractors.Contractor;
import macchiato.Expressions.Expression;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.VariableFrame;

import java.util.ArrayDeque;

public class ForLoop extends Instruction {

    private final char variable;
    private final Expression expression;
    private final Instruction[] instructions;

    public ForLoop(char variable, Expression expression,
                   Instruction[] instructions) {
        this.variable = variable;
        this.expression = expression;
        this.instructions = instructions;
    }

    @Override
    public void execute(ArrayDeque<VariableFrame> variableFrames,
                        Contractor contractor) throws MacchiatoException {
        int cyclesNumber = expression.compute(variableFrames.getLast());

        // Tworzymy nową ramkę na zmienne na podstawie poprzedniej.
        VariableFrame newVariableFrame = variableFrames.getLast().copy();
        newVariableFrame.initializeVariable(variable, 0);
        variableFrames.add(newVariableFrame);

        for (int i = 0; i < cyclesNumber; i++) {
            // Ustawiamy wartość zmiennej na podstawie liczby obrotów pętli.
            newVariableFrame.setVariableValue(variable, i);

            for (Instruction instruction : instructions) {
                contractor.executeCommand(instruction, variableFrames);
            }
        }

        // Zdejmujemy ze stosu ostatnią ramkę i przepisujemy zmienne, które
        // zostały w niej zmienione (ale nie zadeklarowane) do przedostaniej
        // ramki.
        VariableFrame lastFrame = variableFrames.removeLast();
        variableFrames.getLast().rewrite(lastFrame);
    }

    @Override
    public void print() {
        System.out.print("for " + variable + " ");
        expression.print();
        System.out.println();
    }
}
