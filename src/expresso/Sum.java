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
        SimpleExpression first = this.first.simplify();
        SimpleExpression second = this.second.simplify();
        Monomial firstMon = first.leading();
        SimpleExpression firstRemainder = first.truncate();
        Monomial secondMon = second.leading();
        SimpleExpression secondRemainder = second.truncate();
        Monomial addedTerm;
        int compareVal =  new MonomialComparator().compare(firstMon, secondMon);
        if (firstMon.equals(secondMon)){
            addedTerm =  firstMon.addCoeff(new Number(secondMon.getCoefficient()));
            if (first.checkMonomial()){
                if (second.checkMonomial()){
                    return addedTerm;
                }
                else return secondRemainder.simpleAdd(addedTerm);
            }
            else if (second.checkMonomial()){
                return firstRemainder.simpleAdd(addedTerm);
            }
            else return firstRemainder.add(secondRemainder).simplify().simpleAdd(addedTerm);
            
        }
        else if (compareVal > 0){
            addedTerm = secondMon;
            if (second.checkMonomial()){
                return first.simpleAdd(addedTerm);
            }
            else return secondRemainder.add(first).simplify();
        }
        else {
            addedTerm = firstMon;
            if (first.checkMonomial()){
                return second.simpleAdd(addedTerm);
            }
            else return firstRemainder.add(second).simplify();
        }    
    }
    
    /*
    public SimpleExpression simplify2() {
        Iterator<Monomial> firstIter = first.simplify().iterator();
        Iterator<Monomial> secondIter = second.simplify().iterator();   
        Monomial first = firstIter.next();
        Monomial second = secondIter.next();
        int compareVal;
        Comparator<Monomial> comparator = new MonomialComparator();
        SimpleExpression simplified = new Number(0);
        while (true) {
            compareVal = comparator.compare(first, second);
                if (first.getMap().equals(second.getMap())){
                    simplified = simplified.simpleAdd(first.addCoeff(new Number(second.getCoefficient())));
                    if (!firstIter.hasNext() || !secondIter.hasNext()){
                        break;
                    }
                    first = firstIter.next();
                    second = secondIter.next();
                }
                else if (compareVal == 1){
                    simplified = simplified.simpleAdd(second);
                    if (!secondIter.hasNext()){
                        simplified = simplified.simpleAdd(first);
                        break;
                    }
                    second = secondIter.next();                    
                }
                else if (compareVal == -1){
                    simplified = simplified.simpleAdd(first);
                    if (!firstIter.hasNext()){
                        simplified = simplified.simpleAdd(second);
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
        return simplified;
    }*/
    
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