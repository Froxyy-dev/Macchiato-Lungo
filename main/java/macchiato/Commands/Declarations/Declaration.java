package macchiato.Commands.Declarations;

import macchiato.Commands.Command;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.Context;
import macchiato.Runtime.Contractor;

import java.util.ArrayDeque;

public abstract class Declaration extends Command {

    public abstract void execute(ArrayDeque<Context> contexts,
                                 Contractor contractor) throws MacchiatoException;

    public abstract void print();
}
