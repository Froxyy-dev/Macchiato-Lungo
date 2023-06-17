package macchiato.Commands.Instructions;

import macchiato.Commands.Procedure;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Expressions.Expression;
import macchiato.Context.Context;
import macchiato.Context.VariableFrame;
import macchiato.Runtime.Contractor;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class InvokeProcedure extends Instruction {

    private final String procedureName;
    private final ArrayList<Expression> arguments;

    public InvokeProcedure(String procedureName,
                           ArrayList<Expression> arguments) {

        this.procedureName = procedureName;
        this.arguments = arguments;
    }

    @Override
    public void execute(ArrayDeque<Context> contexts,
                        Contractor contractor) throws MacchiatoException {
        Context lastContext = contexts.getLast();

        if(!lastContext.containsProcedure(procedureName)) {
            throw new MacchiatoException(procedureName + "is not declared.");
        }

        Procedure procedure = lastContext.getProcedure(procedureName);

        if (procedure.getParameters().size() != arguments.size()) {
            throw new MacchiatoException("Number of arguments is not correct.");
        }

        ArrayList<Integer> argumentsValue = new ArrayList<>();
        for (Expression expression : arguments) {
            argumentsValue.add(expression.compute(lastContext.getVariableFrame()));
        }

        Context newContext = new Context(lastContext);
        VariableFrame newVariableFrame = newContext.getVariableFrame();

        for (int i = 0; i < procedure.getParameters().size(); i++) {
            newVariableFrame.initializeVariable(procedure.getParameters().get(i),
                    argumentsValue.get(i));
        }

        contexts.add(newContext);

        contractor.executeCommand(procedure.getProcedureContent(), contexts);

        Context previousContext = contexts.removeLast();
        contexts.getLast().rewrite(previousContext);
    }

    @Override
    public void print() {
        System.out.print("invoke " + procedureName +" (");

        boolean firstParameter = true;

        for (Expression expression : arguments) {
            if (!firstParameter) {
                System.out.print(", ");
            }
            else {
                firstParameter = false;
            }

            expression.print();
        }

        System.out.println(")");
    }
}