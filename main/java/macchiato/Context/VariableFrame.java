package macchiato.Context;

import macchiato.Exceptions.MacchiatoException;

import java.util.HashMap;
import java.util.Map;

public class VariableFrame {

    public final Map<Character, Integer> variableValues;
    public final Map<Character, Boolean> variableInitialized;

    public VariableFrame() {
        variableValues = new HashMap<>();
        variableInitialized = new HashMap<>();
    }

    public void initializeVariable(char variable, int value)
            throws MacchiatoException {
        if (variableInitialized.getOrDefault(variable, false)) {
            throw new MacchiatoException(variable + " is already initialized.");
        }
        else {
            variableValues.put(variable, value);
            variableInitialized.put(variable, true);
        }
    }

    public void setVariableValue(char variable, int newValue)
            throws MacchiatoException {
        if (!variableInitialized.containsKey(variable)) {
            throw new MacchiatoException(variable + " is not initialized.");
        }
        else {
            variableValues.put(variable, newValue);
        }
    }

    public int getVariableValue(char variable) throws MacchiatoException {
        if (!variableInitialized.containsKey(variable)) {
            throw new MacchiatoException(variable + " is not initialized.");
        }
        else {
            return variableValues.get(variable);
        }
    }

    // Funkcja zwraca skopiowaną ramkę.
    public VariableFrame copy() {
        VariableFrame newFrame = new VariableFrame();

        for (Character variable : variableInitialized.keySet()) {
            newFrame.variableValues.put(variable, variableValues.get(variable));
            newFrame.variableInitialized.put(variable, false);
        }

        return newFrame;
    }

    // Funkcja przepisuje ramkę zdejmowaną ze stosu.
    public void rewrite(VariableFrame variableFrame) {
        for (Character variable : variableInitialized.keySet()) {
            if (!variableFrame.variableInitialized.get(variable)) {
                variableValues.put(variable,
                        variableFrame.variableValues.get(variable));
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Variable values:\n");

        for (Character variable : variableInitialized.keySet()) {
            stringBuilder.append(variable).append(" = ")
                    .append(variableValues.get(variable));
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
