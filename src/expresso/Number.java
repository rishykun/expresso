package expresso;

public class Number implements Expression{

    /**
     * Creates a number expression representing the specified integer
     * @param n integer to represent
     */
    public Number(int n){
        throw new RuntimeException("unimplemented");
    }
    
    /**
     * Creates a number expression representing the specified decimal
     * @param d decimal to represent
     */
    public Number(double d){
        throw new RuntimeException("unimplemented");
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
