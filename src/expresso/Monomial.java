package expresso;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a monomial Expression
 */
public class Monomial extends SimpleExpression {
    
    protected double coeff;
    protected Map<String, Integer> exps = new TreeMap<>();
    
    //Abstraction function
    //Rep Invariant
    //Rep Exposure
    
    public Monomial(int n, TreeMap<String, Integer> components){
        coeff = n;
        exps = Collections.unmodifiableMap(components);
        isMonomial = true;
    }
    
    public Monomial(double d, TreeMap<String, Integer> components){
        coeff = d;
        exps = Collections.unmodifiableMap(components);
        isMonomial = true;
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
    public Expression add(Expression e) {
        return new Sum(this, e);
    }

    @Override
    public Expression multiply(Expression e) {
        return new Product(this, e);
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
    public Monomial leading(){
        return this;
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
    public SimpleExpression simplify() {
        return this;
    }
    
    @Override
    public Iterator<Monomial> iterator(){
        return Arrays.asList(this).iterator();
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
        if (coeff == 1.0){
            out=out.substring(4, out.length());
        }
        return out;
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

}
