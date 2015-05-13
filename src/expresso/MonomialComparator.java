package expresso;

import java.util.Comparator;
import java.util.Map;

public class MonomialComparator implements Comparator<Monomial>{

    //Abstraction function
    //  MonomialComparator() represents a comparator that can compare
    //  any two monomials
    //Rep Invariant
    //  True
    //Rep Exposure
    //  No rep to expose
    
    
    /**
     * Compares two monomials
     * @param first first monomial
     * @param second second monomial
     * @return -1 if first has the max exponent
     *          1 if second has the max exponent
     *          0 if max exponents are equal
     */
    @Override
    public int compare(Monomial first, Monomial second) {
        Map<String, Integer> firstMap = first.getMap();
        Map<String, Integer> secondMap = second.getMap();
        int largestOfFirst = -1;
        int largestOfSecond = -1;
        for (int exp : firstMap.values()){
            if (exp > largestOfFirst){
                largestOfFirst = exp;
            }
        }
        for (int exp : secondMap.values()){
            if (exp > largestOfSecond){
                largestOfSecond = exp;
            }
        }
        return (largestOfFirst < largestOfSecond ? -1 : (largestOfFirst == largestOfSecond ? 0 : 1));
    }

}
