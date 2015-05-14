package expresso;


import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.BeforeClass;
import org.junit.Test;

public class MonomialTest extends TestSetup {

    private static final Object threepointtwoxtimesy = null;

    /** 
     * getCoefficient
     * coeff is 1
     * coeff is an integer > 1
     * coeff is a double
     *   
     * addCoeff
     * add 0
     * add 1
     * add val>1
     * 
     * 
     * truncate
     * should simply return 0
     * 
     * getMap
     * Map has 1 component
     * Map has >1 component
     * 
     * multiply
     * by singlevariable monomial
     * by multivariable monomial
     * 
     * differentiate
     * singlevariable monomial by that variable
     * singlevariable monomial by different variable
     * 
     * multivariable monomial by each of variables
     * multivariable monomial by different variables
     *  
     * simplify
     * singlevariable monomial
     * multivariable monomial
     *  
     * equals
     * same variables, different coefficients
     * same coefficients, different variables
     * same coefficients, same variables
     * different coefficients, different variables
     * 
     * hashCode
     * test equal singlevariable monomial 
     * test equal multivariable monomial
     *  
     * toString
     * toString singlevariable monomial
     * toString multivariable monomial
     * 
     *
     **/
    
    @BeforeClass
    public static void setUpBeforeClass(){
        TestSetup.setup();
    }
    
    @Test
    public void isUnitCoefficient() {
        assertEquals(1.0, xtimesx.getCoefficient(), 0);
        assertEquals(1.0, xtimesy.getCoefficient(), 0);
        
        
    }
    
    @Test
    public void isDoubleCoefficient() {
        assertEquals(3.2, threepointtwotimesxtimesy.getCoefficient(), 0);
    }
    
    @Test
    public void isIntegerCoefficient() {
        assertEquals(2.0, twotimesxtimesx.getCoefficient(), 0);
        
    }
    
    @Test
    public void addZeroOrOneCoeff() {
        assertEquals(xtimesx, xtimesx.addCoeff(zero));
        assertEquals(twotimesxtimesx, xtimesx.addCoeff(one));
        
    }
    
    @Test
    public void addLargerCoeff() {
        assertEquals(threepointtwotimesxtimesy, xtimesy.addCoeff(new Number(2.2)));
    }
    
    @Test
    public void truncateTest() {
        assertEquals(zero, xtimesx.truncate());
        assertEquals(zero, threepointtwotimesxtimesy.truncate());
    }
    
    @Test
    public void getMapTest() {
        assertEquals(mapxtimesx, xtimesx.getMap());
        assertEquals(mapxtimesy, xtimesy.getMap());
        assertEquals(mapthreepointtwotimesxtimesy, threepointtwotimesxtimesy.getMap());
        assertEquals(maptwotimesxtimesx, twotimesxtimesx.getMap());
    }
    
    @Test
    public void singleVariableMultiply() {
        TreeMap<String, Integer> expectedMap = new TreeMap<>();
        expectedMap.put("x", 4);
        Monomial expected = new Monomial(2, expectedMap);
        assertEquals(expected, xtimesx.multiply(twotimesxtimesx));
    }
    
    @Test
    public void multiVariableMultiply() {
        TreeMap<String, Integer> expectedMap = new TreeMap<>();
        expectedMap.put("x", 2);
        expectedMap.put("y", 2);
        Monomial expected = new Monomial(3.2, expectedMap);
        assertEquals(expected, xtimesy.multiply(threepointtwotimesxtimesy));
    }
    
    @Test
    public void singleVariableDifferentiate() {
        assertEquals(new Product(two,x).simplify(), xtimesx.differentiate(x));
        assertEquals(zero, xtimesx.differentiate(y));
    }
    
    @Test
    public void multiVariableDifferentiatePresentVariables() {
        assertEquals(y.toString(), xtimesy.differentiate(x).toString());
        assertEquals(x.toString(), xtimesy.differentiate(y).toString());
        
        String expected = new Product(y, new Number(3.2)).simplify().toString();
        assertEquals(expected, threepointtwotimesxtimesy.differentiate(x).toString());
    }
    
    @Test
    public void multiVariableDifferentiateAbsentVariables() {
        assertEquals(zero, xtimesy.differentiate(z));
        assertEquals(zero, threepointtwotimesxtimesy.differentiate(z));
        
    }
    
    @Test
    public void simplifySingleVariable() {
        assertEquals(xtimesx,  xtimesx.simplify());
        assertEquals(twotimesxtimesx, twotimesxtimesx.simplify());
    }
    
    @Test
    public void simplifyMultiVariable() {
        assertEquals(xtimesy, xtimesy.simplify());
        assertEquals(threepointtwotimesxtimesy, threepointtwotimesxtimesy.simplify());
    }
    
    @Test
    public void equalsSameVariableSameCoeff() {
        assertEquals(xtimesx.multiply(new Number(1.000)), xtimesx);
        assertEquals(xtimesx.multiply(two), twotimesxtimesx);
    }
    
    @Test
    public void equalsDifferentVariable() {
        assertFalse(xtimesy.equals(xtimesx));
    }
    
    @Test
    public void equalsDifferentCoeff() {
        assertFalse(xtimesx.equals(twotimesxtimesx));
        assertFalse(xtimesy.equals(threepointtwoxtimesy));
    }
    
    @Test
    public void testHashCode() {
        assertEquals(twotimesxtimesx.multiply(new Number(0.5)), xtimesx);
        assertEquals(xtimesy.multiply(new Number(3.2)), threepointtwotimesxtimesy);
    }
    
    @Test
    public void testToString() {
        assertEquals("x*x", xtimesx.toString());
        assertEquals("2*x*x", twotimesxtimesx.toString());
        assertEquals("3.2*x*y", threepointtwotimesxtimesy.toString());
    }

}
