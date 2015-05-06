package expresso.parser.Expression;

import static org.junit.Assert.assertTrue;



import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;


/*
 * TESTING STRATEGY
 * 
 * (the following partitions are interdependent rather than discrete)
 * 
 * 
 * Balanced Parenthesis (valid i.e. balanced and invalid i.e. unbalanced)
 * -no parenthesis (valid and invalid)
 * -single parenthesis (valid and invalid)
 * -multiple parenthesis (valid and invalid)
 * -valid Expression, multiple parenthesis
 * 
 * 
 * 
 * 
 * Variables and Numbers
 * -valid and invalid integer Expression
 * -valid and invalid double Expression
 * -valid and invalid single variables
 * -valid and invalid multiple variables
 * -valid and invalid Combination
 * 
 * 
 * 
 * 
 * Operations
 * -valid and invalid Expression with no operations
 * -valid and invalid Expression with *
 * -valid and invalid Expression with +
 * -valid and invalid multiple operations
 * 
 * 
 * Whitespace
 * -valid and invalid whitespace Expression
 */


public class ExpressionParserTest {
    
    /**
     * Helper test to determine if an input (Expression) to the parser
     * is valid or not. Test fails whenever a parser raises an exception
     * for valid inputs or fails to raise one for invalid inputs.
     * 
     * @param isValid boolean indicating whether or not testInput is valid
     * @param testInput a String for the parser to denote as valid or invalid
     */
    public void helperTest(boolean isValid, String testInput) {
        CharStream stream = new ANTLRInputStream(testInput);
        ExpressionLexer lexer = new ExpressionLexer(stream);
        lexer.reportErrorsAsExceptions();
        try {
            TokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(tokens);
            parser.reportErrorsAsExceptions();
            ParseTree tree = parser.line();
            assertTrue(isValid);
        }catch (Exception e){
            assertTrue(!isValid);
        }
    }
    
    
    @Test
    public void testNoOperations() {
        
        //valid integers
        helperTest(true, "56");
        helperTest(true, "(7000)");
        
        //invalid integers
        helperTest(false, "56.");
        
        //valid double
        helperTest(true, "((5.6))");
        helperTest(true, "((((5.678000))))");
        
        //invalid double
        helperTest(false, "5.6.6");
        helperTest(false, "5.six");
        
        //valid variable
        helperTest(true, "x");
        helperTest(true, "((foo))");
        
        
        //invalid variable
        helperTest(false, "x###");
        helperTest(false, "(x.)");
        
    }
    
    @Test
    public void testSingleOperations() {
        
        //valid *
        helperTest(true, "5*7");
        helperTest(true, "5 *    7");
        helperTest(true, "foo * ((drink))");
        helperTest(true, "foo * drink * ((me)) * 5");
        helperTest(true, "((5*99))");
        
        //invalid *
        helperTest(false, "5*((7)");
        helperTest(false, "5*");
        helperTest(false, "((5*99)");
        helperTest(false, "foo * drink * ");
        
        //valid +
        helperTest(true, "5+7");
        helperTest(true, "(((((55)))) + ((7)) + 3)");
        helperTest(true, "a + (b) + ((c)) + (((d)))");
        
        //invalid +
        helperTest(false, "5+");
        helperTest(false, "(5+7))");
        helperTest(false, "a ++ b");
        helperTest(false, "a + b + c +");
        
        //invalid
        helperTest(false, "5-7");
        helperTest(false, "6/30");
        helperTest(false, "a/b");
    }
    
    
    @Test
    public void testMultiOperations() {
        
        //valid
        helperTest(true, "1+2*3");
        helperTest(true, "(1+2)*((3))");
        helperTest(true, "1  +   2   * (3)");
        helperTest(true, "foo*drunk+alpha+beta*55");
        helperTest(true, "(a) * ((b)) + (((c*d+3))) + (e*f) + 123.555");
        helperTest(true, "a*b*c*d*1*2*3*4+5+6+7+8");
        
        
        //invalid
        helperTest(false, "((1+2*))");
        helperTest(false, "((1++2*3))");
        helperTest(false, "(1+2*3+ 5 foo oo oo)");
        
    }
    
    @Test
    public void testEdgeCasesParenthesis() {
        
        //valid
        helperTest(true, "x*y*z");
        helperTest(true, "(x*y*z)");
        helperTest(true, "((x*y*z))");
        helperTest(true, "((x*y*z))+(((((54))))) + 3*x*y + 455"); 
        
        //invalid
        helperTest(false, "( )");
        helperTest(false, "x*y*z)");
        helperTest(false, "((x*y*z)");
        helperTest(false, "((x*y*z)) + (((((54)))) - 3*x*y + (455)");
    }
    
    
    @Test
    public void testEdgeCasesVariablesAndNumbers() {
        
        //valid
        helperTest(true, "123456789012345");
        helperTest(true, "123456789.012345");
        helperTest(true, "foo * 123 * foo + 456");
        helperTest(true, "foo * food * foods");
        
        //invalid
        helperTest(false, "123456789s");
        helperTest(false, "12345..6789");
        helperTest(false, "foo * s * $");
        
    }
    
    @Test
    public void testEdgeCasesWhiteSpace() {
        
        //valid
        helperTest(true, "1+2+3");
        helperTest(true, "good * bad * unconditional + 5");
        helperTest(true, "a               *                   b               +           ((((  77))))");
        
        //invalid
        helperTest(false, "1        *    ");
        helperTest(false, "1*2*3          (5)  ");
        
    }
    
    @Test
    public void testVeryComplicatedExpression() {
        
        //valid
        helperTest(true, "a*b*c+d+e+f*g*h*i + ((((j)))) + ((k))*(((m))) + 5555.23 + dreams");
        helperTest(true, "(x+y)*(x+y) + 2*x*x*x*x*x*x + 3*y*y*y + (zzz) +     (z * z + z)");
        helperTest(true, "Expressions * can * even *be* actual*expressions + That* is+ truly  ");
        helperTest(true, "((Scary * Parenthesis + 6.005 + (((((((((((5))))))))))) * ((3))*(((4)))*(((((5)))))))");
        
        //invalid
        helperTest(false, "(((((2+3+4+5*6))))))) + 1000 * 99 * f00 * 6.005");
        helperTest(false, "But expressions cannot be arbitrary strings * Which is Saddening   6.005 is awesome");
        
        
    }
    

}