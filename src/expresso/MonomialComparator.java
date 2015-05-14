package expresso;

import java.util.Comparator;
import java.util.Map;
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
     * @param second second monomial second.getMap() is not equal to first.getMap()
     * @return -1 if first has the max exponent
     *          1 if second has the max exponent
     *          if max exponents are equal, and the terms don't have exactly the same
     *          variables, return 1 if the first non-identical variable in first is before
     *          that in second lexicographically -1 otherwise
     *          and if the terms have exactly the same variables return 1 if the first with non
     *          -identical exponents has a greater exponent in first, -1 if greater exponent in 2nd
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
        return (largestOfFirst < largestOfSecond ? -1 : (largestOfFirst == largestOfSecond ? keyCompare(first.getMap(), second.getMap()) : 1));
    }
    
    private int keyCompare(Map<String, Integer> first, Map<String, Integer> second) {
        if (first.keySet().equals(second.keySet())){
            return valueCompare(first, second);
        }
        else {
            TreeSet<String> firstKeys = new TreeSet<>(first.keySet());
            TreeSet<String> secondKeys = new TreeSet<>(second.keySet());
            firstKeys.removeAll(secondKeys);
            if (firstKeys.isEmpty()) return 1;
            secondKeys.removeAll(firstKeys);
            if (secondKeys.isEmpty()) return -1;
            return -1*(firstKeys.first().compareTo(secondKeys.first())) > 0 ? 1:-1;
        }
    }
    
    private int valueCompare(Map<String, Integer> first, Map<String, Integer> second){
        for (String key: first.keySet()){
            if (first.get(key) > second.get(key)) return 1;
            else if (second.get(key) > first.get(key)) return -1;
        }
        throw new RuntimeException("Maps were equal!");
    }
}
