package expresso;

/**
 * Represents the sum of two expressions
 */
public class Sum implements Expression{
    
    private final Expression first;
    private final Expression second;
    
    //Abstraction function
    // Represents the sum first + second
    //Rep Invariant
    // True
    //Rep Exposure
    // first and second are private, final, and immutable
    
    /**
     * Constructs the sum of two specified expressions
     * @param e1 first expression
     * @param e2 second expression
     */
    public Sum(Expression e1, Expression e2){
        first = e1;
        second = e2;
        checkRep();
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
        if (first.equals(new Number(0))) {
            return second;
        }
        else if (second.equals(new Number(0))){
            return first;
        }
        Monomial firstMon = first.leading();
        SimpleExpression firstRemainder = first.truncate();
        Monomial secondMon = second.leading();
        SimpleExpression secondRemainder = second.truncate();
        Monomial addedTerm;
        if (firstMon.getMap().equals(secondMon.getMap())){
            addedTerm =  firstMon.addCoeff(new Number(secondMon.getCoefficient()));
            if (first.checkMonomial()){
                if (second.checkMonomial()){
                    return addedTerm;
                }
                else {
                    return secondRemainder.simpleAdd(addedTerm);
                }
            }
            else if (second.checkMonomial()){
                return firstRemainder.simpleAdd(addedTerm);
            }
            else {
                return firstRemainder.add(secondRemainder).simplify().simpleAdd(addedTerm);
            }
            
        }
        else{
            int compareVal =  new MonomialComparator().compare(firstMon, secondMon);
            if (compareVal > 0){
                addedTerm = firstMon;
                if (first.checkMonomial()){
                    return second.simpleAdd(addedTerm);
                }
                else {
                    return firstRemainder.add(second).simplify().simpleAdd(addedTerm);
                }
            }
            else {
                addedTerm = secondMon;
                if (second.checkMonomial()){
                    return first.simpleAdd(addedTerm);
                }
                else {
                    return secondRemainder.add(first).simplify().simpleAdd(addedTerm);
                }
            }
        }    
    }

    /**
     * Returns the String representation of this sum
     * in the form (first expression) + (second expression)
     */
    @Override
    public String toString() {
        return "(" + first.toString() + ") + (" + second.toString() + ')';
    }
    
    /**
     * Returns that the two sums are equal if and only if the 
     * first expressions are equal and second expressions are
     * equal
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Sum)){return false;}
        Sum otherSum = (Sum) other;
        return (first.equals(otherSum.first)) && (second.equals(otherSum.second));
    }
    
    @Override
    public int hashCode(){
        return first.hashCode() + second.hashCode();
    }
    
    private void checkRep(){
        assert true;
    }

}