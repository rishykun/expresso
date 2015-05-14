package expresso;

import java.util.Collections;

/**
 *  Represents a variable in an Expression
 */
public class Variable extends Monomial{
    
    private final String variable;
    
    //Abstraction function
    //  variable is the string representation of the variable represented
    //Rep Invariant
    //  True
    //Rep Exposure
    //  variable is private, final, and immutable
    
    /**
     * Creates a variable expression representing the specified variable
     * @param v variable to represent
     */
    public Variable(String v){
        variable = v; 
        coeff = 1;
        exps.put(v, 1);
        exps = Collections.unmodifiableMap(exps);
        isMonomial = true;
        leading = this;
        checkRep();
    }
    
    @Override
    public Expression differentiate(Variable v) {
        if (equals(v)) {
            return new Number(1);
        } return new Number(0); 
    }
    
    @Override
    public String toString() {
        return variable;
    }
    
    @Override
    public int hashCode(){
        return variable.hashCode();
    }
    
    public void checkRep(){
        assert true;
    }

}
