/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockstar.parser.checker;

import rockstar.expression.Expression;
import rockstar.expression.ListExpression;
import rockstar.expression.VariableReference;
import rockstar.parser.ExpressionFactory;
import rockstar.statement.FunctionBlock;
import rockstar.statement.Statement;

/**
 *
 * @author Gabor
 */
public class FunctionDefChecker extends Checker {

    @Override
    public Statement check() {
        int paramCount = -1;
        if (match(0, "takes", 1) || match(0, "takes", "nothing")) {
            paramCount = 2 - getMatchCounter();
        }

        if (paramCount >= 0) {
            // function name is the same as a variable name
            VariableReference nameRef = ExpressionFactory.tryVariableReferenceFor(getResult()[0], line);
            if (nameRef != null) {
                FunctionBlock fb = new FunctionBlock(nameRef.getFunctionName());
                if (paramCount > 0) {
                    // parse the expression, for an expression list
                    Expression expr = ExpressionFactory.tryExpressionFor(getResult()[1], line);
                    // treats null expression
                    ListExpression listExpr = ListExpression.asListExpression(expr);
                    if (listExpr != null) {
                        for (Expression expression : listExpr.getParameters()) {
                            if (expression instanceof VariableReference) {
                                // it is a variable reference
                                VariableReference paramRef = (VariableReference) expression;
                                fb.addParameterName(paramRef);
                            } else {
                                return null;
                            }
                        }
//                        }
                    } else {
                        return null;
                    }
                }
                return fb;
            }
        }

        return null;
    }

}
