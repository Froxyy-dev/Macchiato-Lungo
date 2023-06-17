package macchiato.Builders;

import macchiato.Commands.Declarations.*;
import macchiato.Commands.Instructions.Block;
import macchiato.Expressions.Expression;

import java.util.ArrayList;
import java.util.List;

public class DeclarationBuilder {

    private final ArrayList<Declaration> declarations;

    public DeclarationBuilder() {
        declarations = new ArrayList<>();
    }

    public DeclarationBuilder declareVariable(char variable,
                                              Expression expression) {
        declarations.add(new VariableDeclaration(variable, expression));
        return this;
    }

    public DeclarationBuilder declareProcedure(String procedureName,
                                               List<Character> parameters,
                                               Block procedureContent) {
        declarations.add(new ProcedureDeclaration(procedureName,
                new ArrayList<>(parameters),
                procedureContent));
        return this;
    }

    public ArrayList<Declaration> build() {
        return declarations;
    }
}
