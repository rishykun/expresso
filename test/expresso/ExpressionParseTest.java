package expresso;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionParseTest extends TestSetup {

    /*
     * parse
     *  number of variables: []0, []1, []>1
     *  length of variable: []1, []>1
     *  exists []integers, []floating point numbers
     *  operations: []add, []multiply
     *  number of enclosing parenthesis: []0, []1, []>1
     *  parenthesis grouping: []yes, []no
     *  spacing: []none, []some
     *  [] space between variables e.g. f o o
     *  [] no operation between expressions e.g. (x+1)(x+2)
     *  
     *  
     *  
     *  */
    
    @BeforeClass
    public static void setUpBeforeClass(){
        TestSetup.setup();
    }
    
    @Test
    public void noVariablesTest() {
        assertEquals(one, Expression.parse("1"));
        assertEquals(two, Expression.parse("2"));
           
    }
    
    @Test
    public void oneVariableTest() {
        assertEquals(x, Expression.parse("x"));
        assertEquals(y, Expression.parse("y"));
        assertEquals(twox, Expression.parse("2*x"));
    }
    
    @Test
    public void multiVariableTest() {
        assertEquals(xy, Expression.parse("x*y"));
        assertFalse(xy.equals(Expression.parse("y*x")));
        assertEquals(xplusy, Expression.parse("x+y"));
        assertFalse(xplusy.equals(Expression.parse("y+x")));
        
    }
    
    @Test
    public void longVariableNameTest() {
        assertEquals(alongname, Expression.parse("alongname"));
    }
    
    @Test
    public void floatingPoints() {
        assertEquals(two, Expression.parse("02.0"));
        assertEquals(onepointone, Expression.parse("001.100"));
    }
    
    @Test
    public void addOperation () {
        assertEquals(oneplusone, Expression.parse("1+1"));
        assertEquals(xplusy, Expression.parse("x+y"));
        assertEquals(xplusypluszplusone, Expression.parse("x+y+z+1"));
        
    }
    
    @Test
    public void multiplyOperation() {
        assertEquals(twox, Expression.parse("2*x"));
        assertEquals(xy, Expression.parse("x*y"));
        assertEquals(xtimesx, Expression.parse("x*x").simplify());
    }
    
    @Test
    public void singleEnclosingParenthesis() {
        assertEquals(twox, Expression.parse("(2*x)"));
        assertEquals(twox, Expression.parse("(2)*x"));
        assertEquals(xplusone, Expression.parse("(x+1)"));
        assertEquals(alongname, Expression.parse("(alongname)"));
    }
    
    @Test
    public void multiEnclosingParenthesis() {
        assertEquals(xplusypluszplusone, Expression.parse("(((x+y+z+1)))"));
        assertEquals(xy, Expression.parse("(((x))) * (((y)))"));
        assertEquals(xtimesx, Expression.parse("((((x)))*(((x))))").simplify());
        assertEquals(twotimesxtimesxtimesy, Expression.parse("(2*x)*(x*y)"));
    }
    
    @Test
    public void parenthesisGroupingMultiOperations() {
        assertEquals(nestedproductandsumtwoxy, Expression.parse("(2+(x*y))*((2+y)*x)"));
        assertEquals(twoplusxtimesxplusy, Expression.parse("(((2+x)))*(((x+y)))"));
    }
    
    @Test
    public void someSpacing(){
        assertEquals(x, Expression.parse("     x   "));
        assertEquals(one, Expression.parse("    1   "));
        assertEquals(twox, Expression.parse("2   *   x"));
        assertEquals(xplusy, Expression.parse("x    +     y  "));
        assertEquals(twotimesxtimesxtimesy, Expression.parse("(2 * ( ( x ) )) *  (( x )*   ( y ))  "));
        assertEquals(nestedproductandsumtwoxy, Expression.parse("( 2 + ( x * y ) ) * ( ( 2 + y ) *  x)"));
        
    }
    
    @Test
    public void failNoSpaceBetweenVariables(){
        try{
            Expression.parse("x y");
            assertFalse(true);
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }
        
        
        try{
            Expression.parse("x x");
            assertFalse(true);
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }
        
        
        try{
            Expression.parse("f o o + 2");
            assertFalse(true);
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }
        
    }
    
    
    @Test
    public void failNoOperationBetweenExpression() {
        try{
            Expression.parse("(x+1)y");
            assertFalse(true);
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }
        
        
        try{
            Expression.parse("(x+3)(x+4) + 5");
            assertFalse(true);
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }
        
        
        try{
            Expression.parse("22 * 22(x)");
            assertFalse(true);
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }
        
        
    }
    
}
