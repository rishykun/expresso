package expresso;

import java.util.Iterator;

public class SimpleExpression implements Expression {

    private Monomial leading;
    private SimpleExpression remaining;
    
    public SimpleExpression(Monomial m, SimpleExpression e){
        leading = m;
        remaining = e;
    }
    
    public SimpleExpression(){};
    
    @Override
    public Expression add(Expression e) {
        return leading.add(remaining).add(e);
    }
    
    public SimpleExpression simpleAdd(Monomial m){
        return new SimpleExpression(m, this);
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
    
    public SimpleExpression truncate(){
        return remaining;
    }
    
    public static void main(String[] args){
        
    }
}
