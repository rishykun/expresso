package expresso;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Series of tests for the Expression datatype
 */
public class ExpressionTest {

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
     * add
     *  Argument: [x]Number, [x]Product, [x]Sum, [x]Variable 
     *  
     * multiply
     *  Argument: [x]Number, [x]Product, [x]Sum, [x]Variable
     * 
     * differentiate
     *  Let v be the variable differentiated with respect to, we'll
     *  break up the partition depending on who's calling
     *  Number
     *      []Any number
     *  Variable
     *      [] v is the same Variable, [] v is not the same Variable
     *  Sum
     *      []Number+Number
     *      []Number+Expression in v
     *      []Expression in v + Expression in v
     *      []Expression in v + Expression with some other variable
     *      Recurse on []Variable, []Sum, Product
     *  Product
     *      []Number*Number
     *      []Number*Expression in v
     *      []Expression in v * Expression in v
     *      []Expression in v * Expression with some other variable
     *      Recurse on []Variable or Number, []Sum, Product
     *  
     * simplify
     *  Type of expression: []Number, []Product, []Sum, []Variable
     *  Expression is already simplified in []correct order, []incorrect order
     *  Ways to simplify: [] integer+float, [] integer*float, sum of single-term 
     *  expressions with same exponentials in its variables where the ordering of the 
     *  variables is the []same, []different e.g. x*y+y*x
     *  []Operation between two unsimplified expressions
     *  []Combining like terms needed after performing an operation between two expressions 
     *  Expression before simplification has []1*expression, []0*expression, []0+expression
     *  
     * equals
     *  
     * toString
     *  Type of expression: []Number, []Product, []Sum, []Variable
     * 
     * hashCode
     * 
     */
    
    private static Number one;
    private static Number zero;
    private static Number two;
    private static Variable x;
    private static Variable y;
    private static Product xy;
    private static Sum xplusy;
    private static Expression xplusytimesxy;
    private static Expression xytimesxplusy;
    private static Expression xplusyplusxy;
    private static Expression xyplusxplusy;
    private static Expression dxy;
    private static Expression dxplusy;
    private static Expression oneplusone ;
    private static Expression onetimesone;
    
    
    @BeforeClass
    public static void setUpBeforeClass(){
        one = new Number(1);
        zero = new Number(0);
        two = new Number(2);
        x = new Variable("x");
        y = new Variable("y");
        xy = new Product(x,y);
        xplusy = new Sum(x,y);
        xplusytimesxy = new Product(xplusy, xy); 
        xytimesxplusy = new Product(xy, xplusy);
        xplusyplusxy = new Sum(xplusy, xy); 
        xyplusxplusy = new Sum(xy, xplusy);
        dxy = xy.differentiate(x);
        dxplusy = xplusy.differentiate(x);
        
        oneplusone = new Sum(one, one);
        onetimesone = new Product(one, one);
    }
    
    /**
     * Tests the result of add and multiply operations on each 
     * combination of expression type
     */
    @Test
    public void operationTest(){      
        assertEquals(xplusy, x.add(y));
        assertEquals(xy, x.multiply(y));
        assertEquals(oneplusone, one.add(one));
        assertEquals(onetimesone, one.multiply(one));
        assertEquals(xplusytimesxy, xplusy.multiply(xy));
        assertEquals(xytimesxplusy, xy.multiply(xplusy));
        assertEquals(xplusyplusxy, xplusy.add(xy));
        assertEquals(xyplusxplusy, xy.add(xplusy));
    }
    
    /**
     * Tests differentiating a number
     */
    @Test
    public void numberDerivativeTest(){
        assertEquals(zero, two.differentiate(x));
    }
    
    /**
     * Tests differentiating a Variable with respect to itself
     * and a different variable
     * -dx/dx, covers same variable
     * -dy/dx, covers different variable
     */
    @Test
    public void variableDerivativeTest(){
        assertEquals(one, x.differentiate(x));
        assertEquals(zero, y.differentiate(x));
    }
    
    /**
     * Tests derivatives wrt x of expressions of the form
     *  -1*1, covers number*number, recursion on number
     *  -1*x, covers number*expr in x, recursion on variable
     *  -y*(x*x), covers expr in x * expr in x and expr in not x * expr in x
     *  -xy*(x+y), covers recursion on sum and product
     */
    @Test
    public void productDerivativeTest(){
        assertEquals(new Sum(new Product(one, zero), new Product(one, zero)), onetimesone.differentiate(x));
        assertEquals(new Sum(new Product(one, one), new Product(x, zero)) ,(new Product(one, x)).differentiate(x));
        assertEquals(new Sum(new Product(y, new Sum(new Product(x, one),
                new Product(x, one))), new Product(new Product(x, x), zero)),
                (new Product(y, new Product(x, x))).differentiate(x));
        assertEquals(new Sum(new Product(x, zero), new Product(y, one)), dxy);
        assertEquals(new Sum(new Product(xy,dxplusy), new Product(xplusy,dxy)), xytimesxplusy.differentiate(x));
    }
    
    /**
     * Tests derivatives wrt x of expressions of the form
     *  -1+1, covers number+number, recursion on number
     *  -1+x, covers number+expr in x, recursion on variable
     *  -y+(x+x), covers expr in x + expr in x and expr in not x + expr in x
     *  -xy+(x+y), covers recursion on sum and product
     */
    @Test
    public void sumDerivativeTest(){
        assertEquals(zero.add(zero), oneplusone.differentiate(x));
        assertEquals(zero.add(one) ,one.add(x).differentiate(x));
        assertEquals(zero.add(one.add(one)), y.add(x.add(x)).differentiate(x));
        assertEquals(one.add(zero), dxplusy);
        assertEquals(dxy.add(dxplusy), xyplusxplusy.differentiate(x));
    }
}
