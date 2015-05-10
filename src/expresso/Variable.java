package expresso;

import java.util.Collections;
import java.util.TreeMap;


public class Variable extends Monomial{

    /**
     * Creates a variable expression representing the specified variable
     * @param v variable to represent
     */
    
    private final String variable;
    
    public Variable(String v){
        super(v);
        variable = v; 
        exps = Collections.unmodifiableMap(exps);
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
    
    public Monomial multiply(Monomial m){
        TreeMap<String, Integer> vMap = new TreeMap<>(m.getMap());
        if (vMap.keySet().contains(variable)){
            vMap.put(variable, vMap.get(variable)+1);
            return new Monomial(m.getCoefficient(), vMap);
        }
        else{
            vMap.put(variable, 1);
            return new Monomial(m.getCoefficient(), vMap);
        }
    }
    
    @Override
    public Expression differentiate(Variable v) {
        if (v.toString().equals(variable)) {
            return new Number(1);
        } return new Number(0); 
    }

    @Override
    public SimpleExpression simplify() {
        return this;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Variable)){return false;}
        Variable otherVariable = (Variable) obj;
        return toString().equals(otherVariable.toString());
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
