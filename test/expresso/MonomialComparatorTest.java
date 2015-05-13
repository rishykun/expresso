package expresso;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.BeforeClass;
import org.junit.Test;

public class MonomialComparatorTest extends TestSetup {
   
    /*
     * compare
     *  first [x]>, [x]=, [x]< second
     *  [x]Number, [x]Variable, [x]Other
     *  [x]Two different terms with highest exponent
     *  same but in different variables
     */
    
    private Comparator<Monomial> comparator = new MonomialComparator();
    
    @BeforeClass
    public static void setUpBeforeClass(){
        TestSetup.setup();
    }
        
    /**
     * Compares 1 with 1, 1.1 with 1, and 1 with x
     */
    @Test
    public void numberCompareTest() {
        assertEquals(0, comparator.compare(one, one));
        assertEquals(0, comparator.compare(onepointone,  one));
        assertEquals(-1, comparator.compare(one, x));
    }
    
    /**
     * Compares x*x with x and x with y
     */
    @Test
    public void variableMonomialCompareTest(){
        assertEquals(1, comparator.compare(xtimesx, x));
        assertEquals(0, comparator.compare(x, y));
    }
}
