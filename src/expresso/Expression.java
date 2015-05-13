package expresso;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import expresso.parser.Expression.OurExpressionListener;
import expresso.parser.Expression.ExpressionLexer;
import expresso.parser.Expression.ExpressionParser;

/**
 * Represents an algebraic expression consisting of one or more
 * nonnegative numbers or variables.
 */
public interface Expression {
    
    // Datatype definition
    //  Expression = Number(n: Integer) + Number(d: Double) + Variable(v: String) 
    //            +   Sum(e1: Expression, e2: Expression) + Product(e1: Expression, e2: Expression)
    //              +   Monomial(n: Integer, vMap: TreeMap<String,Integer>) + Monomial(d: Double, vMap: TreeMap<String,Integer>)
    //              +   SimpleExpression(m: Monomial, e: SimpleExpression)

    /**
     * Parse an expression.
     * @param input expression to parse
     * @return expression AST for the input that is mathematically equivalent to the desired Expression,
     *         whose toString() method returns a String that includes all numbers,
     *         letters, and operations of the input, appearing  in the same order.
     *         Parentheses in the output are distributed as follows:
     *         1) If the input is a single variable name or number, no parentheses are shown (i.e. 5 -> 5, (mf) -> mf)
     *         2) Else each number and variable in the expression is encapsulated in its own set of 
     *            parentheses. Then, for all the sets of grouped operations implied by the parentheses in the 
     *            input, parentheses are distributed around the two Monomials involved in that operation.
     *            Operations in Innermost levels are considered first.
     *            For operations that are in the same nested level, parentheses are distributed around pairs of numbers
     *            or letters being multiplied first, from left to right, then parentheses group sums from right to left
     *            up until the left-most plus sign in that level if the level of the sum takes the least precedence.
     *            If the right variable or number in an operation-paired set is already doubly encapsulated in parentheses
     *            as a result of putting parentheses around all numbers and variables (which occurs when parentheses
     *            are in the input), no further parentheses are distributed for the operation involved.
     *            
     *            Examples: The input x * y + x * y + x * y + x has multiple variables, so the output 
     *                      would so far have: (x) * (y) + (x) * (y) + (x) * (y) + (x)
     *                      After grouping multiplied terms left to right: ((x) * (y)) + ((x) * (y)) + ((x) * (y)) + (x)
     *                      Then sums from right to left: ((x) * (y)) + (((x) * (y)) + (((x) * (y)) + (x)))
     *                      And this is what would be printed.
     *                      
     *                      The input x + ((x * y) + x) + x * y * (x + y) would be transformed as follows:
     *                      After encapsulating all variables: (x) + (((x) * (y)) + (x)) + (x) * (y) * ((x) + (y))
     *                      Then the innermost operations are considered first. In this case, it would be the first occurrence of
     *                      ((x) * (y)). Because the y is already covered with two parentheses as a result of the input parentheses,
     *                      no additional parentheses are placed. Next, the addition operation immediately following is the first occurrence
     *                      of an operation on its level, and no multiplication operations are on its level, so parentheses would be distributed
     *                      around the numbers and variables that it operates on, but because the x to the right of the addition is covered with
     *                      2 parentheses, none are added. By similar reasoning, no parentheses are added for the other addition on its level
     *                      (the rightmost addition). After looking at the multiplication in the lower level, we get:
     *                      (x) + (((x) * (y)) + (x)) + ((x) * (y)) * ((x) + (y)) followed by (x) + (((x) * (y)) + (x)) + (((x) * (y)) * ((x) + (y))).
     *                      Next, since the sum in the same level with the multiplications is not on the most primitive level, we consider it, even
     *                      though it is the left-most sum on its level. Thus, we get (x) + ((((x) * (y)) + (x)) + (((x) * (y)) * ((x) + (y))))
     *                      The final sum operation is then ignored, and the output reveals the expression above.
     *            
     * @throws IllegalArgumentException if the expression is invalid
     *         Invalid inputs are defined as having at least one of the following characteristics:
     *           1) Unbalanced parentheses
     *           2) Letters and numbers that appear immediately after one another with no separation
     *              by operations (x9u, 3.00f2, 5 y, 3x, etc.)
     *           3) Numbers with more than one decimal, letters that are in the immediate vicinity of a
     *              decimal (i.e. would appear right next to one if spaces are removed), or variable names
     *              or numbers that include spaces or parentheses
     *           4) Invalid operations (-, /, ^, or any character that is not a letter, number, decimal,
     *              +, *, space, or parenthesis.)
     *           5) One or more operative symbols that do not have a left and right subexpression
     *              to perform their respective operations on such that both subexpressions would be
     *              valid stand-alone inputs (t + (), a + , etc.) In this case, valid subexpressions
     *              do not include empty Strings even though an empty input is a valid
     *              query (ending the program), so inputs like "6 +" are also considered invalid.
     */
    public static Expression parse(String input) throws IllegalArgumentException {
        try{
            CharStream stream = new ANTLRInputStream(input);
            ExpressionLexer lexer = new ExpressionLexer(stream);
            TokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(tokens);
            parser.reportErrorsAsExceptions();
            lexer.reportErrorsAsExceptions();
            ParseTree tree = parser.line();
            ParseTreeWalker walker = new ParseTreeWalker();
            OurExpressionListener listener = new OurExpressionListener();
            walker.walk(listener, tree);
            return listener.getExpression();
        } catch(RuntimeException e){
            System.out.println("caught");
            throw new IllegalArgumentException();
        }
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
     * Differentiates this expression with respect to a specified variable using the rules
     * dc/dx = 0, dx/dx = 1, d(u+v)/dx = du/dx +dv/dx, d(uv)/dx = u*dv/dx + v*du/dx
     * @param v Variable to differentiate with respect to
     * @return Expression that represents the derivative of this Expression with respect to v
     */
    public Expression differentiate(Variable v);
      
    /**
     * Simplifies this expression a sum of terms such that for all variables 
     * vari with exponents ei, the term (var1^e1 * var2^e2 * ... * varn^en) appears at most once.
     * Each non-constant term may be multiplied by a non-zero, non-identity constant factor.
     * As the simplified expression is read left-to-right, the largest exponent in each term must be non-increasing.
     * @return an expression that represents the simplified version of this expression 
     */
    public SimpleExpression simplify();
       
}
