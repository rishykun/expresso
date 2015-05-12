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
    */
    
    @BeforeClass
    public static void setUpBeforeClass(){
        TestSetup.setup();
    }
    
    @Test
    public void test() {
        fail("Not yet implemented");
    }
}
