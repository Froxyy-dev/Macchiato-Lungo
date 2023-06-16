package macchiato.Commands;

import macchiato.Context.Context;
import macchiato.Runtime.Contractor;
import macchiato.Exceptions.MacchiatoException;

import java.util.ArrayDeque;

public abstract class Command {

    public abstract void execute(ArrayDeque<Context> contexts,
                                 Contractor contractor) throws MacchiatoException;

    public abstract void print();
}
