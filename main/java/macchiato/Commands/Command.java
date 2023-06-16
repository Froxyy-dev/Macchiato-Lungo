package macchiato.Commands;

import macchiato.Contractors.Contractor;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Helpers.VariableFrame;

import java.util.ArrayDeque;

public abstract class Command {

    public abstract void execute(ArrayDeque<VariableFrame> variableFrames,
                              Contractor contractor) throws MacchiatoException;

    public abstract void print();
}