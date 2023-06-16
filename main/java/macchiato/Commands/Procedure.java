package macchiato.Commands;

import macchiato.Commands.Instructions.Block;

import java.util.ArrayList;

public class Procedure {

    private final String procedureName;
    private final ArrayList<Character> parameters;
    private final Block procedureContent;

    public Procedure(String procedureName, ArrayList<Character> parameters,
                     Block procedureContent) {
        this.procedureName = procedureName;
        this.parameters = parameters;
        this.procedureContent = procedureContent;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public ArrayList<Character> getParameters() {
        return parameters;
    }

    public Block getProcedureContent() {
        return procedureContent;
    }

    public String getString() {
        StringBuilder stringBuilder = new StringBuilder(procedureName);

        stringBuilder.append(" (");

        boolean firstParameter = true;

        for (Character parameter : parameters) {
            if (!firstParameter) {
                stringBuilder.append(", ");
            }
            else {
                firstParameter = false;
            }

            stringBuilder.append(parameter);
        }

        stringBuilder.append(")\n");

        return stringBuilder.toString();
    }
}