package expresso;


public class Variable implements Expression{

    /**
     * Creates a variable expression representing the specified variable
     * @param v variable to represent
     */
    
    private final String variable;
    public Variable(String v){
        variable = v; 
    }
    
    @Override
    public Expression add(Expression e) {
        Expression copyOfVariable = new Variable(variable); //copy to prevent rep exposure in Sum class?
        return new Sum(copyOfVariable, e);
    }

    @Override
    public Expression multiply(Expression e) {
        Expression copyOfVariable = new Variable(variable);
        return new Product(copyOfVariable, e);
    }

    @Override
    public Expression differentiate(Variable v) {
        if (v.toString().equals(variable)) {
            return new Number(1);
        } return new Number(0);
        
        
        
    }

    @Override
    public Expression simplify() {
        throw new RuntimeException("unimplemented");
    }
    
    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException("unimplemented");
    }
    
    
    @Override
    public String toString() {
        return variable;
    }
    
    
    @Override
    public int hashCode(){
        return variable.hashCode();
    }

}
