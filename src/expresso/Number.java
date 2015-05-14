package expresso;

import java.util.Collections;

/**
 *  Represents a number Expression
 */
public class Number extends Monomial {
    
    private final String numString;
    private final boolean isInteger;
    
    //Abstraction function
    //  numString = number represented without extraneous zeros
    //Rep Invariant
    //  numString is a string of numbers not beginning with 0 unless
    //  the number is between 0 and 1 followed by an optional decimal point 
    //  which is followed by a string of numbers not ending in 0
    //Rep Exposure
    //  numString and isInteger are private, final, and immutable
    
    /**
     * Creates a number expression representing the specified integer
     * @param n integer to represent
     */
    public Number(int n) {
        coeff = n;
        this.numString = Integer.toString(n);
        isInteger = true;
        exps = Collections.unmodifiableMap(exps);
        isMonomial = true;
        leading = this;
        checkRep();
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
            this.numString = Double.toString(d).replaceAll("0*$", "");
            isInteger = false;
        }
        exps = Collections.unmodifiableMap(exps);
        isMonomial = true;
        leading = this;
        checkRep();
    }
    
    /**
     * Gets the derivative of a number with respect to a
     * specified variable
     * 
     * @param v variable to differentiate with respect to
     * @return derivative of this number
     */
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
    
    /**
     * Returns the string value of the number represented with no extraneous zeros 
     * (beginning or trailing). Integers are represented without the decimal point.
     */
    @Override
    public String toString() {
        return this.numString;
    }
    
    private void checkRep(){
        assert numString.matches("(([1-9][0-9]*)|0)(.[0-9]*[1-9])?") && isMonomial;
    }
}