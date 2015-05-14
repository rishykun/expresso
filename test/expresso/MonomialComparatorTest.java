package expresso;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.BeforeClass;
import org.junit.Test;

public class MonomialComparatorTest extends TestSetup {
   
    /*
     * compare
     *  first [x]>, [x]< second
     *  [x]Number, [x]Variable, [x]other Monomial
     *  [x]Max exponents different
     *  Max exponents are the same
     *      [x]Variables are different
     *      [x]Variables are the same
     */
    
    private Comparator<Monomial> comparator = new MonomialComparator();
    
    @BeforeClass
    public static void setUpBeforeClass(){
        TestSetup.setup();
    }
        
    // All tests test both comparison directions
    // i.e. compare(first, second) and compare(second, first)
    // to test the symmetry
    
    /**
     * Compares 1 with x
     * 
     * Covers using Numbers and Variables
     */
    @Test
    public void numberVariableCompareTest() {
        assertEquals(-1, comparator.compare(one, x));
        assertEquals(1, comparator.compare(x, one));
    }
    
    /**
     * Compares x*x with y*y and x*y with y*z
     * 
     * Covers using other Monomial and comparing
     * Monomials w/ same max exponent but different
     * variables
     */
    @Test
    public void sameMaxExponentDifferentVariablesCompareTest(){
        assertEquals(1, comparator.compare(xtimesx, y.multiply(y)));
        assertEquals(-1, comparator.compare(y.multiply(y), xtimesx));
        assertEquals(1, comparator.compare(x.multiply(y), y.multiply(new Variable("z"))));
        assertEquals(-1, comparator.compare(y.multiply(new Variable("z")), x.multiply(y)));
    }
    
    /**
     * Compares x*y*y with x*x*y
     * 
     * Covers comparing Monomials w/ same max exponent
     * and same variables
     */
    @Test
    public void sameMaxExponentSameVariablesDifferentExponentTest(){
        assertEquals(-1, comparator.compare(x.multiply(y).multiply(y), xtimesx.multiply(y)));
        assertEquals(1, comparator.compare(xtimesx.multiply(y), x.multiply(y).multiply(y)));
    }
}


