package expresso;

public class Product implements Expression{

    /**
     * Constructs the product of two specified expressions
     * @param e1 first expression
     * @param e2 second expression
     */
    public Product(Expression e1, Expression e2){
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
