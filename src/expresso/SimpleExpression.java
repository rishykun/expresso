package expresso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a simplified expression
 */
public class SimpleExpression implements Expression, Iterable<Monomial>  {

    protected Monomial leading;
    private SimpleExpression remaining;
    protected boolean isMonomial = false;
    
    //Abstraction function
    //Rep Invariant
    //Rep Exposure
    
    public SimpleExpression(Monomial m, SimpleExpression e){
        leading = m;
        remaining = e;
    }
    
    public SimpleExpression(){}
    
    @Override
    public Expression add(Expression e) {
        return new Sum(this, e);
    }
    
    /**
     * Adds a monomial to the front of a simple expression
     * @param m m is not equal to the first term of this simple expression 
     *          and max exponent of m must be >= max exponent of the first term as well
     * @return this simple expression with the monomial as the leading term
     */
    public SimpleExpression simpleAdd(Monomial m){
        return new SimpleExpression(m, this);
    }

    @Override
    public Expression multiply(Expression e) {
        return new Product(this, e);
    }

    @Override
    public Expression differentiate(Variable v) {
        return leading.add(remaining).differentiate(v);
    }

    @Override
    public SimpleExpression simplify() {
        return this;
    }
    
    /**
     * Checks if a simplified expression is a monomial or not
     * @return if this simplified expression is a monomial
     */
    public boolean checkMonomial() {
        return isMonomial;
    }
    
    /**
     * Returns an iterator over the monomials of a simple expression
     * @return Iterator over monomials of this expression in non-decreasing
     *          order of max exponent
     */
    @Override
    public Iterator<Monomial> iterator(){
        List<Monomial> listOfTerms = new ArrayList<Monomial>();
        SimpleExpression currentExpression = this;
        
        while (!currentExpression.checkMonomial()){
            Monomial currentLeading = currentExpression.leading();
            listOfTerms.add(0, currentLeading);
            currentExpression = currentExpression.truncate();
        }
        Monomial lastMonomial = currentExpression.leading();
        listOfTerms.add(0, lastMonomial);
        return listOfTerms.iterator();
        
    }
    
    /**
     * Gets the leading monomial term of a simplified expression
     * @return leading monomial term of this simplified expression
     */
    public Monomial leading(){
        return leading;
    }
    
    /**
     * Gets the remaining terms after the leading monomial term of a
     * simplified expression
     * @return the remaining terms after the leading monomial term of this
     *          simplified expression, 0 if there are no remaining terms
     */
    public SimpleExpression truncate(){
        return remaining;
    }
    
    @Override
    public String toString(){
        String out = leading.toString() + "+" + remaining.toString();
        if (out.endsWith("+0")){
            out = out.substring(0, out.length()-2);
        }
        return out;
    }
    
    @Override
    public boolean equals(Object other){
        if (!(other instanceof SimpleExpression)){return false;}
        SimpleExpression otherSimple = (SimpleExpression) other;
        return leading.equals(otherSimple.leading) && remaining.equals(otherSimple.remaining);
    }
    
    @Override
    public int hashCode(){
        return leading.hashCode() + remaining.hashCode();
    }
}