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
     *         variables, and operations of the input, appearing  in the same order.
     * @throws IllegalArgumentException if the expression is invalid
     *         Invalid inputs are defined as having at least one of the following characteristics:
     *           1) Unbalanced parentheses
     *           2) Letters and numbers that appear immediately after one another with no separation
     *              by operations (x9u, 3.00f2, 5 y, 3x, etc.)
     *           3) Numbers with more than one decimal, letters that are in the immediate vicinity of a
     *              decimal (i.e. would appear right next to one if spaces are removed), or variable names
     *              that include space characters
     *           4) Invalid operations (-, /, ^, or any character that is not a letter, number, decimal,
     *              +, *, space, or parenthesis.)
     *           5) One or more operative symbols that do not have a left and right subexpression
     *              to perform their respective operations on such that both subexpressions would be
     *              valid stand-alone inputs (t + (), a + , etc.) In this case, valid subexpressions
     *              do not include empty Strings even though an empty input is a valid
     *              query (ending the program), so inputs like "6 +" are also considered invalid.
     */
    public static Expression parse(String input) {
        try{
            CharStream stream = new ANTLRInputStream(input);
            ExpressionLexer lexer = new ExpressionLexer(stream);
            TokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(tokens);
            parser.reportErrorsAsExceptions();
            ParseTree tree = parser.line();
            ParseTreeWalker walker = new ParseTreeWalker();
            OurExpressionListener listener = new OurExpressionListener();
            walker.walk(listener, tree);
            return listener.getExpression();
        } catch(RuntimeException e){
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
