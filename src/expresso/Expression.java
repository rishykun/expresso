package expresso;

/**
 * Represents an algebraic expression consisting of zero or more
 * nonnegative numbers and variables.
 */
public interface Expression {
    
    // Datatype definition
    //  Expression = Empty + Number(n: Integer) + Number(d: Double) + Variable(v: String) 
    //                  +  Sum(e1: Expression, e2: Expression) + Product(e1: Expression, e2: Expression)

    /**
     * Parse an expression.
     * @param input expression to parse
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        throw new RuntimeException("unimplemented");
    }
    
    /**
     * Creates an empty expression
     * @return an empty expression
     */
    public static Expression Empty(){
        throw new RuntimeException("unimplemented");
    }
    
    /**
     * Adds specified expression to this expression
     * @param e expression to add
     * @return an expression that represents the sum of e with this expression
     */
    public Expression add(Expression e);
    
    /**
     * Multiplies specified expression to this expression
     * @param e expression to multiply
     * @return an expression that represents the product of e with this expression
     */    
    public Expression multiply(Expression e);
    
    /**
     * Differentiates this expression with respect to a specified variable
     * @param v Variable to differentiate with respect to
     * @return an expression that represents the derivative of this expression with respect to v
     */
    public Expression differentiate(Variable v);
      
    /**
     * Simplifies this expression a sum of terms such that for all variables 
     * vari with exponents ei, the term (var1^e1 * var2^e2 * ... * varn^en) appears at most once.
     * Each term may be multiplied by a non-zero, non-identity constant factor.
     * As the simplified expression is read left-to-right, the largest exponent in each term must be non-increasing.
     * @return an expression that represents the simplified version of this expression 
     */
    public Expression simplify();
       
}
