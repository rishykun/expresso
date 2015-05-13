package expresso;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
     *          if max exponents are equal, take the monomial
     *          which has a higher leading exponent to be greater i.e.
     *          compare(x*x*y, x*y*y) = 1, compare(x*y*z*z, x*y*y*z) = -1
     *          since in the first one x*x > x, and in the second one y < y*y
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
        return (largestOfFirst < largestOfSecond ? -1 : (largestOfFirst == largestOfSecond ? mapCompare(first, second) : 1));
    }
    
    private int mapCompare(Monomial first, Monomial second) {
        Map<String, Integer> firstMap = first.getMap();
        Map<String, Integer> secondMap = second.getMap();
        for (String key: firstMap.keySet()){
            if (!secondMap.containsKey(key)) return setCompare(firstMap.keySet(), secondMap.keySet());
            else if (firstMap.get(key) > secondMap.get(key)){
                return 1;
            }
            else if (firstMap.get(key) < secondMap.get(key)){
                return -1;
            }
        }
        return -1;
    }
    
    private int setCompare(Set<String> first, Set<String> second){
        TreeSet <String> firstDifference = new TreeSet<>(first);
        firstDifference.removeAll(second);
        if (firstDifference.isEmpty()) return -1;
        TreeSet <String> secondDifference = new TreeSet<>(second);
        secondDifference.removeAll(first);
        if (secondDifference.isEmpty()) return 1;
        return firstDifference.first().compareTo(secondDifference.first());
    }
}
