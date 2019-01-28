/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockstar.expression;

import rockstar.runtime.BlockContext;
import rockstar.runtime.Value;

/**
 *
 * @author Gabor
 */
public class NotExpression extends CompoundExpression {

    @Override
    protected String getFormat() {
        return "NOT (%s)";
    }

    @Override
    public int getPrecedence() {
        return 600;
    }

    @Override
    public int getParameterCount() {
        return 1;
    }

    @Override
    public Value evaluate(BlockContext ctx) {
        Expression expr = this.getParameters().get(0);
        Value v = expr.evaluate(ctx);
        return v.negate();
    }

}
