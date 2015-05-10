package expresso;

/**
 * String-based interface to the expression system.
 */
public class Expressions {
    
    /**
     * Differentiate an expression with respect to a variable.
     * @param expression the expression to differentiate
     * @param variable the variable to differentiate by
     * @return expression's derivative with respect to variable; will be a valid expression
     * @throws IllegalArgumentException if the expression or variable is invalid
     */
    public static String differentiate(String expression, String variable) {
        Expression parsedInput = Expression.parse(expression);
        Expression diffInput = parsedInput.differentiate(new Variable(variable));
        return diffInput.toString();
    }
    
    /**
     * Simplify an expression.
     * @param expression the expression to simplify
     * @return an expression equal to the input that is a sum of terms without parentheses,
     *         where for all variables var_i in the expression, for all exponents e_i, the
     *         term (var_1^e_1 x var_2^e_2 x ... x var_n^e_n) appears at most once; each
     *         term may be multiplied by a non-zero, non-identity constant factor; and read
     *         left-to-right, the largest exponent in each term is non-increasing
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static String simplify(String expression) {
        Expression parsedInput = Expression.parse(expression);
        Expression simplified = parsedInput.simplify();
        return simplified.toString();
    }
    
}
