package macchiato.Helpers;

import macchiato.Exceptions.MacchiatoException;

public class VariableFrame {

    private final int[] variableValues;
    private final int[] variableInitialized; // Przyjmuje wartości ze zbioru
    // {0, 1, 2}. Oznaczają one:
    // 0 - zmienna niezainicjalizowana;
    // 1 - zmienna zainicjalizowana we wcześniejszym bloku;
    // 2 - zmienna zainicjalizowana w tym bloku.

    public VariableFrame() {
        variableValues = new int[26];
        variableInitialized = new int[26];
    }

    public void initializeVariable(char variable, int value)
            throws MacchiatoException {
        int variableToChange = mapper(variable);

        if (variableInitialized[variableToChange] == 2) {
            throw new MacchiatoException(variable + " is already initialized.");
        }
        else {
            variableValues[variableToChange] = value;
            variableInitialized[variableToChange] = 2;
        }
    }

    public void setVariableValue(char variable, int newValue)
            throws MacchiatoException {
        int variableToChange = mapper(variable);

        if (variableInitialized[variableToChange] == 0) {
            throw new MacchiatoException(variable + " is not initialized.");
        }
        else {
            variableValues[variableToChange] = newValue;
        }
    }

    public int getVariableValue(char variable) throws MacchiatoException {
        int variableToReturn = mapper(variable);

        if (variableInitialized[variableToReturn] == 0) {
            throw new MacchiatoException(variable + " is not initialized.");
        }
        else {
            return variableValues[variableToReturn];
        }
    }

    // Funkcja zwraca skopiowaną ramkę.
    public VariableFrame copy() {
        VariableFrame newFrame = new VariableFrame();

        for (int i=0; i<26; i++) {
            if (variableInitialized[i] != 0) {
                newFrame.variableInitialized[i] = 1;
                newFrame.variableValues[i] = variableValues[i];
            }
        }

        return newFrame;
    }

    // Funkcja przepisuje ramkę zdejmowaną ze stosu.
    public void rewrite(VariableFrame variableFrame) {
        for (int i=0; i<26; i++) {
            // Sprawdzamy, czy dana zmienna już była wcześniej oraz
            // czy nie została zainicjalizowana ponownie.
            if (variableInitialized[i] != 0 &&
                    variableFrame.variableInitialized[i] != 2) {
                variableValues[i] = variableFrame.variableValues[i];
            }
        }
    }

    // Funkcja mapuje znak na liczbę całkowitą.
    private int mapper(char c) throws MacchiatoException {
        int variable = Integer.parseInt(String.valueOf(c - 'a'));

        if (variable >= 26 || variable < 0) {
            throw new MacchiatoException("Incorrect variable " + c + ".");
        }
        else {
            return variable;
        }
    }

    public void print() {
        for (int i=0; i<26; i++) {
            if(variableInitialized[i] != 0) {
                System.out.println((char)(i + 'a') + " = " + variableValues[i]);
            }
        }

        System.out.println();
    }
}
