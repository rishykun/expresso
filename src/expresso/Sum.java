package expresso;

import java.util.Comparator;
import java.util.Iterator;

public class Sum implements Expression{

    /**
     * Constructs the sum of two specified expressions
     * @param e1 first expression
     * @param e2 second expression
     */
    
    private final Expression first;
    private final Expression second;
    
    public Sum(Expression e1, Expression e2){
        first = e1;
        second = e2;
    }
    
    @Override
    public Expression add(Expression e) {
        Expression copyOfSummation = new Sum(first, second);
        return new Sum(copyOfSummation, e);
    }

    @Override
    public Expression multiply(Expression e) {
        Expression copyOfSummation = new Sum(first, second);
        return new Product(copyOfSummation, e);
    }

    @Override
    public Expression differentiate(Variable v) {
        Expression diffFirst = first.differentiate(v);
        Expression diffSecond = second.differentiate(v);
        return new Sum(diffFirst, diffSecond);
    }

    @Override
    public SimpleExpression simplify() {
        Iterator<Monomial> firstIter = first.simplify().iterator();
        Iterator<Monomial> secondIter = second.simplify().iterator();
        Monomial first = firstIter.next();
        Monomial second = secondIter.next();
        int compareVal;
        Comparator<Monomial> comparator = new MonomialComparator();
        SimpleExpression simplified = new Number(0);
        while (true) {
            compareVal = comparator.compare(first, second);
                if (first.equals(second)){
                    simplified.simpleAdd(first.addCoeff(new Number(second.getCoefficient())));
                    if (!firstIter.hasNext() || !secondIter.hasNext()){
                        break;
                    }
                    first = firstIter.next();
                    second = secondIter.next();
                }
                else if (compareVal == 1){
                    simplified.simpleAdd(second);
                    if (!secondIter.hasNext()){
                        break;
                    }
                    second = secondIter.next();                    
                }
                else if (compareVal == -1){
                    simplified.simpleAdd(first);
                    if (!firstIter.hasNext()){
                        break;
                    }
                    first = firstIter.next();
                }

            }
        while (firstIter.hasNext()){
            simplified = simplified.simpleAdd(firstIter.next());
        }
        while (secondIter.hasNext()){
            simplified = simplified.simpleAdd(secondIter.next());
        }
        return simplified.truncate();//removes 0
    }
    
    @Override
    public String toString() {
        return "(" + first.toString() + ") + " + second.toString() + ')';
    }
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Sum)){return false;}
        Sum otherExpression = (Sum) other;
        return this.toString().equals(otherExpression.toString());
    }
    
    @Override
    public int hashCode(){
        return first.hashCode() + second.hashCode();
    }

}