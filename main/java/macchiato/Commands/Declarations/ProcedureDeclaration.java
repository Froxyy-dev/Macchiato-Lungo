package macchiato.Commands.Declarations;

import macchiato.Commands.Instructions.Block;
import macchiato.Commands.Procedure;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.Context;
import macchiato.Runtime.Contractor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProcedureDeclaration extends Declaration {

    private final Procedure procedure;

    public ProcedureDeclaration(String procedureName,
                                ArrayList<Character> parameters,
                                Block procedureContent) {
        this.procedure = new Procedure(procedureName, parameters,
                procedureContent);
    }

    @Override
    public void execute(ArrayDeque<Context> contexts, Contractor contractor)
            throws MacchiatoException {

        ArrayList<Character> parameters = procedure.getParameters();
        Set<Character> distinctParameters = new HashSet<>(parameters);

        if (distinctParameters.size() != parameters.size()) {
            throw new MacchiatoException("Parameter names are not different.");
        }

        contexts.getLast().getProcedureFrame().declareProcedure(procedure);
    }

    @Override
    public void print() {
        System.out.print(procedure.getString());
    }
}