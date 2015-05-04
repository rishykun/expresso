package expresso;

public class Empty implements Expression{

    /**
     * Constructs an empty expression
     */
    public Empty(){
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
    public int hashCode(){
        throw new RuntimeException("unimplemented");
    }

}
