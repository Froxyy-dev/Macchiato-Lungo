package macchiato.Commands.Instructions;

import macchiato.Contractors.Contractor;
import macchiato.Commands.VariableDeclaration;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.VariableFrame;

import java.util.ArrayDeque;

public class Block extends Instruction {

    private final VariableDeclaration[] variableDeclarations;
    private final Instruction[] instructions;

    public Block(VariableDeclaration[] variableDeclarations,
                 Instruction[] instructions) {
        this.variableDeclarations = variableDeclarations;
        this.instructions = instructions;
    }

    @Override
    public void execute(ArrayDeque<VariableFrame> variableFrames,
                        Contractor contractor) throws MacchiatoException {
        // Tworzymy nową ramkę na zmienne na podstawie poprzedniej.
        VariableFrame newVariableFrame = variableFrames.getLast().copy();
        variableFrames.add(newVariableFrame);

        // Deklarujemy zmienne.
        for (VariableDeclaration variableDeclaration : variableDeclarations) {
            contractor.executeCommand(variableDeclaration, variableFrames);
        }

        // Wykonujemy instrukcje.
        for (Instruction instruction : instructions) {
            contractor.executeCommand(instruction, variableFrames);
        }

        // Wykonujemy czynności związane z końcem bloku.
        contractor.executeEndBlock(variableFrames);
    }

    @Override
    public void print() {
        System.out.println("begin block");
    }
}
