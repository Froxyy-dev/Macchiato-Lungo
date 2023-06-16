package macchiato.Contractors;

import macchiato.Commands.Command;
import macchiato.Exceptions.MacchiatoException;
import macchiato.Helpers.VariableFrame;

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
    public void executeEndBlock(ArrayDeque<VariableFrame> variableFrames) {
        // Dopóki nie otrzymaliśmy komendy "continue" oraz liczba kroków do 
        // wykonania jest równa 0, oczekujemy na komendy.
        while (stepsToMake == 0 && !continueRequest) {
            System.out.println("end block");
            getDebuggerCommand(variableFrames);
        }

        // Zmniejszamy liczbę instrukcji, które mamy wykonać.
        stepsToMake--;

        // Zdejmujemy ze stosu ostatnią ramkę i przepisujemy zmienne, które
        // zostały w niej zmienione (ale nie zadeklarowane) do przedostaniej
        // ramki.
        VariableFrame lastFrame = variableFrames.removeLast();
        variableFrames.getLast().rewrite(lastFrame);

        // Sprawdzamy, czy właśnie wykonaliśmy ostatni blok, jeśli tak to
        // wypisujemy końcowe wartościowanie zmiennych na standardowe wyjście.
        if (variableFrames.size() == 1) {
            System.out.println("Final variable values:");
            lastFrame.print();
        }
    }

    @Override
    public void executeCommand(Command command,
           ArrayDeque<VariableFrame> variableFrames) throws MacchiatoException {
        // Dopóki nie otrzymaliśmy komendy "continue" oraz liczba kroków do
        // wykonania jest równa 0, oczekujemy na komendy.
        while (stepsToMake == 0 && !continueRequest) {
            command.print();
            getDebuggerCommand(variableFrames);
        }

        // Zmniejszamy liczbę instrukcji, które mamy wykonać.
        stepsToMake--;

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

    // Funkcja czeka na komendę debuggera.
    private void getDebuggerCommand(ArrayDeque<VariableFrame> variableFrames) {
        while (scanner.hasNext()) {
            char command = scanner.next().charAt(0);
            int number = 0;

            if (command == 's' || command == 'd') {
                number = scanner.nextInt();
            }

            // Liczba całowita może być w szczególności ujemna.
            if (number < 0) {
                System.out.println("Entered negative number.");
                continue;
            }

            switch (command) {
                case 'c' -> {
                    // Ustawiamy flagę informującą o komendzie continue.
                    continueRequest = true;
                    return;
                }
                case 's' -> {
                    // Ustawiamy ilość kroków, która była równa 0.
                    stepsToMake = number;
                    return;
                }
                case 'd' -> {
                    if (number + 1 >= variableFrames.size()) {
                        System.out.println("Number is greater than " +
                                           "number of nested blocks.");
                    }
                    else {
                        ArrayDeque<VariableFrame> newArrayDeque =
                                variableFrames.clone();

                        for (int i = 0; i < number; i++) {
                            newArrayDeque.removeLast();
                        }

                        System.out.println("Variable values:");
                        newArrayDeque.getLast().print();
                    }
                }
                case 'e' -> {
                    System.out.println("Debugger ended successfully.");
                    System.exit(0);
                }
                default -> System.out.println("Incorrect debugger command.");
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
}