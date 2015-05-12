package expresso;

public class Sum implements Expression{
    
    private final Expression first;
    private final Expression second;
    
    //Abstraction function
    //Rep Invariant
    //Rep Exposure
    
    /**
     * Constructs the sum of two specified expressions
     * @param e1 first expression
     * @param e2 second expression
     */
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
        System.out.println("Simplifying "+this.toString());
        SimpleExpression first = this.first.simplify();
        SimpleExpression second = this.second.simplify();
        System.out.println("Pre-simplify "+this.toString());
        System.out.println("Simplified first: "+first.toString());
        System.out.println("Simplified second: "+second.toString());
        if (first.equals(new Number(0))) {
            System.out.println("Simplified "+ this.toString() + "to" + second.toString());
            return second;
        }
        else if (second.equals(new Number(0))){
            System.out.println("Simplified "+ this.toString() + "to" + first.toString());
            return first;
        }
        Monomial firstMon = first.leading();
        SimpleExpression firstRemainder = first.truncate();
        Monomial secondMon = second.leading();
        SimpleExpression secondRemainder = second.truncate();
        Monomial addedTerm;
        int compareVal =  new MonomialComparator().compare(firstMon, secondMon);
        System.out.println(firstMon);
        System.out.println(secondMon);
        System.out.println(firstMon.equals(secondMon));
        if (firstMon.getMap().equals(secondMon.getMap())){
            System.out.println("Equal leading monomials");
            addedTerm =  firstMon.addCoeff(new Number(secondMon.getCoefficient()));
            if (first.checkMonomial()){
                if (second.checkMonomial()){
                    System.out.println("Simplified "+ this.toString() + "to" + addedTerm.toString());
                    return addedTerm;
                }
                else {
                    SimpleExpression s = secondRemainder.simpleAdd(addedTerm);
                    System.out.println("Simplified "+ this.toString() + "to" + s.toString());
                    return s;
                    //return secondRemainder.simpleAdd(addedTerm);
                }
            }
            else if (second.checkMonomial()){
                SimpleExpression s = firstRemainder.simpleAdd(addedTerm);
                System.out.println("Simplified "+ this.toString() + "to" + s.toString());
                return s;
                //return firstRemainder.simpleAdd(addedTerm);
            }
            else {
                SimpleExpression s = firstRemainder.add(secondRemainder).simplify().simpleAdd(addedTerm);
                System.out.println("Simplified "+ this.toString() + "to" + s.toString());
                return s;
                //return firstRemainder.add(secondRemainder).simplify().simpleAdd(addedTerm);
            }
            
        }
        else if (compareVal > 0){
            addedTerm = firstMon;
            if (first.checkMonomial()){
                SimpleExpression s = second.simpleAdd(addedTerm);
                System.out.println("Simplified "+ this.toString() + "to" + s.toString());
                return s;
                //return second.simpleAdd(addedTerm);
            }
            else {
                System.out.println("here");
                System.out.println(first.checkMonomial());
                System.out.println(first);
                System.out.println(second);
                System.out.println(firstRemainder);
                System.out.println(second);
                System.out.println(addedTerm);
                SimpleExpression s = firstRemainder.add(second).simplify().simpleAdd(addedTerm);
                System.out.println("Simplified "+ this.toString() + "to" + s.toString());
                return s;
                //return firstRemainder.add(second).simplify().simpleAdd(addedTerm);
            }
        }
        else {
            addedTerm = secondMon;
            if (second.checkMonomial()){
                SimpleExpression s = first.simpleAdd(addedTerm);
                System.out.println("Simplified "+ this.toString() + "to" + s.toString());
                return s;
                //return first.simpleAdd(addedTerm);
            }
            else {
                SimpleExpression s =  secondRemainder.add(first).simplify().simpleAdd(addedTerm);
                System.out.println("Simplified "+ this.toString() + "to" + s.toString());
                return s;
                //return secondRemainder.add(first).simplify().simpleAdd(addedTerm);
            }
        }    
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ") + (" + second.toString() + ')';
    }
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Sum)){return false;}
        Sum otherSum = (Sum) other;
        return (first.equals(otherSum.first)) && (second.equals(otherSum.second));
        //return this.toString().equals(otherExpression.toString());
    }
    
    @Override
    public int hashCode(){
        return first.hashCode() + second.hashCode();
    }

}