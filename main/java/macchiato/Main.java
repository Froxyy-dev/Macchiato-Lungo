package macchiato;

import macchiato.Commands.Instructions.*;
import macchiato.Commands.Declarations.VariableDeclaration;
import macchiato.Expressions.*;

import macchiato.Runtime.Program;

public class Main {
    public static void main(String[] args) {
        // Kwestie techniczne:
        // Żeby stworzyć nowy program, trzeba utworzyć nowy obiekt klasy
        // Program, a w konstruktorze przekazać mu blok.
        // program.run() -> zwykłe uruchomienie
        // program.runWithDebugger() -> uruchomienie z debuggerem

        // Program z tresci zadania zaliczeniowego.
        /*
        VariableExpression n = new VariableExpression('n');
        VariableExpression k = new VariableExpression('k');
        VariableExpression p = new VariableExpression('p');
        VariableExpression i = new VariableExpression('i');

        Constant zero = new Constant(0);
        Constant one = new Constant(1);
        Constant two = new Constant(2);
        Constant thirty = new Constant(30);

        Addition k_plus_2 = new Addition(k, two);
        Addition i_plus_2 = new Addition(i, two);

        Subtraction k_minus_2 = new Subtraction(k, two);
        Subtraction n_minus_1 = new Subtraction(n, one);

        Modulo k_mod_i = new Modulo(k, i);

        VariableDeclaration n_30 = new VariableDeclaration('n', thirty);
        VariableDeclaration p_1 = new VariableDeclaration('p', one);

        VariableAssignment k_to_k_plus_2 = new VariableAssignment('k',
                k_plus_2);
        VariableAssignment i_to_i_plus_2 = new VariableAssignment('i',
                i_plus_2);
        VariableAssignment p_to_zero = new VariableAssignment('p', zero);

        Block block = new Block(
          new VariableDeclaration[]{n_30},

          new Instruction[]{
            new ForLoop('k', n_minus_1, new Instruction[]{
              new Block(
                new VariableDeclaration[]{p_1},

                new Instruction[]{
                  k_to_k_plus_2,

                  new ForLoop('i', k_minus_2,
                    new Instruction[]{
                      i_to_i_plus_2,

                      new IfInstruction(k_mod_i, "=", zero,
                        new Instruction[]{p_to_zero})
                    }
                  ),

                  new IfInstruction(p, "=", one,
                    new Instruction[]{new PrintInstruction(k)})
                }
              )
            })
          }
        );

        Program program = new Program(block);
        program.run();

        // System.out.println("\nExecution with debugger\n");
        // program.runWithDebugger();
         */
    }

}