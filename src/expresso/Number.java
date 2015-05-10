package expresso;

import java.util.Collections;
import java.util.TreeMap;

public class Number extends Monomial {
    
    private final String numString;
    private final boolean isInteger;

    /**
     * Creates a number expression representing the specified integer
     * @param n integer to represent
     */
    public Number(int n) {
        super(n);
        this.numString = Integer.toString(n);
        isInteger = true;
        exps = Collections.unmodifiableMap(exps);
    }
    
    /**
     * Creates a number expression representing the specified decimal
     * @param d decimal to represent
     */
    public Number(double d) {
        super(d);
        this.numString = Double.toString(d);
        isInteger = false;
        exps = Collections.unmodifiableMap(exps);
    }
    
    @Override
    public Expression add(Expression e) {
        if (this.isInteger){
            Number copyOfNumber = new Number(Integer.parseInt(this.numString));
            return new Sum(copyOfNumber, e);
        }
        return new Sum(new Number(Double.parseDouble(this.numString)), e);
    }

    @Override
    public Expression multiply(Expression e) {
        if (this.isInteger){
            Number copyOfNumber = new Number(Integer.parseInt(this.numString));
            return new Product(copyOfNumber, e);
        }
        return new Product(new Number(Double.parseDouble(this.numString)), e);
    }
    
    public Monomial multiply(Monomial m){
        TreeMap<String, Integer> vMap = new TreeMap<>(m.getMap());
        return new Monomial(Double.parseDouble(numString)*m.getCoefficient(), vMap);
    }

    @Override
    public Expression differentiate(Variable v) {
        return new Number(0);
    }

    @Override
    public SimpleExpression simplify() {
        return this;
    }
    
    /**
     * Returns true if this Number object is representing an Integer, false otherwise
     * 
     * @return A boolean stating whether this Number is an Integer
     */
    public boolean isInteger() {
        return this.isInteger;
    }
    
    @Override
    public String toString() {
        return this.numString;
    }
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Number)){return false;}
        Number otherNumber = (Number) other;
        return Double.parseDouble(this.numString) == Double.parseDouble(otherNumber.toString());
    }
    
    @Override
    public int hashCode() {
        if (this.isInteger){return Integer.parseInt(this.numString);}
        return (int) Double.parseDouble(this.numString);
    }
}