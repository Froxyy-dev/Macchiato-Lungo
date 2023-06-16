package macchiato.Commands.Instructions;

import macchiato.Context.Context;
import macchiato.Runtime.Contractor;
import macchiato.Expressions.Expression;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.VariableFrame;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class ForLoop extends Instruction {

    private final char variable;
    private final Expression expression;
    private final ArrayList<Instruction> instructions;

    public ForLoop(char variable, Expression expression,
                   ArrayList<Instruction> instructions) {
        this.variable = variable;
        this.expression = expression;
        this.instructions = instructions;
    }

    @Override
    public void execute(ArrayDeque<Context> contexts,
                        Contractor contractor) throws MacchiatoException {

        int cyclesNumber = expression.compute(contexts.getLast().getVariableFrame());

        // Tworzymy nową ramkę na zmienne na podstawie poprzedniej.
        Context newContext = new Context(contexts.getLast());
        contexts.add(newContext);

        VariableFrame variableFrame = newContext.getVariableFrame();
        variableFrame.initializeVariable(variable, 0);

        for (int i = 0; i < cyclesNumber; i++) {
            // Ustawiamy wartość zmiennej na podstawie liczby obrotów pętli.
            variableFrame.setVariableValue(variable, i);

            for (Instruction instruction : instructions) {
                contractor.executeCommand(instruction, contexts);
            }
        }

        // Zdejmujemy ze stosu ostatnią ramkę i przepisujemy zmienne, które
        // zostały w niej zmienione (ale nie zadeklarowane) do przedostaniej
        // ramki.
        Context lastContext = contexts.removeLast();
        contexts.getLast().rewrite(lastContext);
    }

    @Override
    public void print() {
        System.out.print("for " + variable + " ");
        expression.print();
        System.out.println();
    }
}