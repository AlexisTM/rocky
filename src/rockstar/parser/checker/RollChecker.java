/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockstar.parser.checker;

import rockstar.expression.VariableReference;
import rockstar.statement.RollStatement;
import rockstar.statement.Statement;

/**
 *
 * @author Gabor
 */
public class RollChecker extends Checker<VariableReference, VariableReference, Object> {

    private static final ParamList[] PARAM_LIST = new ParamList[]{
        new ParamList("Roll", variableAt(2), "into", variableAt(1)),
        new ParamList("Roll", variableAt(2), "in", variableAt(1)),
        new ParamList("Pop", variableAt(2), "into", variableAt(1)),
        new ParamList("Pop", variableAt(2), "in", variableAt(1)),
        new ParamList("Pull", variableAt(1), "from", variableAt(2))};

    @Override
    public Statement check() {
        return check(PARAM_LIST, this::validate);
    }

    private Statement validate(ParamList params) {
        VariableReference targetRefExpr = getE1();
        VariableReference arrayExpr = getE2();
        return new RollStatement(arrayExpr, targetRefExpr);
    }
}
