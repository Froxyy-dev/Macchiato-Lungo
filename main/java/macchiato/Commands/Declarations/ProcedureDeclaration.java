package macchiato.Commands.Declarations;

import macchiato.Commands.Instructions.Block;
import macchiato.Commands.Procedure;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.Context;
import macchiato.Runtime.Contractor;

import java.util.ArrayDeque;
import java.util.ArrayList;

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
        contexts.getLast().getProcedureFrame().declareProcedure(procedure);
    }

    @Override
    public void print() {
        System.out.print(procedure.getString());
    }
}