package macchiato.Runtime;

import macchiato.Commands.Command;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.Context;

import java.util.ArrayDeque;

public abstract class Contractor {

    // Funkcja obsługuje wyjście z bloku.
    public abstract void executeCommand(Command command,
                                        ArrayDeque<Context> contexts) throws MacchiatoException;

    // Funkcja wykonuje komendę, czyli instrukcje lub deklaracje zmiennej.
    public abstract void executeEndBlock(ArrayDeque<Context> contexts)
            throws MacchiatoException;
}
