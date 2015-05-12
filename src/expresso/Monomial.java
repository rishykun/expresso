package expresso;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a monomial Expression
 */
public class Monomial extends SimpleExpression {
    
    double coeff;
    Map<String, Integer> exps = new TreeMap<>();
    
    //Abstraction function
    //Rep Invariant
    //Rep Exposure
    
    public Monomial(int n, TreeMap<String, Integer> components){
        coeff = n;
        exps = Collections.unmodifiableMap(components);
        isMonomial = true;
        leading = this;
    }
    
    public Monomial(double d, TreeMap<String, Integer> components){
        coeff = d;
        exps = Collections.unmodifiableMap(components);
        isMonomial = true;
        leading = this;
    }
    
    protected Monomial(){}
    
    /**
     * Gets the coefficient of a monomial term
     * @return the coefficient of this term
     */
    public double getCoefficient(){
        return coeff;
    }
    
    /**
     * Returns a Monomial whose coefficient is this one
     * added by a specified number
     * @param N Number to add to coefficient
     * @return Monomial with the added coefficient
     */
    public Monomial addCoeff(Number N){
        return new Monomial(coeff+Double.parseDouble(N.toString()), new TreeMap<>(exps));
    }
    
    @Override
    public SimpleExpression truncate(){
        return new Number(0);
    }
    
    /**
     * Get the Variable to Exponent TreeMap associated with this Monomial
     * 
     * @return a TreeMap mapping this Monomial's Variables to their respective exponents
     */
    public Map<String, Integer> getMap(){
        Map<String, Integer> copyOfMap = new TreeMap<>(exps);
        return copyOfMap;
    }
    
    /** Multiplies this monomial by a specified monomial
     * @param m monomial to multiply by
     * @return the product of this monomial with m
     */
    public Monomial multiply(Monomial m){
        TreeMap<String, Integer> vMap = new TreeMap<>(getMap());
        m.getMap().forEach((key, value) -> vMap.merge(key, value, (a,b) -> a+b));
        return new Monomial(coeff*m.getCoefficient(), vMap);
    }
    
    @Override
    public Expression differentiate(Variable v) {
        if (!exps.containsKey(v.toString())){
            return new Number(0);
        }
        TreeMap<String, Integer> vMap = new TreeMap<>(exps);
        vMap.put(v.toString(), exps.get(v.toString())-1);
        return new Monomial(coeff*(exps.get(v.toString())), vMap);
    }
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Monomial)){return false;}
        Monomial otherMonomial = (Monomial) other;
        return (getCoefficient() == otherMonomial.getCoefficient()) && (getMap().equals(otherMonomial.getMap()));
    }
    
    @Override
    public int hashCode(){
        throw new RuntimeException();
    }
    
    /**
     * Outputs the string representation of this monomial
     * 
     * @return String representation of this monomial in the form
     *         "Coefficient*PRODUCT" where Coefficient is the coefficient of the
     *         monomial and PRODUCT is the product of the variables in the
     *         expression in lexicographical order separated by * where each
     *         variable vi appears ei number of times where ei is its exponent
     *         in the monomial, "Coefficient*" is neglected if the coefficient is
     *         1 or 1.0 and "*PRODUCT" is neglected if there are no variables
     */
    @Override
    public String toString(){
        String out = String.valueOf(coeff);
        for (String v:exps.keySet()){
            for (int i =0; i<exps.get(v); i++)
                out += "*"+v;
        }
        if (coeff == 1.0 && !exps.isEmpty()){
            out=out.substring(4, out.length());
        }
        return out;
    }

}
