package macchiato.Runtime;

import macchiato.Commands.Command;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Context.Context;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Debugger extends Contractor{

    private int stepsToMake;
    // Zmienna, która kontroluje, czy została wywołana komendą "continue".
    private boolean continueRequest;
    // Flaga, która mówi czy błąd został już obsłużony.
    private boolean errorHandled;
    private final Scanner scanner;

    public Debugger() {
        stepsToMake = 0;
        continueRequest = false;
        errorHandled = false;
        scanner = new Scanner(System.in);
    }

    @Override
    public void executeEndBlock(ArrayDeque<Context> contexts)
            throws MacchiatoException {
        // Dopóki nie otrzymaliśmy komendy "continue" oraz liczba kroków do
        // wykonania jest równa 0, oczekujemy na komendy.
        while (stepsToMake == 0 && !continueRequest) {
            System.out.println("end block");
            getDebuggerCommand(contexts);
        }

        // Zmniejszamy liczbę instrukcji, które mamy wykonać.
        stepsToMake--;

        // Zdejmujemy ze stosu ostatnią ramkę i przepisujemy zmienne, które
        // zostały w niej zmienione (ale nie zadeklarowane) do przedostaniej
        // ramki.
        Context lastContext = contexts.removeLast();
        contexts.getLast().rewrite(lastContext);

        // Sprawdzamy, czy właśnie wykonaliśmy ostatni blok, jeśli tak to
        // wypisujemy końcowe wartościowanie zmiennych na standardowe wyjście.
        if (contexts.size() == 1) {
            System.out.println("Program ended.");
            System.out.println(lastContext);
        }
    }

    @Override
    public void executeCommand(Command command,
                               ArrayDeque<Context> contexts) throws MacchiatoException {
        // Dopóki nie otrzymaliśmy komendy "continue" oraz liczba kroków do
        // wykonania jest równa 0, oczekujemy na komendy.
        while (stepsToMake == 0 && !continueRequest) {
            command.print();
            getDebuggerCommand(contexts);
        }

        // Zmniejszamy liczbę instrukcji, które mamy wykonać.
        stepsToMake--;

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

            // 3. Wypisujemy wartościowanie zmiennych widocznych w bloku.
            System.out.println(contexts.getLast().toString());

            // 4. Ustawiamy flagę w przypadku wystąpienia błędu i rzucamy
            // wyjątek.
            errorHandled = true;
            throw macchiatoException;
        }
    }

    // Funkcja czeka na komendę debuggera.
    private void getDebuggerCommand(ArrayDeque<Context> contexts)
            throws MacchiatoException {
        while (scanner.hasNext()) {
            char command = scanner.next().charAt(0);
            int number = 0;
            String pathToFile = "";

            if (command == 's' || command == 'd') {
                number = scanner.nextInt();
            }
            else if (command == 'm'){
                pathToFile = scanner.next();
            }

            // Liczba całowita może być w szczególności ujemna.
            if (number < 0) {
                System.out.println("Entered negative number.");
                continue;
            }

            executeDebuggerCommand(command, number, pathToFile, contexts);

            if (continueRequest || stepsToMake > 0) {
                return;
            }
        }
    }

    // Funkcja jest wywoływana po zakończeniu wykonywania programu i czeka na
    // zakończenia działania debuggera.
    public void waitForExit() {
        // Jeżeli wykonywanie programu zakończyło się przed wykonaniem
        // zadanej liczby instrukcji, to wypisujemy odpowiedni komunikat.
        if (stepsToMake > 0 && !continueRequest) {
            System.out.println("Program ended before executing steps.");
        }
        // W przeciwnym przypadku informujemy o pomyślnym zakończeniu programu.
        else {
            System.out.println("Program ended successfully.");
        }

        while (scanner.hasNext()) {
            char command = scanner.next().charAt(0);

            switch (command) {
                // Obługujemy wywołanie komendy continue.
                case 'c' -> System.out.println("Program has already ended.");
                case 'e' -> {
                    System.out.println("Debugger ended successfully.");
                    System.exit(0);
                }
                default -> System.out.println("Incorrect debugger command.");
            }
        }
    }

    public void executeDebuggerCommand(char command, int number,
                                       String pathToFile,
                                       ArrayDeque<Context> contexts)
            throws MacchiatoException {
        switch (command) {
            case 'c' -> {
                // Ustawiamy flagę informującą o komendzie continue.
                continueRequest = true;
            }
            case 's' -> {
                // Ustawiamy ilość kroków, która była równa 0.
                stepsToMake = number;
            }
            case 'd' -> {
                display(number, contexts);
            }
            case 'e' -> {
                System.out.println("Debugger ended successfully.");
                System.exit(0);
            }
            case 'm' -> {
                writeToFile(pathToFile, contexts.getLast().toString());
            }
            default -> System.out.println("Incorrect debugger command.");
        }
    }

    public void display(int number, ArrayDeque<Context> contexts) {
        if (number + 1 >= contexts.size()) {
            System.out.println("Number is greater than " +
                    "number of nested blocks.");
        }
        else {
            ArrayDeque<Context> newArrayDeque = contexts.clone();

            for (int i = 0; i < number; i++) {
                Context lastContext = newArrayDeque.removeLast();
                newArrayDeque.getLast().rewrite(lastContext);
            }

            System.out.println(newArrayDeque.getLast());
        }
    }

    public void writeToFile(String pathToFile, String content)
            throws MacchiatoException {
        try {
            PrintWriter printWriter =
                    new PrintWriter(new FileWriter(pathToFile));

            printWriter.println(content);
            printWriter.close();

            System.out.println("Successful dump to file.");
        } catch (IOException e) {
            throw new MacchiatoException(e.getMessage());
        }
    }
}