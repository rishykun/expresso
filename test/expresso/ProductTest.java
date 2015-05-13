package expresso;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProductTest extends TestSetup {
    
    /**
     * Each of the tests that take in a specific parameter use the methods of
     * instances of the following types of Products:
     * 1) A Product of Monomials (twox = new Product(two, x))
     * 2) A Product of Sums of Monomials (twoplusxtimesxplusy = new Product(new Sum(two, x), new Sum(x, y)))
     * 3) A Product of Products of Monomials (twotimesxtimesxtimesy = new Product(new Product(two, x), new Product(x, y)))
     * 4) A Product of Sums and Products of Monomials (nestedproductandsumtwoxy = new Product(new Sum(two, new Product(x, y)), new Product(new Sum(two, y), x)))
     *
     * add
     * The input space for the add method was partitioned as follows:
     * 1) Monomials
     * 2) A Sum of Monomials
     * 3) A Product of Monomial
     * 4) Sums and Products of Monomials
     * 
     * The method testAddMonomial covers input partition 1
     * The method testAddSum covers input partition 2
     * The method testAddProduct covers input partition 3
     * The method testAddSumAndProduct covers input partition 4
     * 
     * multiply
     * The input space for the multiply method is the same as that of the add method:
     * 1) Monomials
     * 2) A Sum of Monomials
     * 3) A Product of Monomial
     * 4) Sums and Products of Monomials
     * 
     * The method testMultiplyMonomial covers input partition 1
     * The method testMultiplySum covers input partition 2
     * The method testMultiplyProduct covers input partition 3
     * The method testMultiplySumAndProduct covers input partition 4
     * 
     * differentiate
     * The input space for the multiply method was partitioned as follows:
     * 1) A Variable found in the Product
     * 2) A Variable not found in the Product
     * 3) A Variable with multiple letters
     * 
     * The method testDiffWithVariable covers input partition 1
     * The method testDiffWithoutVariable covers input partition 2
     * The method testDiffMultipleLetters covers input partition 3
     * 
     * simplify
     * The input space for the simplify method was partitioned as follows:
     * 1) A Product of Monomials
     * 2) A Product of Sums of Monomials
     * 3) A Product of Products of Monomials
     * 4) A Product of Sums and Products of Monomials
     * 
     * The method testSimplifyMonomials covers input partition 1
     * The method testSimplifySum covers input partition 2
     * The method testSimplifyProduct covers input partition 3
     * The method testSimplifySumAndProduct covers input partition 4
     * 
     * toString
     * The input space for the toString method is the same as that of the simplify method:
     * 1) A Product of Monomials
     * 2) A Product of Sums of Monomials
     * 3) A Product of Products of Monomials
     * 4) A Product of Sums and Products of Monomials
     * 
     * The method testToStringMonomials covers input partition 1
     * The method testToStringSum covers input partition 2
     * The method testToStringProduct covers input partition 3
     * The method testToStringSumAndProduct covers input partition 4
     * 
     * equals
     * The input space for the equals method is the same as that of the simplify and toString methods:
     * 1) A Product of Monomials
     * 2) A Product of Sums of Monomials
     * 3) A Product of Products of Monomials
     * 4) A Product of Sums and Products of Monomials
     * 
     * The method testEqualsMonomials covers input partition 1
     * The method testEqualsSum covers input partition 2
     * The method testEqualsProduct covers input partition 3
     * The method testEqualsSumAndProduct covers input partition 4
     * 
     * hashCode
     * The input space for the hashCode method is the same as that of the simplify, toString, and equals methods:
     * 1) A Product of Monomials
     * 2) A Product of Sums of Monomials
     * 3) A Product of Products of Monomials
     * 4) A Product of Sums and Products of Monomials
     * 
     * The method testHashCodeMonomials covers input partition 1
     * The method testHashCodeSum covers input partition 2
     * The method testHashCodeProduct covers input partition 3
     * The method testHashCodeSumAndProduct covers input partition 4
     * 
     */
    
    @BeforeClass
    public static void setUpBeforeClass(){
        TestSetup.setup();
    }

    @Test
    public void testAddMonomial(){
        Expression addToProductOfMonomials = twox;
        Expression addToProductOfSums = twoplusxtimesxplusy;
        Expression addToProductOfProducts = twotimesxtimesxtimesy;
        Expression addToProductOfSumsAndProducts = nestedproductandsumtwoxy;
    }
    
    @Test
    public void testAddSum(){
        
    }
    
    @Test
    public void testAddProduct(){
        
    }
    
    @Test
    public void testAddSumAndProduct(){
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
