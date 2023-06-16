package macchiato.Expressions;

import macchiato.Exceptions.MacchiatoException;
import macchiato.Helpers.VariableFrame;

public abstract class Expression {

    public abstract int compute(VariableFrame variableFrame)
            throws MacchiatoException;

    public abstract void print();
}