package expresso;

import java.util.Collections;
import java.util.TreeMap;

public class Monomial extends SimpleExpression {
    
    private double coeff;
    private Expression term = new Number(1);
    protected TreeMap<String, Integer> exps = new TreeMap<>();
    
    public Monomial(){};
    
    public Monomial(int n, TreeMap<String, Integer> components){
        coeff = n;
        monomial(components);
        exps = (TreeMap<String, Integer>) Collections.unmodifiableMap(exps);
    }
    
    public Monomial(double d, TreeMap<String, Integer> components){
        coeff = d;
        monomial(components);
        exps = (TreeMap<String, Integer>) Collections.unmodifiableMap(exps);
    }
    
    public Monomial(String v){
        coeff = 1;
        exps.put(v, 1);
        exps = (TreeMap<String, Integer>) Collections.unmodifiableMap(exps);
    }
    
    private void monomial(TreeMap<String, Integer> components){
       for (String var: components.keySet()){
           exps.put(var, components.get(var));
           Variable variable = new Variable(var);
           for (int i = 0; i < components.get(var); i++)
               term = term.multiply(variable);
       term = term.simplify(); //removes the 1 in front
       }
    }
    
    public double getCoefficient(){
        return coeff;
    }
    
    public Monomial addCoeff(Number N){
        return new Monomial(coeff+Double.parseDouble(N.toString()), exps);
    }
    
    @Override
    public Expression add(Expression e) {
        return (new Number(coeff)).multiply(term.add(e));
    }

    @Override
    public Expression multiply(Expression e) {
        return (new Number(coeff)).multiply(term.multiply(e));
    }

    @Override
    public Expression differentiate(Variable v) {
        return (new Number(coeff)).multiply(term.differentiate(v));
    }

    @Override
    public SimpleExpression simplify() {
        return this;
    }
    
    @Override
    public boolean checkMonomial() {
        return true;
        
    }
    
    /**
     * Get the Variable to Exponent TreeMap associated with this Monomial
     * 
     * @return a TreeMap mapping this Monomial's Variables to their respective exponents
     */
    public TreeMap<String, Integer> getMap(){
        TreeMap<String, Integer> copyOfMap = new TreeMap<>(exps);
        return copyOfMap;
    }

}
