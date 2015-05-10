package expresso;

import java.util.Iterator;
import java.util.TreeMap;

public class SimpleExpression implements Expression {

    private Monomial leading;
    private Expression remaining = new Number(0);
    
    public SimpleExpression(Number n, TreeMap<String, Integer> components){
        leading = new Monomial(n, components);
    }
    
    public SimpleExpression(){};
    
    @Override
    public Expression add(Expression e) {
        return leading.add(remaining).add(e);
    }

    @Override
    public Expression multiply(Expression e) {
        return leading.add(remaining).multiply(e);
    }

    @Override
    public Expression differentiate(Variable v) {
        return leading.add(remaining).differentiate(v);
    }

    @Override
    public SimpleExpression simplify() {
        return this;
    }

    public Iterator<Monomial> iterator(){
        return new SimpleExpressionIterator(this);
    }
}
