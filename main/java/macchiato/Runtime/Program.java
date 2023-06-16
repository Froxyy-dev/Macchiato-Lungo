package macchiato.Runtime;

import macchiato.Commands.Instructions.Block;
import macchiato.Context.Context;
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
        ArrayDeque<Context> contexts = new ArrayDeque<>();
        contexts.add(new Context());

        Runner runner = new Runner();

        // Przechwytujemy błąd wykonania.
        try {
            runner.executeCommand(block, contexts);
            contexts.removeLast();

            System.out.println("Program ended successfully.");
        }
        catch (MacchiatoException macchiatoException) {
            System.out.println("Program execution failed.");
        }
    }

    // Funkcja wykonuje program z odpluskiwaczem.
    public void runWithDebugger() {
        //Stos ramek ze zmiennymi widocznymi w danym bloku.
        ArrayDeque<Context> contexts = new ArrayDeque<>();
        contexts.add(new Context());

        Debugger debugger = new Debugger();

        // Przechwytujemy błąd wykonania.
        try {
            debugger.executeCommand(block, contexts);

            // Po zakończeniu wykonania czekamy na koniec działania debuggera.
            debugger.waitForExit();

            contexts.removeLast();
        }
        catch (MacchiatoException macchiatoException) {
            System.out.println("Program execution failed.");
        }
    }
}