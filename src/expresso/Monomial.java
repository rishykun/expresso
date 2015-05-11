package expresso;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Monomial extends SimpleExpression {
    
    private double coeff;
    protected Map<String, Integer> exps = new TreeMap<>();
    
    public Monomial(int n, TreeMap<String, Integer> components){
        super();
        coeff = n;
        exps = Collections.unmodifiableMap(components);
    }
    
    public Monomial(double d, TreeMap<String, Integer> components){
        coeff = d;
        exps = Collections.unmodifiableMap(components);
    }
    
    public Monomial(int n){
        coeff = n;
        exps = Collections.emptyMap();
    }
    
    public Monomial(double d){
        coeff = d;
        exps = Collections.emptyMap();
    }
    
    public Monomial(String v){
        coeff = 1;
        exps.put(v, 1);
        exps = Collections.unmodifiableMap(exps);
    }
    
    public double getCoefficient(){
        return coeff;
    }
    
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
        TreeMap<String, Integer> vMap = new TreeMap<>(exps);
        vMap.put(v.toString(), exps.get(v.toString())-1);
        return new Monomial(coeff*(exps.get(v.toString())), vMap);
    }

    @Override
    public SimpleExpression simplify() {
        return this;
    }
    
    @Override
    public boolean checkMonomial() {
        return true;
    }
    
    @Override
    public Iterator<Monomial> iterator(){
        return Arrays.asList(this).iterator();
    }
    
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
