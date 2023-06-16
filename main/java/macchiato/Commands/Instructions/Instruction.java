package macchiato.Commands.Instructions;

import macchiato.Contractors.Contractor;
import macchiato.Commands.Command;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Helpers.VariableFrame;

import java.util.ArrayDeque;

public abstract class Instruction extends Command {

    // Funkcja wykonuje instrukcję.
    @Override
    public abstract void execute(ArrayDeque<VariableFrame> variableFrames,
                               Contractor contractor) throws MacchiatoException;

    // Funkcja wypisuje na standardowe wyjście instrukcję.
    @Override
    public abstract void print();
}
