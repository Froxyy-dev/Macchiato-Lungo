package macchiato.Commands;

import macchiato.Runtime.Contractor;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.VariableFrame;

import java.util.ArrayDeque;

public abstract class Command {

    public abstract void execute(ArrayDeque<VariableFrame> variableFrames,
                              Contractor contractor) throws MacchiatoException;

    public abstract void print();
}
