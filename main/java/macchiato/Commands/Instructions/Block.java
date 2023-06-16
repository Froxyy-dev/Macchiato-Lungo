package macchiato.Commands.Instructions;

import macchiato.Commands.Declarations.Declaration;
import macchiato.Context.Context;
import macchiato.Runtime.Contractor;
import macchiato.Exceptions.MacchiatoException;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Block extends Instruction {

    private final ArrayList<Declaration> declarations;
    private final ArrayList<Instruction> instructions;

    public Block(ArrayList<Declaration> declarations,
                 ArrayList<Instruction> instructions) {
        this.declarations = declarations;
        this.instructions = instructions;
    }

    @Override
    public void execute(ArrayDeque<Context> contexts,
                        Contractor contractor) throws MacchiatoException {
        // Tworzymy nową ramkę na zmienne na podstawie poprzedniej.
        Context newContext = new Context(contexts.getLast());
        contexts.add(newContext);

        // Deklarujemy zmienne.
        for (Declaration declaration : declarations) {
            contractor.executeCommand(declaration, contexts);
        }

        // Wykonujemy instrukcje.
        for (Instruction instruction : instructions) {
            contractor.executeCommand(instruction, contexts);
        }

        // Wykonujemy czynności związane z końcem bloku.
        contractor.executeEndBlock(contexts);
    }

    @Override
    public void print() {
        System.out.println("begin block");
    }
}