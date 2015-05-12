package expresso;

import java.util.Collections;

/**
 *  Represents a number Expression
 */
public class Number extends Monomial {
    
    private final String numString;
    private final boolean isInteger;
    
    //Abstraction function
    //Rep Invariant
    //Rep Exposure
    
    /**
     * Creates a number expression representing the specified integer
     * @param n integer to represent
     */
    public Number(int n) {
        //super(n);
        coeff = n;
        this.numString = Integer.toString(n);
        isInteger = true;
        exps = Collections.unmodifiableMap(exps);
        isMonomial = true;
        leading = this;
    }
    
    /**
     * Creates a number expression representing the specified decimal
     * @param d decimal to represent
     */
    public Number(double d) {
        if (Math.round(d) - d == 0) {
            int n = Integer.parseInt(String.valueOf(d).substring(0, String.valueOf(d).indexOf(".")));
            coeff = n;
            numString = Integer.toString(n);
            isInteger = true;
        } else {
            coeff = d;
            this.numString = Double.toString(d);
            isInteger = false;
        }
        exps = Collections.unmodifiableMap(exps);
        isMonomial = true;
        leading = this;
    }
    
    @Override
    public Expression differentiate(Variable v) {
        return new Number(0);
    }
    
    /**
     * Returns true if this Number object is representing an Integer, false otherwise
     * 
     * @return A boolean stating whether this Number is an Integer
     */
    public boolean isInteger() {
        return this.isInteger;
    }
    
    /*
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Number)){return false;}
        Number otherNumber = (Number) other;
        return Double.parseDouble(this.numString) == Double.parseDouble(otherNumber.toString());
    }
    */
    
    @Override
    public int hashCode() {
        if (this.isInteger){return Integer.parseInt(this.numString);}
        return (int) Double.parseDouble(this.numString);
    }
    
    @Override
    public String toString() {
        return this.numString;
    }
}