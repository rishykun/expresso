package expresso;

import java.util.Comparator;
import java.util.Map;

public class MonomialComparator implements Comparator<Monomial>{

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
