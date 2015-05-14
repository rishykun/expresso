package expresso;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class NumberTest extends TestSetup {

    /**
     * differentiate
     *  [x]0, [x]1, [x]Decimal
     * 
     * isInteger
     *  Integer [x]with, [x]without decimal
     *  [x]Non-integer decimal
     * 
     * toString
     *  [x]0, [x]1, [x]Other integer
     *  [x]0.0, [x]1.0, [x]Other decimal
     *  Unnecessary trailing zeros in initialization: [x]0, [x]1, [x]>1
     *  Unnecessary preceding zeros in initialization: [x]0, [x]1, [x]>1
     */
    @BeforeClass
    public static void setUpBeforeClass(){
        TestSetup.setup();
    }
    
    /**
     * Differentiates 0, 1, and 001.100
     * 
     * Covers all differentiate partitions
     */
    @Test
    public void derivativeTest() {
        assertEquals(zero, zero.differentiate(x).simplify());
        assertEquals(zero, one.differentiate(x).simplify());
        assertEquals(zero, onepointone.differentiate(x).simplify());
    }
    
    /**
     * Tests isInteger on 1, 2.0, and 001.100
     * 
     * Covers all isInteger partitions
     */
    @Test
    public void isIntegerTest() {
        assertTrue(one.isInteger());
        assertTrue(two.isInteger());
        assertFalse(onepointone.isInteger());
    }
    
    /**
     * Tests toString on 0, 1, 02.0, 0.0, 1.0, 001.100
     * 
     * Covers all toString partitions
     */
    @Test
    public void toStringTest() {
        assertEquals("0", zero.toString());
        assertEquals("1", one.toString());
        assertEquals("2", two.toString());
        assertEquals("0", new Number(0.0).toString());
        assertEquals("1", new Number(1.0).toString());
        assertEquals("1.1", onepointone.toString());
    }

}
