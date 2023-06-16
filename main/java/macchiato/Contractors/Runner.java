package macchiato.Contractors;

import macchiato.Commands.Command;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Helpers.VariableFrame;

import java.util.ArrayDeque;

public class Runner extends Contractor {

    // Flaga, która mówi, czy błąd został już obsłużony.
    private boolean errorHandled;

    public Runner() {
        errorHandled = false;
    }

    @Override
    public void executeEndBlock(ArrayDeque<VariableFrame> variableFrames) {
        // Zdejmujemy ze stosu ostatnią ramkę i przepisujemy zmienne, które
        // zostały w niej zmienione (ale nie zadeklarowane) do przedostaniej
        // ramki.
        VariableFrame lastFrame = variableFrames.removeLast();
        variableFrames.getLast().rewrite(lastFrame);

        // Sprawdzamy, czy właśnie wykonaliśmy ostatni blok, jeśli tak to
        // wypisujemy końcowe wartościowanie zmiennych na standardowe wyjście.
        if (variableFrames.size() == 1) {
            System.out.println("Final variable values: ");
            lastFrame.print();
        }
    }

    @Override
    public void executeCommand(Command command,
           ArrayDeque<VariableFrame> variableFrames) throws MacchiatoException {
        try {
            command.execute(variableFrames, this);
        }
        catch (MacchiatoException macchiatoException) {
            // Sprawdzamy czy wyjątek został już obsłużony, jak tak to 
            // rzucamy go ponownie.
            if (errorHandled) {
                throw macchiatoException;
            }
            
            // Jeżeli wystąpi błąd wykonania, to:
            // 1. Wypisujemy powód, przez który wystąpił.
            System.out.println("Exception which occurred:");
            System.out.println(macchiatoException.getMessage());

            // 2. Wypisujemy instrukcję, która go spowodowała.
            System.out.println("Exception was caused by:");
            command.print();

            // 3. Wypisujemy wartościowanie zmiennych widocznych w bloku.
            System.out.println("Variable values in a block which caused an " +
                    "exception: ");
            variableFrames.getLast().print();

            // 4. Ustawiamy flagę w przypadku wystąpienia błędu i rzucamy 
            // wyjątek.
            errorHandled = true;
            throw macchiatoException;
        }
    }
}
