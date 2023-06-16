package macchiato.Context;

import macchiato.Commands.Procedure;
import macchiato.Exceptions.MacchiatoException;

import java.util.HashMap;
import java.util.Map;

public class ProcedureFrame {

    private final Map<String, Procedure> procedureMap;
    private final Map<String, Boolean> initializedMap;

    public ProcedureFrame() {
        procedureMap = new HashMap<>();
        initializedMap = new HashMap<>();
    }

    public void declareProcedure(Procedure procedure) throws MacchiatoException {
        String procedureName = procedure.getProcedureName();

        if (initializedMap.getOrDefault(procedureName, false)){
            throw new MacchiatoException(procedureName + " is already declared.");
        }
        else {
            procedureMap.put(procedureName, procedure);
            initializedMap.put(procedureName, true);
        }
    }

    public ProcedureFrame copy() {
        ProcedureFrame newFrame = new ProcedureFrame();

        for (String procedureName : initializedMap.keySet()) {
            newFrame.procedureMap.put(procedureName,
                    procedureMap.get(procedureName));
            newFrame.initializedMap.put(procedureName, false);
        }

        return newFrame;
    }

    public void rewrite(ProcedureFrame procedureFrame) {
        for (String procedureName : initializedMap.keySet()) {
            if (!procedureFrame.initializedMap.get(procedureName)) {
                procedureMap.put(procedureName,
                        procedureFrame.procedureMap.get(procedureName));
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Procedures:\n");

        for (Procedure procedure : procedureMap.values()) {
            stringBuilder.append(procedure.getString());

        }

        return stringBuilder.toString();
    }

    public boolean containsProcedure(String procedureName) {
        return initializedMap.containsKey(procedureName);
    }

    public Procedure getProcedure(String procedureName) {
        return procedureMap.get(procedureName);
    }
}
