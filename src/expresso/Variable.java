package expresso;

import java.util.Collections;


public class Variable extends Monomial{
    
    private final String variable;
    
    //Abstraction function
    //Rep Invariant
    //Rep Exposure
    
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

}
