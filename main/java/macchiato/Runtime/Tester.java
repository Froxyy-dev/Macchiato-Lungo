package macchiato.Runtime;

import macchiato.Commands.Command;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.Context;

import java.util.ArrayDeque;

public class Tester extends Contractor {

    // Flaga, która mówi, czy błąd został już obsłużony.
    private boolean errorHandled;
    private final StringBuilder stringBuilder;

    public Tester() {
        errorHandled = false;
        this.stringBuilder = new StringBuilder();
    }

    @Override
    public void executeCommand(Command command, ArrayDeque<Context> contexts)
            throws MacchiatoException {
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
            stringBuilder.append(macchiatoException.getMessage());

            // 2. Ustawiamy flagę w przypadku wystąpienia błędu i rzucamy
            // wyjątek.
            errorHandled = true;
            throw macchiatoException;
        }
    }

    @Override
    public void executeEndBlock(ArrayDeque<Context> contexts)
            throws MacchiatoException {
        // Zdejmujemy ze stosu ostatnią ramkę i przepisujemy zmienne oraz
        // procedury, które zostały w niej zmienione (ale nie zadeklarowane)
        // do przedostaniej ramki.
        Context lastContext = contexts.removeLast();
        contexts.getLast().rewrite(lastContext);

        // Sprawdzamy, czy właśnie wykonaliśmy ostatni blok, jeśli tak to
        // wypisujemy końcowe wartościowanie zmiennych i nagłówki procedur na
        // standardowe wyjście.
        if (contexts.size() == 1) {
            stringBuilder.append(lastContext);
        }
    }

    public String getResult() {
        return stringBuilder.toString();
    }
}
