package expresso;

import java.util.TreeMap;

public class Monomial extends SimpleExpression {
    
    private Number coeff;
    private Expression term = new Number(1);
    private TreeMap<Variable, Integer> exps = new TreeMap<>();
    
    public Monomial(){};
    
    public Monomial(Number n, TreeMap<String, Integer> components){
        coeff = n;
        monomial(components);
    }
    
    public Monomial(Number coefficient, Expression variableTerm){
        coeff = coefficient;
        term = variableTerm;
    }
    
    private void monomial(TreeMap<String, Integer> components){
       for (String var: components.keySet()){
           Variable variable = new Variable(var);
           exps.put(variable, components.get(var));
           for (int i = 0; i < components.get(var); i++)
               term = term.multiply(variable);
       term = term.simplify(); //removes the 1 in front
       }
    }
    
    public Number getCoefficient(){
        return coeff;
    }
    
    public Monomial addCoeff(Number N){
        return new Monomial(new Number(Double.parseDouble(coeff.toString())+Double.parseDouble(N.toString())), term);
    }
    
    @Override
    public Expression add(Expression e) {
        return coeff.multiply(term).add(e);
    }

    @Override
    public Expression multiply(Expression e) {
        return coeff.multiply(term).multiply(e);
    }

    @Override
    public Expression differentiate(Variable v) {
        return coeff.multiply(term.differentiate(v));
    }

    @Override
    public SimpleExpression simplify() {
        return this;
    }
    
    /**
     * Get the Variable to Exponent TreeMap associated with this Monomial
     * 
     * @return a TreeMap mapping this Monomial's Variables to their respective exponents
     */
    public TreeMap<Variable, Integer> getMap(){
        TreeMap<Variable, Integer> copyOfMap = new TreeMap<>(exps);
        return copyOfMap;
    }

}
