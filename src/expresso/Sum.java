package expresso;

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
        SimpleExpression firstSimp = first.simplify();
        SimpleExpression secondSimp = second.simplify();
        throw new RuntimeException("unimplemented");
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