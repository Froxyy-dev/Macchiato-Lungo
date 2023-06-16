package macchiato.Helpers;

import macchiato.Commands.Instructions.Block;
import macchiato.Contractors.Debugger;
import macchiato.Contractors.Runner;
import macchiato.Exceptions.MacchiatoException;

import java.util.ArrayDeque;

public class Program {

    // Programy są pojedynczym blokiem.
    private final Block block;

    public Program(Block block) {
        this.block = block;
    }

    // Funkcja wykonuje program bez odpluskiwacza.
    public void run() {
        //Stos ramek ze zmiennymi widocznymi w danym bloku.
        ArrayDeque<VariableFrame> variableFrames = new ArrayDeque<>();
        variableFrames.add(new VariableFrame());

        Runner runner = new Runner();

        // Przechwytujemy błąd wykonania.
        try {
            runner.executeCommand(block, variableFrames);
            variableFrames.removeLast();

            System.out.println("Program ended successfully.");
        }
        catch (MacchiatoException macchiatoException) {
            System.out.println("Program execution failed.");
        }
    }

    // Funkcja wykonuje program z odpluskiwaczem.
    public void runWithDebugger() {
        //Stos ramek ze zmiennymi widocznymi w danym bloku.
        ArrayDeque<VariableFrame> variableFrames = new ArrayDeque<>();
        variableFrames.add(new VariableFrame());

        Debugger debugger = new Debugger();

        // Przechwytujemy błąd wykonania.
        try {
            debugger.executeCommand(block, variableFrames);

            // Po zakończeniu wykonania czekamy na koniec działania debuggera.
            debugger.waitForExit();

            variableFrames.removeLast();
        }
        catch (MacchiatoException macchiatoException) {
            System.out.println("Program execution failed.");
        }
    }
}