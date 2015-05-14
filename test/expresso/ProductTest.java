package expresso;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

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
     * 
     * The method testDiffWithVariable covers input partition 1
     * The method testDiffWithoutVariable covers input partition 2
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
     * The input space for the equals method was partitioned as follows:
     * 1) Inputs with same first and second subexpressions
     * 2) One subexpression being different, other the same
     * 3) Both different subexpressions
     * 
     * The method testEqualsSame covers input partition 1
     * The method testEqualsHalfDifferent covers input partition 2
     * The method testEqualsAllDifferent covers input partition 3
     * 
     * hashCode
     * The input space for the hashCode method is the same as that of the simplify, toString:
     * 1) A Product of Monomials
     * 2) A Product of Sums of Monomials
     * 3) A Product of Products of Monomials
     * 4) A Product of Sums and Products of Monomials
     * 
     * All four partitions are handled by the testHashCode method
     * 
     */
    
    @BeforeClass
    public static void setUpBeforeClass(){
        TestSetup.setup();
    }

    @Test
    public void testAddMonomial(){
        Expression addToProductOfMonomials = twox.add(two);
        assertTrue(addToProductOfMonomials.simplify().equals(new Sum(new Product(two, x), two).simplify()));
        Expression addToProductOfSums = twoplusxtimesxplusy.add(two);
        assertTrue(addToProductOfSums.simplify().equals(new Sum(new Product(new Sum(two, x), new Sum(x, y)), two).simplify()));
        Expression addToProductOfProducts = twotimesxtimesxtimesy.add(two);
        assertTrue(addToProductOfProducts.simplify().equals(new Sum(new Product(new Product(two, x), new Product(x, y)), two).simplify()));
        Expression addToProductOfSumsAndProducts = nestedproductandsumtwoxy.add(two);
        assertTrue(addToProductOfSumsAndProducts.simplify().equals(new Sum(new Product(new Sum(two, new Product(x, y)), new Product(new Sum(two, y), x)), two).simplify()));
    }
    
    @Test
    public void testAddSum(){
        Expression addToProductOfMonomials = twox.add(xplustwo);
        assertTrue(addToProductOfMonomials.simplify().equals(new Sum(new Product(two, x), xplustwo).simplify()));
        Expression addToProductOfSums = twoplusxtimesxplusy.add(xplustwo);
        assertTrue(addToProductOfSums.simplify().equals(new Sum(new Product(new Sum(two, x), new Sum(x, y)), xplustwo).simplify()));
        Expression addToProductOfProducts = twotimesxtimesxtimesy.add(xplustwo);
        assertTrue(addToProductOfProducts.simplify().equals(new Sum(new Product(new Product(two, x), new Product(x, y)), xplustwo).simplify()));
        Expression addToProductOfSumsAndProducts = nestedproductandsumtwoxy.add(xplustwo);
        assertTrue(addToProductOfSumsAndProducts.simplify().equals(new Sum(new Product(new Sum(two, new Product(x, y)), new Product(new Sum(two, y), x)), xplustwo).simplify()));
    }
    
    @Test
    public void testAddProduct(){
        Expression addToProductOfMonomials = twox.add(xy);
        assertTrue(addToProductOfMonomials.simplify().equals(new Sum(new Product(two, x), xy).simplify()));
        Expression addToProductOfSums = twoplusxtimesxplusy.add(xy);
        assertTrue(addToProductOfSums.simplify().equals(new Sum(new Product(new Sum(two, x), new Sum(x, y)), xy).simplify()));
        Expression addToProductOfProducts = twotimesxtimesxtimesy.add(xy);
        assertTrue(addToProductOfProducts.simplify().equals(new Sum(new Product(new Product(two, x), new Product(x, y)), xy).simplify()));
        Expression addToProductOfSumsAndProducts = nestedproductandsumtwoxy.add(xy);
        assertTrue(addToProductOfSumsAndProducts.simplify().equals(new Sum(new Product(new Sum(two, new Product(x, y)), new Product(new Sum(two, y), x)), xy).simplify()));
    }
    
    @Test
    public void testAddSumAndProduct(){
        Expression addToProductOfMonomials = twox.add(nestedproductandsumtwoxy);
        assertTrue(addToProductOfMonomials.simplify().equals(new Sum(new Product(two, x), nestedproductandsumtwoxy).simplify()));
        Expression addToProductOfSums = twoplusxtimesxplusy.add(nestedproductandsumtwoxy);
        assertTrue(addToProductOfSums.simplify().equals(new Sum(new Product(new Sum(two, x), new Sum(x, y)), nestedproductandsumtwoxy).simplify()));
        Expression addToProductOfProducts = twotimesxtimesxtimesy.add(nestedproductandsumtwoxy);
        assertTrue(addToProductOfProducts.simplify().equals(new Sum(new Product(new Product(two, x), new Product(x, y)), nestedproductandsumtwoxy).simplify()));
        Expression addToProductOfSumsAndProducts = nestedproductandsumtwoxy.add(nestedproductandsumtwoxy);
        assertTrue(addToProductOfSumsAndProducts.simplify().equals(new Sum(new Product(new Sum(two, new Product(x, y)), new Product(new Sum(two, y), x)), nestedproductandsumtwoxy).simplify()));
    }
    
    public void testMultiplyMonomial(){
        Expression multiplyProductOfMonomials = twox.multiply(two);
        assertTrue(multiplyProductOfMonomials.simplify().equals(new Product(new Product(two, x), two).simplify()));
        Expression multiplyProductOfSums = twoplusxtimesxplusy.multiply(two);
        assertTrue(multiplyProductOfSums.simplify().equals(new Product(new Product(new Sum(two, x), new Sum(x, y)), two).simplify()));
        Expression multiplyProductOfProducts = twotimesxtimesxtimesy.multiply(two);
        assertTrue(multiplyProductOfProducts.simplify().equals(new Product(new Product(new Product(two, x), new Product(x, y)), two).simplify()));
        Expression multiplyProductOfSumsAndProducts = nestedproductandsumtwoxy.multiply(two);
        assertTrue(multiplyProductOfSumsAndProducts.simplify().equals(new Product(new Product(new Sum(two, new Product(x, y)), new Product(new Sum(two, y), x)), two).simplify()));
    }
    
    @Test
    public void testMultiplySum(){
        Expression multiplyProductOfMonomials = twox.multiply(xplustwo);
        assertTrue(multiplyProductOfMonomials.simplify().equals(new Product(new Product(two, x), xplustwo).simplify()));
        Expression multiplyProductOfSums = twoplusxtimesxplusy.multiply(xplustwo);
        assertTrue(multiplyProductOfSums.simplify().equals(new Product(new Product(new Sum(two, x), new Sum(x, y)), xplustwo).simplify()));
        Expression multiplyProductOfProducts = twotimesxtimesxtimesy.multiply(xplustwo);
        assertTrue(multiplyProductOfProducts.simplify().equals(new Product(new Product(new Product(two, x), new Product(x, y)), xplustwo).simplify()));
        Expression multiplyProductOfSumsAndProducts = nestedproductandsumtwoxy.multiply(xplustwo);
        assertTrue(multiplyProductOfSumsAndProducts.simplify().equals(new Product(new Product(new Sum(two, new Product(x, y)), new Product(new Sum(two, y), x)), xplustwo).simplify()));
    }
    
    @Test
    public void testMultiplyProduct(){
        Expression multiplyProductOfMonomials = twox.multiply(xy);
        assertTrue(multiplyProductOfMonomials.simplify().equals(new Product(new Product(two, x), xy).simplify()));
        Expression multiplyProductOfSums = twoplusxtimesxplusy.multiply(xy);
        assertTrue(multiplyProductOfSums.simplify().equals(new Product(new Product(new Sum(two, x), new Sum(x, y)), xy).simplify()));
        Expression multiplyProductOfProducts = twotimesxtimesxtimesy.multiply(xy);
        assertTrue(multiplyProductOfProducts.simplify().equals(new Product(new Product(new Product(two, x), new Product(x, y)), xy).simplify()));
        Expression multiplyProductOfSumsAndProducts = nestedproductandsumtwoxy.multiply(xy);
        assertTrue(multiplyProductOfSumsAndProducts.simplify().equals(new Product(new Product(new Sum(two, new Product(x, y)), new Product(new Sum(two, y), x)), xy).simplify()));
    }
    
    @Test
    public void testMultiplySumAndProduct(){
        Expression multiplyProductOfMonomials = twox.multiply(nestedproductandsumtwoxy);
        assertTrue(multiplyProductOfMonomials.simplify().equals(new Product(new Product(two, x), nestedproductandsumtwoxy).simplify()));
        Expression multiplyProductOfSums = twoplusxtimesxplusy.multiply(nestedproductandsumtwoxy);
        assertTrue(multiplyProductOfSums.simplify().equals(new Product(new Product(new Sum(two, x), new Sum(x, y)), nestedproductandsumtwoxy).simplify()));
        Expression multiplyProductOfProducts = twotimesxtimesxtimesy.multiply(nestedproductandsumtwoxy);
        assertTrue(multiplyProductOfProducts.simplify().equals(new Product(new Product(new Product(two, x), new Product(x, y)), nestedproductandsumtwoxy).simplify()));
        Expression multiplyProductOfSumsAndProducts = nestedproductandsumtwoxy.multiply(nestedproductandsumtwoxy);
        assertTrue(multiplyProductOfSumsAndProducts.simplify().equals(new Product(new Product(new Sum(two, new Product(x, y)), new Product(new Sum(two, y), x)), nestedproductandsumtwoxy).simplify()));
    }
    
    @Test
    public void testDiffWithVariable(){
        Variable withRespectToX = x;
        assertTrue(twox.differentiate(x).simplify().equals(new Number(2)));
        assertTrue(twoplusxtimesxplusy.differentiate(x).simplify().equals(new Sum(new Sum(new Product(two, x), y), two).simplify()));
        assertTrue(twotimesxtimesxtimesy.differentiate(x).simplify().equals(new Product(new Product(x, y), new Number(4)).simplify()));
        assertTrue(nestedproductandsumtwoxy.differentiate(x).simplify().equals(new Sum(new Sum(four, twoy), new Sum(fourxy, twoxysquared)).simplify()));
    }
    
    @Test
    public void testDiffWithoutVariable(){
        Variable withRespectToX = z;
        assertTrue(twox.differentiate(z).simplify().equals(new Number(0)));
        assertTrue(twoplusxtimesxplusy.differentiate(z).simplify().equals(new Number(0)));
        assertTrue(twotimesxtimesxtimesy.differentiate(z).simplify().equals(new Number(0)));
        assertTrue(nestedproductandsumtwoxy.differentiate(z).simplify().equals(new Number(0)));
    }
    
    @Test
    public void testSimplifyMonomials(){
        assertTrue(twox.simplify().equals(new Product(x, two).simplify()));
    }
    
    @Test
    public void testSimplifySum(){
        String answer = twoplusxtimesxplusy.simplify().toString();
        String[] terms = answer.split("\\+");
        List<String> termList = Arrays.asList(terms);
        assertTrue(termList.containsAll(Arrays.asList("2*x", "2*y", "x*x", "x*y")));
        assertTrue(termList.size() == 4);
    }
    
    @Test
    public void testSimplifyProduct(){
        String answer = twotimesxtimesxtimesy.simplify().toString();
        String[] terms = answer.split("\\*");
        List<String> termList = Arrays.asList(terms);
        assertTrue(termList.containsAll(Arrays.asList("2", "x", "y")));
        assertTrue(termList.size() == 4);
        int count = 0;
        for (String term: termList){
            if (term.equals("x")){
                count++;
            }
        }
        assertTrue(count == 2);
    }
    
    @Test
    public void testSimplifySumAndProduct(){
        String answer = nestedproductandsumtwoxy.simplify().toString();
        String[] terms = answer.split("\\+");
        List<String> termList = Arrays.asList(terms);
        assertTrue(termList.containsAll(Arrays.asList("x*x*y*y", "2*x*x*y", "2*x*y", "4*x")));
        assertTrue(termList.size() == 4);
    }
    
    @Test
    public void testToStringMonomials(){
        assertTrue(twox.toString().equals("(2) * (x)"));
    }
    
    @Test
    public void testToStringSum(){
        assertTrue(twoplusxtimesxplusy.toString().equals("((2) + (x)) * ((x) + (y))"));
    }
    
    @Test
    public void testToStringProduct(){
        assertTrue(twotimesxtimesxtimesy.toString().equals("((2) * (x)) * ((x) * (y))"));
    }
    
    @Test
    public void testToStringSumAndProduct(){
        assertTrue(nestedproductandsumtwoxy.toString().equals("((2) + ((x) * (y))) * (((2) + (y)) * (x))"));
    }
    
    @Test
    public void testEqualsSame(){
        assertTrue(twox.equals(new Product(two, x)));
        assertTrue(twoplusxtimesxplusy.equals(new Product(new Sum(two, x), new Sum(x, y))));
        assertTrue(twotimesxtimesxtimesy.equals(new Product(new Product(two, x), new Product(x, y))));
        assertTrue(nestedproductandsumtwoxy.equals(new Product(new Sum(two, new Product(x, y)), new Product(new Sum(two, y), x))));
    }
    
    @Test
    public void testEqualsHalfDifferent(){
        assertFalse(twox.equals(new Product(two, y)));
        assertFalse(twox.equals(new Product(y, x)));
        assertFalse(twoplusxtimesxplusy.equals(new Product(new Sum(x, y), new Sum(two, x))));
        assertFalse(twotimesxtimesxtimesy.equals(new Product(new Product(x, y), new Product(two, x))));
        assertFalse(nestedproductandsumtwoxy.equals(new Product(new Product(new Sum(two, y), x), new Sum(two, new Product(x, y)))));
    }
    
    @Test
    public void testEqualsAllDifferent(){
        assertFalse(twox.equals(new Product(x, two)));
        assertFalse(twoplusxtimesxplusy.equals(new Product(new Sum(x, two), new Sum(y, x))));
        assertFalse(twotimesxtimesxtimesy.equals(new Product(new Product(z, y), new Product(two, y))));
        assertFalse(nestedproductandsumtwoxy.equals(new Product(new Sum(two, new Product(y, x)), new Product(new Sum(x, y), two))));
    }
    
    @Test
    public void testHashCode(){
        assertEquals(twox.hashCode(), two.hashCode() * x.hashCode());
        assertEquals(twoplusxtimesxplusy.hashCode(), new Sum(two, x).hashCode() * new Sum(x, y).hashCode());
        assertEquals(twotimesxtimesxtimesy.hashCode(), new Product(two, x).hashCode() * new Product(x, y).hashCode());
        assertEquals(nestedproductandsumtwoxy.hashCode(), new Sum(two, new Product(x, y)).hashCode() * new Product(new Sum(two, y), x).hashCode());
    }
}