package macchiato.Commands.Declarations;

import macchiato.Commands.Command;
import macchiato.Context.VariableFrame;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Runtime.Contractor;

import java.util.ArrayDeque;

public abstract class Declaration extends Command {

    public abstract void execute(ArrayDeque<VariableFrame> variableFrames,
                                 Contractor contractor) throws MacchiatoException;

    public abstract void print();
}
