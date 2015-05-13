package expresso;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class VariableTest extends TestSetup {

    /**
     * differentiate
     * The input space for differentiate was partitioned as follows:
     * 1) Differentiating with respect to the variable
     * 2) Differentiating with respect to something else
     * 
     * The method testDiffWithRespect() covers input partition 1
     * The method testDiffWithoutRespect() covers input partition 2
     * 
     * toString
     * The input space for toString was partitioned as follows:
     * 1) Name lengths of 1, 2+
     * 2) Uppercase and lowercase letters
     * 
     * The method testToStringVaryingLengths() covers input partition 1
     * The method testToStringCaseTest() covers input partition 2
     * 
     * equals
     * The input space for equals was partitioned as follows:
     * 1) Another variable that has the same letter(s) and same casing for its name
     * 2) Another variable with the same letters but different casing
     * 3) Another variable with different letters but same casing
     * 
     * The method testEqualsSameVar() covers input partition 1
     * The method testEqualsSameLettersDiffCase() covers input partition 2
     * The method testEqualsDiffLettersSameCase() covers input partition 3
     * 
     * hashCode
     * The input space for hashCode is the same as that of toString:
     * 1) Name lengths of 1, 2+
     * 2) Uppercase and lowercase letters
     * 
     * The method testHashCodeVaryingLengths() covers input partition 1
     * The method testHashCodeCaseTest() covers input partition 2
     */
    
    @BeforeClass
    public static void setUpBeforeClass(){
        TestSetup.setup();
    }
    
    @Test
    public void testDiffWithRespect(){
        assertTrue(x.differentiate(x).equals(new Number(1)));
    }
    
    @Test
    public void testDiffWithoutRespect(){
        assertTrue(x.differentiate(y).equals(new Number(0)));
    }
    
    @Test
    public void testToStringVaryingLengths(){
        assertTrue(x.toString().equals("x"));
        assertTrue(multiLetter.toString().equals("multi"));
    }
    
    @Test
    public void testToStringCaseTest(){
        assertTrue(multiCase.toString().equals("HeLlO"));
    }
    
    @Test
    public void testEqualsSameVar(){
        assertTrue(x.equals(new Variable("x")));
        assertTrue(multiCase.equals(new Variable("HeLlO")));
        assertTrue(multiLetter.equals(new Variable("multi")));
    }
    
    @Test
    public void testEqualsSameLettersDiffCase(){
        assertFalse(x.equals(new Variable("X")));
        assertFalse(multiCase.equals(new Variable("HeLLO")));
    }
    
    @Test
    public void testEqualsDiffLettersSameCase(){
        assertFalse(x.equals(y));
        assertFalse(multiLetter.equals("hello"));
    }
    
    @Test
    public void testHashCodeVaryingLengths() {
        assertEquals(x.hashCode(), "x".hashCode());
        assertEquals(multiLetter.hashCode(), "multi".hashCode());
    }
    
    @Test
    public void testHashCodeCaseTest() {
        assertEquals(multiCase.hashCode(), "HeLlO".hashCode());
    }
}