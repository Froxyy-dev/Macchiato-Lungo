package macchiato.Context;

import macchiato.Commands.Procedure;

public class Context {

    private final VariableFrame variableFrame;
    private final ProcedureFrame procedureFrame;

    public Context() {
        this.variableFrame = new VariableFrame();
        this.procedureFrame = new ProcedureFrame();
    }

    public Context(Context context) {
        this.variableFrame = context.variableFrame.copy();
        this.procedureFrame = context.procedureFrame.copy();
    }

    public boolean containsProcedure(String procedureName) {
        return procedureFrame.containsProcedure(procedureName);
    }

    public VariableFrame getVariableFrame() {
        return variableFrame;
    }

    public ProcedureFrame getProcedureFrame() {
        return procedureFrame;
    }

    public Procedure getProcedure(String procedureName) {
        return procedureFrame.getProcedure(procedureName);
    }

    public void rewrite(Context context) {
        variableFrame.rewrite(context.getVariableFrame());
        procedureFrame.rewrite(context.getProcedureFrame());
    }

    public String toString() {
        return variableFrame.toString() + procedureFrame.toString();
    }
}
