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
        throw new RuntimeException("unimplemented");
    }

    @Override
    public Expression multiply(Expression e) {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public Expression differentiate(Variable v) {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public Expression simplify() {
        throw new RuntimeException("unimplemented");
    }
    
    @Override
    public String toString() {
        throw new RuntimeException("unimplemented");
    }
    
    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException("unimplemented");
    }
    
    @Override
    public int hashCode(){
        throw new RuntimeException("unimplemented");
    }

}
