package macchiato.Runtime;

import macchiato.Commands.Command;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.Context;

import java.util.ArrayDeque;

public class Runner extends Contractor {

    // Flaga, która mówi, czy błąd został już obsłużony.
    private boolean errorHandled;

    public Runner() {
        errorHandled = false;
    }

    @Override
    public void executeEndBlock(ArrayDeque<Context> contexts) {
        // Zdejmujemy ze stosu ostatnią ramkę i przepisujemy zmienne oraz
        // procedury, które zostały w niej zmienione (ale nie zadeklarowane)
        // do przedostaniej ramki.
        Context lastContext = contexts.removeLast();
        contexts.getLast().rewrite(lastContext);

        // Sprawdzamy, czy właśnie wykonaliśmy ostatni blok, jeśli tak to
        // wypisujemy końcowe wartościowanie zmiennych i nagłówki procedur na
        // standardowe wyjście.
        if (contexts.size() == 1) {
            System.out.println("Program ended.");
            System.out.println(lastContext);
        }
    }

    @Override
    public void executeCommand(Command command,
                               ArrayDeque<Context> contexts) throws MacchiatoException {
        try {
            command.execute(contexts, this);
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

            // 3. Wypisujemy wartościowanie zmiennych oraz procedur
            // widocznych w bloku.
            System.out.println(contexts.getLast());

            // 4. Ustawiamy flagę w przypadku wystąpienia błędu i rzucamy 
            // wyjątek.
            errorHandled = true;
            throw macchiatoException;
        }
    }
}
