package expresso;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class SumTest extends TestSetup {
    
    /**
     * add
     *  [x]Any expression
     *  
     * multiply
     *  [x]Any expression
     *
     * differentiate
     *  Let v be the variable differentiated with respect to,
     *  partition on whether the Sum involves a
     *      [x]Number
     *      [x]Expression in v
     *      [x]Expression in some other variable
     *      [x]Expression in v and some other variable
     *  
     * simplify
     *  Basic sums
     *      [x]Number + Number
     *      [x]Non-number + 0
     *      Two monomials that can't combine
     *          [x]Non-variable monomial + variable (coefficient 1)
     *          [x]Non-Number Monomial + Number
     *          Non-Number Monomial+Non-Number Monomial
     *              [x]first one has higher exponent
     *              [x]second one has higher exponent
     *              [x]max exponents the same
     *              [x]one variable exponent to compare 
     *              [x]two variable exponents to compare
     *      Two monomials that can combine
     *          [x]Variable + Monomial with coeff != 1
     *          [x]Monomials with identical terms but arranged differently
     *  Non-trivial sums
     *      SimpleExpression + SimpleExpression
     *          [x]length of 1st < 2nd, all terms of 1st go into 2nd
     *          [x]length of 2nd < 1st, all terms of 2nd go into 1st
     *          [x]length of 2nd = 1st, all terms combine 
     *          [x]the two interleave somewhere when summed
     *          [x]All terms have same highest exponent but arranged differently
     *          in each sum
     *      Non-SimpleExpression + Expression
     *          [x]Max exps of terms in arbitrary order + 
     *          max exps of terms in non-decreasing order
     *          [x]Product + Sum which can combine
     *          [x]Product + Product which can combine

     *          
     * toString
     *  [x]Any Sum
     *  
     * equals
     *  [x]Sums are identical in operations, terms, ordering, and parenthesis
     *  Sums are identical but one of the sums has extra set
     *  of parenthesis around
     *      [x]first expression of the sum
     *      [x]second expression of the sum
     *      [x]the entire sum
     *  Sums are not identical in 
     *      [x]Terms or operations
     *      [x]Term ordering
     *      [x]Term grouping
     *      
     * hashCode
     *  [x]Sums are identical in operations, terms, ordering, and parenthesis
     *  Sums are identical but one of the sums has extra set
     *  of parenthesis around
     *      [x]first expression of the sum
     *      [x]second expression of the sum
     *      [x]the entire sum
     */
    
    @BeforeClass
    public static void setUpBeforeClass(){
        TestSetup.setup();
    }
    
    /**
     * Tests summations while building up the expression (x+y)*xy
     */
    @Test
    public void operationTest() {
        assertEquals(xplusy, x.add(y));
        assertEquals(xplusyplusxy, xplusy.add(xy));
        assertEquals(xplusytimesxy, xplusy.multiply(xy));
    }
    
    /**
     * Tests derivatives of x+1 and x*y+y with respect to x
     */
    @Test
    public void derivativeTest() {
        assertEquals(one, xplusone.differentiate(x).simplify());
        assertEquals(y, xy.add(y).differentiate(x).simplify());
    }
    
    /**
     * Tests simplifying the sum of two numbers
     */
    @Test
    public void numberSumSimplifyTest() {
        assertEquals(two, oneplusone.simplify());
        assertEquals(Expression.parse("2.1"), one.add(onepointone).simplify());
        assertEquals(one, one.add(zero).simplify());
    }
    
    /**
     * Tests simplifying additions with zero
     */
    @Test
    public void zeroAddSimplifyTest(){
        assertEquals(x, x.add(zero).simplify());
        assertEquals(xy.simplify(), xy.add(zero).simplify());
        assertEquals(xyplusxplusy.simplify(), xyplusxplusy.add(zero).simplify());
    }
    
    /**
     * Tests simplifying x+1 and (x*x)+x
     */
    @Test
    public void variableAddNoSimplifyTest(){
        assertEquals(new SimpleExpression(x, one), xplusone.simplify());
        assertEquals(new SimpleExpression(x.multiply(x), x), Expression.parse("(x*x)+x").simplify());
    }
    
    /**
     * Tests simplifying 1+x, x+x*x, x+y*y, x+y
     */
    @Test
    public void simplifyOrderingTest(){
        assertEquals(xplusone.simplify(), Expression.parse("1+x").simplify());
        assertEquals(new SimpleExpression(x.multiply(x), x).simplify(), x.add(x.multiply(x)).simplify());
        assertEquals(new SimpleExpression(y.multiply(y), x), x.add(y.multiply(y)).simplify());
        assertTrue(new SimpleExpression(x, y).equals(xplusy.simplify()) || 
                new SimpleExpression(y,x).equals(xplusy.simplify()));
    }
    
    /**
     * Tests simplifying x+1.1x and x*y+1*y*x
     */
    @Test
    public void combineMonomialTest() {
        assertEquals(new Number(2.1).multiply(x),x.add(onepointone.multiply(x)).simplify());
        assertEquals(two.multiply(x).multiply(y), xy.add(one.multiply(new Product(y,x))).simplify());
    }
    
    /**
     * Tests simplifying sum of x*x+x with x in forward and reverse order
     */
    @Test
    public void subsetSimpleSumTest() {
        assertEquals(new SimpleExpression(xtimesx, two.multiply(x)), xtimesxplusx.add(x).simplify());
        assertEquals(new SimpleExpression(xtimesx, two.multiply(x)), x.add(xtimesxplusx).simplify());
    }
    
    /**
     * Tests simplifying sum of x*x+x+1 with x*x+1.1*x+2
     */
    @Test
    public void equalSimpleSumTest() {
        assertEquals(
                new SimpleExpression(two.multiply(xtimesx),
                        new SimpleExpression(new Number(2.1).multiply(x),
                                new Number(3))),
                new SimpleExpression(xtimesx, xplusone.simplify()).add(
                        new SimpleExpression(xtimesx, new SimpleExpression(
                                onepointone.multiply(x), two))).simplify());
    }
    
    /**
     * Tests simplifying sum of x*x+x with x+1
     */
    @Test
    public void interleaveSimpleSumTest() {
        assertEquals(new SimpleExpression(xtimesx, new SimpleExpression(two.multiply(x), one)), xtimesx.add(x).add(xplusone).simplify());
        assertEquals(new SimpleExpression(xtimesx, new SimpleExpression(two.multiply(x), one)), xplusone.add(xtimesx.add(x)).simplify());       
    }
    
    /**
     * Tests simplifying sum of x and y+x
     */
    @Test
    public void sameExponentDifferentOrderingTest() {
        SimpleExpression forwardAdd = x.add(Expression.parse("y+x").simplify()).simplify();
        SimpleExpression backwardAdd = Expression.parse("y+x").simplify().add(x).simplify();
        SimpleExpression expected1 = new SimpleExpression(two.multiply(x), y);
        SimpleExpression expected2 = new SimpleExpression(y, two.multiply(x));
        assertTrue(forwardAdd.equals(expected1) || forwardAdd.equals(expected2));
        assertTrue(backwardAdd.equals(expected1) || backwardAdd.equals(expected2));
    }
    
    /**
     * Tests simplifying sum of x+x*y+y*y with x*y+y*y+x
     */
    @Test
    public void differentOrderExponentTest() {
        assertEquals(new SimpleExpression(two.multiply(y).multiply(y), new SimpleExpression(two.multiply(x).multiply(y), two.multiply(x))), x.add(xy).add(y.multiply(y)).add(xy.add(y.multiply(y).add(x))).simplify());
    }
    
    /**
     * Tests simplifying sum of x*(x+1) with x and (x+1)*(x+1) with x*x
     */
    @Test
    public void sumAndProductSimplifyTest(){
        assertEquals(new SimpleExpression(xtimesx, two.multiply(x)), x.multiply(xplusone).add(x).simplify());
        assertEquals(new SimpleExpression(two.multiply(xtimesx), new SimpleExpression(two.multiply(x), one)), xplusone.multiply(xplusone).add(xtimesx).simplify());
    }
    
    /**
     * Tests toStrings of sums while building up the expression
     * (x+y)+x*y
     */
    @Test
    public void toStringTest() {
        assertEquals("(x) + (y)", xplusy.toString());
        assertEquals("((x) + (y)) " + "+" + " (" + xy.toString() + ")",  xplusyplusxy.toString());
    }
    
    /**
     * Tests equality between x+1 and x+1
     */
    @Test
    public void balancedParenContractTest() {
        assertEquals(xplusone, Expression.parse("x+1"));
        assertEquals(xplusone, new Sum(x, one));
        assertEquals(xplusone.hashCode(), new Sum(x, one).hashCode());
    }
    
    /**
     * Tests equality between x*y+1, (x*y)+1, x*y+(1), and (x*y+1)
     */
    @Test
    public void extraParenContractTest(){
        Expression xyplusone = Expression.parse("x*y+1");
        assertEquals(new Product(x,y).add(one) ,xyplusone);
        assertEquals(new Product(x,y).add(one).hashCode(), xyplusone.hashCode());
        assertEquals(xyplusone, Expression.parse("(x*y)+1"));
        assertEquals(xyplusone.hashCode(), Expression.parse("(x*y)+1").hashCode());
        assertEquals(xyplusone, Expression.parse("x*y+(1)"));
        assertEquals(xyplusone.hashCode(), Expression.parse("x*y+(1)").hashCode());
        assertEquals(xyplusone, Expression.parse("(x*y+1)"));
        assertEquals(xyplusone.hashCode(), Expression.parse("(x*y+1)").hashCode());
    }
    
    /*
     * Tests inequality between x+1, x+y, and x*1
     */
    @Test
    public void unequalTermsTest(){
        assertFalse(xplusone.equals(xplusy));
        assertFalse(xplusone.equals(new Product(x,one)));
        assertFalse(xplusy.equals(new Product(x,one)));
    }
    
    /*
     * Tests inequality after adding 0
     */
    @Test
    public void zeroAddUnequalTest(){
        assertFalse(x.equals(Expression.parse("x+0")));
    }
    
    /*
     * Tests inequality after multilpying by 1
     */
    @Test
    public void multiplyOneUnequalTest(){
        assertFalse(x.equals(Expression.parse("1*x")));
    }
    
    /**
     * Tests inequality between x+1 and 1+x
     */
    @Test
    public void unequalOrderingTest(){
        assertFalse(xplusone.equals(Expression.parse("1+x")));
    }
    
    /*
     * Tests inequality between (x+x)+y and x+(x+y)
     */
    @Test
    public void unequalGroupingTest(){
        assertFalse(Expression.parse("(x+x)+y").equals(Expression.parse("x+(x+y)"))); 
    }
}
