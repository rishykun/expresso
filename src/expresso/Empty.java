package expresso;

/**
 * Empty expression used to assist in finding 
 * the end of an expression when parsing via ANTLR
 * 
 * Only available methods are equals and hashCode,
 * the rest return RuntimeExceptions
 */
public class Empty implements Expression {
    
    //Abstraction function
    //  Empty() represents an empty expression
    //Rep Invariant
    //  True
    //Rep exposure
    //  There is no rep to expose
    
    /**
     * @throws RuntimeException always
     */
    @Override
    public Expression add(Expression e) throws RuntimeException {
        throw new RuntimeException("Invalid operation");
    }
    
    /**
     * @throws RuntimeException always
     */
    @Override
    public Expression multiply(Expression e) {
        throw new RuntimeException("Invalid operation");
    }

    /**
     * @throws RuntimeException always
     */
    @Override
    public Expression differentiate(Variable v) {
        throw new RuntimeException("Invalid operation");
    }

    /**
     * @throws RuntimeException always
     */
    @Override
    public SimpleExpression simplify() {
        throw new RuntimeException("Invalid operation");
    }
    
    /**
     * @throws RuntimeException always
     */
    @Override
    public String toString(){
        throw new RuntimeException("Invalid operation");
    }
    
    @Override
    public boolean equals(Object other){
        if (other instanceof Empty){
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return 0;
    }
    
    private void checkRep() {
        assert true;
    }
}
